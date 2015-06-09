package ninefoo.model;

import ninefoo.config.*;
import ninefoo.config.Config;
import ninefoo.helper.DateHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class contains methods to manipulate the activities in the database. For example, to
 *      add a new activity, find a specific activity by ID.
 * Created by Farzad on 03-Jun-2015.
 */
public class Activity_model {
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Inserts a new activity into the database.
     * @param activity <code>Activity</code> object to be inserted into the DB.
     * @return the ID generated by the database for the new activity; <code>Database.ERROR</code> if the insertion
     *         was not successful.
     */
    public int insertNewActivity(Activity activity) {
        Statement statement = DbManager.createConnectionStatement();

        if (statement == null) {
            LOGGER.warn("Could not get a connection statement to DB");
            return Database.ERROR;
        }

        if (activity.getProject() == null || activity.getMember() == null)
            return Database.ERROR;

        String insertActivitySql = String.format(
                "INSERT INTO activity(activity_label, description, duration, " +
                "optimistic_duration, likely_duration, pessimistic_duration, " +
                "project_id, member_id) VALUES ('%s', '%s', %d, %d, %d, %d, %d, %d)",
                activity.getActivityLabel(), activity.getDescription(),
                activity.getDuration(), activity.getOptimisticDuration(),
                activity.getLikelyDuration(), activity.getPessimisticDuration(),
                activity.getProject().getProjectId(),
                activity.getMember().getMemberId());

        try {
            int updatedRows = statement.executeUpdate(insertActivitySql);

            if (updatedRows == 1) {
                ResultSet rs = statement.executeQuery("SELECT last_insert_rowid()");

                int newActivityId = 0;
                if (rs.next())
                    newActivityId = rs.getInt("last_insert_rowid()");

                List<Activity> prerequisites = activity.getPrerequisites();
                if (prerequisites != null && !insertPrereqs(newActivityId, prerequisites))
                    LOGGER.warn("There was a problem when adding prerequisites for activity " +
                            "with ID = " + activity.getActivityId());

                return newActivityId;
            }

            LOGGER.warn("Updated row count was not equal to 1");

        } catch (SQLException e) {
            LOGGER.error("Could not insert new activity into db --- detailed info: " + e.getMessage());
        }

        return Database.ERROR;
    }

    // Helper function to insert the prerequisites for a given activity.
    private boolean insertPrereqs(int activityId, List<Activity> prerequisites) {
        Statement statement = DbManager.createConnectionStatement();

        if (statement == null) {
            LOGGER.warn("Could not get a connection statement to DB");
            return false;
        }

        // TODO Commented by Amir
//        // To be safe, we first delete all the prerequisites associated with the activity and
//        //      then add them.
//        String deletePrereqsSql = "DELETE FROM activity_relation WHERE activity_id = " + activityId;
//        try {
//            statement.executeUpdate(deletePrereqsSql);
//        } catch (SQLException e) {
//            LOGGER.warn("Could not delete prerequisites for activity ID = " +
//                    activityId + " --- detailed info: " + e.getMessage());
//            return false;
//        }

        String insertPrereqSql;
        for (Activity prereq : prerequisites) {
            insertPrereqSql = String.format(
                    "INSERT INTO activity_relation VALUES(%d, %d)",
                    activityId, prereq.getActivityId()
            );

            try {
                statement.executeUpdate(insertPrereqSql);
            } catch (SQLException e) {
                LOGGER.error("Could not insert prerequisites for activity with ID = " +
                        activityId + " --- detailed info: " + e.getMessage());
                return false;
            }
        }

        return true;
    }

