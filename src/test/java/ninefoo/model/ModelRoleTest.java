package ninefoo.model;

import static org.junit.Assert.*;
import ninefoo.model.object.Role;
import ninefoo.model.sql.Role_model;

import org.junit.Test;

/*This test will test both the Role model and the Role_model class
 * It makes sure that all the functionalities are working in the correct manner
 */
public class ModelRoleTest {
	
	/*
	 * This test will create a role using the constructor and verify whether the role is created
	 * 
	 */
	@Test
	public void test01createNewRole(){
		//Create a role
		Role testRole = new Role("Delete", "This role allows you to delete");
		
		String expectedRoleName = "Delete";
		String actualRoleName = testRole.getRoleName();
		
		assertEquals("The roles are not the same", expectedRoleName, actualRoleName);
		
	}

	
	/*
	 * This test will add a newly created role to the DB and compare the role using the role IDs by using the
	 * getRoleByID method
	 */
	@Test
	public void test02addNewRoleAndGetRoleByID(){
		//Create a role
		Role testRole = new Role("Update", "This role allows you to update");
		Role_model testRoleModel = new Role_model();
		
		//Insert the role into the role table
		int expectedRoleID = testRoleModel.insertNewRole(testRole);
		Role testRoleID = testRoleModel.getRoleById(expectedRoleID);
		
		int actualRoleID = testRoleID.getRoleId();
		
		assertEquals("The roles are not the same", expectedRoleID, actualRoleID);
		
	}
	
	/*
	 * This test will add a newly created role to the DB and compare the role using the role IDs by using the
	 * getRoleByID method
	 */
	@Test
	public void test03getRoleName(){
		//Create a role
		Role testRole = new Role("Create", "This role allows you to Create");
		Role_model testRoleModel = new Role_model();
		testRoleModel.insertNewRole(testRole);
		
		//Insert the role into the role table
		Role testRoleName = testRoleModel.getRoleByName("Create");
		
		String actualRoleName = testRoleName.getRoleName();
		
		String expectedRoleName = testRole.getRoleName();
		
		assertEquals("The roles are not the same name", expectedRoleName, actualRoleName);
		
	}
	
	/*
	 * This test will add a newly created role to the DB and set the name to a new name and verify if the names are correct
	 * getRoleByID method
	 */
	@Test
	public void test04setRoleName(){
		//Create a role
		Role testRole = new Role("Configure", "This role allows you to Configure");
		Role_model testRoleModel = new Role_model();
		
		//Insert the role into the role table
		testRole.setRoleName("Configure2");
		testRoleModel.insertNewRole(testRole);

		Role testRoleName = testRoleModel.getRoleByName("Configure2");

		String actualRoleName = testRoleName.getRoleName();
		
		System.out.println(actualRoleName);

		String expectedRoleName = testRole.getRoleName();
		System.out.println(expectedRoleName);

		assertEquals("The roles are not the same name", expectedRoleName, actualRoleName);
		
	}
	
	/*
	 * This test will get a newly created role by its description and it with what is in the DataBase
	 */
	@Test
	public void test05getRoleDescription(){
		//Create a role
		Role testRole = new Role("Modify", "This role allows you to modify a role");
		Role_model testRoleModel = new Role_model();
		int roleId = testRoleModel.insertNewRole(testRole);
		
		//Insert the role into the role table
		Role testRoleName = testRoleModel.getRoleById(roleId);
		String actualRoleDescription = testRoleName.getDescription();
		
		String expectedRoleDescription = testRole.getDescription();
		
		assertEquals("The roles are not the same name", expectedRoleDescription, actualRoleDescription);
		
	}
	
	/*
	 * This test will change the description of a role and verify if the update has taken place by getting the 
	 * new description and comparing it to the other description
	 */
	@Test
	public void test06setRoleDescription(){
		//Create a role
		Role testRole = new Role("Alter", "This role allows you to delete....");
		Role_model testRoleModel = new Role_model();

		//Insert the role into the role table
		testRole.setDescription("This role allows you to alter");
		testRoleModel.insertNewRole(testRole);

		Role testRoleName = testRoleModel.getRoleByName("Alter");
		String actualRoleDescription = testRoleName.getDescription();
		
		String expectedRoleDescription = "This role allows you to alter";
		
		assertEquals("The roles are not the same name description", expectedRoleDescription, actualRoleDescription);
		
	}

}
