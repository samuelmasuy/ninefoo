package ninefoo.model.object;

import junit.framework.TestCase;
import ninefoo.config.Database;
import ninefoo.config.Session;
import ninefoo.model.object.Member;
import ninefoo.model.sql.Member_model;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by samuel on 2015-08-15.
 */
public class MemberTest extends TestCase {
    static Member_model member_model;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        ninefoo.config.Config.autoload();
        Session.getInstance().open();
    }

    @Before
    public void setUp() throws Exception {
        member_model = new Member_model();
    }

    @Test
    public void testCreateMember() throws Exception {
        Member memberA = new Member("firstName", "lastName", "username", "password");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("username", memberB.getUsername());
        assertEquals("password", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }
}