    // Helper function to read the next Activity object from the specified ResultSet object.
    private Activity getNextActivity(ResultSet activities) {
        Activity activity = null;

        try {
            Project_model projectModel = new Project_model();
            Member_model memberModel = new Member_model();

            int activityId = activities.getInt("activity_id");
            String activityLabel = activities.getString("activity_label");
            String description = activities.getString("description");
            int duration = activities.getInt("duration");
            int optimisticDuration = activities.getInt("optimistic_duration");
            int likelyDuration = activities.getInt("likely_duration");
            int pessimisticDuration = activities.getInt("pessimistic_duration");
            Date createDate = DateHelper.parse(activities.getString("create_date"), Config.DATE_FORMAT);
            Project project = projectModel.getProjectById(activities.getInt("project_id"));
            Member member = memberModel.getMemberById(activities.getInt("member_id"));
            
            
            // TODO Commented by Amir
//            List<Activity> prerequisites = getActivityPrerequisites(activityId);
//            activity = new Activity(activityId, activityLabel, description, duration,
//                    optimisticDuration, likelyDuration, pessimisticDuration,
//                    createDate, project, member, prerequisites);
            
            activity = new Activity(activityId, activityLabel, description, duration,
                    optimisticDuration, likelyDuration, pessimisticDuration,
                    createDate, project, member, new ArrayList<Activity>());
        } catch (SQLException e) {
            LOGGER.error("Could not get next activity from db --- detailed info: " + e.getMessage());
        }

        return activity;
    }

    /**
     * Gets the prerequisites for the specified activity.
     * @param activity Activity object representing the activity.
     * @return List of Activity objects, or empty ArrayList if there are no prerequisites for this
     *         activity; NULL if there is a problem connecting to the DB.
     */
    public List<Activity> getActivityPrerequisites(Activity activity) {

        if (activity == null)
            return null;

        return getActivityPrerequisites(activity.getActivityId());
    }

    /**
     * Gets the prerequisites for the activity corresponding to the specified ID.
     * @param activityId ID of the activity to get the prerequisites for.
     * @return List of Activity objects, or empty ArrayList if there are no prerequisites for this
     *         activity; NULL if there is a problem connecting to the DB.
     */
    public List<Activity> getActivityPrerequisites(int activityId) {
        Statement statement = DbManager.createConnectionStatement();

        if (statement == null) {
            LOGGER.warn("Could not get a connection statement to DB");
            return null;
        }

        List<Activity> prerequisites = new ArrayList<>();
        String getPrereqSql =
                "SELECT prereq_activity_id FROM activity_relation " +
                "WHERE activity_id = " + activityId;

        try {
            ResultSet activityIds = statement.executeQuery(getPrereqSql);

            while (activityIds.next())
                prerequisites.add(getActivityById(activityIds.getInt("prereq_activity_id")));

        } catch (SQLException e) {
            LOGGER.error("Could not prerequisites for activity ID = " + activityId +
                    " --- detailed message: " + e.getMessage());
        }

        return prerequisites;
    }

    /**
     * Gets the Activity object associated with the specified ID.
     * @param activityId integer representing the ID of the activity to search for.
     * @return Activity object corresponding to the specified ID; NULL if no activity
     *         is found for the ID.
     */
    public Activity getActivityById(int activityId) {
        Statement statement = DbManager.createConnectionStatement();

        if (statement == null) {
            LOGGER.warn("Could not get a connection statement to DB");
            return null;
        }

        String getActivityByIdSql = "SELECT * FROM activity " + "WHERE activity_id = " + activityId;

        try {
            ResultSet activities = statement.executeQuery(getActivityByIdSql);

            if (activities.next()) {
                Activity activity = getNextActivity(activities);

                if (activity != null)
                    return activity;
            }
        } catch (SQLException e) {
            LOGGER.error("Could not get activity with activity_id = " + activityId + " --- " +
                    "detailed info: " + e.getMessage());
        }

        return null;
    }

    /**
     * Gets the activities for a given Project object.
     * @param project Project object for which we want to get the activities.
     * @return List of Activity objects for the project, empty ArrayList if no activity
     *         is found; NULL if there is a problem connecting to the database.
     */
    public List<Activity> getActivitiesByProject(Project project) {

        if (project == null)
            return null;

        return getActivitiesByProjectId(project.getProjectId());
    }

