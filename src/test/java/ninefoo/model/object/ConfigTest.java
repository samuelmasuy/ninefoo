package ninefoo.model.object;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by samuel on 2015-07-05.
 */
public class ConfigTest {

    @Test
    public void testGetConfigName() throws Exception {
        String expected = "config";
        Config config = new Config(expected);
        assertEquals("get config", expected, config.getConfigName());
    }

    @Test
    public void testSetConfigName() throws Exception {
        String expected = "config";
        Config config = new Config("null");
        config.setConfigName(expected);
        assertEquals("set config", expected, config.getConfigName());

    }
}