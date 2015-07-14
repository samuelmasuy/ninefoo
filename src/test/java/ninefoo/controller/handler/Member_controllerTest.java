package ninefoo.controller.handler;

import ninefoo.Mocks.MockUpdatableView;
import ninefoo.config.Config;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by samuel on 2015-07-11.
 */
public class Member_controllerTest {
    private MockUpdatableView mockUpdatableView = new MockUpdatableView();
    private Member_controller member_controller;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Config.autoload();
    }

    @Before
    public void setUp() throws Exception {
        member_controller = new Member_controller(mockUpdatableView);
    }

    @Test
    public void testLogin_success() throws Exception {
        member_controller.register("fistname", "lastname", "username", "password");
        mockUpdatableView.reset_default();
        member_controller.login("username", "password");
        assertEquals("login is successful method", "updateLogin", mockUpdatableView.get_called_method());
        assertEquals("login is successful success", "true", mockUpdatableView.get_success());
        assertEquals("login is successful message", null, mockUpdatableView.get_message());
    }

    @Test
    public void testLogin_invalid_username() throws Exception {
        member_controller.register("fistname", "lastname", "username", "password");
        mockUpdatableView.reset_default();
        member_controller.login("XXXXX", "password");
        assertEquals("login username is invalid method", "updateLogin", mockUpdatableView.get_called_method());
        assertEquals("login username is invalid success", "false", mockUpdatableView.get_success());
        assertEquals("login username is invalid message", "Wrong username or password.", mockUpdatableView.get_message());
    }

    @Test
    public void testLogin_invalid_password() throws Exception {
        member_controller.register("fistname", "lastname", "username", "password");
        mockUpdatableView.reset_default();
        member_controller.login("username", "XXXXX");
        assertEquals("login password is invalid method", "updateLogin", mockUpdatableView.get_called_method());
        assertEquals("login password is invalid success", "false", mockUpdatableView.get_success());
        assertEquals("login password is invalid message", "Wrong username or password.", mockUpdatableView.get_message());
    }

    @Test
    public void testLogin_invalid_username_password() throws Exception {
        member_controller.register("fistname", "lastname", "username", "password");
        mockUpdatableView.reset_default();
        member_controller.login("XXXXX", "XXXXX");
        assertEquals("login credentials are invalid method", "updateLogin", mockUpdatableView.get_called_method());
        assertEquals("login credentials are invalid success", "false", mockUpdatableView.get_success());
        assertEquals("login credentials are invalid message", "Wrong username or password.", mockUpdatableView.get_message());
    }

    @Test
    public void testRegister_success() throws Exception {
        member_controller.register("fistname", "lastname", "unique", "password");
        assertEquals("register is successful method", "updateRegister", mockUpdatableView.get_called_method());
        assertEquals("register is successful success", "true", mockUpdatableView.get_success());
        assertEquals("register is successful message", "Registration successful! Please login.", mockUpdatableView.get_message());
    }

    @Test
    public void testRegister_invalid_firstname() throws Exception {
        member_controller.register("1-3241fistname", "lastname", "username", "password");
        assertEquals("register with invalid firstname method", "updateRegister", mockUpdatableView.get_called_method());
        assertEquals("register with invalid firstname success", "false", mockUpdatableView.get_success());
        assertEquals("register with invalid firstname message", "First name is not valid.", mockUpdatableView.get_message());
    }

    @Test
    public void testRegister_empty_firstname() throws Exception {
        member_controller.register("", "lastname", "username", "password");
        assertEquals("register with empty firstname method", "updateRegister", mockUpdatableView.get_called_method());
        assertEquals("register with empty firstname success", "false", mockUpdatableView.get_success());
        assertEquals("register with empty firstname message", "First name is required.", mockUpdatableView.get_message());
    }

    @Test
    public void testRegister_invalid_last_name() throws Exception {
        member_controller.register("fistname", "la]]stname", "username", "password");
        assertEquals("register with invalid  method", "updateRegister", mockUpdatableView.get_called_method());
        assertEquals("register with invalid  success", "false", mockUpdatableView.get_success());
        assertEquals("register with invalid  message", "Last name is not valid.", mockUpdatableView.get_message());
    }

    @Test
    public void testRegister_invalid_username() throws Exception {
        member_controller.register("fistname", "lastname", "[[]", "password");
        assertEquals("register with invalid username method", "updateRegister", mockUpdatableView.get_called_method());
        assertEquals("register with invalid username success", "false", mockUpdatableView.get_success());
        assertEquals("register with invalid username message", "Username is not valid.", mockUpdatableView.get_message());
    }

    @Test
    public void testRegister_existing_username() throws Exception {
        member_controller.register("fistname", "lastname", "username", "password");
        mockUpdatableView.reset_default();
        member_controller.register("fistname", "lastname", "username", "password");
        assertEquals("register with existing username method", "updateRegister", mockUpdatableView.get_called_method());
        assertEquals("register with existing username success", "false", mockUpdatableView.get_success());
        assertEquals("register with existing username message", "Username must be unique", mockUpdatableView.get_message());
    }

    @Test
    public void testRegister_invalid_password() throws Exception {
        member_controller.register("fistname", "lastname", "username", "           ");
        assertEquals("register with invalid password method", "updateRegister", mockUpdatableView.get_called_method());
        assertEquals("register with invalid password success", "false", mockUpdatableView.get_success());
        assertEquals("register with invalid password message", "Password is not valid.", mockUpdatableView.get_message());
    }

    @Test
    public void testRegister_long_password() throws Exception {
        member_controller.register("fistname", "lastname", "username", "passwordpasswordpasswordpassword");
        assertEquals("register with too long password method", "updateRegister", mockUpdatableView.get_called_method());
        assertEquals("register with too long password success", "false", mockUpdatableView.get_success());
        assertEquals("register with too long password message", "Password length must be at most 10.", mockUpdatableView.get_message());
    }

    @Test
    public void testLogout() throws Exception {
        member_controller.register("fistname", "lastname", "username", "password");
        mockUpdatableView.reset_default();
        member_controller.login("username", "password");
        mockUpdatableView.reset_default();
        member_controller.logout();
        assertEquals("logout is successful method", "updateLogout", mockUpdatableView.get_called_method());
    }

    @Test
    public void testLoadAllMembers_success() throws Exception {
        member_controller.loadAllMembers();
        assertEquals("register with too long password method", "updateLoadAllMembers", mockUpdatableView.get_called_method());
        // TODO check if all members match.
    }

    @Test
    public void testLoadAllMembers_no_members() throws Exception {
        // TODO without any members.
//        member_controller.loadAllMembers();
    }

    @Test
    public void testRegisterAndAssign() throws Exception {
        // TODO
    }
}