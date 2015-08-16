package ninefoo.model.object;

import junit.framework.TestCase;
import ninefoo.config.Database;
import ninefoo.config.Session;
import ninefoo.helper.DateHelper;
import ninefoo.model.sql.Activity_model;
import ninefoo.model.sql.Member_model;
import ninefoo.model.sql.Project_model;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by samuel on 2015-08-15.
 * <p/>
 * Black box testing for createActiviy and updateActivity.
 */
public class ActivityTest extends TestCase {

    static Activity_model activity_model;
    static Member member;
    static Project project;
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
        activity_model = new Activity_model();
        member_model = new Member_model();
        project_model = new Project_model();
        member = member_model.getMemberById(1);
        project = project_model.getProjectById(1);
    }

    @Test
    public void testCreateActivity_A_0() throws Exception {
        Activity activityA = new Activity("A", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("A", activityB.getActivityLabel());
        assertEquals(new Double(0), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_ZZZZZZZZZZZZZZZZZZZZZZZZZ_0() throws Exception {
        Activity activityA = new Activity("ZZZZZZZZZZZZZZZZZZZZZZZZZ", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZZZ", activityB.getActivityLabel());
        assertEquals(new Double(0), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_AB_0() throws Exception {
        Activity activityA = new Activity("AB", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("AB", activityB.getActivityLabel());
        assertEquals(new Double(0), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_ZZZZZZZZZZZZZZZZZZZZZZZY_0() throws Exception {
        Activity activityA = new Activity("ZZZZZZZZZZZZZZZZZZZZZZZY", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZY", activityB.getActivityLabel());
        assertEquals(new Double(0), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_PROJECT_0() throws Exception {
        Activity activityA = new Activity("PROJECT", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("PROJECT", activityB.getActivityLabel());
        assertEquals(new Double(0), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_0_0() throws Exception {
        Activity activityA = new Activity("0", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("0", activityB.getActivityLabel());
        assertEquals(new Double(0), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_1234567890123456789012345_0() throws Exception {
        Activity activityA = new Activity("1234567890123456789012345", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("1234567890123456789012345", activityB.getActivityLabel());
        assertEquals(new Double(0), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_01_0() throws Exception {
        Activity activityA = new Activity("01", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("01", activityB.getActivityLabel());
        assertEquals(new Double(0), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_123456789012345678901234_0() throws Exception {
        Activity activityA = new Activity("123456789012345678901234", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("123456789012345678901234", activityB.getActivityLabel());
        assertEquals(new Double(0), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_125845_0() throws Exception {
        Activity activityA = new Activity("125845", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("125845", activityB.getActivityLabel());
        assertEquals(new Double(0), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_PROJECT125845_0() throws Exception {
        Activity activityA = new Activity("PROJECT125845", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("PROJECT125845", activityB.getActivityLabel());
        assertEquals(new Double(0), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_A_1000000000() throws Exception {
        Activity activityA = new Activity("A", "description", 2, 2, 2, 2, 1000000000d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("A", activityB.getActivityLabel());
        assertEquals(new Double(1000000000), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_ZZZZZZZZZZZZZZZZZZZZZZZZZ_1000000000() throws Exception {
        Activity activityA = new Activity("ZZZZZZZZZZZZZZZZZZZZZZZZZ", "description", 2, 2, 2, 2, 1000000000d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZZZ", activityB.getActivityLabel());
        assertEquals(new Double(1000000000), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_AB_1000000000() throws Exception {
        Activity activityA = new Activity("AB", "description", 2, 2, 2, 2, 1000000000d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("AB", activityB.getActivityLabel());
        assertEquals(new Double(1000000000), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_ZZZZZZZZZZZZZZZZZZZZZZZZY_1000000000() throws Exception {
        Activity activityA = new Activity("ZZZZZZZZZZZZZZZZZZZZZZZZY", "description", 2, 2, 2, 2, 1000000000d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZZY", activityB.getActivityLabel());
        assertEquals(new Double(1000000000), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_PROJECT_1000000000() throws Exception {
        Activity activityA = new Activity("PROJECT", "description", 2, 2, 2, 2, 1000000000d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("PROJECT", activityB.getActivityLabel());
        assertEquals(new Double(1000000000), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_125845_1000000000() throws Exception {
        Activity activityA = new Activity("125845", "description", 2, 2, 2, 2, 1000000000d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("125845", activityB.getActivityLabel());
        assertEquals(new Double(1000000000), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_PROJECT125845_1000000000() throws Exception {
        Activity activityA = new Activity("PROJECT125845", "description", 2, 2, 2, 2, 1000000000d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("PROJECT125845", activityB.getActivityLabel());
        assertEquals(new Double(1000000000), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_PROJECT_1() throws Exception {
        Activity activityA = new Activity("PROJECT", "description", 2, 2, 2, 2, 1d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("PROJECT", activityB.getActivityLabel());
        assertEquals(new Double(1), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_125845_1() throws Exception {
        Activity activityA = new Activity("125845", "description", 2, 2, 2, 2, 1d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("125845", activityB.getActivityLabel());
        assertEquals(new Double(1), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_PROJECT125845_1() throws Exception {
        Activity activityA = new Activity("PROJECT125845", "description", 2, 2, 2, 2, 1d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("PROJECT125845", activityB.getActivityLabel());
        assertEquals(new Double(1), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_PROJECT_999999999() throws Exception {
        Activity activityA = new Activity("PROJECT", "description", 2, 2, 2, 2, 999999999d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("PROJECT", activityB.getActivityLabel());
        assertEquals(new Double(999999999), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_125845_999999999() throws Exception {
        Activity activityA = new Activity("125845", "description", 2, 2, 2, 2, 999999999d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("125845", activityB.getActivityLabel());
        assertEquals(new Double(999999999), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_PROJECT125845_999999999() throws Exception {
        Activity activityA = new Activity("PROJECT125845", "description", 2, 2, 2, 2, 999999999d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("PROJECT125845", activityB.getActivityLabel());
        assertEquals(new Double(999999999), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_AB_1() throws Exception {
        Activity activityA = new Activity("AB", "description", 2, 2, 2, 2, 1d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("AB", activityB.getActivityLabel());
        assertEquals(new Double(1), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_12_1() throws Exception {
        Activity activityA = new Activity("12", "description", 2, 2, 2, 2, 1d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("12", activityB.getActivityLabel());
        assertEquals(new Double(1), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_ZZZZZZZZZZZZZZZZZZZZZZZY_999999999() throws Exception {
        Activity activityA = new Activity("ZZZZZZZZZZZZZZZZZZZZZZZY", "description", 2, 2, 2, 2, 999999999d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZY", activityB.getActivityLabel());
        assertEquals(new Double(999999999), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_123456789012345678901234_999999999() throws Exception {
        Activity activityA = new Activity("123456789012345678901234", "description", 2, 2, 2, 2, 999999999d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("123456789012345678901234", activityB.getActivityLabel());
        assertEquals(new Double(999999999), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_AB_999999999() throws Exception {
        Activity activityA = new Activity("AB", "description", 2, 2, 2, 2, 999999999d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("AB", activityB.getActivityLabel());
        assertEquals(new Double(999999999), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_12_999999999() throws Exception {
        Activity activityA = new Activity("12", "description", 2, 2, 2, 2, 999999999d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("12", activityB.getActivityLabel());
        assertEquals(new Double(999999999), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_ZZZZZZZZZZZZZZZZZZZZZZZY_1() throws Exception {
        Activity activityA = new Activity("ZZZZZZZZZZZZZZZZZZZZZZZY", "description", 2, 2, 2, 2, 1d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZY", activityB.getActivityLabel());
        assertEquals(new Double(1), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_123456789012345678901234_1() throws Exception {
        Activity activityA = new Activity("123456789012345678901234", "description", 2, 2, 2, 2, 1d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("123456789012345678901234", activityB.getActivityLabel());
        assertEquals(new Double(1), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testUpdateActivity_A_0() throws Exception {
        Activity activityA = new Activity("name", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        activityA = activity_model.getActivityById(insertedActivityId);
        activityA.setProject(project);
        activityA.setMember(member);
        activityA.setActivityLabel("A");
        activityA.setCost(0d);
        activity_model.updateActivity(activityA);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("A", activityB.getActivityLabel());
        assertEquals(new Double(0), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testUpdateActivity_ZZZZZZZZZZZZZZZZZZZZZZZZZ_0() throws Exception {
        Activity activityA = new Activity("name", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        activityA = activity_model.getActivityById(insertedActivityId);
        activityA.setProject(project);
        activityA.setMember(member);
        activityA.setActivityLabel("ZZZZZZZZZZZZZZZZZZZZZZZZZ");
        activityA.setCost(0d);
        activity_model.updateActivity(activityA);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZZZ", activityB.getActivityLabel());
        assertEquals(new Double(0), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testUpdateActivity_AB_0() throws Exception {
        Activity activityA = new Activity("name", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        activityA = activity_model.getActivityById(insertedActivityId);
        activityA.setProject(project);
        activityA.setMember(member);
        activityA.setActivityLabel("AB");
        activityA.setCost(0d);
        activity_model.updateActivity(activityA);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("AB", activityB.getActivityLabel());
        assertEquals(new Double(0), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testUpdateActivity_ZZZZZZZZZZZZZZZZZZZZZZZY_0() throws Exception {
        Activity activityA = new Activity("name", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        activityA = activity_model.getActivityById(insertedActivityId);
        activityA.setProject(project);
        activityA.setMember(member);
        activityA.setActivityLabel("ZZZZZZZZZZZZZZZZZZZZZZZY");
        activityA.setCost(0d);
        activity_model.updateActivity(activityA);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZY", activityB.getActivityLabel());
        assertEquals(new Double(0), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testUpdateActivity_PROJECT_0() throws Exception {
        Activity activityA = new Activity("name", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        activityA = activity_model.getActivityById(insertedActivityId);
        activityA.setProject(project);
        activityA.setMember(member);
        activityA.setActivityLabel("PROJECT");
        activityA.setCost(0d);
        activity_model.updateActivity(activityA);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("PROJECT", activityB.getActivityLabel());
        assertEquals(new Double(0), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testUpdateActivity_0_0() throws Exception {
        Activity activityA = new Activity("name", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        activityA = activity_model.getActivityById(insertedActivityId);
        activityA.setProject(project);
        activityA.setMember(member);
        activityA.setActivityLabel("0");
        activityA.setCost(0d);
        activity_model.updateActivity(activityA);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("0", activityB.getActivityLabel());
        assertEquals(new Double(0), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testUpdateActivity_1234567890123456789012345_0() throws Exception {
        Activity activityA = new Activity("name", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        activityA = activity_model.getActivityById(insertedActivityId);
        activityA.setProject(project);
        activityA.setMember(member);
        activityA.setActivityLabel("1234567890123456789012345");
        activityA.setCost(0d);
        activity_model.updateActivity(activityA);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("1234567890123456789012345", activityB.getActivityLabel());
        assertEquals(new Double(0), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testUpdateActivity_01_0() throws Exception {
        Activity activityA = new Activity("name", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        activityA = activity_model.getActivityById(insertedActivityId);
        activityA.setProject(project);
        activityA.setMember(member);
        activityA.setActivityLabel("01");
        activityA.setCost(0d);
        activity_model.updateActivity(activityA);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("01", activityB.getActivityLabel());
        assertEquals(new Double(0), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testUpdateActivity_123456789012345678901234_0() throws Exception {
        Activity activityA = new Activity("name", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        activityA = activity_model.getActivityById(insertedActivityId);
        activityA.setProject(project);
        activityA.setMember(member);
        activityA.setActivityLabel("123456789012345678901234");
        activityA.setCost(0d);
        activity_model.updateActivity(activityA);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("123456789012345678901234", activityB.getActivityLabel());
        assertEquals(new Double(0), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testUpdateActivity_125845_0() throws Exception {
        Activity activityA = new Activity("name", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        activityA = activity_model.getActivityById(insertedActivityId);
        activityA.setProject(project);
        activityA.setMember(member);
        activityA.setActivityLabel("125845");
        activityA.setCost(0d);
        activity_model.updateActivity(activityA);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("125845", activityB.getActivityLabel());
        assertEquals(new Double(0), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testUpdateActivity_PROJECT125845_0() throws Exception {
        Activity activityA = new Activity("name", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        activityA = activity_model.getActivityById(insertedActivityId);
        activityA.setProject(project);
        activityA.setMember(member);
        activityA.setActivityLabel("PROJECT125845");
        activityA.setCost(0d);
        activity_model.updateActivity(activityA);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("PROJECT125845", activityB.getActivityLabel());
        assertEquals(new Double(0), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testUpdateActivity_A_1000000000() throws Exception {
        Activity activityA = new Activity("name", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        activityA = activity_model.getActivityById(insertedActivityId);
        activityA.setProject(project);
        activityA.setMember(member);
        activityA.setActivityLabel("A");
        activityA.setCost(1000000000d);
        activity_model.updateActivity(activityA);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("A", activityB.getActivityLabel());
        assertEquals(new Double(1000000000), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testUpdateActivity_ZZZZZZZZZZZZZZZZZZZZZZZZZ_1000000000() throws Exception {
        Activity activityA = new Activity("name", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        activityA = activity_model.getActivityById(insertedActivityId);
        activityA.setProject(project);
        activityA.setMember(member);
        activityA.setActivityLabel("ZZZZZZZZZZZZZZZZZZZZZZZZZ");
        activityA.setCost(1000000000d);
        activity_model.updateActivity(activityA);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZZZ", activityB.getActivityLabel());
        assertEquals(new Double(1000000000), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testUpdateActivity_AB_1000000000() throws Exception {
        Activity activityA = new Activity("name", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        activityA = activity_model.getActivityById(insertedActivityId);
        activityA.setProject(project);
        activityA.setMember(member);
        activityA.setActivityLabel("AB");
        activityA.setCost(1000000000d);
        activity_model.updateActivity(activityA);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("AB", activityB.getActivityLabel());
        assertEquals(new Double(1000000000), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testUpdateActivity_ZZZZZZZZZZZZZZZZZZZZZZZZY_1000000000() throws Exception {
        Activity activityA = new Activity("name", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        activityA = activity_model.getActivityById(insertedActivityId);
        activityA.setProject(project);
        activityA.setMember(member);
        activityA.setActivityLabel("ZZZZZZZZZZZZZZZZZZZZZZZZY");
        activityA.setCost(1000000000d);
        activity_model.updateActivity(activityA);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZZY", activityB.getActivityLabel());
        assertEquals(new Double(1000000000), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testUpdateActivity_PROJECT_1000000000() throws Exception {
        Activity activityA = new Activity("name", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        activityA = activity_model.getActivityById(insertedActivityId);
        activityA.setProject(project);
        activityA.setMember(member);
        activityA.setActivityLabel("PROJECT");
        activityA.setCost(1000000000d);
        activity_model.updateActivity(activityA);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("PROJECT", activityB.getActivityLabel());
        assertEquals(new Double(1000000000), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testUpdateActivity_125845_1000000000() throws Exception {
        Activity activityA = new Activity("name", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        activityA = activity_model.getActivityById(insertedActivityId);
        activityA.setProject(project);
        activityA.setMember(member);
        activityA.setActivityLabel("125845");
        activityA.setCost(1000000000d);
        activity_model.updateActivity(activityA);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("125845", activityB.getActivityLabel());
        assertEquals(new Double(1000000000), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testUpdateActivity_PROJECT125845_1000000000() throws Exception {
        Activity activityA = new Activity("name", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        activityA = activity_model.getActivityById(insertedActivityId);
        activityA.setProject(project);
        activityA.setMember(member);
        activityA.setActivityLabel("PROJECT125845");
        activityA.setCost(1000000000d);
        activity_model.updateActivity(activityA);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("PROJECT125845", activityB.getActivityLabel());
        assertEquals(new Double(1000000000), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testUpdateActivity_PROJECT_1() throws Exception {
        Activity activityA = new Activity("name", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        activityA = activity_model.getActivityById(insertedActivityId);
        activityA.setProject(project);
        activityA.setMember(member);
        activityA.setActivityLabel("PROJECT");
        activityA.setCost(1d);
        activity_model.updateActivity(activityA);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("PROJECT", activityB.getActivityLabel());
        assertEquals(new Double(1), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testUpdateActivity_125845_1() throws Exception {
        Activity activityA = new Activity("name", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        activityA = activity_model.getActivityById(insertedActivityId);
        activityA.setProject(project);
        activityA.setMember(member);
        activityA.setActivityLabel("125845");
        activityA.setCost(1d);
        activity_model.updateActivity(activityA);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("125845", activityB.getActivityLabel());
        assertEquals(new Double(1), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testUpdateActivity_PROJECT125845_1() throws Exception {
        Activity activityA = new Activity("name", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        activityA = activity_model.getActivityById(insertedActivityId);
        activityA.setProject(project);
        activityA.setMember(member);
        activityA.setActivityLabel("PROJECT125845");
        activityA.setCost(1d);
        activity_model.updateActivity(activityA);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("PROJECT125845", activityB.getActivityLabel());
        assertEquals(new Double(1), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testUpdateActivity_PROJECT_999999999() throws Exception {
        Activity activityA = new Activity("name", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        activityA = activity_model.getActivityById(insertedActivityId);
        activityA.setProject(project);
        activityA.setMember(member);
        activityA.setActivityLabel("PROJECT");
        activityA.setCost(999999999d);
        activity_model.updateActivity(activityA);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("PROJECT", activityB.getActivityLabel());
        assertEquals(new Double(999999999), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testUpdateActivity_125845_999999999() throws Exception {
        Activity activityA = new Activity("name", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        activityA = activity_model.getActivityById(insertedActivityId);
        activityA.setProject(project);
        activityA.setMember(member);
        activityA.setActivityLabel("125845");
        activityA.setCost(999999999d);
        activity_model.updateActivity(activityA);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("125845", activityB.getActivityLabel());
        assertEquals(new Double(999999999), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testUpdateActivity_PROJECT125845_999999999() throws Exception {
        Activity activityA = new Activity("name", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        activityA = activity_model.getActivityById(insertedActivityId);
        activityA.setProject(project);
        activityA.setMember(member);
        activityA.setActivityLabel("PROJECT125845");
        activityA.setCost(999999999d);
        activity_model.updateActivity(activityA);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("PROJECT125845", activityB.getActivityLabel());
        assertEquals(new Double(999999999), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testUpdateActivity_AB_1() throws Exception {
        Activity activityA = new Activity("name", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        activityA = activity_model.getActivityById(insertedActivityId);
        activityA.setProject(project);
        activityA.setMember(member);
        activityA.setActivityLabel("AB");
        activityA.setCost(1d);
        activity_model.updateActivity(activityA);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("AB", activityB.getActivityLabel());
        assertEquals(new Double(1), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testUpdateActivity_12_1() throws Exception {
        Activity activityA = new Activity("name", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        activityA = activity_model.getActivityById(insertedActivityId);
        activityA.setProject(project);
        activityA.setMember(member);
        activityA.setActivityLabel("12");
        activityA.setCost(1d);
        activity_model.updateActivity(activityA);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("12", activityB.getActivityLabel());
        assertEquals(new Double(1), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testUpdateActivity_ZZZZZZZZZZZZZZZZZZZZZZZY_999999999() throws Exception {
        Activity activityA = new Activity("name", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        activityA = activity_model.getActivityById(insertedActivityId);
        activityA.setProject(project);
        activityA.setMember(member);
        activityA.setActivityLabel("ZZZZZZZZZZZZZZZZZZZZZZZY");
        activityA.setCost(999999999d);
        activity_model.updateActivity(activityA);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZY", activityB.getActivityLabel());
        assertEquals(new Double(999999999), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testUpdateActivity_123456789012345678901234_999999999() throws Exception {
        Activity activityA = new Activity("name", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        activityA = activity_model.getActivityById(insertedActivityId);
        activityA.setProject(project);
        activityA.setMember(member);
        activityA.setActivityLabel("123456789012345678901234");
        activityA.setCost(999999999d);
        activity_model.updateActivity(activityA);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("123456789012345678901234", activityB.getActivityLabel());
        assertEquals(new Double(999999999), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testUpdateActivity_AB_999999999() throws Exception {
        Activity activityA = new Activity("name", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        activityA = activity_model.getActivityById(insertedActivityId);
        activityA.setProject(project);
        activityA.setMember(member);
        activityA.setActivityLabel("AB");
        activityA.setCost(999999999d);
        activity_model.updateActivity(activityA);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("AB", activityB.getActivityLabel());
        assertEquals(new Double(999999999), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testUpdateActivity_12_999999999() throws Exception {
        Activity activityA = new Activity("name", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        activityA = activity_model.getActivityById(insertedActivityId);
        activityA.setProject(project);
        activityA.setMember(member);
        activityA.setActivityLabel("12");
        activityA.setCost(999999999d);
        activity_model.updateActivity(activityA);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("12", activityB.getActivityLabel());
        assertEquals(new Double(999999999), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testUpdateActivity_ZZZZZZZZZZZZZZZZZZZZZZZY_1() throws Exception {
        Activity activityA = new Activity("name", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        activityA = activity_model.getActivityById(insertedActivityId);
        activityA.setProject(project);
        activityA.setMember(member);
        activityA.setActivityLabel("ZZZZZZZZZZZZZZZZZZZZZZZY");
        activityA.setCost(1d);
        activity_model.updateActivity(activityA);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZY", activityB.getActivityLabel());
        assertEquals(new Double(1), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testUpdateActivity_123456789012345678901234_1() throws Exception {
        Activity activityA = new Activity("name", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", ninefoo.config.Config.DATE_FORMAT_SHORT), 1, 1, null);
        activityA.setProject(project);
        activityA.setMember(member);
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != Database.ERROR);
        activityA = activity_model.getActivityById(insertedActivityId);
        activityA.setProject(project);
        activityA.setMember(member);
        activityA.setActivityLabel("123456789012345678901234");
        activityA.setCost(1d);
        activity_model.updateActivity(activityA);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("123456789012345678901234", activityB.getActivityLabel());
        assertEquals(new Double(1), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != Database.ERROR) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }
}