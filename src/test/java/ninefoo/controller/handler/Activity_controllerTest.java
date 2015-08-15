package ninefoo.controller.handler;


import ninefoo.Mocks.MockUpdatableView;
import ninefoo.config.Config;
import ninefoo.config.Session;
import ninefoo.helper.DateHelper;
import ninefoo.lib.lang.LanguageText;
import ninefoo.model.object.Activity;
import ninefoo.model.object.Member;
import ninefoo.model.object.Project;
import ninefoo.model.sql.Activity_model;
import ninefoo.model.sql.Member_model;
import ninefoo.model.sql.Project_model;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by samuel on 2015-07-09.
 */
public class Activity_controllerTest {
    private MockUpdatableView mockUpdatableView = new MockUpdatableView();
    private Activity_controller activity_controller;
    private static Project_controller project_controller;
    private static Activity_model activity_model;
    private static Activity activity1;
    private static Activity activity2;
    private static int activityID1;
    private static int activityID2;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Config.autoload();
        MockUpdatableView mockUpdatableViewTest = new MockUpdatableView();
        project_controller = new Project_controller(mockUpdatableViewTest);
        project_controller.createProject("name", "0", "1/1/1111", "1/1/1112", "description");
        activity_model = new Activity_model();

        Member_model member_model = new Member_model();
        Project_model project_model = new Project_model();
        Member member = member_model.getMemberById(1);
        Project project = project_model.getProjectById(Session.getInstance().getProjectId());

        // create activity
        activity1 = new Activity("label1", "description", 2, 2, 2, 2, 2.0,
                DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT),
                DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT),
                Session.getInstance().getProjectId(), 1, null);
        // Set project
        activity1.setProject(project);
        // Set member
        activity1.setMember(member);
        //insert
        activityID1 = activity_model.insertNewActivity(activity1);

        // create activity
        activity2 = new Activity("label2", "description", 2, 2, 2, 2, 2.0,
                DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT),
                DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT),
                Session.getInstance().getProjectId(), 1, null);
        // Set project
        activity2.setProject(project);
        // Set member
        activity2.setMember(member);
        List<Activity> activitiesList = new ArrayList<>();
        activitiesList.add(activity1);
        activity2.setPrerequisites(activitiesList);
        //insert
        activityID2 = activity_model.insertNewActivity(activity2);
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
    public void testEditActivity_self_dependant_activity() throws Exception {
        Session.getInstance().setProjectId(1);
        activity_controller.editActivity(activityID1, "label", "", "5", "", "", "", "", "11/11/1111", "12/11/1111", 1, new int[]{activityID1}, "", "");
        assertEquals("edit activity with prereqs that are dependant on themselves method", "updateEditActivity", mockUpdatableView.get_called_method());
        assertEquals("edit activity with prereqs that are dependant on themselves success", "false", mockUpdatableView.get_success());
        assertEquals("edit activity with prereqs that are dependant on themselves message", LanguageText.getConstant("SELF_DEPENDENT_ACTIVITY"), mockUpdatableView.get_message());
    }

    @Test
    public void testLoadActivity_unknown_activity() throws Exception {
        Session.getInstance().setProjectId(1);
        activity_controller.loadActivity(999);
        assertEquals("load unknown activity method", "updateLoadActivity", mockUpdatableView.get_called_method());
        assertEquals("load unknown activity success", "false", mockUpdatableView.get_success());
        assertEquals("load unknown activity message", LanguageText.getConstant("ERROR_OCCURED"), mockUpdatableView.get_message());
    }
}