    /**
     * Gets the activities for the project corresponding to the project ID.
     * @param projectId ID of the project for which to get the activities.
     * @return List of Activity objects for the project, empty ArrayList if no activity
     *         is found; NULL if there is a problem connecting to the database.
     */
    public List<Activity> getActivitiesByProjectId(int projectId) {
        Statement statement = DbManager.createConnectionStatement();

        if (statement == null) {
            LOGGER.warn("Could not get a connection statement to DB");
            return null;
        }

        List<Activity> projectActivities = new ArrayList<>();
        String getProjectActivitiesSql =
                "SELECT activity_id FROM activity WHERE project_id = " + projectId;

        try {
            ResultSet activities = statement.executeQuery(getProjectActivitiesSql);

            int activityId;
            Activity activity;
            while (activities.next()) {
                activityId = activities.getInt("activity_id");
                activity = getActivityById(activityId);

                if (activity != null)
                    projectActivities.add(activity);
            }
        } catch (SQLException e) {
            LOGGER.error("Could not get activities for project with ID = " + projectId +
                    " --- detailed info: " + e.getMessage());
            projectActivities = null;
        }

        return projectActivities;
    }

    /**
     * Updates an activity in the database.
     * @param activity Activity object to be updated.
     * @return True if the update was successful; False otherwise.
     */
    public boolean updateActivity(Activity activity) {

        if (activity == null)
            return false;

        int activityId = activity.getActivityId();
        if (activityId == 0)
            return false;

        Statement statement = DbManager.createConnectionStatement();

        if (statement == null) {
            LOGGER.warn("Could not get a connection statement to DB");
            return false;
        }

        StringBuilder updateActivitySql = new StringBuilder();
        updateActivitySql.append(String.format(
                "UPDATE activity " +
                "SET    activity_label = '%s', description = '%s', " +
                "       duration = %d, optimistic_duration = %d, likely_duration = %d, " +
                "       pessimistic_duration = %d, update_date = '%s', project_id = %d, " +
                "       member_id = %d", activity.getActivityLabel(), activity.getDescription(),
                activity.getDuration(), activity.getOptimisticDuration(),
                activity.getLikelyDuration(), activity.getPessimisticDuration(),
                DateHelper.format(new Date(), Config.DATE_FORMAT),
                activity.getProject().getProjectId(), activity.getMember().getMemberId(),
                activityId));

        // Skip updating start and finish dates if either of them is NULL.
        if (activity.getStartDate() != null)
            updateActivitySql.append(String.format(", start_date = '%s'",
                    DateHelper.format(activity.getStartDate(), Config.DATE_FORMAT)));

        if (activity.getFinishDate() != null)
            updateActivitySql.append(String.format(", finish_date = '%s'",
                    DateHelper.format(activity.getFinishDate(), Config.DATE_FORMAT)));

        updateActivitySql.append(String.format(" WHERE activity_id = %d", activityId));
        try {

            int updatedRows = statement.executeUpdate(updateActivitySql.toString());

            if (updatedRows == 1) {
                List<Activity> prerequisites = activity.getPrerequisites();
                if (prerequisites != null)
                    return insertPrereqs(activityId, activity.getPrerequisites());
            }

        } catch (SQLException e) {
            LOGGER.error("Could not update activity with ID = " + activity.getActivityId() +
                    " --- detailed info: " + e.getMessage());
        }

        return false;
    }
    
    /**
     * Add one Prerequisites only
     * @param activityIdDependent
     * @param activityIdDependentOn
     * @return True if insert is successful
     */
    public boolean insertActivityPrerequisites(int activityIdDependent, Activity activityIdDependentOn){
    	
    	// Add one only
    	List<Activity> list = new ArrayList<>();
    	list.add(activityIdDependentOn);
    	return this.insertPrereqs(activityIdDependent, list);
    }
}
