package ninefoo.model.sql;

import ninefoo.config.Database;
import ninefoo.model.object.Member;
import ninefoo.model.object.Project;
import ninefoo.model.object.Role;
import ninefoo.model.sql.template.AbstractModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains method for manipulating the records in the database where there is
 *      a relationship between project(s) and member(s). For example, to find all the
 *      projects for a member, or add a list of members to a project.
 * Created on 02-Jun-2015.
 * @author Farzad MajidFayyaz
 */
public class ProjectMember_model extends AbstractModel{

    /**
     * Gets the list of projects for the specified member in which that member
     *      has the specified role.
     * @param member Member object corresponding to the member.
     * @param role Role object representing the role of the member.
     * @return List of Project objects corresponding to the specified member and role.
     */
    public List<Project> getProjectsByMember(Member member, Role role) {

        if (member == null || role == null)
            return null;

        return getProjectsByMember(member.getMemberId(), role.getRoleId());
    }

    /**
     * Gets the list of projects for the member corresponding to memberId in which that
     *      member has the role corresponding to the roleId.
     * @param memberId ID of the member for whom we want to find the projects.
     * @param roleId ID of the role of that member in the projects.
     * @return List of Project objects, or NULL if error
     */
    public List<Project> getProjectsByMember(int memberId, int roleId) {

        if (memberId == 0 || roleId == 0)
            return null;

        // Open
        this.open();

        List<Project> projects = new ArrayList<>();

        // Query
        sql = "SELECT * FROM project_member pm, project p " +
              "WHERE member_id = ? AND role_id = ? AND p.project_id = pm.project_id";

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
            	Project project = this.getNextProject(result);

                if (project != null)
                    projects.add(project);
            }
            
        // Error
        } catch (SQLException e) {
            LOGGER.error("Could not get projects form member_id = " + memberId +
                    " --- detailed info: " + e.getMessage());
            return null;
            
        // Close
        } finally {
        	this.close();
        }

        return projects;
    }

    /**
     * Adds the specified list of members to the specified project with. Members will have
     *      the specified role in the project.
     * @param project Project object corresponding to the target project.
     * @param members List of members to be added to the project.
     * @param role Role object representing the role of the members.
     * @return True if successful, False otherwise.
     */
    @Deprecated
    public boolean addMembersToProject(Project project, List<Member> members, Role role) {

        if (project == null || members == null || role == null)
            return false;

        List<Integer> memberIds = new ArrayList<>();
        for (Member member : members)
            memberIds.add(member.getMemberId());

        return addMembersToProject(project.getProjectId(), memberIds, role.getRoleId());
    }

    /**
     * Adds the members corresponding to the specified list of memberIds to the project
     *      corresponding to projectId with the role corresponding to roleId.
     * @param projectId Id of the project to add members.
     * @param memberIds Ids of the members to be added to the project.
     * @param roleId Id of the role of all the members in the list in the specified project.
     * @return True if successful, False otherwise.
     */
    @Deprecated
    public boolean addMembersToProject(int projectId, List<Integer> memberIds, int roleId) {

    	// No need to update because it's not used
    	return false;
    	
//        if (projectId == 0 || roleId == 0)
//            return false;
//
//        // Open
//        this.open();
//        
//        String insertNewProjectMemberSql;
//        boolean success = true;
//
//        for (int memberId : memberIds) {
//
//            insertNewProjectMemberSql = String.format(
//                    "INSERT INTO project_member VALUES(%d, %d, %d)", projectId, memberId, roleId);
//
//            try {
//                statement.executeUpdate(insertNewProjectMemberSql);
//            } catch (SQLException e) {
//                LOGGER.error("Could not add members to project with ID: " +
//                        projectId + " --- detailed info: " + e.getMessage());
//                success = false;
//            }
//        }
//
//        return success;
    }
    
    /**
     * Adds the specified member to the specified project with. Member will have
     *      the specified role in the project.
     * @param projectId
     * @param memberId
     * @param role 
     * @return True if successful, False otherwise.
     */
    public boolean addMemberToProject(int projectId, int memberId, Role role) {

    	// Condition
        if (role == null)
            return false;

        // Open
        this.open();
        
        // Query
        sql = "INSERT INTO project_member(project_id, member_id, role_id) VALUES (?, ?, ?)";
        
        try {	
        	// Prepare
        	this.prepareStatement();
        	
        	// Data
			ps.setInt(1, projectId);
        	ps.setInt(2, memberId);
        	ps.setInt(3, role.getRoleId());
        	
        	// Run
        	affectedRows = ps.executeUpdate();
        	
        	// Check inserted
        	return affectedRows == 1;
        	
    	// Error
    	} catch (SQLException e) {
    		LOGGER.info("Could not insert project_member" +
                " --- detailed info: " + e.getMessage());
		
    	// Close
    	} finally {
			this.close();
		}
        
        return false;
    }
}
