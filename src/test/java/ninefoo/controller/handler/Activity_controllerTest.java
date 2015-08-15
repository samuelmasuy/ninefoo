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
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by samuel on 2015-07-09.
 */
public class Activity_controllerTest {


    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Config.autoload();
    }

    @Before
    public void setUp() throws Exception {
    }


    @Test
    public void testCreateActivity_A_0() throws Exception {
        Session.getInstance().setProjectId(1);
        Activity activityA = new Activity("A", "description", 2, 2, 2, 2, (double)0, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), Session.getInstance().getProjectId(), 1, null);
        Session.getInstance().getUserId();
        int insertedActivityId = activity_model.insertNewActivity(activityA);
        assertTrue(insertedActivityId != 0);
        Activity activityB = activity_model.getActivityById(insertedActivityId);
        assertEquals("A", activity2.getActivityLabel());
        assertEquals(new Double(0), new Double(activityB.getPlannedCost()));
        if (insertedActivityId != 0) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_ZZZZZZZZZZZZZZZZZZZZZZZZZ_0() throws Exception {
        Session.getInstance().setProjectId(1);
        Activity activity = new Activity("ZZZZZZZZZZZZZZZZZZZZZZZZZ", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), Session.getInstance().getProjectId(), 1, null);
        int insertedActivityId = activity_model.insertNewActivity(activity);
        assertTrue(insertedActivityId != 0);
        activity2 = activity_model.getActivityById(insertedActivityId);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZZZ", activity2.getActivityLabel());
        assertEquals(new Double(0), new Double(activity2.getPlannedCost()));
        if (insertedActivityId != 0) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_AB_0() throws Exception {
        Session.getInstance().setProjectId(1);
        Activity activity = new Activity("AB", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), Session.getInstance().getProjectId(), 1, null);
        int insertedActivityId = activity_model.insertNewActivity(activity);
        assertTrue(insertedActivityId != 0);
        activity2 = activity_model.getActivityById(insertedActivityId);
        assertEquals("AB", activity2.getActivityLabel());
        assertEquals(new Double(0), new Double(activity2.getPlannedCost()));
        if (insertedActivityId != 0) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_ZZZZZZZZZZZZZZZZZZZZZZZY_0() throws Exception {
        Session.getInstance().setProjectId(1);
        Activity activity = new Activity("ZZZZZZZZZZZZZZZZZZZZZZZY", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), Session.getInstance().getProjectId(), 1, null);
        int insertedActivityId = activity_model.insertNewActivity(activity);
        assertTrue(insertedActivityId != 0);
        activity2 = activity_model.getActivityById(insertedActivityId);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZY", activity2.getActivityLabel());
        assertEquals(new Double(0), new Double(activity2.getPlannedCost()));
        if (insertedActivityId != 0) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_PROJECT_0() throws Exception {
        Session.getInstance().setProjectId(1);
        Activity activity = new Activity("PROJECT", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), Session.getInstance().getProjectId(), 1, null);
        int insertedActivityId = activity_model.insertNewActivity(activity);
        assertTrue(insertedActivityId != 0);
        activity2 = activity_model.getActivityById(insertedActivityId);
        assertEquals("PROJECT", activity2.getActivityLabel());
        assertEquals(new Double(0), new Double(activity2.getPlannedCost()));
        if (insertedActivityId != 0) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_0_0() throws Exception {
        Session.getInstance().setProjectId(1);
        Activity activity = new Activity("0", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), Session.getInstance().getProjectId(), 1, null);
        int insertedActivityId = activity_model.insertNewActivity(activity);
        assertTrue(insertedActivityId != 0);
        activity2 = activity_model.getActivityById(insertedActivityId);
        assertEquals("0", activity2.getActivityLabel());
        assertEquals(new Double(0), new Double(activity2.getPlannedCost()));
        if (insertedActivityId != 0) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_1234567890123456789012345_0() throws Exception {
        Session.getInstance().setProjectId(1);
        Activity activity = new Activity("1234567890123456789012345", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), Session.getInstance().getProjectId(), 1, null);
        int insertedActivityId = activity_model.insertNewActivity(activity);
        assertTrue(insertedActivityId != 0);
        activity2 = activity_model.getActivityById(insertedActivityId);
        assertEquals("1234567890123456789012345", activity2.getActivityLabel());
        assertEquals(new Double(0), new Double(activity2.getPlannedCost()));
        if (insertedActivityId != 0) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_01_0() throws Exception {
        Session.getInstance().setProjectId(1);
        Activity activity = new Activity("01", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), Session.getInstance().getProjectId(), 1, null);
        int insertedActivityId = activity_model.insertNewActivity(activity);
        assertTrue(insertedActivityId != 0);
        activity2 = activity_model.getActivityById(insertedActivityId);
        assertEquals("01", activity2.getActivityLabel());
        assertEquals(new Double(0), new Double(activity2.getPlannedCost()));
        if (insertedActivityId != 0) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_123456789012345678901234_0() throws Exception {
        Session.getInstance().setProjectId(1);
        Activity activity = new Activity("123456789012345678901234", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), Session.getInstance().getProjectId(), 1, null);
        int insertedActivityId = activity_model.insertNewActivity(activity);
        assertTrue(insertedActivityId != 0);
        activity2 = activity_model.getActivityById(insertedActivityId);
        assertEquals("123456789012345678901234", activity2.getActivityLabel());
        assertEquals(new Double(0), new Double(activity2.getPlannedCost()));
        if (insertedActivityId != 0) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_125845_0() throws Exception {
        Session.getInstance().setProjectId(1);
        Activity activity = new Activity("125845", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), Session.getInstance().getProjectId(), 1, null);
        int insertedActivityId = activity_model.insertNewActivity(activity);
        assertTrue(insertedActivityId != 0);
        activity2 = activity_model.getActivityById(insertedActivityId);
        assertEquals("125845", activity2.getActivityLabel());
        assertEquals(new Double(0), new Double(activity2.getPlannedCost()));
        if (insertedActivityId != 0) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_PROJECT125845_0() throws Exception {
        Session.getInstance().setProjectId(1);
        Activity activity = new Activity("PROJECT125845", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), Session.getInstance().getProjectId(), 1, null);
        int insertedActivityId = activity_model.insertNewActivity(activity);
        assertTrue(insertedActivityId != 0);
        activity2 = activity_model.getActivityById(insertedActivityId);
        assertEquals("PROJECT125845", activity2.getActivityLabel());
        assertEquals(new Double(0), new Double(activity2.getPlannedCost()));
        if (insertedActivityId != 0) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_A_1000000000() throws Exception {
        Session.getInstance().setProjectId(1);
        Activity activity = new Activity("A", "description", 2, 2, 2, 2, 1000000000d, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), Session.getInstance().getProjectId(), 1, null);
        int insertedActivityId = activity_model.insertNewActivity(activity);
        assertTrue(insertedActivityId != 0);
        activity2 = activity_model.getActivityById(insertedActivityId);
        assertEquals("A", activity2.getActivityLabel());
        assertEquals(new Double(1000000000), new Double(activity2.getPlannedCost()));
        if (insertedActivityId != 0) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_ZZZZZZZZZZZZZZZZZZZZZZZZZ_1000000000() throws Exception {
        Session.getInstance().setProjectId(1);
        Activity activity = new Activity("ZZZZZZZZZZZZZZZZZZZZZZZZZ", "description", 2, 2, 2, 2, 1000000000d, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), Session.getInstance().getProjectId(), 1, null);
        int insertedActivityId = activity_model.insertNewActivity(activity);
        assertTrue(insertedActivityId != 0);
        activity2 = activity_model.getActivityById(insertedActivityId);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZZZ", activity2.getActivityLabel());
        assertEquals(new Double(1000000000), new Double(activity2.getPlannedCost()));
        if (insertedActivityId != 0) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_AB_1000000000() throws Exception {
        Session.getInstance().setProjectId(1);
        Activity activity = new Activity("AB", "description", 2, 2, 2, 2, 1000000000d, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), Session.getInstance().getProjectId(), 1, null);
        int insertedActivityId = activity_model.insertNewActivity(activity);
        assertTrue(insertedActivityId != 0);
        activity2 = activity_model.getActivityById(insertedActivityId);
        assertEquals("AB", activity2.getActivityLabel());
        assertEquals(new Double(1000000000), new Double(activity2.getPlannedCost()));
        if (insertedActivityId != 0) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_ZZZZZZZZZZZZZZZZZZZZZZZZY_1000000000() throws Exception {
        Session.getInstance().setProjectId(1);
        Activity activity = new Activity("ZZZZZZZZZZZZZZZZZZZZZZZZY", "description", 2, 2, 2, 2, 1000000000d, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), Session.getInstance().getProjectId(), 1, null);
        int insertedActivityId = activity_model.insertNewActivity(activity);
        assertTrue(insertedActivityId != 0);
        activity2 = activity_model.getActivityById(insertedActivityId);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZZY", activity2.getActivityLabel());
        assertEquals(new Double(1000000000), new Double(activity2.getPlannedCost()));
        if (insertedActivityId != 0) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_PROJECT_1000000000() throws Exception {
        Session.getInstance().setProjectId(1);
        Activity activity = new Activity("PROJECT", "description", 2, 2, 2, 2, 1000000000d, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), Session.getInstance().getProjectId(), 1, null);
        int insertedActivityId = activity_model.insertNewActivity(activity);
        assertTrue(insertedActivityId != 0);
        activity2 = activity_model.getActivityById(insertedActivityId);
        assertEquals("PROJECT", activity2.getActivityLabel());
        assertEquals(new Double(1000000000), new Double(activity2.getPlannedCost()));
        if (insertedActivityId != 0) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_125845_1000000000() throws Exception {
        Session.getInstance().setProjectId(1);
        Activity activity = new Activity("125845", "description", 2, 2, 2, 2, 1000000000d, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), Session.getInstance().getProjectId(), 1, null);
        int insertedActivityId = activity_model.insertNewActivity(activity);
        assertTrue(insertedActivityId != 0);
        activity2 = activity_model.getActivityById(insertedActivityId);
        assertEquals("125845", activity2.getActivityLabel());
        assertEquals(new Double(1000000000), new Double(activity2.getPlannedCost()));
        if (insertedActivityId != 0) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_PROJECT125845_1000000000() throws Exception {
        Session.getInstance().setProjectId(1);
        Activity activity = new Activity("PROJECT125845", "description", 2, 2, 2, 2, (double)1000000000, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), Session.getInstance().getProjectId(), 1, null);
        int insertedActivityId = activity_model.insertNewActivity(activity);
        assertTrue(insertedActivityId != 0);
        activity2 = activity_model.getActivityById(insertedActivityId);
        assertEquals("PROJECT125845", activity2.getActivityLabel());
        assertEquals(new Double(1000000000), new Double(activity2.getPlannedCost()));
        if (insertedActivityId != 0) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_PROJECT_1() throws Exception {
        Session.getInstance().setProjectId(1);
        Activity activity = new Activity("PROJECT", "description", 2, 2, 2, 2, 1d, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), Session.getInstance().getProjectId(), 1, null);
        int insertedActivityId = activity_model.insertNewActivity(activity);
        assertTrue(insertedActivityId != 0);
        activity2 = activity_model.getActivityById(insertedActivityId);
        assertEquals("PROJECT", activity2.getActivityLabel());
        assertEquals(new Double(1), new Double(activity2.getPlannedCost()));
        if (insertedActivityId != 0) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_125845_1() throws Exception {
        Session.getInstance().setProjectId(1);
        Activity activity = new Activity("125845", "description", 2, 2, 2, 2, 1d, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), Session.getInstance().getProjectId(), 1, null);
        int insertedActivityId = activity_model.insertNewActivity(activity);
        assertTrue(insertedActivityId != 0);
        activity2 = activity_model.getActivityById(insertedActivityId);
        assertEquals("125845", activity2.getActivityLabel());
        assertEquals(new Double(1), new Double(activity2.getPlannedCost()));
        if (insertedActivityId != 0) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_PROJECT125845_1() throws Exception {
        Session.getInstance().setProjectId(1);
        Activity activity = new Activity("PROJECT125845", "description", 2, 2, 2, 2, 1d, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), Session.getInstance().getProjectId(), 1, null);
        int insertedActivityId = activity_model.insertNewActivity(activity);
        assertTrue(insertedActivityId != 0);
        activity2 = activity_model.getActivityById(insertedActivityId);
        assertEquals("PROJECT125845", activity2.getActivityLabel());
        assertEquals(new Double(1), new Double(activity2.getPlannedCost()));
        if (insertedActivityId != 0) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_PROJECT_999999999() throws Exception {
        Session.getInstance().setProjectId(1);
        Activity activity = new Activity("PROJECT", "description", 2, 2, 2, 2, 999999999d, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), Session.getInstance().getProjectId(), 1, null);
        int insertedActivityId = activity_model.insertNewActivity(activity);
        assertTrue(insertedActivityId != 0);
        activity2 = activity_model.getActivityById(insertedActivityId);
        assertEquals("PROJECT", activity2.getActivityLabel());
        assertEquals(new Double(999999999), new Double(activity2.getPlannedCost()));
        if (insertedActivityId != 0) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_125845_999999999() throws Exception {
        Session.getInstance().setProjectId(1);
        Activity activity = new Activity("125845", "description", 2, 2, 2, 2, 999999999d, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), Session.getInstance().getProjectId(), 1, null);
        int insertedActivityId = activity_model.insertNewActivity(activity);
        assertTrue(insertedActivityId != 0);
        activity2 = activity_model.getActivityById(insertedActivityId);
        assertEquals("125845", activity2.getActivityLabel());
        assertEquals(new Double(999999999), new Double(activity2.getPlannedCost()));
        if (insertedActivityId != 0) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_PROJECT125845_999999999() throws Exception {
        Session.getInstance().setProjectId(1);
        Activity activity = new Activity("PROJECT125845", "description", 2, 2, 2, 2, 999999999d, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), Session.getInstance().getProjectId(), 1, null);
        int insertedActivityId = activity_model.insertNewActivity(activity);
        assertTrue(insertedActivityId != 0);
        activity2 = activity_model.getActivityById(insertedActivityId);
        assertEquals("PROJECT125845", activity2.getActivityLabel());
        assertEquals(new Double(999999999), new Double(activity2.getPlannedCost()));
        if (insertedActivityId != 0) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_AB_1() throws Exception {
        Session.getInstance().setProjectId(1);
        Activity activity = new Activity("AB", "description", 2, 2, 2, 2, 1, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), Session.getInstance().getProjectId(), 1, null);
        int insertedActivityId = activity_model.insertNewActivity(activity);
        assertTrue(insertedActivityId != 0);
        activity2 = activity_model.getActivityById(insertedActivityId);
        assertEquals("AB", activity2.getActivityLabel());
        assertEquals(new Double(1), new Double(activity2.getPlannedCost()));
        if (insertedActivityId != 0) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_12_1() throws Exception {
        Session.getInstance().setProjectId(1);
        Activity activity = new Activity("12", "description", 2, 2, 2, 2, 1d, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), Session.getInstance().getProjectId(), 1, null);
        int insertedActivityId = activity_model.insertNewActivity(activity);
        assertTrue(insertedActivityId != 0);
        activity2 = activity_model.getActivityById(insertedActivityId);
        assertEquals("12", activity2.getActivityLabel());
        assertEquals(new Double(1), new Double(activity2.getPlannedCost()));
        if (insertedActivityId != 0) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_ZZZZZZZZZZZZZZZZZZZZZZZY_999999999() throws Exception {
        Session.getInstance().setProjectId(1);
        Activity activity = new Activity("ZZZZZZZZZZZZZZZZZZZZZZZY", "description", 2, 2, 2, 2, 999999999d, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), Session.getInstance().getProjectId(), 1, null);
        int insertedActivityId = activity_model.insertNewActivity(activity);
        assertTrue(insertedActivityId != 0);
        activity2 = activity_model.getActivityById(insertedActivityId);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZY", activity2.getActivityLabel());
        assertEquals(new Double(999999999), new Double(activity2.getPlannedCost()));
        if (insertedActivityId != 0) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_123456789012345678901234_999999999() throws Exception {
        Session.getInstance().setProjectId(1);
        Activity activity = new Activity("123456789012345678901234", "description", 2, 2, 2, 2, 999999999d, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), Session.getInstance().getProjectId(), 1, null);
        int insertedActivityId = activity_model.insertNewActivity(activity);
        assertTrue(insertedActivityId != 0);
        activity2 = activity_model.getActivityById(insertedActivityId);
        assertEquals("123456789012345678901234", activity2.getActivityLabel());
        assertEquals(new Double(999999999), new Double(activity2.getPlannedCost()));
        if (insertedActivityId != 0) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_AB_999999999() throws Exception {
        Session.getInstance().setProjectId(1);
        Activity activity = new Activity("AB", "description", 2, 2, 2, 2, 999999999d, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), Session.getInstance().getProjectId(), 1, null);
        int insertedActivityId = activity_model.insertNewActivity(activity);
        assertTrue(insertedActivityId != 0);
        activity2 = activity_model.getActivityById(insertedActivityId);
        assertEquals("AB", activity2.getActivityLabel());
        assertEquals(new Double(999999999), new Double(activity2.getPlannedCost()));
        if (insertedActivityId != 0) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_12_999999999() throws Exception {
        Session.getInstance().setProjectId(1);
        Activity activity = new Activity("12", "description", 2, 2, 2, 2, 999999999d, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), Session.getInstance().getProjectId(), 1, null);
        int insertedActivityId = activity_model.insertNewActivity(activity);
        assertTrue(insertedActivityId != 0);
        activity2 = activity_model.getActivityById(insertedActivityId);
        assertEquals("12", activity2.getActivityLabel());
        assertEquals(new Double(999999999), new Double(activity2.getPlannedCost()));
        if (insertedActivityId != 0) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_ZZZZZZZZZZZZZZZZZZZZZZZY_1() throws Exception {
        Session.getInstance().setProjectId(1);
        Activity activity = new Activity("ZZZZZZZZZZZZZZZZZZZZZZZY", "description", 2, 2, 2, 2, 1d, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), Session.getInstance().getProjectId(), 1, null);
        int insertedActivityId = activity_model.insertNewActivity(activity);
        assertTrue(insertedActivityId != 0);
        activity2 = activity_model.getActivityById(insertedActivityId);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZY", activity2.getActivityLabel());
        assertEquals(new Double(1), new Double(activity2.getPlannedCost()));
        if (insertedActivityId != 0) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }

    @Test
    public void testCreateActivity_123456789012345678901234_1() throws Exception {
        Session.getInstance().setProjectId(1);
        Activity activity = new Activity("123456789012345678901234", "description", 2, 2, 2, 2, 1d, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), Session.getInstance().getProjectId(), 1, null);
        int insertedActivityId = activity_model.insertNewActivity(activity);
        assertTrue(insertedActivityId != 0);
        activity2 = activity_model.getActivityById(insertedActivityId);
        assertEquals("123456789012345678901234", activity2.getActivityLabel());
        assertEquals(new Double(1), new Double(activity2.getPlannedCost()));
        if (insertedActivityId != 0) {
            activity_model.deleteActivityById(insertedActivityId);
        }
    }
}