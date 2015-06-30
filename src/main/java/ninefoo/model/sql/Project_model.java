package ninefoo.model.sql;

import ninefoo.config.*;
import ninefoo.helper.DateHelper;
import ninefoo.config.Config;
import ninefoo.model.object.Project;
import ninefoo.model.sql.template.AbstractModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class contains methods for manipulating the projects in the database. For example,
 *      to add a new project, get the list of all projects, find a specific project by ID,
 *      or delete a project from the database.
 * Created on 01-Jun-2015.
 * @author Farzad MajidFayyaz
 */
public class Project_model extends AbstractModel{
    
    /**
     * Inserts a new project into the database.
     * @param project the Project object to be stored in the DB.
     * @return The ID (primary key) of the newly inserted record, <code>Database.ERROR</code> if the insertion
     *         was not successful.
     */
    public int insertNewProject(Project project) {
    	
    	// Open
    	this.open();
    	
    	// Query
    	sql = 		"INSERT INTO project(project_name, budget, start_date, deadline_date, description) "
    			+ 	"VALUES (?, ?, ?, ?, ?)";
    	
    	// Prepared statement
		try {
			
			// Prepare
			this.prepareStatement();
		
			// Data
	        ps.setString(1, project.getProjectName());
	        ps.setDouble(2, project.getBudget());
	        ps.setString(3, DateHelper.format(project.getStartDate(), Config.DATE_FORMAT));
	        ps.setString(4, DateHelper.format(project.getDeadlineDate(), Config.DATE_FORMAT));
	        ps.setString(5, project.getDescription());
	        
	        // Run
	        affectedRows = ps.executeUpdate();
	        
	        // If row inserted
	        if(affectedRows == 1)
	        	return this.getLastInsertId();
	       
	    // Error 
		} catch (SQLException e) {
			LOGGER.error("Could not insert new project into db --- detailed info: " + e.getMessage());
		
		// Close
		} finally{
			this.close();
		}
        
        return Database.ERROR;
    }

    /**
     * Returns the Project object from the database that is associated with the specified ID.
     * @param projectId integer representing the ID of the project to be found in the DB.
     * @return Project object if it exists in the DB, NULL otherwise.
     */
    public Project getProjectById(int projectId) {
    	
    	// Open
    	this.open();
    	
    	// Query
        sql = "SELECT * FROM project WHERE project_id = ?";

        try {
        	// Prepare
        	this.prepareStatement();
        	
        	// Data
        	ps.setInt(1, projectId);
        	
        	// Select
        	result = ps.executeQuery();

           if (result.next()) {
               Project project = getNextProject(result);

               if (project != null)
            	   return project;
           }

        // Error
        } catch (SQLException e) {
            LOGGER.error("Could not get project for id " + projectId + " from db --- " +
                    "detailed info: " + e.getMessage());
        
        // Close
        } finally {
        	this.close();
        }

        return null;
    }

    /**
     * Returns all the projects stored in the database.
     * @return a List of Project objects (it would be empty if the table is empty).
     */
    public List<Project> getAllProjects() {
    	
    	// Open
    	this.open();
    	
        List<Project> allProjects = new ArrayList<>();
        
        // Query
        sql = "SELECT * FROM Project";
        try {
        	
        	// Prepare
        	this.prepareStatement();
        	
        	// Run
            result = ps.executeQuery();

            // Get all
            while (result.next()) {
                Project nextProject = getNextProject(result);

                if (nextProject != null)
                    allProjects.add(nextProject);
            }

            return allProjects;

        // Error
        } catch (SQLException e) {
            LOGGER.error("Could not get projects from db --- detailed info: " + e.getMessage());
        
        // Close
        } finally {
        	this.close();
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
    	
    	// Open
    	this.open();
    	
    	List<Project> allProjects = new ArrayList<>();
    	
    	// Query
        sql =  	  "SELECT P.project_id, P.project_name, P.create_date, P.start_date, P.update_date, P.budget, P.deadline_date, P.description "
        		+ "FROM Project P, Project_member PM "
        		+ "WHERE PM.project_id = P.project_id "
        		+ "AND PM.member_id = ? "
        		+ "AND PM.role_id = ?";
        try {
        	
        	// Prepare
        	this.prepareStatement();
        	
        	// Data
        	ps.setInt(1, memberId);
        	ps.setInt(2, roleId);
        	
        	// Run
            result = ps.executeQuery();

            // Get all
            while (result.next()) {
                Project nextProject = getNextProject(result);

                if (nextProject != null)
                    allProjects.add(nextProject);
            }

            return allProjects;
        
        // Error
        } catch (SQLException e) {
            LOGGER.error("Could not get projects from db --- detailed info: " + e.getMessage());
        
        // Close
        } finally {
        	this.close();
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
    	
    	// Open
    	this.open();

        // Query
    	sql = "DELETE FROM project WHERE project_id = ?";

        try {
        	
        	// Prepare
        	this.prepareStatement();
        	
        	// Data
        	ps.setInt(1, projectId);
        	
        	// Run
            affectedRows = ps.executeUpdate();
            
            // Check if deleted
            return (affectedRows == 1);

        // Error
        } catch (SQLException e) {
            LOGGER.error("Could not delete project --- detailed info: " + e.getMessage());
        
        // Close
        } finally {
        	this.close();
        }

        return false;
    }

    /**
     * Updates a project in the database
     * @param project Project object representing the updated project
     * @return True if update was successful, False otherwise
     */
    public boolean updateProject(Project project) {

    	// Conditions
    	int projectId;
    	if (project == null || (projectId = project.getProjectId() ) == 0)
            return false;

        // Open
        this.open();
        
        sql = 	"UPDATE project " +
                "SET    project_name = ?, update_date = ?, budget = ?, " +
                "       description = ?, start_date = ?, deadline_date = ?" +
                " WHERE project_id = ?";
        
        try {
        	
        	// Prepare
        	this.prepareStatement();
        	
        	// Data
        	ps.setString(1, project.getProjectName());
        	ps.setString(2, DateHelper.format(new Date(), Config.DATE_FORMAT));
        	ps.setDouble(3, project.getBudget());
        	ps.setString(4, project.getDescription());
        	ps.setString(5, DateHelper.format(project.getStartDate(), Config.DATE_FORMAT));
        	ps.setString(6, DateHelper.format(project.getDeadlineDate(), Config.DATE_FORMAT));
        	ps.setInt(7, projectId);
        	
        	// Run
            affectedRows = ps.executeUpdate();

            // Check if updated
            return (affectedRows == 1);
            
        // Error
        } catch (SQLException e) {
            LOGGER.error("Could not update project with ID = " + projectId +
                    " --- detailed info: " + e.getMessage());
        
        // Close
        } finally {
        	this.close();
        }

        return false;
    }
}
