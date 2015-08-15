package ninefoo.controller.handler;

import ninefoo.Mocks.MockUpdatableView;
import ninefoo.config.Config;
import ninefoo.lib.lang.LanguageText;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by samuel on 2015-07-10.
 */
public class Project_controllerTest {
    private MockUpdatableView mockUpdatableView = new MockUpdatableView();
    private Project_controller project_controller;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Config.autoload();
    }

    @Before
    public void setUp() throws Exception {
        project_controller = new Project_controller(mockUpdatableView);
    }

    @Test
    public void testCreateProject_successful() throws Exception {
        project_controller.createProject("name", "0", "1/1/1111", "1/1/1112", "description");
        assertEquals("project is successful method", "updateCreateProject", mockUpdatableView.get_called_method());
        assertEquals("project is successful success", "true", mockUpdatableView.get_success());
        assertEquals("project is successful message", String.format(LanguageText.getConstant("CREATED"), LanguageText.getConstant("PROJECT")), mockUpdatableView.get_message());
    }

    @Test
    public void testCreateProject_no_name() throws Exception {
        project_controller.createProject("", "0", "1/1/1112", "1/1/1112", "description");
        assertEquals("project has no name method", "updateCreateProject", mockUpdatableView.get_called_method());
        assertEquals("project has no name success", "false", mockUpdatableView.get_success());
        assertEquals("project has no name message", String.format(LanguageText.getConstant("REQUIRED"), LanguageText.getConstant("NAME")), mockUpdatableView.get_message());
    }

    @Test
    public void testCreateProject_no_description() throws Exception {
        project_controller.createProject("foo", "0", "1/1/1112", "1/1/1115", "");
        assertEquals("project has no description method", "updateCreateProject", mockUpdatableView.get_called_method());
        assertEquals("project has no description success", "true", mockUpdatableView.get_success());
        assertEquals("project has no description message", String.format(LanguageText.getConstant("CREATED"), LanguageText.getConstant("PROJECT")), mockUpdatableView.get_message());
    }

    @Test
    public void testCreateProject_invalid_budget() throws Exception {
        project_controller.createProject("foo", "0periwoq", "1/1/1112", "1/1/1115", "");
        assertEquals("project has invalid budget method", "updateCreateProject", mockUpdatableView.get_called_method());
        assertEquals("project has invalid budget success", "false", mockUpdatableView.get_success());
        assertEquals("project has invalid budget message", String.format(LanguageText.getConstant("WRONG_FORMAT"), LanguageText.getConstant("BUDGET")), mockUpdatableView.get_message());
    }

    @Test
    public void testCreateProject_end_earlier_than_start() throws Exception {
        project_controller.createProject("foo", "0", "1/1/1114", "1/1/1112", "description");
        assertEquals("project deadline do not make sense method", "updateCreateProject", mockUpdatableView.get_called_method());
        assertEquals("project deadline do not make sense success", "false", mockUpdatableView.get_success());
        assertEquals("project deadline do not make sense message", String.format(LanguageText.getConstant("WRONG_DATE_BEFORE_DATE"), LanguageText.getConstant("START_DATE"), "1/1/1112"), mockUpdatableView.get_message());
    }

    @Test
    public void testCreateProject_multiple_errors() throws Exception {
        project_controller.createProject("", "0", "1/1/1112", "1/1/1110", "description");
        assertEquals("project has multiple errors method", "updateCreateProject", mockUpdatableView.get_called_method());
        assertEquals("project has multiple errors success", "false", mockUpdatableView.get_success());
        assertEquals("project has multiple errors message", String.format(LanguageText.getConstant("REQUIRED"), LanguageText.getConstant("NAME")) + "<br>" + String.format(LanguageText.getConstant("WRONG_DATE_BEFORE_DATE"), LanguageText.getConstant("START_DATE"), "1/1/1110"), mockUpdatableView.get_message());
    }

    @Test
    public void testEditProject_success() throws Exception {
        project_controller.createProject("name", "0", "1/1/1111", "1/1/1112", "description");
        mockUpdatableView.reset_default();
        project_controller.editProject(1, "name", "0", "1/1/1111", "1/1/1112", "description");
        assertEquals("edit project is successful method", "updateEditProject", mockUpdatableView.get_called_method());
        assertEquals("edit project is successful success", "true", mockUpdatableView.get_success());
        assertEquals("edit project is successful message", String.format(LanguageText.getConstant("UPDATED"), LanguageText.getConstant("PROJECT")), mockUpdatableView.get_message());
    }

    @Test
    public void testEditProject_end_earlier_than_start() throws Exception {
        project_controller.createProject("name", "0", "1/1/1111", "1/1/1112", "description");
        mockUpdatableView.reset_default();
        project_controller.editProject(1, "foo", "0", "1/1/1114", "1/1/1112", "description");
        assertEquals("edit project with date that does not make sense method", "updateEditProject", mockUpdatableView.get_called_method());
        assertEquals("edit project with date that does not make sense success", "false", mockUpdatableView.get_success());
        assertEquals("edit project with date that does not make sense message", String.format(LanguageText.getConstant("WRONG_DATE_BEFORE_DATE"), LanguageText.getConstant("START_DATE"), "1/1/1112"), mockUpdatableView.get_message());
    }

    @Test
    public void testLoadProject_success() throws Exception {
        project_controller.createProject("name", "0", "1/1/1111", "1/1/1112", "description");
        mockUpdatableView.reset_default();
        project_controller.loadProject(1);
        assertEquals("load project success method", "updateLoadProject", mockUpdatableView.get_called_method());
        assertEquals("load project success success", "true", mockUpdatableView.get_success());
        assertEquals("load project success message", String.format(LanguageText.getConstant("LOADED"), String.format("%s '%s'", LanguageText.getConstant("PROJECT"), "name")), mockUpdatableView.get_message());
    }

    @Test
    public void testLoadProject_without_id() throws Exception {
        project_controller.loadProject(99999);
        assertEquals("load project without id method", "updateLoadProject", mockUpdatableView.get_called_method());
        assertEquals("load project without id success", "false", mockUpdatableView.get_success());
        assertEquals("load project without id message", LanguageText.getConstant("ERROR_OCCURED"), mockUpdatableView.get_message());
    }

    @Test
    public void testLoadEditProjectFields_success() throws Exception {
        project_controller.createProject("name", "0", "1/1/1111", "1/1/1112", "description");
        mockUpdatableView.reset_default();
        project_controller.loadEditProjectFields(1);
        assertEquals("load edit project success method", "updateLoadEditProjectFields", mockUpdatableView.get_called_method());
        assertEquals("load edit project success success", "true", mockUpdatableView.get_success());
        assertEquals("load edit project success message", null, mockUpdatableView.get_message());
    }

    @Test
    public void testLoadEditProjectFields_without_id() throws Exception {
        project_controller.loadEditProjectFields(9999);
        assertEquals("load edit project with invalid project id method", "updateLoadEditProjectFields", mockUpdatableView.get_called_method());
        assertEquals("load edit project with invalid project id success", "false", mockUpdatableView.get_success());
        assertEquals("load edit project with invalid project id message", LanguageText.getConstant("ERROR_OCCURED"), mockUpdatableView.get_message());
    }
}