package ninefoo.controller.handler;


import ninefoo.Mocks.MockUpdatableView;
import ninefoo.config.Config;
import ninefoo.config.Session;
import ninefoo.lib.lang.LanguageText;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * Created by samuel on 2015-07-09.
 */
public class Activity_controllerTest {
    private MockUpdatableView mockUpdatableView = new MockUpdatableView();
    private Activity_controller activity_controller;
    private static Project_controller project_controller;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Config.autoload();
        MockUpdatableView mockUpdatableViewTest = new MockUpdatableView();
        project_controller = new Project_controller(mockUpdatableViewTest);
        project_controller.createProject("name", "0", "1/1/1111", "1/1/1112", "description");
    }

    @Before
    public void setUp() throws Exception {
        activity_controller = new Activity_controller(mockUpdatableView);
    }

    @Test
    public void testCreateActivity() throws Exception {
        Session.getInstance().setProjectId(1);
        activity_controller.createActivity("label", "description", "5", "6", "7", "8", "100", "11/11/1111", "12/11/1111", 1, new int[]{});
        assertEquals("create activity is successful method", "updateCreateActivity", mockUpdatableView.get_called_method());
        assertEquals("create activity is successful success", "true", mockUpdatableView.get_success());
        assertEquals("create activity is successful message", String.format(LanguageText.getConstant("CREATED"), LanguageText.getConstant("ACTIVITY_ACT")), mockUpdatableView.get_message());

    }

    @Test
    public void testCreateActivity_minimum_requirements() throws Exception {
        Session.getInstance().setProjectId(1);
        activity_controller.createActivity("label", "", "5", "", "", "", "", "11/11/1111", "12/11/1111", 1, new int[]{});
        assertEquals("create activity is successful method", "updateCreateActivity", mockUpdatableView.get_called_method());
        assertEquals("create activity is successful success", "true", mockUpdatableView.get_success());
        assertEquals("create activity is successful message", String.format(LanguageText.getConstant("CREATED"), LanguageText.getConstant("ACTIVITY_ACT")), mockUpdatableView.get_message());

    }

    @Test
    public void testEditActivity() throws Exception {
        Session.getInstance().setProjectId(1);


    }

    @Test
    public void testLoadActivitiesByProject() throws Exception {

    }

    @Test
    public void testLoadActivitiesForAllProjectByMember() throws Exception {

    }
}