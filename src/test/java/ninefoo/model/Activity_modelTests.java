package ninefoo.model;

import ninefoo.config.Config;
import ninefoo.helper.DateHelper;
import ninefoo.model.object.Activity;
import ninefoo.model.object.Member;
import ninefoo.model.object.Project;
import ninefoo.model.sql.Member_model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ninefoo.model.sql.*;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Activity_modelTests {
    private static Project_model project_Model = new Project_model();
    private static Member_model member_Model = new Member_model();
    private static Activity_model activity_Model = new Activity_model();

    private static int newProjectId, newMemberId, newActivityId;

    // Parameters used for the new activity
    private String label = "new_test_activity_label";
    private int duration = 10;
    private String startDate = "07/07/2014";
    private String finishDate = "08/08/2014";
    private double cost = 1111.11;

    @BeforeClass
    public static void setup() {
        Project project = new Project("project_for_activity", 2222.22,
                DateHelper.parse("05/05/2014", Config.DATE_FORMAT_SHORT),
                DateHelper.parse("10/10/2014", Config.DATE_FORMAT_SHORT),
                "project_for_activity_description");
        Member member = new Member("member_activity_first_name",
                "member_activity_lastname", "member_activity_username",
                "member_activity_password");
        newProjectId = project_Model.insertNewProject(project);
        newMemberId = member_Model.insertNewMember(member);
    }

    @AfterClass
    public static void tearDown() {
        project_Model.deleteProjectById(newProjectId);
        member_Model.deleteMemberById(newMemberId);
        activity_Model.deleteActivityById(newActivityId);
    }

    @Test
    public void test01_Activity_InsertActivity_ActivityIsAdded() {
//        Activity activity = new Activity(label, duration,
//                DateHelper.parse(startDate, Config.DATE_FORMAT_SHORT),
//                DateHelper.parse(finishDate, Config.DATE_FORMAT_SHORT),
//                project_Model.getProjectById(newProjectId),
//                member_Model.getMemberById(newMemberId), cost);
//        newActivityId = activity_Model.insertNewActivity(activity);
//        assertNotEquals("Inserted activity ID should not be zero", 0, newActivityId);
    }

    @Test
    // FIXME [Amir - 16-07-2015]: The original method have been modified
    public void test02_Activity_InsertActivity_ActivityParametersAreGood() {
        // Test the attributes of the activity inserted in the previous test.
//        Activity activity = activity_Model.getActivityById(newActivityId);
//        assertEquals("Activity ID should be " + newActivityId, newActivityId, activity.getActivityId());
//        assertEquals("Activity label should be " + label, label, activity.getActivityLabel());
//        assertEquals("Activity duration should be " + duration, duration, activity.getDuration());
//        assertEquals("Activity start date should be " + startDate, startDate,
//                DateHelper.format(activity.getStartDate(), Config.DATE_FORMAT_SHORT));
//        assertEquals("Activity finish date should be " + finishDate, finishDate,
//                DateHelper.format(activity.getFinishDate(), Config.DATE_FORMAT_SHORT));
//        assertEquals("Activity's project ID should be " + newProjectId, newProjectId,
//                activity.getProject().getProjectId());
//        assertEquals("Activity's member ID should be " + newMemberId, newMemberId,
//                activity.getMember().getMemberId());
//        assertEquals("Activity's cost should be " + cost, cost, activity.getCost(), 0.00001);
    }
}
