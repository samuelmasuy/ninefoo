package ninefoo.model;

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
 * Created by Farzad on 02-Jun-2015.
 */
public class ProjectMember_model {
    private static final Logger LOGGER = LogManager.getLogger();

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
     * @return List of Project objects, or NULL if no project is found.
     */
    public List<Project> getProjectsByMember(int memberId, int roleId) {

        if (memberId == 0 || roleId == 0)
            return null;

        Statement statement = DbManager.createConnectionStatement();

        if (statement == null) {
            LOGGER.warn("Could not get a connection statement to DB");
            return null;
        }

        List<Integer> projectIds = new ArrayList<>();
        List<Project> projects = new ArrayList<>();

        // First get the list of project IDs from "project_member" table.
        String getProjectIdsSql = String.format(
                "SELECT project_id FROM project_member " +
                "WHERE member_id = %d AND role_id = %d", memberId, roleId);

        try {
            ResultSet projectIdsFromDb = statement.executeQuery(getProjectIdsSql);

            while (projectIdsFromDb.next()) {
                projectIds.add(projectIdsFromDb.getInt("project_id"));
            }

        } catch (SQLException e) {
            LOGGER.error("Could not get projects form member_id = " + memberId +
                    " --- detailed info: " + e.getMessage());

            return null;
        } finally {
            DbManager.closeConnection();
        }

        // Now that we have the list of project IDs, we get the list of projects.
        Project_model projectModel = new Project_model();

        for (Integer projectId : projectIds) {
            Project project = projectModel.getProjectById(projectId);

            if (project != null)
                projects.add(project);
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
    public boolean addMembersToProject(int projectId, List<Integer> memberIds, int roleId) {

        if (projectId == 0 || roleId == 0)
            return false;

        Statement statement = DbManager.createConnectionStatement();

        if (statement == null) {
            LOGGER.warn("Could not get a connection statement to DB");
            return false;
        }

        String insertNewProjectMemberSql;
        boolean success = true;

        for (int memberId : memberIds) {

            insertNewProjectMemberSql = String.format(
                    "INSERT INTO project_member VALUES(%d, %d, %d)", projectId, memberId, roleId);

            try {
                statement.executeUpdate(insertNewProjectMemberSql);
            } catch (SQLException e) {
                LOGGER.error("Could not add members to project with ID: " +
                        projectId + " --- detailed info: " + e.getMessage());
                success = false;
            }
        }

        DbManager.closeConnection();
        return success;
    }
}
