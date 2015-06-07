package ninefoo.model;

import ninefoo.config.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    public int insertNewRole(Role role) {
        Statement statement = DbManager.createConnectionStatement();

        if (statement == null) {
            LOGGER.warn("Could not get a connection statement to DB");
            return Database.ERROR;
        }

        String insertRoleSql = String.format("INSERT INTO " +
                "role(role_name, description) VALUES('%s', '%s')",
                role.getRoleName(), role.getDescription());

        try {
            int updatedRows = statement.executeUpdate(insertRoleSql);

            if (updatedRows == 1) {
                ResultSet rs = statement.executeQuery("SELECT last_insert_rowid()");

                if (rs.next())
                    return rs.getInt("last_insert_rowid()");
            }

            LOGGER.warn("Updated row count was not equal to 1");

        } catch (SQLException e) {
            LOGGER.error("Could not add member to db --- detailed info: " + e.getMessage());
        }

        return Database.ERROR;
    }

    // Helper method to get the next Role object from the DB ResultSet object.
    private Role getNextRole(ResultSet roles) {
        try {
            int roleId = roles.getInt("role_id");
            String roleName = roles.getString("role_name");
            String description = roles.getString("description");

            return new Role(roleId, roleName, description);

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
        }

        return null;
    }

    /**
     * Returns all the roles stored in the database.
     * @return List of Role objects; empty ArrayList if there are no roles in the database; NULL
     *         if there is an error connecting to the database.
     */
    public List<Role> getAllRoles() {
        List<Role> allRoles = new ArrayList<>();
        Statement statement = DbManager.createConnectionStatement();

        if (statement == null) {
            LOGGER.warn("Could not get a connection statement to DB");
            return null;
        }

        String getAllMembersSql = "SELECT * FROM Role";
        try {
            ResultSet allRolesFromDb = statement.executeQuery(getAllMembersSql);

            while (allRolesFromDb.next()) {
                Role nextRole = getNextRole(allRolesFromDb);

                if (nextRole != null)
                    allRoles.add(nextRole);
            }

            return allRoles;

        } catch (SQLException e) {
            LOGGER.error("Could not get members from db --- detailed info: " + e.getMessage());
        }

        return null;
    }
}
