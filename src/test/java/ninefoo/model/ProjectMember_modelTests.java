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
    private static int newProjectId, project1Id, project2Id, project3Id;

    @BeforeClass
    public static void setupDb() {
        // Setup the DB so that we have something to work with.
        Member member = new Member("first_name", "last_name", "user_name_1234", "pass_1234");
        Role role = new Role("role_name", "role_name description");
        Project project = new Project("test_project_for_members", 5555.00,
                DateHelper.parse("07/07/2015", Config.DATE_FORMAT_SHORT),
                DateHelper.parse("08/08/2015", Config.DATE_FORMAT_SHORT),
                "test_project description");
        Project project1 = new Project("project1_for_members", 5555.00,
                DateHelper.parse("07/07/2015", Config.DATE_FORMAT_SHORT),
                DateHelper.parse("08/08/2015", Config.DATE_FORMAT_SHORT),
                "description");
        Project project2 = new Project("project2_for_members", 5555.00,
                DateHelper.parse("07/07/2015", Config.DATE_FORMAT_SHORT),
                DateHelper.parse("08/08/2015", Config.DATE_FORMAT_SHORT),
                "description");
        Project project3 = new Project("project3_for_members", 5555.00,
                DateHelper.parse("07/07/2015", Config.DATE_FORMAT_SHORT),
                DateHelper.parse("08/08/2015", Config.DATE_FORMAT_SHORT),
                "description");

        newMemberId = member_model.insertNewMember(member);
        if (newMemberId == 0)
            newMemberId = member_model.getMemberByUsername(member.getUsername()).getMemberId();

        newRoleId = role_model.insertNewRole(role);
        if (newRoleId == 0)
            newRoleId = role_model.getRoleByName(role.getRoleName()).getRoleId();

        newProjectId = project_model.insertNewProject(project);
        project1Id = project_model.insertNewProject(project1);
        project2Id = project_model.insertNewProject(project2);
        project3Id = project_model.insertNewProject(project3);
    }

    @AfterClass
    public static void cleanUP() {
        member_model.deleteMemberById(newMemberId);
        role_model.deleteRoleById(newRoleId);
        project_model.deleteProjectById(newProjectId);
        project_model.deleteProjectById(project1Id);
        project_model.deleteProjectById(project2Id);
        project_model.deleteProjectById(project3Id);
    }

    @Test
    public void test01_ProjectMember_getProjectsByMember_InitiallyNoMemberInProject() {
        List<Project> projects = projectMember_model.getProjectsByMember(newMemberId, newRoleId);
        assertEquals("Initially, the number of projects for member should be zero", 0, projects.size());
    }

    @Test
    public void test02_ProjectMember_addMemberToProject_MemberIsAddedToProject() {
        Role role = role_model.getRoleById(newRoleId);

        boolean success = projectMember_model.addMemberToProject(newProjectId, newMemberId, role);
        assertTrue("addMemberToProject should return true", success);
    }

    @Test
    public void test03_ProjectMember_getProjectsByMember_ProjectNumberIsOneForTestMember() {
        List<Project> projects = projectMember_model.getProjectsByMember(
                member_model.getMemberById(newMemberId),
                role_model.getRoleById(newRoleId)
        );

        assertEquals("Project number for member should be 1", 1, projects.size());
    }

    @Test
    public void test04_ProjectMember_getProjectsByMember_ReturnsNullForNullValues() {
        List<Project> projects = projectMember_model.getProjectsByMember(null, null);
        assertNull("Project list for null member and role should be null", projects);
    }

    @Test
    public void test05_ProjectMember_getProjectsByMember_ReturnsNullForIdEqualZero() {
        List<Project> projects = projectMember_model.getProjectsByMember(0, 0);
        assertNull("Project list for member role id of zero should be null", projects);
    }

    @Test
    public void test06_ProjectMember_addMemberToProject_NullIsReturnedForNullRole() {
        boolean success = projectMember_model.addMemberToProject(1, 2, null);
        assertFalse("addMember returns false when role object is null", success);
    }

    @Test
    public void test07_ProjectMember_getProjectsByMemberAndRole_NumberOfProjectsIsCorrect() {
        // Add the member to more projects
        Role role = role_model.getRoleById(newRoleId);
        boolean success = false;

        success = projectMember_model.addMemberToProject(project1Id, newMemberId, role);
        assertTrue("Add member to 'project1' should return true", success);
        success = projectMember_model.addMemberToProject(project2Id, newMemberId, role);
        assertTrue("Add member to 'project2' should return true", success);
        success = projectMember_model.addMemberToProject(project3Id, newMemberId, role);
        assertTrue("Add member to 'project3' should return true", success);

        // Now the member should be part of 4 projects.
        int expectedProjectCount = 4;
        List<Project> projects = projectMember_model.getAllProjectsByMemberAndRole(newMemberId, newRoleId);
        assertEquals("Member should be part of " + expectedProjectCount + " projects",
                expectedProjectCount, projects.size());
    }

    @Test
    public void test08_ProjectMember_getAssignedMembers_ReturnsFalseForMemberNotInProject() {
        // Add a new member
        Member member = new Member("npm_firstname", "npm_lastname", "npm_username", "npm_password");
        int memberId = member_model.insertNewMember(member);

        boolean memberIsInProject = projectMember_model.getAssignedAnyRole(memberId, newProjectId);
        assertFalse("Newly created member should not be in a project", memberIsInProject);

        // Delete the member.
        member_model.deleteMemberById(memberId);
    }

    @Test
    public void test09_ProjectMember_getAssignedMembers_ReturnsTrueForMemberInProject() {
        boolean memberIsInProject = projectMember_model.getAssignedAnyRole(newMemberId, newProjectId);
        assertTrue("Other member should be part of the project", memberIsInProject);
    }
}
