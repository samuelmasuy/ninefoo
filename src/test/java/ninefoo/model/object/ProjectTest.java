package ninefoo.model.object;

import junit.framework.TestCase;
import ninefoo.Mocks.MockUpdatableView;
import ninefoo.config.Database;
import ninefoo.config.Session;
import ninefoo.controller.handler.Project_controller;
import ninefoo.helper.DateHelper;
import ninefoo.lib.lang.LanguageText;
import ninefoo.model.sql.Member_model;
import ninefoo.model.sql.Project_model;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by samuel on 2015-08-15.
 * <p/>
 * Black Box testing for Create and Update Project.
 */
public class ProjectTest extends TestCase {
    private MockUpdatableView mockUpdatableView = new MockUpdatableView();
    private Project_controller project_controller;
    static Member member;
    static Project_model project_model;
    static Member_model member_model;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        ninefoo.config.Config.autoload();
        Session.getInstance().open();
        Session.getInstance().setUserId(1);

    }

    @Before
    public void setUp() throws Exception {
        member_model = new Member_model();
        project_model = new Project_model();
        member = member_model.getMemberById(1);
    }

    @Test
    public void testInsertNewProject_successful() throws Exception {
        project_controller = new Project_controller(mockUpdatableView);
        project_controller.createProject("name", "0", "1/1/1111", "1/1/1112", "description");
        assertEquals("project is successful method", "updateCreateProject", mockUpdatableView.get_called_method());
        assertEquals("project is successful success", "true", mockUpdatableView.get_success());
        assertEquals("project is successful message", String.format(LanguageText.getConstant("CREATED"), LanguageText.getConstant("PROJECT")), mockUpdatableView.get_message());
    }

    @Test
    public void testInsertNewProject_no_name() throws Exception {
        project_controller = new Project_controller(mockUpdatableView);
        project_controller.createProject("", "0", "1/1/1112", "1/1/1112", "description");
        assertEquals("project has no name method", "updateCreateProject", mockUpdatableView.get_called_method());
        assertEquals("project has no name success", "false", mockUpdatableView.get_success());
        assertEquals("project has no name message", String.format(LanguageText.getConstant("REQUIRED"), LanguageText.getConstant("NAME")), mockUpdatableView.get_message());
    }

    @Test
    public void testInsertNewProject_no_description() throws Exception {
        project_controller = new Project_controller(mockUpdatableView);
        project_controller.createProject("foo", "0", "1/1/1112", "1/1/1115", "");
        assertEquals("project has no description method", "updateCreateProject", mockUpdatableView.get_called_method());
        assertEquals("project has no description success", "true", mockUpdatableView.get_success());
        assertEquals("project has no description message", String.format(LanguageText.getConstant("CREATED"), LanguageText.getConstant("PROJECT")), mockUpdatableView.get_message());
    }

    @Test
    public void testInsertNewProject_invalid_budget() throws Exception {
        project_controller = new Project_controller(mockUpdatableView);
        project_controller.createProject("foo", "0periwoq", "1/1/1112", "1/1/1115", "");
        assertEquals("project has invalid budget method", "updateCreateProject", mockUpdatableView.get_called_method());
        assertEquals("project has invalid budget success", "false", mockUpdatableView.get_success());
        assertEquals("project has invalid budget message", String.format(LanguageText.getConstant("WRONG_FORMAT"), LanguageText.getConstant("BUDGET")), mockUpdatableView.get_message());
    }

    @Test
    public void testInsertNewProject_end_earlier_than_start() throws Exception {
        project_controller = new Project_controller(mockUpdatableView);
        project_controller.createProject("foo", "0", "1/1/1114", "1/1/1112", "description");
        assertEquals("project deadline do not make sense method", "updateCreateProject", mockUpdatableView.get_called_method());
        assertEquals("project deadline do not make sense success", "false", mockUpdatableView.get_success());
        assertEquals("project deadline do not make sense message", String.format(LanguageText.getConstant("WRONG_DATE_BEFORE_DATE"), LanguageText.getConstant("START_DATE"), "1/1/1112"), mockUpdatableView.get_message());
    }

    @Test
    public void testInsertNewProject_multiple_errors() throws Exception {
        project_controller = new Project_controller(mockUpdatableView);
        project_controller.createProject("", "0", "1/1/1112", "1/1/1110", "description");
        assertEquals("project has multiple errors method", "updateCreateProject", mockUpdatableView.get_called_method());
        assertEquals("project has multiple errors success", "false", mockUpdatableView.get_success());
        assertEquals("project has multiple errors message", String.format(LanguageText.getConstant("REQUIRED"), LanguageText.getConstant("NAME")) + "<br>" + String.format(LanguageText.getConstant("WRONG_DATE_BEFORE_DATE"), LanguageText.getConstant("START_DATE"), "1/1/1110"), mockUpdatableView.get_message());
    }

    @Test
    public void testEditProject_success() throws Exception {
        project_controller = new Project_controller(mockUpdatableView);
        project_controller.createProject("name", "0", "1/1/1111", "1/1/1112", "description");
        mockUpdatableView.reset_default();
        project_controller.editProject(1, "name", "0", "1/1/1111", "1/1/1112", "description");
        assertEquals("edit project is successful method", "updateEditProject", mockUpdatableView.get_called_method());
        assertEquals("edit project is successful success", "true", mockUpdatableView.get_success());
        assertEquals("edit project is successful message", String.format(LanguageText.getConstant("UPDATED"), LanguageText.getConstant("PROJECT")), mockUpdatableView.get_message());
    }

    @Test
    public void testEditProject_end_earlier_than_start() throws Exception {
        project_controller = new Project_controller(mockUpdatableView);
        project_controller.createProject("name", "0", "1/1/1111", "1/1/1112", "description");
        mockUpdatableView.reset_default();
        project_controller.editProject(1, "foo", "0", "1/1/1114", "1/1/1112", "description");
        assertEquals("edit project with date that does not make sense method", "updateEditProject", mockUpdatableView.get_called_method());
        assertEquals("edit project with date that does not make sense success", "false", mockUpdatableView.get_success());
        assertEquals("edit project with date that does not make sense message", String.format(LanguageText.getConstant("WRONG_DATE_BEFORE_DATE"), LanguageText.getConstant("START_DATE"), "1/1/1112"), mockUpdatableView.get_message());
    }

    @Test
    public void testLoadProject_success() throws Exception {
        project_controller = new Project_controller(mockUpdatableView);
        project_controller.createProject("name", "0", "1/1/1111", "1/1/1112", "description");
        mockUpdatableView.reset_default();
        project_controller.loadProject(1);
        assertEquals("load project success method", "updateLoadProject", mockUpdatableView.get_called_method());
        assertEquals("load project success success", "true", mockUpdatableView.get_success());
        assertEquals("load project success message", String.format(LanguageText.getConstant("LOADED"), String.format("%s '%s'", LanguageText.getConstant("PROJECT"), "name")), mockUpdatableView.get_message());
    }

    @Test
    public void testLoadProject_without_id() throws Exception {
        project_controller = new Project_controller(mockUpdatableView);
        project_controller.loadProject(99999);
        assertEquals("load project without id method", "updateLoadProject", mockUpdatableView.get_called_method());
        assertEquals("load project without id success", "false", mockUpdatableView.get_success());
        assertEquals("load project without id message", LanguageText.getConstant("ERROR_OCCURED"), mockUpdatableView.get_message());
    }

    @Test
    public void testLoadEditProjectFields_success() throws Exception {
        project_controller = new Project_controller(mockUpdatableView);
        project_controller.createProject("name", "0", "1/1/1111", "1/1/1112", "description");
        mockUpdatableView.reset_default();
        project_controller.loadEditProjectFields(1);
        assertEquals("load edit project success method", "updateLoadEditProjectFields", mockUpdatableView.get_called_method());
        assertEquals("load edit project success success", "true", mockUpdatableView.get_success());
        assertEquals("load edit project success message", null, mockUpdatableView.get_message());
    }

    @Test
    public void testLoadEditProjectFields_without_id() throws Exception {
        project_controller = new Project_controller(mockUpdatableView);
        project_controller.loadEditProjectFields(9999);
        assertEquals("load edit project with invalid project id method", "updateLoadEditProjectFields", mockUpdatableView.get_called_method());
        assertEquals("load edit project with invalid project id success", "false", mockUpdatableView.get_success());
        assertEquals("load edit project with invalid project id message", LanguageText.getConstant("ERROR_OCCURED"), mockUpdatableView.get_message());
    }

    @Test
    public void testInsertNewProject_A_0() throws Exception {
        Project projectA = new Project("A", 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("A", projectB.getProjectName());
        assertEquals(new Double(0d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testInsertNewProject_ZZZZZZZZZZZZZZZZZZZZZZZZZ_0() throws Exception {
        Project projectA = new Project("ZZZZZZZZZZZZZZZZZZZZZZZZZ", 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZZZ", projectB.getProjectName());
        assertEquals(new Double(0d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testInsertNewProject_AB_0() throws Exception {
        Project projectA = new Project("AB", 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("AB", projectB.getProjectName());
        assertEquals(new Double(0d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testInsertNewProject_ZZZZZZZZZZZZZZZZZZZZZZZY_0() throws Exception {
        Project projectA = new Project("ZZZZZZZZZZZZZZZZZZZZZZZY", 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZY", projectB.getProjectName());
        assertEquals(new Double(0d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testInsertNewProject_PROJECT_0() throws Exception {
        Project projectA = new Project("PROJECT", 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("PROJECT", projectB.getProjectName());
        assertEquals(new Double(0d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testInsertNewProject_0_0() throws Exception {
        Project projectA = new Project("0", 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("0", projectB.getProjectName());
        assertEquals(new Double(0d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testInsertNewProject_1234567890123456789012345_0() throws Exception {
        Project projectA = new Project("1234567890123456789012345", 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("1234567890123456789012345", projectB.getProjectName());
        assertEquals(new Double(0d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testInsertNewProject_01_0() throws Exception {
        Project projectA = new Project("01", 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("01", projectB.getProjectName());
        assertEquals(new Double(0d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testInsertNewProject_123456789012345678901234_0() throws Exception {
        Project projectA = new Project("123456789012345678901234", 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("123456789012345678901234", projectB.getProjectName());
        assertEquals(new Double(0d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testInsertNewProject_125845_0() throws Exception {
        Project projectA = new Project("125845", 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("125845", projectB.getProjectName());
        assertEquals(new Double(0d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testInsertNewProject_PROJECT125845_0() throws Exception {
        Project projectA = new Project("PROJECT125845", 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("PROJECT125845", projectB.getProjectName());
        assertEquals(new Double(0d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testInsertNewProject_A_1000000000() throws Exception {
        Project projectA = new Project("A", 1000000000d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("A", projectB.getProjectName());
        assertEquals(new Double(1000000000d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testInsertNewProject_ZZZZZZZZZZZZZZZZZZZZZZZZZ_1000000000() throws Exception {
        Project projectA = new Project("ZZZZZZZZZZZZZZZZZZZZZZZZZ", 1000000000d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZZZ", projectB.getProjectName());
        assertEquals(new Double(1000000000d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testInsertNewProject_AB_1000000000() throws Exception {
        Project projectA = new Project("AB", 1000000000d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("AB", projectB.getProjectName());
        assertEquals(new Double(1000000000d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testInsertNewProject_ZZZZZZZZZZZZZZZZZZZZZZZZY_1000000000() throws Exception {
        Project projectA = new Project("ZZZZZZZZZZZZZZZZZZZZZZZZY", 1000000000d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZZY", projectB.getProjectName());
        assertEquals(new Double(1000000000d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testInsertNewProject_PROJECT_1000000000() throws Exception {
        Project projectA = new Project("PROJECT", 1000000000d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("PROJECT", projectB.getProjectName());
        assertEquals(new Double(1000000000d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testInsertNewProject_125845_1000000000() throws Exception {
        Project projectA = new Project("125845", 1000000000d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("125845", projectB.getProjectName());
        assertEquals(new Double(1000000000d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testInsertNewProject_PROJECT125845_1000000000() throws Exception {
        Project projectA = new Project("PROJECT125845", 1000000000d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("PROJECT125845", projectB.getProjectName());
        assertEquals(new Double(1000000000d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testInsertNewProject_PROJECT_1() throws Exception {
        Project projectA = new Project("PROJECT", 1d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("PROJECT", projectB.getProjectName());
        assertEquals(new Double(1d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testInsertNewProject_125845_1() throws Exception {
        Project projectA = new Project("125845", 1d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("125845", projectB.getProjectName());
        assertEquals(new Double(1d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testInsertNewProject_PROJECT125845_1() throws Exception {
        Project projectA = new Project("PROJECT125845", 1d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("PROJECT125845", projectB.getProjectName());
        assertEquals(new Double(1d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testInsertNewProject_PROJECT_999999999() throws Exception {
        Project projectA = new Project("PROJECT", 999999999d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("PROJECT", projectB.getProjectName());
        assertEquals(new Double(999999999d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testInsertNewProject_125845_999999999() throws Exception {
        Project projectA = new Project("125845", 999999999d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("125845", projectB.getProjectName());
        assertEquals(new Double(999999999d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testInsertNewProject_PROJECT125845_999999999() throws Exception {
        Project projectA = new Project("PROJECT125845", 999999999d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("PROJECT125845", projectB.getProjectName());
        assertEquals(new Double(999999999d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testInsertNewProject_AB_1() throws Exception {
        Project projectA = new Project("AB", 1d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("AB", projectB.getProjectName());
        assertEquals(new Double(1d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testInsertNewProject_12_1() throws Exception {
        Project projectA = new Project("12", 1d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("12", projectB.getProjectName());
        assertEquals(new Double(1d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testInsertNewProject_ZZZZZZZZZZZZZZZZZZZZZZZY_999999999() throws Exception {
        Project projectA = new Project("ZZZZZZZZZZZZZZZZZZZZZZZY", 999999999d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZY", projectB.getProjectName());
        assertEquals(new Double(999999999d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testInsertNewProject_123456789012345678901234_999999999() throws Exception {
        Project projectA = new Project("123456789012345678901234", 999999999d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("123456789012345678901234", projectB.getProjectName());
        assertEquals(new Double(999999999d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testInsertNewProject_AB_999999999() throws Exception {
        Project projectA = new Project("AB", 999999999d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("AB", projectB.getProjectName());
        assertEquals(new Double(999999999d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testInsertNewProject_12_999999999() throws Exception {
        Project projectA = new Project("12", 999999999d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("12", projectB.getProjectName());
        assertEquals(new Double(999999999d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testInsertNewProject_ZZZZZZZZZZZZZZZZZZZZZZZY_1() throws Exception {
        Project projectA = new Project("ZZZZZZZZZZZZZZZZZZZZZZZY", 1d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZY", projectB.getProjectName());
        assertEquals(new Double(1d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testInsertNewProject_123456789012345678901234_1() throws Exception {
        Project projectA = new Project("123456789012345678901234", 1d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("123456789012345678901234", projectB.getProjectName());
        assertEquals(new Double(1d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testUpdateProject_A_0() throws Exception {
        Project projectA = new Project("name", 0.0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        projectA = project_model.getProjectById(insertedProjectID);
        projectA.setProjectName("A");
        projectA.setBudget(0d);
        project_model.updateProject(projectA);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("A", projectB.getProjectName());
        assertEquals(new Double(0d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testUpdateProject_ZZZZZZZZZZZZZZZZZZZZZZZZZ_0() throws Exception {
        Project projectA = new Project("name", 0.0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        projectA = project_model.getProjectById(insertedProjectID);
        projectA.setProjectName("ZZZZZZZZZZZZZZZZZZZZZZZZZ");
        projectA.setBudget(0d);
        project_model.updateProject(projectA);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZZZ", projectB.getProjectName());
        assertEquals(new Double(0d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testUpdateProject_AB_0() throws Exception {
        Project projectA = new Project("name", 0.0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        projectA = project_model.getProjectById(insertedProjectID);
        projectA.setProjectName("AB");
        projectA.setBudget(0d);
        project_model.updateProject(projectA);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("AB", projectB.getProjectName());
        assertEquals(new Double(0d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testUpdateProject_ZZZZZZZZZZZZZZZZZZZZZZZY_0() throws Exception {
        Project projectA = new Project("name", 0.0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        projectA = project_model.getProjectById(insertedProjectID);
        projectA.setProjectName("ZZZZZZZZZZZZZZZZZZZZZZZY");
        projectA.setBudget(0d);
        project_model.updateProject(projectA);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZY", projectB.getProjectName());
        assertEquals(new Double(0d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testUpdateProject_PROJECT_0() throws Exception {
        Project projectA = new Project("name", 0.0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        projectA = project_model.getProjectById(insertedProjectID);
        projectA.setProjectName("PROJECT");
        projectA.setBudget(0d);
        project_model.updateProject(projectA);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("PROJECT", projectB.getProjectName());
        assertEquals(new Double(0d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testUpdateProject_0_0() throws Exception {
        Project projectA = new Project("name", 0.0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        projectA = project_model.getProjectById(insertedProjectID);
        projectA.setProjectName("0");
        projectA.setBudget(0d);
        project_model.updateProject(projectA);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("0", projectB.getProjectName());
        assertEquals(new Double(0d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testUpdateProject_1234567890123456789012345_0() throws Exception {
        Project projectA = new Project("name", 0.0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        projectA = project_model.getProjectById(insertedProjectID);
        projectA.setProjectName("1234567890123456789012345");
        projectA.setBudget(0d);
        project_model.updateProject(projectA);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("1234567890123456789012345", projectB.getProjectName());
        assertEquals(new Double(0d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testUpdateProject_01_0() throws Exception {
        Project projectA = new Project("name", 0.0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        projectA = project_model.getProjectById(insertedProjectID);
        projectA.setProjectName("01");
        projectA.setBudget(0d);
        project_model.updateProject(projectA);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("01", projectB.getProjectName());
        assertEquals(new Double(0d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testUpdateProject_123456789012345678901234_0() throws Exception {
        Project projectA = new Project("name", 0.0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        projectA = project_model.getProjectById(insertedProjectID);
        projectA.setProjectName("123456789012345678901234");
        projectA.setBudget(0d);
        project_model.updateProject(projectA);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("123456789012345678901234", projectB.getProjectName());
        assertEquals(new Double(0d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testUpdateProject_125845_0() throws Exception {
        Project projectA = new Project("name", 0.0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        projectA = project_model.getProjectById(insertedProjectID);
        projectA.setProjectName("125845");
        projectA.setBudget(0d);
        project_model.updateProject(projectA);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("125845", projectB.getProjectName());
        assertEquals(new Double(0d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testUpdateProject_PROJECT125845_0() throws Exception {
        Project projectA = new Project("name", 0.0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        projectA = project_model.getProjectById(insertedProjectID);
        projectA.setProjectName("PROJECT125845");
        projectA.setBudget(0d);
        project_model.updateProject(projectA);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("PROJECT125845", projectB.getProjectName());
        assertEquals(new Double(0d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testUpdateProject_A_1000000000() throws Exception {
        Project projectA = new Project("name", 0.0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        projectA = project_model.getProjectById(insertedProjectID);
        projectA.setProjectName("A");
        projectA.setBudget(1000000000d);
        project_model.updateProject(projectA);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("A", projectB.getProjectName());
        assertEquals(new Double(1000000000d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testUpdateProject_ZZZZZZZZZZZZZZZZZZZZZZZZZ_1000000000() throws Exception {
        Project projectA = new Project("name", 0.0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        projectA = project_model.getProjectById(insertedProjectID);
        projectA.setProjectName("ZZZZZZZZZZZZZZZZZZZZZZZZZ");
        projectA.setBudget(1000000000d);
        project_model.updateProject(projectA);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZZZ", projectB.getProjectName());
        assertEquals(new Double(1000000000d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testUpdateProject_AB_1000000000() throws Exception {
        Project projectA = new Project("name", 0.0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        projectA = project_model.getProjectById(insertedProjectID);
        projectA.setProjectName("AB");
        projectA.setBudget(1000000000d);
        project_model.updateProject(projectA);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("AB", projectB.getProjectName());
        assertEquals(new Double(1000000000d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testUpdateProject_ZZZZZZZZZZZZZZZZZZZZZZZZY_1000000000() throws Exception {
        Project projectA = new Project("name", 0.0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        projectA = project_model.getProjectById(insertedProjectID);
        projectA.setProjectName("ZZZZZZZZZZZZZZZZZZZZZZZZY");
        projectA.setBudget(1000000000d);
        project_model.updateProject(projectA);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZZY", projectB.getProjectName());
        assertEquals(new Double(1000000000d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testUpdateProject_PROJECT_1000000000() throws Exception {
        Project projectA = new Project("name", 0.0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        projectA = project_model.getProjectById(insertedProjectID);
        projectA.setProjectName("PROJECT");
        projectA.setBudget(1000000000d);
        project_model.updateProject(projectA);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("PROJECT", projectB.getProjectName());
        assertEquals(new Double(1000000000d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testUpdateProject_125845_1000000000() throws Exception {
        Project projectA = new Project("name", 0.0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        projectA = project_model.getProjectById(insertedProjectID);
        projectA.setProjectName("125845");
        projectA.setBudget(1000000000d);
        project_model.updateProject(projectA);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("125845", projectB.getProjectName());
        assertEquals(new Double(1000000000d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testUpdateProject_PROJECT125845_1000000000() throws Exception {
        Project projectA = new Project("name", 0.0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        projectA = project_model.getProjectById(insertedProjectID);
        projectA.setProjectName("PROJECT125845");
        projectA.setBudget(1000000000d);
        project_model.updateProject(projectA);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("PROJECT125845", projectB.getProjectName());
        assertEquals(new Double(1000000000d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testUpdateProject_PROJECT_1() throws Exception {
        Project projectA = new Project("name", 0.0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        projectA = project_model.getProjectById(insertedProjectID);
        projectA.setProjectName("PROJECT");
        projectA.setBudget(1d);
        project_model.updateProject(projectA);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("PROJECT", projectB.getProjectName());
        assertEquals(new Double(1d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testUpdateProject_125845_1() throws Exception {
        Project projectA = new Project("name", 0.0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        projectA = project_model.getProjectById(insertedProjectID);
        projectA.setProjectName("125845");
        projectA.setBudget(1d);
        project_model.updateProject(projectA);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("125845", projectB.getProjectName());
        assertEquals(new Double(1d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testUpdateProject_PROJECT125845_1() throws Exception {
        Project projectA = new Project("name", 0.0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        projectA = project_model.getProjectById(insertedProjectID);
        projectA.setProjectName("PROJECT125845");
        projectA.setBudget(1d);
        project_model.updateProject(projectA);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("PROJECT125845", projectB.getProjectName());
        assertEquals(new Double(1d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testUpdateProject_PROJECT_999999999() throws Exception {
        Project projectA = new Project("name", 0.0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        projectA = project_model.getProjectById(insertedProjectID);
        projectA.setProjectName("PROJECT");
        projectA.setBudget(999999999d);
        project_model.updateProject(projectA);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("PROJECT", projectB.getProjectName());
        assertEquals(new Double(999999999d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testUpdateProject_125845_999999999() throws Exception {
        Project projectA = new Project("name", 0.0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        projectA = project_model.getProjectById(insertedProjectID);
        projectA.setProjectName("125845");
        projectA.setBudget(999999999d);
        project_model.updateProject(projectA);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("125845", projectB.getProjectName());
        assertEquals(new Double(999999999d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testUpdateProject_PROJECT125845_999999999() throws Exception {
        Project projectA = new Project("name", 0.0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        projectA = project_model.getProjectById(insertedProjectID);
        projectA.setProjectName("PROJECT125845");
        projectA.setBudget(999999999d);
        project_model.updateProject(projectA);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("PROJECT125845", projectB.getProjectName());
        assertEquals(new Double(999999999d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testUpdateProject_AB_1() throws Exception {
        Project projectA = new Project("name", 0.0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        projectA = project_model.getProjectById(insertedProjectID);
        projectA.setProjectName("AB");
        projectA.setBudget(1d);
        project_model.updateProject(projectA);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("AB", projectB.getProjectName());
        assertEquals(new Double(1d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testUpdateProject_12_1() throws Exception {
        Project projectA = new Project("name", 0.0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        projectA = project_model.getProjectById(insertedProjectID);
        projectA.setProjectName("12");
        projectA.setBudget(1d);
        project_model.updateProject(projectA);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("12", projectB.getProjectName());
        assertEquals(new Double(1d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testUpdateProject_ZZZZZZZZZZZZZZZZZZZZZZZY_999999999() throws Exception {
        Project projectA = new Project("name", 0.0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        projectA = project_model.getProjectById(insertedProjectID);
        projectA.setProjectName("ZZZZZZZZZZZZZZZZZZZZZZZY");
        projectA.setBudget(999999999d);
        project_model.updateProject(projectA);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZY", projectB.getProjectName());
        assertEquals(new Double(999999999d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testUpdateProject_123456789012345678901234_999999999() throws Exception {
        Project projectA = new Project("name", 0.0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        projectA = project_model.getProjectById(insertedProjectID);
        projectA.setProjectName("123456789012345678901234");
        projectA.setBudget(999999999d);
        project_model.updateProject(projectA);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("123456789012345678901234", projectB.getProjectName());
        assertEquals(new Double(999999999d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testUpdateProject_AB_999999999() throws Exception {
        Project projectA = new Project("name", 0.0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        projectA = project_model.getProjectById(insertedProjectID);
        projectA.setProjectName("AB");
        projectA.setBudget(999999999d);
        project_model.updateProject(projectA);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("AB", projectB.getProjectName());
        assertEquals(new Double(999999999d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testUpdateProject_12_999999999() throws Exception {
        Project projectA = new Project("name", 0.0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        projectA = project_model.getProjectById(insertedProjectID);
        projectA.setProjectName("12");
        projectA.setBudget(999999999d);
        project_model.updateProject(projectA);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("12", projectB.getProjectName());
        assertEquals(new Double(999999999d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testUpdateProject_ZZZZZZZZZZZZZZZZZZZZZZZY_1() throws Exception {
        Project projectA = new Project("name", 0.0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        projectA = project_model.getProjectById(insertedProjectID);
        projectA.setProjectName("ZZZZZZZZZZZZZZZZZZZZZZZY");
        projectA.setBudget(1d);
        project_model.updateProject(projectA);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZY", projectB.getProjectName());
        assertEquals(new Double(1d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }

    @Test
    public void testUpdateProject_123456789012345678901234_1() throws Exception {
        Project projectA = new Project("name", 0.0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), "description");
        int insertedProjectID = project_model.insertNewProject(projectA);
        assertTrue(insertedProjectID != Database.ERROR);
        projectA = project_model.getProjectById(insertedProjectID);
        projectA.setProjectName("123456789012345678901234");
        projectA.setBudget(1d);
        project_model.updateProject(projectA);
        Project projectB = project_model.getProjectById(insertedProjectID);
        assertEquals("123456789012345678901234", projectB.getProjectName());
        assertEquals(new Double(1d), new Double(projectB.getBudget()));
        if (insertedProjectID != Database.ERROR) {
            project_model.deleteProjectById(insertedProjectID);
        }
    }
}
