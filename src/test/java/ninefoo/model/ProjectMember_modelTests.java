package ninefoo.model;

import static org.junit.Assert.*;
import ninefoo.config.Config;
import ninefoo.helper.DateHelper;
import ninefoo.model.object.Member;
import ninefoo.model.object.Project;
import ninefoo.model.object.Role;
import ninefoo.model.sql.Member_model;
import ninefoo.model.sql.ProjectMember_model;
import ninefoo.model.sql.Project_model;
import ninefoo.model.sql.Role_model;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectMember_modelTests {
    private static ProjectMember_model projectMember_model = new ProjectMember_model();
    private static Project_model project_model = new Project_model();
    private static Member_model member_model = new Member_model();
    private static Role_model role_model = new Role_model();

    private static int newMemberId;
    private static int newRoleId;
    private static int newProjectId;

    @BeforeClass
    public static void setupDb() {
        // Setup the DB so that we have something to work with.
        Member member = new Member("first_name", "last_name", "user_name_1234", "pass_1234");
        Role role = new Role("role_name", "role_name description");
        Project project = new Project("test_project_for_members", 5555.00,
                DateHelper.parse("07/07/2015", Config.DATE_FORMAT_SHORT),
                DateHelper.parse("08/08/2015", Config.DATE_FORMAT_SHORT),
                "test_project description");

        newMemberId = member_model.insertNewMember(member);
        if (newMemberId == 0)
            newMemberId = member_model.getMemberByUsername(member.getUsername()).getMemberId();

        newRoleId = role_model.insertNewRole(role);
        if (newRoleId == 0)
            newRoleId = role_model.getRoleByName(role.getRoleName()).getRoleId();

        newProjectId = project_model.insertNewProject(project);
    }

    @AfterClass
    public static void cleanUP() {
        member_model.deleteMemberById(newMemberId);
        role_model.deleteRoleById(newRoleId);
        project_model.deleteProjectById(newProjectId);
    }

    @Test
    public void test01_ProjectMember_InitiallyNoMemberInProject() {
        List<Project> projects = projectMember_model.getProjectsByMember(newMemberId, newRoleId);
        assertEquals("Initially, the number of projects for member should be zero", 0, projects.size());
    }

    @Test
    public void test02_ProjectMember_MemberIsAddedToProject() {
        Role role = role_model.getRoleById(newRoleId);

        boolean success = projectMember_model.addMemberToProject(newProjectId, newMemberId, role);
        assertTrue("addMemberToProject should return true", success);
    }

    @Test
    public void test03_ProjectMember_ProjectNumberIsOneForMember() {
        List<Project> projects = projectMember_model.getProjectsByMember(
                member_model.getMemberById(newMemberId),
                role_model.getRoleById(newRoleId)
        );

        assertEquals("Project number for member should be 1", 1, projects.size());
    }

    @Test
    public void test04_ProjectMember_getProjectsReturnsNullForNullValues() {
        List<Project> projects = projectMember_model.getProjectsByMember(null, null);
        assertNull("Project list for null member and role should be null", projects);
    }

    @Test
    public void test05_ProjectMember_getProjectsReturnsNullForIdEqualZero() {
        List<Project> projects = projectMember_model.getProjectsByMember(0, 0);
        assertNull("Project list for member role id of zero should be null", projects);
    }

    @Test
    public void test06_ProjectMember_addMemberReturnsFalseForNullRole() {
        boolean success = projectMember_model.addMemberToProject(1, 2, null);
        assertFalse("addMember returns false when role object is null", success);
    }
}
