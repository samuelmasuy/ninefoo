package ninefoo.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Farzad on 02-Jun-2015.
 */
public class ProjectMemberModel {
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

        List<Integer> projectIds = new ArrayList<>();
        List<Project> projects = new ArrayList<>();
        Statement statement = DbManager.createConnectionStatement();

        if (statement == null)
            return null;

        // First get the list of project IDs from "project_member" table.
        int roleId = role.getRoleId();
        int memberId = member.getMemberId();
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
        ProjectModel projectModel = new ProjectModel();

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

        Statement statement = DbManager.createConnectionStatement();
        boolean success = true;

        if (statement == null)
            return false;

        int projectId, memberId, roleId;
        String insertNewProjectMemberSql;

        for (Member member : members) {
            projectId = project.getProjectId();
            memberId = member.getMemberId();
            roleId = role.getRoleId();

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
