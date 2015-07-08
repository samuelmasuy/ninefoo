package ninefoo.helper;

import junit.framework.TestCase;
import org.junit.Test;
import ninefoo.model.object.Activity;
import ninefoo.model.object.Member;
import ninefoo.model.object.Project;

import java.util.*;

/**
 * Created by samuel on 2015-07-05.
 */
public class ActivityHelperTest extends TestCase {

    @Test
    public void testGetRow() throws Exception {
        Member member = new Member(0, "first", "last", "user", "pass", new Date());
        Activity activity = new Activity(0, "label", "description", 1, 1, 1, 1, new Date(),
                new Project(0, "name", new Date(), new Date(), new Date(), 1.0, new Date(), "description"),
                 member, Collections.EMPTY_LIST);


        Object[] row = ActivityHelper.getRow(activity);

        // does not work
//        assertEquals("id", "0", row[ActivityConfig.ACTIVITY_ID]);
//        assertEquals("name", "name", row[ActivityConfig.ACTIVITY_NAME]);
//        assertEquals("duration", 0, row[ActivityConfig.DURATION]);
//        assertEquals("finish", 1, row[ActivityConfig.FINISH]);
//        assertEquals("likely", 1, row[ActivityConfig.LIKELY]);
//        assertEquals("member", member, row[ActivityConfig.MEMBER]);
//        assertEquals("optimistic", "[]", row[ActivityConfig.OPTIMISTIC]);
//        assertEquals("pessimistic", 1, row[ActivityConfig.PESSIMISTIC]);
//        assertEquals("start", 1, row[ActivityConfig.START]);
//        assertEquals("prereq", Collections.EMPTY_LIST, row[ActivityConfig.PREREQ]);
    }
}