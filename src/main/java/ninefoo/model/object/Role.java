package ninefoo.model.object;

/**
 * This class represents a role entity in the database.
 * Created on 30-May-2015.
 * @author Farzad MajidFayyaz
 */
public class Role {
    private int roleId;
    private String roleName;
    private String description;

    /**
     * This constructor is used when converting db entities to Java classes.
     * @param roleId ID of the role
     * @param roleName Name of the role
     */
    public Role(int roleId, String roleName, String description) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.description = description;
    }

    /**
     * This constructor is used when creating a new role object from Java.
     * @param roleName Name of the role
     */
    public Role(String roleName, String description) {
        this.roleName = roleName;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return String.format("Role [ID: %d, Name: '%s', Desc: '%s']", roleId, roleName, description);
    }
}
