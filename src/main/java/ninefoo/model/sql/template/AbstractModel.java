package ninefoo.model.sql.template;

import ninefoo.config.Config;
import ninefoo.config.Database;
import ninefoo.helper.DateHelper;
import ninefoo.model.object.Activity;
import ninefoo.model.object.Member;
import ninefoo.model.object.Project;
import ninefoo.model.object.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public abstract class AbstractModel {

    // Variables
    protected Database db;
    protected Connection conn;
    protected PreparedStatement ps;
    protected String sql;
    protected int affectedRows;
    protected ResultSet result;

    // Logger
    protected static final Logger LOGGER = LogManager.getLogger();

    /**
     * Constructor
     */
    public AbstractModel() {

        // Set the database
        this.db = Database.getInstance();
    }

    /**
     * Prepare statement
     *
     * @throws SQLException
     */
    public void prepareStatement() throws SQLException {
        ps = this.conn.prepareStatement(sql);
    }

    /**
     * Get last inserted id
     *
     * @return Id or ERROR
     * @throws SQLException
     */
    public final int getLastInsertId() throws SQLException {

        // Flush old query on console
        if (sql != null && !sql.isEmpty())
            LOGGER.debug(sql);

        // Query
        sql = "SELECT last_insert_rowid()";

        // Prepare
        this.prepareStatement();

        // Run
        result = ps.executeQuery();

        // Get id
        if (result.next())
            return result.getInt("last_insert_rowid()");
        return Database.ERROR;
    }

    /**
     * Open connection
     */
    protected final void open() {
        this.conn = db.openConnection();
    }

    /**
     * Close prepared statement and connection
     */
    protected final void close() {

        // Logger
        LOGGER.debug(sql);

        // Close prepared statement
        if (ps != null)
            try {
                ps.close();
            } catch (SQLException e) {
                LOGGER.error("Prepared statement was not closed: " + e.getMessage());
            }

        // Close connection
        if (db != null)
            db.closeConnection();

        // Clean the variables
        sql = null;
        affectedRows = 0;
        result = null;
    }

    // Helper method to get the next Project object from the DB ResultSet object.
    protected Project getNextProject(ResultSet projects) {

        try {
            int projectId = projects.getInt("project_id");
            String projectName = projects.getString("project_name");
            Date createDate = DateHelper.parse(projects.getString("create_date"), Config.DATE_FORMAT);
            Date updateDate = DateHelper.parse(projects.getString("update_date"), Config.DATE_FORMAT);
            double budget = projects.getDouble("budget");
            Date deadlineDate = DateHelper.parse(projects.getString("deadline_date"), Config.DATE_FORMAT);
            String description = projects.getString("description");
            Date startDate = DateHelper.parse(projects.getString("start_date"), Config.DATE_FORMAT);

            return new Project(projectId, projectName, createDate, startDate, updateDate, budget, deadlineDate, description);

        } catch (SQLException e) {
            LOGGER.error("Could not get next project from db --- detailed info: " + e.getMessage());
        }

        return null;
    }

    // Helper function to read the next Activity object from the specified ResultSet object.
    protected Activity getNextActivity(ResultSet activities) {
        Activity activity = null;

        try {
            int activityId = activities.getInt("activity_id");
            String activityLabel = activities.getString("activity_label");
            String description = activities.getString("description");
            int duration = activities.getInt("duration");
            int optimisticDuration = activities.getInt("optimistic_duration");
            int likelyDuration = activities.getInt("likely_duration");
            int pessimisticDuration = activities.getInt("pessimistic_duration");
            Date createDate = DateHelper.parse(activities.getString("create_date"), ninefoo.config.Config.DATE_FORMAT);
            Date startDate = DateHelper.parse(activities.getString("start_date"), Config.DATE_FORMAT);
            Date finishDate = DateHelper.parse(activities.getString("finish_date"), Config.DATE_FORMAT);
            int projectId = activities.getInt("project_id");
            int memberId = activities.getInt("member_id");
            double cost = activities.getDouble("planned_cost");
            Project project = null;
            Member member = null;

            activity = new Activity(activityId, activityLabel, description, duration,
                    optimisticDuration, likelyDuration, pessimisticDuration,
                    createDate, project, member, null);
            activity.setCost(cost);
            activity.setStartDate(startDate);
            activity.setFinishDate(finishDate);
            activity.setProjectId(projectId);
            activity.setMemberId(memberId);
        } catch (SQLException e) {
            LOGGER.error("Could not get next activity from db --- detailed info: " + e.getMessage());
        }

        return activity;
    }

    // Helper method to get the next Role object from the DB ResultSet object.
    protected Role getNextRole(ResultSet roles) {
        try {
            int roleId = roles.getInt("role_id");
            String roleName = roles.getString("role_name");
            String description = roles.getString("description");

            return new Role(roleId, roleName, description);

        } catch (SQLException e) {
            LOGGER.error("Could not get next role from db --- detailed info: " + e.getMessage());
        }

        return null;

    }
}
