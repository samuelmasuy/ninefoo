package ninefoo.model.sql;

import ninefoo.config.*;
import ninefoo.helper.DateHelper;
import ninefoo.config.Config;
import ninefoo.model.object.Activity;
import ninefoo.model.object.Member;
import ninefoo.model.object.Project;
import ninefoo.model.sql.template.AbstractModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class contains methods to manipulate the activities in the database. For example, to
 *      add a new activity, find a specific activity by ID.
 * @author Farzad MajidFayyaz
 * Created on 03-Jun-2015.
 */
public class Activity_model extends AbstractModel{

    /**
     * Inserts a new activity into the database.
     * @param activity <code>Activity</code> object to be inserted into the DB.
     * @return the ID generated by the database for the new activity; <code>Database.ERROR</code> if the insertion
     *         was not successful.
     */
    public int insertNewActivity(Activity activity) {
    	
    	// Condition
        if (activity.getProject() == null || activity.getMember() == null)
            return Database.ERROR;

    	// Open
    	this.open();

    	// Query
        sql = "INSERT INTO activity(activity_label, description, duration, " +
        "optimistic_duration, likely_duration, pessimistic_duration, " +
        "project_id, member_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
        	
        	// Prepare
        	this.prepareStatement();
        	
        	// Data
        	ps.setString(1, activity.getActivityLabel());
        	ps.setString(2, activity.getDescription());
        	ps.setInt(3, activity.getDuration());
        	ps.setInt(4, activity.getOptimisticDuration());
        	ps.setInt(5, activity.getLikelyDuration());
        	ps.setInt(6, activity.getPessimisticDuration());
        	ps.setInt(7, activity.getProject().getProjectId());
        	ps.setInt(8, activity.getMember().getMemberId());
        	
        	// Run 
            affectedRows = ps.executeUpdate();

            // Get id
            if (affectedRows == 1)
            	return this.getLastInsertId();

        // Error
        } catch (SQLException e) {
            LOGGER.error("Could not insert new activity into db --- detailed info: " + e.getMessage());
        
        // Close
        } finally {
        	this.close();
        }

