package ninefoo.config;

import java.util.ArrayList;

import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;

import ninefoo.config.Annotation.autoload;
import ninefoo.config.Annotation.autoloadConfig;
import ninefoo.helper.StringHelper;
import ninefoo.lib.lang.LanguageText;
import ninefoo.model.DbManager;
import ninefoo.model.object.Member;
import ninefoo.model.object.Project;
import ninefoo.model.object.Role;
import ninefoo.model.sql.Member_model;
import ninefoo.model.sql.ProjectMember_model;
import ninefoo.model.sql.Project_model;
import ninefoo.model.sql.Role_model;

/**
 * Methods in this class will be loaded automatically if they have the annotation <code>@autoload</code> and [optional] parameter <code>active = true</code> and [optional] parameter <code>priority = 0</code><br>
 * Note that the order of method execution in this class is random if priority attribute is not set (High priority = 0).<br>
 * To temporarily disable a method, make the <code>active = false</code> in the annotation.
 * @author Amir EL Bawab
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
	
	@autoload
	public void setLookAndFeel(){
		
		//Look and feel for the cross platform 
		try {
			// Set cross-platform Java L&F
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			LOGGER.info("Cross platform F&L applied to the GUI successfully");
		} catch (Exception e) {
			LOGGER.error("GUI Look and Feel error!");
		}
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
		LanguageText.addLanguage("Activity");
		LanguageText.addLanguage("Project");
	}
	
	@autoload
	public void createDB(){
//		File db_file = new File(DbManager.dbName);
//		if(!db_file.exists()){
			LOGGER.info(String.format("Database file '%s' created!", DbManager.dbName));
			DbManager.createTables();
//		} else {
//			LOGGER.info(String.format("Database file '%s' already exist!", DbManager.dbName));
//		}
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
		ArrayList<Role> roles = new ArrayList<>(2);
		roles.add(new Role(RoleNames.MANAGER, "Full privileges"));
		roles.add(new Role(RoleNames.MEMBER, "Limited privileges"));
		
		String rolesCreated = "";
		for(Role role : roles){
			if(role_model.getRoleByName(role.getRoleName()) == null){
				rolesCreated = StringHelper.join(rolesCreated, role.getRoleName());
				role_model.insertNewRole(role);
			}
		}
		LOGGER.info(String.format("Roles %s added to the database", rolesCreated));
	}
	
	@autoload(active=false, priority = 2)
	public void addDummyProject(){
		Project_model project_model = new Project_model();
		int id = project_model.insertNewProject(new Project("Amir", 100, null, null, null));
		ProjectMember_model pm_model = new ProjectMember_model();
		Role_model role = new Role_model();
		pm_model.addMemberToProject(id, 1, role.getRoleByName("Manager"));
		LOGGER.info("Inserted dummy project");
	}
}
