package ninefoo.model.object;

import junit.framework.TestCase;
import ninefoo.config.Database;
import ninefoo.config.Session;
import ninefoo.model.sql.Member_model;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by samuel on 2015-08-15.
 * <p/>
 * Black box testing for createMember.
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
    public void testCreateMember_A_A() throws Exception {
        Member memberA = new Member("firstName", "lastName", "A", "A");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("A", memberB.getUsername());
        assertEquals("A", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_ZZZZZZZZZZZZZZZZZZZZZZZZZ_ZZZZZZZZZZZZZZZZZZZZZZZZZ() throws Exception {
        Member memberA = new Member("firstName", "lastName", "ZZZZZZZZZZZZZZZZZZZZZZZZZ", "ZZZZZZZZZZZZZZZZZZZZZZZZZ");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZZZ", memberB.getUsername());
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZZZ", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_A_ZZZZZZZZZZZZZZZZZZZZZZZZZ() throws Exception {
        Member memberA = new Member("firstName", "lastName", "A", "ZZZZZZZZZZZZZZZZZZZZZZZZZ");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("A", memberB.getUsername());
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZZZ", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_ZZZZZZZZZZZZZZZZZZZZZZZZZ_A() throws Exception {
        Member memberA = new Member("firstName", "lastName", "ZZZZZZZZZZZZZZZZZZZZZZZZZ", "A");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZZZ", memberB.getUsername());
        assertEquals("A", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_AB_A() throws Exception {
        Member memberA = new Member("firstName", "lastName", "AB", "A");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("AB", memberB.getUsername());
        assertEquals("A", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_AB_ZZZZZZZZZZZZZZZZZZZZZZZZZ() throws Exception {
        Member memberA = new Member("firstName", "lastName", "AB", "ZZZZZZZZZZZZZZZZZZZZZZZZZ");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("AB", memberB.getUsername());
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZZZ", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_ZZZZZZZZZZZZZZZZZZZZZZZY_A() throws Exception {
        Member memberA = new Member("firstName", "lastName", "ZZZZZZZZZZZZZZZZZZZZZZZY", "A");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZY", memberB.getUsername());
        assertEquals("A", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_ZZZZZZZZZZZZZZZZZZZZZZZY_ZZZZZZZZZZZZZZZZZZZZZZZZZ() throws Exception {
        Member memberA = new Member("firstName", "lastName", "ZZZZZZZZZZZZZZZZZZZZZZZY", "ZZZZZZZZZZZZZZZZZZZZZZZZZ");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZY", memberB.getUsername());
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZZZ", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_A_PASSWORD() throws Exception {
        Member memberA = new Member("firstName", "lastName", "A", "PASSWORD");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("A", memberB.getUsername());
        assertEquals("PASSWORD", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_ZZZZZZZZZZZZZZZZZZZZZZZZZ_PASSWORD() throws Exception {
        Member memberA = new Member("firstName", "lastName", "ZZZZZZZZZZZZZZZZZZZZZZZZZ", "PASSWORD");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZZZ", memberB.getUsername());
        assertEquals("PASSWORD", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_USERNAME_ZZZZZZZZZZZZZZZZZZZZZZZZZ() throws Exception {
        Member memberA = new Member("firstName", "lastName", "USERNAME", "ZZZZZZZZZZZZZZZZZZZZZZZZZ");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("USERNAME", memberB.getUsername());
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZZZ", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_USERNAME_AB() throws Exception {
        Member memberA = new Member("firstName", "lastName", "USERNAME", "AB");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("USERNAME", memberB.getUsername());
        assertEquals("AB", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_USERNAME_ZZZZZZZZZZZZZZZZZZZZZZZY() throws Exception {
        Member memberA = new Member("firstName", "lastName", "USERNAME", "ZZZZZZZZZZZZZZZZZZZZZZZY");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("USERNAME", memberB.getUsername());
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZY", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_AB_ZZZZZZZZZZZZZZZZZZZZZZZY() throws Exception {
        Member memberA = new Member("firstName", "lastName", "AB", "ZZZZZZZZZZZZZZZZZZZZZZZY");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("AB", memberB.getUsername());
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZY", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_ZZZZZZZZZZZZZZZZZZZZZZZY_AB() throws Exception {
        Member memberA = new Member("firstName", "lastName", "ZZZZZZZZZZZZZZZZZZZZZZZY", "AB");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZY", memberB.getUsername());
        assertEquals("AB", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_AB_AB() throws Exception {
        Member memberA = new Member("firstName", "lastName", "AB", "AB");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("AB", memberB.getUsername());
        assertEquals("AB", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_ZZZZZZZZZZZZZZZZZZZZZZZY_ZZZZZZZZZZZZZZZZZZZZZZZY() throws Exception {
        Member memberA = new Member("firstName", "lastName", "ZZZZZZZZZZZZZZZZZZZZZZZY", "ZZZZZZZZZZZZZZZZZZZZZZZY");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZY", memberB.getUsername());
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZY", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_AB_PASSWORD() throws Exception {
        Member memberA = new Member("firstName", "lastName", "AB", "PASSWORD");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("AB", memberB.getUsername());
        assertEquals("PASSWORD", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_ZZZZZZZZZZZZZZZZZZZZZZZY_PASSWORD() throws Exception {
        Member memberA = new Member("firstName", "lastName", "ZZZZZZZZZZZZZZZZZZZZZZZY", "PASSWORD");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZY", memberB.getUsername());
        assertEquals("PASSWORD", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_USERNAME_PASSWORD() throws Exception {
        Member memberA = new Member("firstName", "lastName", "USERNAME", "PASSWORD");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("USERNAME", memberB.getUsername());
        assertEquals("PASSWORD", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_A_0() throws Exception {
        Member memberA = new Member("firstName", "lastName", "A", "0");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("A", memberB.getUsername());
        assertEquals("0", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_ZZZZZZZZZZZZZZZZZZZZZZZZZ_0() throws Exception {
        Member memberA = new Member("firstName", "lastName", "ZZZZZZZZZZZZZZZZZZZZZZZZZ", "0");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZZZ", memberB.getUsername());
        assertEquals("0", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_AB_0() throws Exception {
        Member memberA = new Member("firstName", "lastName", "AB", "0");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("AB", memberB.getUsername());
        assertEquals("0", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_ZZZZZZZZZZZZZZZZZZZZZZZZY_0() throws Exception {
        Member memberA = new Member("firstName", "lastName", "ZZZZZZZZZZZZZZZZZZZZZZZZY", "0");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZZY", memberB.getUsername());
        assertEquals("0", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_USERNAME_0() throws Exception {
        Member memberA = new Member("firstName", "lastName", "USERNAME", "0");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("USERNAME", memberB.getUsername());
        assertEquals("0", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_0_0() throws Exception {
        Member memberA = new Member("firstName", "lastName", "0", "0");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("0", memberB.getUsername());
        assertEquals("0", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_1234567890123456789012345_0() throws Exception {
        Member memberA = new Member("firstName", "lastName", "1234567890123456789012345", "0");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("1234567890123456789012345", memberB.getUsername());
        assertEquals("0", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_01_0() throws Exception {
        Member memberA = new Member("firstName", "lastName", "01", "0");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("01", memberB.getUsername());
        assertEquals("0", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_123456789012345678901234_0() throws Exception {
        Member memberA = new Member("firstName", "lastName", "123456789012345678901234", "0");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("123456789012345678901234", memberB.getUsername());
        assertEquals("0", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_125845_0() throws Exception {
        Member memberA = new Member("firstName", "lastName", "125845", "0");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("125845", memberB.getUsername());
        assertEquals("0", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_USERNAME125845_0() throws Exception {
        Member memberA = new Member("firstName", "lastName", "USERNAME125845", "0");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("USERNAME125845", memberB.getUsername());
        assertEquals("0", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_A_1000000000() throws Exception {
        Member memberA = new Member("firstName", "lastName", "A", "1000000000");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("A", memberB.getUsername());
        assertEquals("1000000000", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_ZZZZZZZZZZZZZZZZZZZZZZZZZ_1000000000() throws Exception {
        Member memberA = new Member("firstName", "lastName", "ZZZZZZZZZZZZZZZZZZZZZZZZZ", "1000000000");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZZZ", memberB.getUsername());
        assertEquals("1000000000", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_AB_1000000000() throws Exception {
        Member memberA = new Member("firstName", "lastName", "AB", "1000000000");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("AB", memberB.getUsername());
        assertEquals("1000000000", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_ZZZZZZZZZZZZZZZZZZZZZZZZY_1000000000() throws Exception {
        Member memberA = new Member("firstName", "lastName", "ZZZZZZZZZZZZZZZZZZZZZZZZY", "1000000000");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZZY", memberB.getUsername());
        assertEquals("1000000000", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_USERNAME_1000000000() throws Exception {
        Member memberA = new Member("firstName", "lastName", "USERNAME", "1000000000");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("USERNAME", memberB.getUsername());
        assertEquals("1000000000", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_125845_1000000000() throws Exception {
        Member memberA = new Member("firstName", "lastName", "125845", "1000000000");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("125845", memberB.getUsername());
        assertEquals("1000000000", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_USERNAME125845_1000000000() throws Exception {
        Member memberA = new Member("firstName", "lastName", "USERNAME125845", "1000000000");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("USERNAME125845", memberB.getUsername());
        assertEquals("1000000000", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_USERNAME_1() throws Exception {
        Member memberA = new Member("firstName", "lastName", "USERNAME", "1");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("USERNAME", memberB.getUsername());
        assertEquals("1", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_125845_1() throws Exception {
        Member memberA = new Member("firstName", "lastName", "125845", "1");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("125845", memberB.getUsername());
        assertEquals("1", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_USERNAME25845_1() throws Exception {
        Member memberA = new Member("firstName", "lastName", "USERNAME25845", "1");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("USERNAME25845", memberB.getUsername());
        assertEquals("1", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_USERNAME_999999999() throws Exception {
        Member memberA = new Member("firstName", "lastName", "USERNAME", "999999999");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("USERNAME", memberB.getUsername());
        assertEquals("999999999", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_125845_999999999() throws Exception {
        Member memberA = new Member("firstName", "lastName", "125845", "999999999");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("125845", memberB.getUsername());
        assertEquals("999999999", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_USERNAME25845_999999999() throws Exception {
        Member memberA = new Member("firstName", "lastName", "USERNAME25845", "999999999");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("USERNAME25845", memberB.getUsername());
        assertEquals("999999999", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_AB_1() throws Exception {
        Member memberA = new Member("firstName", "lastName", "AB", "1");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("AB", memberB.getUsername());
        assertEquals("1", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_12_1() throws Exception {
        Member memberA = new Member("firstName", "lastName", "12", "1");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("12", memberB.getUsername());
        assertEquals("1", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_ZZZZZZZZZZZZZZZZZZZZZZZY_999999999() throws Exception {
        Member memberA = new Member("firstName", "lastName", "ZZZZZZZZZZZZZZZZZZZZZZZY", "999999999");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZY", memberB.getUsername());
        assertEquals("999999999", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_123456789012345678901234_999999999() throws Exception {
        Member memberA = new Member("firstName", "lastName", "123456789012345678901234", "999999999");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("123456789012345678901234", memberB.getUsername());
        assertEquals("999999999", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_AB_999999999() throws Exception {
        Member memberA = new Member("firstName", "lastName", "AB", "999999999");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("AB", memberB.getUsername());
        assertEquals("999999999", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_12_999999999() throws Exception {
        Member memberA = new Member("firstName", "lastName", "12", "999999999");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("12", memberB.getUsername());
        assertEquals("999999999", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_ZZZZZZZZZZZZZZZZZZZZZZZY_1() throws Exception {
        Member memberA = new Member("firstName", "lastName", "ZZZZZZZZZZZZZZZZZZZZZZZY", "1");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZY", memberB.getUsername());
        assertEquals("1", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_123456789012345678901234_1() throws Exception {
        Member memberA = new Member("firstName", "lastName", "123456789012345678901234", "1");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("123456789012345678901234", memberB.getUsername());
        assertEquals("1", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_0_A() throws Exception {
        Member memberA = new Member("firstName", "lastName", "0", "A");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("0", memberB.getUsername());
        assertEquals("A", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_0_ZZZZZZZZZZZZZZZZZZZZZZZZZ() throws Exception {
        Member memberA = new Member("firstName", "lastName", "0", "ZZZZZZZZZZZZZZZZZZZZZZZZZ");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("0", memberB.getUsername());
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZZZ", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_0_AB() throws Exception {
        Member memberA = new Member("firstName", "lastName", "0", "AB");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("0", memberB.getUsername());
        assertEquals("AB", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_0_ZZZZZZZZZZZZZZZZZZZZZZZZY() throws Exception {
        Member memberA = new Member("firstName", "lastName", "0", "ZZZZZZZZZZZZZZZZZZZZZZZZY");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("0", memberB.getUsername());
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZZY", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_0_PASSWORD() throws Exception {
        Member memberA = new Member("firstName", "lastName", "0", "PASSWORD");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("0", memberB.getUsername());
        assertEquals("PASSWORD", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_0_1234567890123456789012345() throws Exception {
        Member memberA = new Member("firstName", "lastName", "0", "1234567890123456789012345");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("0", memberB.getUsername());
        assertEquals("1234567890123456789012345", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_0_01() throws Exception {
        Member memberA = new Member("firstName", "lastName", "0", "01");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("0", memberB.getUsername());
        assertEquals("01", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_0_123456789012345678901234() throws Exception {
        Member memberA = new Member("firstName", "lastName", "0", "123456789012345678901234");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("0", memberB.getUsername());
        assertEquals("123456789012345678901234", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_0_125845() throws Exception {
        Member memberA = new Member("firstName", "lastName", "0", "125845");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("0", memberB.getUsername());
        assertEquals("125845", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_0_PASSWORD125845() throws Exception {
        Member memberA = new Member("firstName", "lastName", "0", "PASSWORD125845");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("0", memberB.getUsername());
        assertEquals("PASSWORD125845", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_1000000000_A() throws Exception {
        Member memberA = new Member("firstName", "lastName", "1000000000", "A");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("1000000000", memberB.getUsername());
        assertEquals("A", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_1000000000_ZZZZZZZZZZZZZZZZZZZZZZZZZ() throws Exception {
        Member memberA = new Member("firstName", "lastName", "1000000000", "ZZZZZZZZZZZZZZZZZZZZZZZZZ");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("1000000000", memberB.getUsername());
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZZZ", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_1000000000_AB() throws Exception {
        Member memberA = new Member("firstName", "lastName", "1000000000", "AB");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("1000000000", memberB.getUsername());
        assertEquals("AB", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_1000000000_ZZZZZZZZZZZZZZZZZZZZZZZZY() throws Exception {
        Member memberA = new Member("firstName", "lastName", "1000000000", "ZZZZZZZZZZZZZZZZZZZZZZZZY");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("1000000000", memberB.getUsername());
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZZY", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_1000000000_PASSWORD() throws Exception {
        Member memberA = new Member("firstName", "lastName", "1000000000", "PASSWORD");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("1000000000", memberB.getUsername());
        assertEquals("PASSWORD", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_1000000000_125845() throws Exception {
        Member memberA = new Member("firstName", "lastName", "1000000000", "125845");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("1000000000", memberB.getUsername());
        assertEquals("125845", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_1000000000_PASSWORD125845() throws Exception {
        Member memberA = new Member("firstName", "lastName", "1000000000", "PASSWORD125845");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("1000000000", memberB.getUsername());
        assertEquals("PASSWORD125845", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_1_PASSWORD() throws Exception {
        Member memberA = new Member("firstName", "lastName", "1", "PASSWORD");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("1", memberB.getUsername());
        assertEquals("PASSWORD", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_1_125845() throws Exception {
        Member memberA = new Member("firstName", "lastName", "1", "125845");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("1", memberB.getUsername());
        assertEquals("125845", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_1_PASSWORD25845() throws Exception {
        Member memberA = new Member("firstName", "lastName", "1", "PASSWORD25845");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("1", memberB.getUsername());
        assertEquals("PASSWORD25845", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_999999999_PASSWORD() throws Exception {
        Member memberA = new Member("firstName", "lastName", "999999999", "PASSWORD");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("999999999", memberB.getUsername());
        assertEquals("PASSWORD", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_999999999_125845() throws Exception {
        Member memberA = new Member("firstName", "lastName", "999999999", "125845");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("999999999", memberB.getUsername());
        assertEquals("125845", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_999999999_PASSWORD25845() throws Exception {
        Member memberA = new Member("firstName", "lastName", "999999999", "PASSWORD25845");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("999999999", memberB.getUsername());
        assertEquals("PASSWORD25845", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_1_AB() throws Exception {
        Member memberA = new Member("firstName", "lastName", "1", "AB");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("1", memberB.getUsername());
        assertEquals("AB", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_1_12() throws Exception {
        Member memberA = new Member("firstName", "lastName", "1", "12");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("1", memberB.getUsername());
        assertEquals("12", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_999999999_ZZZZZZZZZZZZZZZZZZZZZZZY() throws Exception {
        Member memberA = new Member("firstName", "lastName", "999999999", "ZZZZZZZZZZZZZZZZZZZZZZZY");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("999999999", memberB.getUsername());
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZY", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_999999999_123456789012345678901234() throws Exception {
        Member memberA = new Member("firstName", "lastName", "999999999", "123456789012345678901234");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("999999999", memberB.getUsername());
        assertEquals("123456789012345678901234", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_999999999_AB() throws Exception {
        Member memberA = new Member("firstName", "lastName", "999999999", "AB");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("999999999", memberB.getUsername());
        assertEquals("AB", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_999999999_12() throws Exception {
        Member memberA = new Member("firstName", "lastName", "999999999", "12");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("999999999", memberB.getUsername());
        assertEquals("12", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_1_ZZZZZZZZZZZZZZZZZZZZZZZY() throws Exception {
        Member memberA = new Member("firstName", "lastName", "1", "ZZZZZZZZZZZZZZZZZZZZZZZY");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("1", memberB.getUsername());
        assertEquals("ZZZZZZZZZZZZZZZZZZZZZZZY", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }

    @Test
    public void testCreateMember_1_123456789012345678901234() throws Exception {
        Member memberA = new Member("firstName", "lastName", "1", "123456789012345678901234");
        int insertedMemberId = member_model.insertNewMember(memberA);
        assertTrue(insertedMemberId != Database.ERROR);
        Member memberB = member_model.getMemberById(insertedMemberId);
        assertEquals("1", memberB.getUsername());
        assertEquals("123456789012345678901234", memberB.getPassword());
        if (insertedMemberId != Database.ERROR) {
            member_model.deleteMemberById(insertedMemberId);
        }
    }
}