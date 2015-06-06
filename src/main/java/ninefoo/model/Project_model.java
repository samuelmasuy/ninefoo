package ninefoo.model;

import ninefoo.lib.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class contains methods for manipulating the projects in the database. For example,
 *      to add a new project, get the list of all projects, find a specific project by ID,
 *      or delete a project from the database.
 * Created by Farzad on 01-Jun-2015.
 */
public class Project_model {
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Inserts a new project into the database.
     * @param project the Project object to be stored in the DB.
     * @return The ID (primary key) of the newly inserted record, 0 if the insertion
     *         was not successful.
     */
    public int insertNewProject(Project project) {
        Statement statement = DbManager.createConnectionStatement();

        if (statement == null) {
            LOGGER.warn("Could not get a connection statement to DB");
            return 0;
        }

        String insertProjectSql = String.format(
                "INSERT INTO project(project_name, budget, deadline_date, description) " +
                "VALUES ('%s', %f, '%s', '%s')", project.getProjectName(), project.getBudget(),
                DateUtils.format(project.getDeadlineDate()), project.getDescription());

        try {
            int updatedRows = statement.executeUpdate(insertProjectSql);

            if (updatedRows == 1) {
                ResultSet rs = statement.executeQuery("SELECT last_insert_rowid()");

                if (rs.next())
                    return rs.getInt("last_insert_rowid()");
            }

            LOGGER.warn("Updated row count was not equal to 1");

        } catch (SQLException e) {
            LOGGER.error("Could not insert new project into db --- detailed info: " + e.getMessage());
        } finally {
            DbManager.closeConnection();
        }

        return 0;
    }

    // Helper method to get the next Project object from the DB ResultSet object.
    private Project getNextProject(ResultSet projects) {

        try {
            int projectId = projects.getInt("project_id");
            String projectName = projects.getString("project_name");
            Date createDate = DateUtils.parse(projects.getString("create_date"));
            Date updateDate = DateUtils.parse(projects.getString("update_date"));
            double budget = projects.getDouble("budget");
            Date deadlineDate = DateUtils.parse(projects.getString("deadline_date"));
            String description = projects.getString("description");

            return new Project(projectId, projectName, createDate, updateDate, budget, deadlineDate, description);

        } catch (SQLException e) {
            LOGGER.error("Could not get next project from db --- detailed info: " + e.getMessage());
        }

        return null;
    }

    /**
     * Returns the Project object from the database that is associated with the specified ID.
     * @param projectId integer representing the ID of the project to be found in the DB.
     * @return Project object if it exists in the DB, NULL otherwise.
     */
    public Project getProjectById(int projectId) {
        Statement statement = DbManager.createConnectionStatement();

        if (statement == null)
            return null;

        String getProjectByIdSql = "SELECT * FROM project WHERE project_id = " + projectId;

        try {
            ResultSet projects = statement.executeQuery(getProjectByIdSql);

            if (projects.next()) {
                Project project = getNextProject(projects);

                if (project != null)
                    return project;
            }

        } catch (SQLException e) {
            LOGGER.error("Could not get project for id " + projectId + " from db --- " +
                    "detailed info: " + e.getMessage());
        } finally {
            DbManager.closeConnection();
        }

        return null;
    }

    /**
     * Returns all the projects stored in the database.
     * @return a List of Project objects (it would be empty if the table is empty).
     */
    public List<Project> getAllProjects() {
        List<Project> allProjects = new ArrayList<>();
        Statement statement = DbManager.createConnectionStatement();

        if (statement == null)
            return null;

        String getAllProjectsSql = "SELECT * FROM Project";
        try {
            ResultSet allProjectsFromDb = statement.executeQuery(getAllProjectsSql);

            while (allProjectsFromDb.next()) {
                Project nextProject = getNextProject(allProjectsFromDb);

                if (nextProject != null)
                    allProjects.add(nextProject);
            }

            return allProjects;

        } catch (SQLException e) {
            LOGGER.error("Could not get projects from db --- detailed info: " + e.getMessage());
        } finally {
            DbManager.closeConnection();
        }

        return null;
    }

    /**
     * Deletes a project from DB corresponding to the specified Project object.
     * @param project Project object to be deleted from DB.
     * @return True if a record was deleted; False otherwise.
     */
    public boolean deleteProject(Project project) {

        if (project == null)
            return false;

        return deleteProjectById(project.getProjectId());
    }

    /**
     * Deletes a project from DB corresponding to the specified project ID.
     * @param projectId integer representing the ID of the project to be deleted.
     * @return True if a record was deleted; False otherwise.
     */
    public boolean deleteProjectById(int projectId) {
        Statement statement = DbManager.createConnectionStatement();

        if (statement == null) {
            LOGGER.warn("Could not get a connection statement to DB");
            return false;
        }

        String deleteProjectSql = "DELETE FROM project WHERE project_id = " + projectId;

        try {
            int updatedRows = statement.executeUpdate(deleteProjectSql);
            return (updatedRows == 1);

        } catch (SQLException e) {
            LOGGER.error("Could not delete project --- detailed info: " + e.getMessage());
        }

        return false;
    }

}
