package ninefoo.config;

import java.io.File;

import org.apache.logging.log4j.LogManager;

import ninefoo.config.Annotation.autoload;
import ninefoo.config.Annotation.autoloadConfig;
import ninefoo.lib.LanguageText;
import ninefoo.model.DbManager;
import ninefoo.model.Member;
import ninefoo.model.Member_model;
import ninefoo.model.Project;
import ninefoo.model.ProjectMember_model;
import ninefoo.model.Project_model;
import ninefoo.model.Role;
import ninefoo.model.Role_model;

/**
 * Methods in this class will be loaded automatically if they have the annotation <code>@autoload</code> and [optional] parameter <code>active = true</code> and [optional] parameter <code>priority = 0</code><br>
 * Note that the order of method execution in this class is random if priority attribute is not set (High priority = 0).<br>
 * To temporarily disable a method, make the <code>active = false</code> in the annotation.
 */
@autoloadConfig(lowestPriority = 2)
public class Autoload {
	
	// Logger
	private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
		
	@autoload
	public void loadEngLanguage(){
		LanguageText.setLanguage(LanguageText.ENGLISH);
		LOGGER.info("Auto loaded language is: English");
	}
	
	@autoload(active = false)
	public void loadFrLanguage(){
		LanguageText.setLanguage(LanguageText.FRENCH);
		LOGGER.info("Auto loaded language is: French");
	}
	
	@autoload
	public void loadLanguages(){
		
		// Add language classes - Exclude the Lang from the class name
		LanguageText.addLanguage("ValidationForm");
		LanguageText.addLanguage("InputForm");
	}
	
	@autoload
	public void createDB(){
		File db_file = new File(DbManager.dbName);
		if(!db_file.exists()){
			LOGGER.info(String.format("Database file '%s' created!", DbManager.dbName));
			DbManager.createTables();
		}
	}
	
	@autoload(active=true, priority = 1)
	public void createDemoUser(){
		Member newMember = new Member("hello", "hello", "hello", "hello");
		Member_model mm = new Member_model();
		mm.insertNewMember(newMember);
		LOGGER.info(String.format("Member %s added!", newMember.getUsername()));
	}
	
	@autoload(active=true, priority = 1)
	public void addRoles(){
		Role_model role_model = new Role_model();
		role_model.insertNewRole(new Role("Manager", ""));
		role_model.insertNewRole(new Role("Member", ""));
		LOGGER.info("Roles Manager and Member added to the database");
	}
	
	@autoload(active=true, priority = 2)
	public void addDummyProject(){
		Project_model project_model = new Project_model();
		project_model.insertNewProject(new Project("Amir", 100, null, null, null));
		ProjectMember_model pm_model = new ProjectMember_model();
		Role_model role = new Role_model();
		pm_model.addMemberToProject(1, 1, role.getRoleByName("Manager"));
		LOGGER.info("Inserted dummy project");
	}
}
