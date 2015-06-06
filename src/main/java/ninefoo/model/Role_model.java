package ninefoo.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Farzad on 02-Jun-2015.
 */
public class Role_model {
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Inserts a new role into the database.
     * @param role the Role object to be stored in the database.
     * @return True if successful, false otherwise.
     */
    public boolean insertNewRole(Role role) {
        Statement statement = DbManager.createConnectionStatement();
        if (statement == null)
            return false;

        String insertMemberSql = String.format("INSERT INTO " +
                        "role(role_name) VALUES('%s')", role.getRoleName());

        try {
            statement.executeUpdate(insertMemberSql);
            return true;

        } catch (SQLException e) {
            LOGGER.error("Could not add role to db --- detailed info: " + e.getMessage());
        } finally {
            DbManager.closeConnection();
        }

        return false;
    }

    // Helper method to get the next Role object from the DB ResultSet object.
    private Role getNextRole(ResultSet roles) {
        try {
            int roleId = roles.getInt("role_id");
            String roleName = roles.getString("role_name");

            return new Role(roleId, roleName);

        } catch (SQLException e) {
            LOGGER.error("Could not get next role from db --- detailed info: " + e.getMessage());
        }

        return null;

    }

    /**
     * Returns the Role object from the database that is associated with the specified ID.
     * @param roleId the ID of the role to be searched for.
     * @return Role object corresponding to the ID, NULL if no role can be found for the specified ID.
     */
    public Role getRoleById(int roleId) {
        Statement statement = DbManager.createConnectionStatement();

        if (statement == null)
            return null;

        String getRoleByIdSql = "SELECT * FROM role WHERE role_id = " + roleId;

        try {
            ResultSet roles = statement.executeQuery(getRoleByIdSql);

            if (roles.next()) {
                Role role = getNextRole(roles);

                if (role != null)
                    return role;
            }

        } catch (SQLException e) {
            LOGGER.error("Could not get role for id " + roleId + " from db --- " +
                    "detailed info: " + e.getMessage());
        } finally {
            DbManager.closeConnection();
        }

        return null;
    }

    /**
     * Returns the Role object having the specified name (role_name column in UNIQUE in DB).
     * @param roleName name of the role to be searched for.
     * @return Role object corresponding to the name, NULL if no role can be found for the specified name.
     */
    public Role getRoleByName(String roleName) {
        Statement statement = DbManager.createConnectionStatement();

        if (statement == null)
            return null;

        String getRoleByNameSql = String.format("SELECT * FROM role WHERE role_name = '%s'",
                roleName);

        try {
            ResultSet roles = statement.executeQuery(getRoleByNameSql);

            if (roles.next()) {
                Role role = getNextRole(roles);

                if (role != null)
                    return role;
            }

        } catch (SQLException e) {
            LOGGER.error("Could not get role for name '" + roleName + "' from db --- " +
                    "detailed info: " + e.getMessage());
        } finally {
            DbManager.closeConnection();
        }

        return null;
    }
}