        return Database.ERROR;
    }

    // Helper function to insert the prerequisites for a given activity.
    @Deprecated
    private boolean insertPrereqs(int activityId, List<Activity> prerequisites) {
    	
    	// Open
    	this.open();

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
    	
        for (Activity prereq : prerequisites) {
            
        	// Query
        	sql = "INSERT INTO activity_relation VALUES(?, ?)";

            try {
            	
            	// Prepare
            	this.prepareStatement();
            	
            	// Data
            	ps.setInt(1, activityId);
            	ps.setInt(2, prereq.getActivityId());
            	
            	// Run
                ps.executeUpdate();
                
            // Error
            } catch (SQLException e) {
                LOGGER.error("Could not insert prerequisites for activity with ID = " +
                        activityId + " --- detailed info: " + e.getMessage());
                return false;
            
            // Close
            } finally {
            	this.close();
            }
        }

        return true;
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
    	
    	// Open
    	this.open();

        List<Activity> prerequisites = new ArrayList<>();
        
        // Query
        sql = "SELECT prereq_activity_id FROM activity_relation " +
        "WHERE activity_id = ?";

        try {
        	
        	// Prepare
        	this.prepareStatement();
        	
        	// Data
        	ps.setInt(1, activityId);
        	
        	// Run
            result = ps.executeQuery();

            // Get all
            while (result.next())
                prerequisites.add(getActivityById(result.getInt("prereq_activity_id")));
         
        // Error
        } catch (SQLException e) {
            LOGGER.error("Could not prerequisites for activity ID = " + activityId +
                    " --- detailed message: " + e.getMessage());
        
        // Close
        } finally {
        	this.close();
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
    	
    	// Open
    	this.open();

    	// Query
        sql = "SELECT * FROM activity " + "WHERE activity_id = ?";

        try {
        	
        	// Prepare
        	this.prepareStatement();
        	
        	// Data
        	ps.setInt(1, activityId);
        	
        	// Run
            result = ps.executeQuery();

            // Get all
            if (result.next()) {
                Activity activity = getNextActivity(result);

                if (activity != null)
                    return activity;
            }
            
        // Error
        } catch (SQLException e) {
            LOGGER.error("Could not get activity with activity_id = " + activityId + " --- " +
                    "detailed info: " + e.getMessage());
        
        // Close
        } finally {
        	this.close();
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
     * TODO Use one query to fetch activities and project
     * 
     * Gets the activities for the project corresponding to the project ID.
     * @param projectId ID of the project for which to get the activities.
     * @return List of Activity objects for the project, empty ArrayList if no activity
     *         is found; NULL if there is a problem connecting to the database.
     */
    public List<Activity> getActivitiesByProjectId(int projectId) {

    	// Open
    	this.open();

        List<Activity> projectActivities = new ArrayList<>();
        
        // Query
        sql = "SELECT activity_id FROM activity WHERE project_id = ?";

        try {
        	
        	// Prepare
        	this.prepareStatement();
        	
        	// Data
        	ps.setInt(1, projectId);
        	
        	// Run
            result = ps.executeQuery();

            int activityId;
            Activity activity;
            while (result.next()) {
                activityId = result.getInt("activity_id");
                activity = getActivityById(activityId);

                if (activity != null)
                    projectActivities.add(activity);
            }
            
        // Error
        } catch (SQLException e) {
            LOGGER.error("Could not get activities for project with ID = " + projectId +
                    " --- detailed info: " + e.getMessage());
            projectActivities = null;
        
        // Close
        } finally {
        	this.close();
        }

        return projectActivities;
    }

    /**
     * Updates an activity in the database.
     * @param activity Activity object to be updated.
     * @return True if the update was successful; False otherwise.
     */
    public boolean updateActivity(Activity activity) {
    	
    	int activityId;
        if (activity == null || (activityId = activity.getActivityId()) == 0 )
            return false;

    	// Open
    	this.open();
    	
    	// Query
        sql = 	"UPDATE activity " +
		        "SET    activity_label = ?, description = ?, " +
		        "       duration = ?, optimistic_duration = ?, likely_duration = ?, " +
		        "       pessimistic_duration = ?, update_date = ?, project_id = ?, " +
		        "		start_date = ?, , finish_date = ?" +
		        " 		WHERE activity_id = ?";
        
        try {

        	// Prepare
        	this.prepareStatement();
        	
        	// Data
        	ps.setString(1, activity.getActivityLabel());
        	ps.setString(2, activity.getDescription());
        	ps.setInt(3, activity.getDuration());
        	ps.setInt(4, activity.getOptimisticDuration());
        	ps.setInt(5, activity.getLikelyDuration());
        	ps.setInt(6, activity.getPessimisticDuration());
        	ps.setString(7, DateHelper.format(new Date(), Config.DATE_FORMAT));
        	ps.setInt(8, activity.getProject().getProjectId());
        	ps.setString(9, DateHelper.format(activity.getStartDate(), Config.DATE_FORMAT));
        	ps.setString(10, DateHelper.format(activity.getFinishDate(), Config.DATE_FORMAT));
        	ps.setInt(11, activityId);
        	
        	// Run
            affectedRows = ps.executeUpdate();

            return affectedRows == 1;

        // Error
        } catch (SQLException e) {
            LOGGER.error("Could not update activity with ID = " + activity.getActivityId() +
                    " --- detailed info: " + e.getMessage());
        
        // Close
        } finally {
        	this.close();
        }

        return false;
    }
    
    /**
     * Add one Prerequisites only
     * @param activityIdDependent
     * @param activityIdDependentOn
     * @return True if insert is successful
     */
    @Deprecated
    public boolean insertActivityPrerequisites(int activityIdDependent, Activity activityIdDependentOn){
    	
    	// Add one only
    	List<Activity> list = new ArrayList<>();
    	list.add(activityIdDependentOn);
    	return this.insertPrereqs(activityIdDependent, list);
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
            Date createDate = DateHelper.parse(activities.getString("create_date"), ninefoo.config.Config.DATE_FORMAT);
            Project project = projectModel.getProjectById(activities.getInt("project_id"));
            Member member = memberModel.getMemberById(activities.getInt("member_id"));
            
            activity = new Activity(activityId, activityLabel, description, duration,
                    optimisticDuration, likelyDuration, pessimisticDuration,
                    createDate, project, member, null);
        } catch (SQLException e) {
            LOGGER.error("Could not get next activity from db --- detailed info: " + e.getMessage());
        }

        return activity;
    }
}
