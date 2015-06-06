package ninefoo.model;

/**
 * This class represents a role entity in the database.
 * Created by Farzad on 30-May-2015.
 */
public class Role {
    private int roleId;
    private String roleName;

    /**
     * This constructor is used when converting db entities to Java classes.
     * @param roleId ID of the role
     * @param roleName Name of the role
     */
    Role(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    /**
     * This constructor is used when creating a new role object from Java.
     * @param roleName Name of the role
     */
    public Role(String roleName) {
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String toString() {
        return String.format("Role [ID: %d, Name: '%s']", roleId, roleName);
    }
}
