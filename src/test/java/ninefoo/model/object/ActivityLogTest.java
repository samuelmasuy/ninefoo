package ninefoo.model.object;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by samuel on 2015-07-05.
 */
public class ActivityLogTest {
    private Project project = new Project("projectName", 0.0, new Date(), new Date(), "description");
    private Member member = new Member(0, "firstname", "lastname", "username", "password", new Date());
    private Activity activity = new Activity("activityLabel", 1, "1/1/2001", "1/1/2004", project, member);
    private Status status = new Status("status");

    @Test
    public void testGetProject() throws Exception {
        ActivityLog activityLog = new ActivityLog(project, member, status, activity, new Date());
        assertEquals("get project", project, activityLog.getProject());
    }

    @Test
    public void testSetProject() throws Exception {
        ActivityLog activityLog = new ActivityLog(null, member, status, activity, new Date());
        activityLog.setProject(project);
        assertEquals("set project", project, activityLog.getProject());
    }

    @Test
    public void testGetMember() throws Exception {
        ActivityLog activityLog = new ActivityLog(project, member, status, activity, new Date());
        assertEquals("get member", member, activityLog.getMember());
    }

    @Test
    public void testSetMember() throws Exception {
        ActivityLog activityLog = new ActivityLog(project, null, status, activity, new Date());
        activityLog.setMember(member);
        assertEquals("set member", member, activityLog.getMember());
    }

    @Test
    public void testGetStatus() throws Exception {
        ActivityLog activityLog = new ActivityLog(project, member, status, activity, new Date());
        assertEquals("get status", status, activityLog.getStatus());
    }

    @Test
    public void testSetStatus() throws Exception {
        ActivityLog activityLog = new ActivityLog(project, member, null, activity, new Date());
        activityLog.setStatus(status);
        assertEquals("set status", status, activityLog.getStatus());
    }

    @Test
    public void testGetActivity() throws Exception {
        ActivityLog activityLog = new ActivityLog(project, member, status, activity, new Date());
        assertEquals("get activity", activity, activityLog.getActivity());
    }

    @Test
    public void testSetActivity() throws Exception {
        ActivityLog activityLog = new ActivityLog(project, member, status, null, new Date());
        activityLog.setActivity(activity);
        assertEquals("set activity", activity, activityLog.getActivity());
    }

    @Test
    public void testGetCreateDate() throws Exception {
        Date today = new Date();
        ActivityLog activityLog = new ActivityLog(project, member, status, activity, today);
        assertEquals("get date", today, activityLog.getCreateDate());
    }

    @Test
    public void testSetCreateDate() throws Exception {
        Date today = new Date();
        ActivityLog activityLog = new ActivityLog(project, member, status, activity, null);
        activityLog.setCreateDate(today);
        assertEquals("set date", today, activityLog.getCreateDate());
    }
}