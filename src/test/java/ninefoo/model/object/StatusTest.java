package ninefoo.model.object;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by samuel on 2015-07-05.
 */
public class StatusTest {

    @Test
    public void testGetStatusName() throws Exception {
        String expected = "status";
        Status status = new Status(expected);
        assertEquals("get status", expected, status.getStatusName());
    }

    @Test
    public void testSetStatusName() throws Exception {
        String expected = "status";
        Status status = new Status("null");
        status.setStatusName(expected);
        assertEquals("set status", expected, status.getStatusName());

    }
}