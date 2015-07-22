package ninefoo.model.sql;

import ninefoo.model.object.Member;
import ninefoo.model.object.Project;
import ninefoo.model.object.Role;
import ninefoo.model.sql.template.AbstractModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains method for manipulating the records in the database where there is
 * a relationship between project(s) and member(s). For example, to find all the
 * projects for a member, or add a list of members to a project.
 * Created on 02-Jun-2015.
 *
 * @author Farzad MajidFayyaz
 */
public class ProjectMember_model extends AbstractModel {

    /**
     * Gets the list of projects for the specified member in which that member
     * has the specified role.
     *
     * @param member Member object corresponding to the member.
     * @param role   Role object representing the role of the member.
     * @return List of Project objects corresponding to the specified member and role.
     */
    public List<Project> getProjectsByMember(Member member, Role role) {

        if (member == null || role == null)
            return null;

        return getProjectsByMember(member.getMemberId(), role.getRoleId());
    }

    /**
     * Gets the list of projects for the member corresponding to memberId in which that
     * member has the role corresponding to the roleId.
     *
     * @param memberId ID of the member for whom we want to find the projects.
     * @param roleId   ID of the role of that member in the projects.
     * @return List of Project objects, or NULL if error
     */
    public List<Project> getProjectsByMember(int memberId, int roleId) {

        if (memberId == 0 || roleId == 0)
            return null;

        // Open
        this.open();

        List<Project> projects = new ArrayList<>();

        // Query
        sql = "SELECT p.* FROM project_member pm, project p " +
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
     * Adds the specified member to the specified project with. Member will have
     * the specified role in the project.
     *
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

    /**
     * Get all projects by member id and role id
     * Added by Amir
     *
     * @param memberId
     * @param roleId
     * @return List of projects
     */
    public List<Project> getAllProjectsByMemberAndRole(int memberId, int roleId) {

        // Open
        this.open();

        List<Project> allProjects = new ArrayList<>();

        // Query
        sql = "SELECT P.project_id, P.project_name, P.create_date, P.start_date, P.update_date, P.budget, P.deadline_date, P.description "
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
     * Check if the user is already assigned
     *
     * @param memberId
     * @param projectId
     * @param role
     * @return boolean
     */
    public boolean getAssignedAnyRole(int memberId, int projectId) {

        // Open
        this.open();

        // Query
        sql = "SELECT * FROM project_member WHERE member_id = ? AND project_id = ?";

        try {

            // Prepare
            this.prepareStatement();

            // Set data
            ps.setInt(1, memberId);
            ps.setInt(2, projectId);

            // Run
            result = ps.executeQuery();

            // Get single
            if (result.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            LOGGER.error("Error occured: " + e.getMessage());
        } finally {
            this.close();
        }

        return false;
    }
    
    /**
     * Remove member from a project
     * @param memberId
     * @param projectId
     * @return boolean
     */
    public boolean removeMemberFromProject(int memberId, int projectId) {

        // Open
        this.open();

        // Query
        sql = "DELETE FROM project_member WHERE member_id = ? AND project_id = ?";

        try {

            // Prepare
            this.prepareStatement();

            // Set data
            ps.setInt(1, memberId);
            ps.setInt(2, projectId);

            // Run
            affectedRows = ps.executeUpdate();

            // Get single
            return affectedRows == 1;

        } catch (SQLException e) {
            LOGGER.error("Error occured: " + e.getMessage());
        } finally {
            this.close();
        }

        return false;
    }
}
