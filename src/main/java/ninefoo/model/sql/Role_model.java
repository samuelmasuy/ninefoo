package ninefoo.model.sql;

import ninefoo.config.Database;
import ninefoo.model.object.Role;
import ninefoo.model.sql.template.AbstractModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 02-Jun-2015.
 * @author Farzad MajidFayyaz
 */
public class Role_model extends AbstractModel{

    /**
     * Inserts a new role into the database.
     * @param role the Role object to be stored in the database.
     * @return True if successful, false otherwise.
     */
    public int insertNewRole(Role role) {
    	
    	// Open
    	this.open();

    	// Query
        sql = "INSERT INTO " +
              "role(role_name, description) VALUES(?, ?)";

        try {
        	
        	// Prepare
        	this.prepareStatement();
        	
        	// Data
        	ps.setString(1, role.getRoleName());
        	ps.setString(2, role.getDescription());
        	
        	// Run
            affectedRows = ps.executeUpdate();

            // If inserted
            if (affectedRows == 1) 
            	return this.getLastInsertId();

            LOGGER.warn("Updated row count was not equal to 1");

        // Error
        } catch (SQLException e) {
            LOGGER.error("Could not add member to db --- detailed info: " + e.getMessage());
        
        // Close
        } finally {
        	this.close();
        }

        return Database.ERROR;
    }

    /**
     * Returns the Role object from the database that is associated with the specified ID.
     * @param roleId the ID of the role to be searched for.
     * @return Role object corresponding to the ID, NULL if no role can be found for the specified ID.
     */
    public Role getRoleById(int roleId) {
    	
    	// Open
    	this.open();

    	// Query
        sql = "SELECT * FROM role WHERE role_id = ?";

        try {
        	
        	// Prepare
        	this.prepareStatement();
        	
        	// Data
        	ps.setInt(1, roleId);
        	
        	// Run
            result = ps.executeQuery();

            // Get role
            if (result.next()) {
                Role role = getNextRole(result);

                if (role != null)
                    return role;
            }

        // Error
        } catch (SQLException e) {
            LOGGER.error("Could not get role for id " + roleId + " from db --- " +
                    "detailed info: " + e.getMessage());
        
        // Close
        } finally {
        	this.close();
        }

        return null;
    }

    /**
     * Returns the Role object having the specified name (role_name column in UNIQUE in DB).
     * @param roleName name of the role to be searched for.
     * @return Role object corresponding to the name, NULL if no role can be found for the specified name.
     */
    public Role getRoleByName(String roleName) {
    	
    	// Open
    	this.open();
    	
    	// Query
        sql = "SELECT * FROM role WHERE role_name = ?";

        try {
        	
        	// Prepare
        	this.prepareStatement();
        	
        	// Data
        	ps.setString(1, roleName);
        	
        	// Run
            result = ps.executeQuery();

            // Get role
            if (result.next()) {
                Role role = getNextRole(result);

                if (role != null)
                    return role;
            }

        // Error
        } catch (SQLException e) {
            LOGGER.error("Could not get role for name '" + roleName + "' from db --- " +
                    "detailed info: " + e.getMessage());
        
        // Close
        } finally {
        	this.close();
        }

        return null;
    }

    /**
     * Returns all the roles stored in the database.
     * @return List of Role objects; empty ArrayList if there are no roles in the database; NULL
     *         if there is an error connecting to the database.
     */
    public List<Role> getAllRoles() {
    	
    	// Open
    	this.open();
    	
        List<Role> allRoles = new ArrayList<>();

        // Query
        sql = "SELECT * FROM Role";
        try {
        	// Prepare
        	this.prepareStatement();
        	
        	// Run
            result = ps.executeQuery();

            // Get all
            while (result.next()) {
                Role nextRole = getNextRole(result);

                if (nextRole != null)
                    allRoles.add(nextRole);
            }

            return allRoles;

        // Error
        } catch (SQLException e) {
            LOGGER.error("Could not get members from db --- detailed info: " + e.getMessage());
        
        // Close
        } finally {
        	this.close();
        }

        return null;
    }
}
