package ninefoo.model;

import static org.junit.Assert.*;
import ninefoo.config.Config;
import ninefoo.helper.DateHelper;
import ninefoo.model.object.Project;
import ninefoo.model.sql.Project_model;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Date;
import java.util.List;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Project_modelTests {
    //Create the project model that is used to manipulate the project objects
    private Project_model project_model = new Project_model();

	/*This test creates a new project with the constructor
	 * 
	 */
	//Create New project which is going to be used for the tests bellow
	//Create a DB
	@Test
	public void test01_createProjectConstructor(){
		//Create a new Project 
		Project testProject = new Project("ATestProject1", 1020.00, DateHelper.parse("06/05/2015", Config.DATE_FORMAT_SHORT), DateHelper.parse("07/05/2015", Config.DATE_FORMAT_SHORT), "A Project to test the DB");
		//Set the expected value
		String expectedProjectName = "ATestProject1";
		
		//Get the project name created
		String myProjectName = testProject.getProjectName();

		//Assert that the new project inserted is equal to the project in the DB
		assertEquals("The project was not created", expectedProjectName, myProjectName);
	}
	
	/*
	 * Create a the Project model which is used to manipulate the Projects
	 * and then insert the project into the DB
	 * This test also get the project by the id
	 */
	@Test
	public void test02_insertNewProject(){
		Project testProject = new Project("ATestProject2", 1001.00, DateHelper.parse("06/05/2015", Config.DATE_FORMAT_SHORT), DateHelper.parse("07/05/2015", Config.DATE_FORMAT_SHORT), "A Project to test the DB");

		//Insert a new project
		//Get the ID of the project which is inserted and see it matches the ID of the project currently in the DB
		int expectedID = project_model.insertNewProject(testProject);
		Project testProjectID = project_model.getProjectById(expectedID);
		//Get the project inserted to verify if it works with the new inserted project
		int actualID = testProjectID.getProjectId();
		assertEquals("The ID of the projects inserted does not match the created project", expectedID, actualID);
	}

	/*This will get a project by the given name
	 * and compare the IDS
	 */
	@Test
	public void test03_getProjectByUserName(){
		Project testProject = new Project("ATestProject3", 1003.00, DateHelper.parse("06/05/2015", Config.DATE_FORMAT_SHORT), DateHelper.parse("07/05/2015", Config.DATE_FORMAT_SHORT), "A Project to test the DB");

		//Insert a new project
		//Get the ID of the project which is inserted and see it matches the ID of the project currently in the DB
		int myID = project_model.insertNewProject(testProject);
		String expectedName = "ATestProject3";
		Project testProjectID = project_model.getProjectById(myID);
		String actualName = testProjectID.getProjectName();
		
		assertEquals("The ID of the projects inserted does not match the created project", expectedName, actualName);
	}

	/*This will get a project by the given name
	 * and compare the IDS
	 */
	@Test
	public void test04_setProjectName(){
		Project testProject = new Project("ATestProjec4", 1024.00, DateHelper.parse("06/05/2015", Config.DATE_FORMAT_SHORT), DateHelper.parse("07/05/2015", Config.DATE_FORMAT_SHORT), "A Project to test the DB");

		//Insert a new project
		//Get the ID of the project which is inserted and see it matches the ID of the project currently in the DB
		int myID = project_model.insertNewProject(testProject);
		String expectedName = "ATestProject3.1";
		Project testProjectID = project_model.getProjectById(myID);
		//Set the project name to AtestProject3.1
		testProjectID.setProjectName("ATestProject3.1");
		String actualName = testProjectID.getProjectName();
		
		assertEquals("The ID of the projects inserted does not match the created project", expectedName, actualName);
	}
	
	/*This will get the start date of a given project and compare it with an expected start date defined in the TestCase
	 * and compare the two if equal
	 */
	@Test
	public void test05_getStartDate(){
		Project testProject = new Project("ATestProjec5", 1024.00, DateHelper.parse("06/05/2015", Config.DATE_FORMAT_SHORT), DateHelper.parse("07/05/2015", Config.DATE_FORMAT_SHORT), "A Project to test the DB");

		//Insert a new project
		//Get the ID of the project which is inserted and see it matches the ID of the project currently in the DB
		int myID = project_model.insertNewProject(testProject);
		Date expectedDate = DateHelper.parse("06/05/2015", Config.DATE_FORMAT_SHORT);
		
		Project testProjectID = project_model.getProjectById(myID);
		//getTheProjectCreateDate
		String name = testProjectID.getProjectName();
		Date actualDate = testProjectID.getStartDate();
		
		assertEquals("The ID of the projects inserted does not match the created project", expectedDate, actualDate);
	}
	
	/*This will test will set the date of the project to a new start date as given
	 */
	@Test
	public void test06_setStartDate(){
		Project testProject = new Project("ATestProjec6", 1024.00, DateHelper.parse("06/05/2015", Config.DATE_FORMAT_SHORT), DateHelper.parse("08/05/2015", Config.DATE_FORMAT_SHORT), "A Project to test the DB");

		//Insert a new project
		//Get the ID of the project which is inserted and see it matches the ID of the project currently in the DB
		Date expectedDate = DateHelper.parse("07/05/2015", Config.DATE_FORMAT_SHORT);

		//getTheProjectCreateDate
		testProject.setStartDate(DateHelper.parse("07/05/2015", Config.DATE_FORMAT_SHORT));
		
		int myID = project_model.insertNewProject(testProject);
		//Insert the created project and get the the Date and compare the two
		Project testProjectID = project_model.getProjectById(myID);
		Date actualDate = testProjectID.getStartDate();
				
		assertEquals("The ID of the projects inserted does not match the created project", expectedDate, actualDate);
	}
	
	/*This will change the deadline date and insure that 
	 * the deadline date of the project has changed
	 */
	@Test
	public void test07_getDeadLineDate(){
		Project testProject = new Project("ATestProjec7", 1024.00, DateHelper.parse("06/05/2015", Config.DATE_FORMAT_SHORT), DateHelper.parse("08/05/2015", Config.DATE_FORMAT_SHORT), "A Project to test the DB");

		//Insert a new project
		//Get the ID of the project which is inserted and see it matches the ID of the project currently in the DB
		Date expectedDate = DateHelper.parse("09/05/2015", Config.DATE_FORMAT_SHORT);

		//getTheProjectCreateDate
		testProject.setDeadlineDate(DateHelper.parse("09/05/2015", Config.DATE_FORMAT_SHORT));
		
		int myID = project_model.insertNewProject(testProject);
		//Insert the created project and get the the Date and compare the two
		Project testProjectID = project_model.getProjectById(myID);
		Date actualDate = testProjectID.getDeadlineDate();
				
		assertEquals("The ID of the projects inserted does not match the created project", expectedDate, actualDate);
	}
	
	/*This will get the current budget of the project and assert it against the expected input
	 */
	@Test
	public void test08_getBudget(){
		Project testProject = new Project("ATestProjec8", 1024.00, DateHelper.parse("06/05/2015", Config.DATE_FORMAT_SHORT), DateHelper.parse("08/05/2015", Config.DATE_FORMAT_SHORT), "A Project to test the DB");

		Double expectedBudget = 1024.00;
		
		int myID = project_model.insertNewProject(testProject);
		Project testProjectID = project_model.getProjectById(myID);
		Double actualBudget = testProjectID.getBudget();
				
		assertEquals("The ID of the projects inserted does not match the created project", expectedBudget, actualBudget);
	}
	
	/*This will change the current budget of the project and 
	 * very the change against the expected result
	 */
	@Test
	public void test09_setBudget(){
		Project testProject = new Project("ATestProjec9", 1024.00, DateHelper.parse("06/05/2015", Config.DATE_FORMAT_SHORT), DateHelper.parse("08/05/2015", Config.DATE_FORMAT_SHORT), "A Project to test the DB");

		Double expectedBudget = 1028.00;
		
		int myID = project_model.insertNewProject(testProject);
		Project testProjectID = project_model.getProjectById(myID);
		testProjectID.setBudget(1028.00);
		Double actualBudget = testProjectID.getBudget();
				
		assertEquals("The ID of the projects inserted does not match the created project", expectedBudget, actualBudget);
	}
	
	/*
	 * Get the description of a given project and verify against the input
	 * 
	 */
    @Test
    public void test10_getDescription() {
		Project testProject = new Project("ATestProjec10", 1024.00, DateHelper.parse("06/05/2015", Config.DATE_FORMAT_SHORT), DateHelper.parse("08/05/2015", Config.DATE_FORMAT_SHORT), "A Project to test the DB");

		String expectedDescription = "A Project to test the DB";
		
		int myID = project_model.insertNewProject(testProject);
		Project testProjectID = project_model.getProjectById(myID);
		String actualDescription = testProjectID.getDescription();
				
		assertEquals("The ID of the projects inserted does not match the created project", expectedDescription, actualDescription);
    }

    @Test
    public void test11_Project_TotalProjects_Calculated() {
        // Get the number of existing projects.
        List<Project> existingProjects = project_model.getAllProjects();
        int currentProjectCount = existingProjects.size();

        // Add a few projects to the database.
        Project project1 = createProject("project_1", 1000.00, "07/07/2016",
                "08/08/2016", "project_1_description");
        Project project2 = createProject("project_2", 2000.00, "09/09/2015",
                "10/10/2015", "project_2_description");
        project_model.insertNewProject(project1);
        project_model.insertNewProject(project2);

        // Get the new number of projects.
        existingProjects = project_model.getAllProjects();
        int newProjectCount = existingProjects.size();

        // Compare the values (new count should be old count + 2)
        assertTrue("Total number of projects should be updated when we add new projects",
                newProjectCount == currentProjectCount + 2);
    }

    private Project createProject(String name, double budget,
                                  String startDate, String deadlineDate, String description) {
        return new Project(name, budget, DateHelper.parse(startDate, Config.DATE_FORMAT),
                DateHelper.parse(deadlineDate, Config.DATE_FORMAT), description);
    }
}
