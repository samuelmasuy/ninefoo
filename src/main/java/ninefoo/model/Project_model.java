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
     * @return The ID (primary key) of the newly inserted record, <code>Database.ERROR</code> if the insertion
     *         was not successful.
     */
    public int insertNewProject(Project project) {
        Statement statement = DbManager.createConnectionStatement();

        if (statement == null) {
            LOGGER.warn("Could not get a connection statement to DB");
            return Database.ERROR;
        }

        String insertProjectSql = String.format(
                "INSERT INTO project(project_name, budget, start_date, deadline_date, description) " +
                "VALUES ('%s', %f, '%s', '%s', '%s')", project.getProjectName(), project.getBudget(),
                DateHelper.format(project.getStartDate(), Config.DATE_FORMAT),
                DateHelper.format(project.getDeadlineDate(), Config.DATE_FORMAT), project.getDescription());

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
        }

        return Database.ERROR;
    }

    // Helper method to get the next Project object from the DB ResultSet object.
    private Project getNextProject(ResultSet projects) {

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
        }
//        } finally {
//            DbManager.closeConnection();
//        }

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
        }

        return null;
    }

    /**
     * Get all projects by member id and role id
     * Added by Amir
     * @param memberId
     * @param roleId
     * @return List of projects
     */
    public List<Project> getAllProjectsByMemberAndRole(int memberId, int roleId){
    	List<Project> allProjects = new ArrayList<>();
        Statement statement = DbManager.createConnectionStatement();

        if (statement == null)
            return null;

        String getAllProjectsSql = String.format(
        		"SELECT P.project_id, P.project_name, P.create_date, P.start_date, P.update_date, P.budget, P.deadline_date, P.description "
        		+ "FROM Project P, Project_member PM "
        		+ "WHERE PM.project_id = P.project_id "
        		+ "AND PM.member_id = %d "
        		+ "AND PM.role_id = %d", memberId, roleId);
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

    /**
     * Updates a project in the database
     * @param project Project object representing the updated project
     * @return True if update was successful, False otherwise
     */
    public boolean updateProject(Project project) {

        if (project == null)
            return false;

        int projectId = project.getProjectId();
        if (projectId == 0)
            return false;

        Statement statement = DbManager.createConnectionStatement();

        if (statement == null) {
            LOGGER.warn("Could not get a connection statement to DB");
            return false;
        }

        StringBuilder updateProjectSql = new StringBuilder();
        updateProjectSql.append(String.format(
                "UPDATE project " +
                "SET    project_name = '%s', update_date = '%s', budget = %f, " +
                "       description = '%s'", project.getProjectName(),
                DateHelper.format(new Date(), Config.DATE_FORMAT),
                project.getBudget(), project.getDescription()
        ));

        // Skip updating start and deadline dates if they are NULL.
        if (project.getStartDate() != null)
            updateProjectSql.append(String.format(", start_date = '%s'",
                    DateHelper.format(project.getStartDate(), Config.DATE_FORMAT)));

        if (project.getDeadlineDate() != null)
            updateProjectSql.append(String.format(", deadline_date = '%s'",
                    DateHelper.format(project.getDeadlineDate(), Config.DATE_FORMAT)));

        updateProjectSql.append(String.format(" WHERE project_id = %d", projectId));

        try {
            int updatedRows = statement.executeUpdate(updateProjectSql.toString());

            return (updatedRows == 1);

        } catch (SQLException e) {
            LOGGER.error("Could not update project with ID = " + projectId +
                    " --- detailed info: " + e.getMessage());
        }

        return false;
    }
}
