package ninefoo.controller.handler;


import ninefoo.Mocks.MockUpdatableView;
import ninefoo.config.Config;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by samuel on 2015-07-09.
 */
public class Activity_controllerTest {
    private MockUpdatableView mockUpdatableView = new MockUpdatableView();
    private Activity_controller activity_controller;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Config.autoload();
    }

    @Before
    public void setUp() throws Exception {
        activity_controller = new Activity_controller(mockUpdatableView);
    }

    @Test
    public void testCreateActivity() throws Exception {
//        activity_controller.createActivity(0, "0",
//            "label", "1", "1/1/2004",
//            "1/1/2002", "55", null,
//            1, new String[]{});

    }

    @Test
    public void testEditActivity() throws Exception {

    }

    @Test
    public void testLoadActivitiesByProject() throws Exception {

    }

    @Test
    public void testLoadActivitiesForAllProjectByMember() throws Exception {

    }
}