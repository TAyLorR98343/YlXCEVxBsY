// 代码生成时间: 2025-09-19 20:36:17
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.HashMap;
import java.util.Map;

/**
 * ManagedBean for User Permission Management.
 * This class handles user permissions and provides methods to
 * manage them.
 */
@ManagedBean
@SessionScoped
public class UserPermissionManager {

    private Map<String, Boolean> userPermissions;

    /**
     * Initializes the UserPermissionManager with an empty permissions map.
     */
    public UserPermissionManager() {
        this.userPermissions = new HashMap<>();
    }

    /**
     * Adds or updates a user permission.
     *
     * @param username The username for whom the permission is being set.
     * @param permission The permission to be added or updated.
     * @param value The value of the permission (true for allowed, false for denied).
     */
    public void setPermission(String username, String permission, boolean value) {
        userPermissions.put(createKey(username, permission), value);
    }

    /**
     * Checks if a user has a specific permission.
     *
     * @param username The username to check.
     * @param permission The permission to check for.
     * @return true if the user has the permission, false otherwise.
     */
    public boolean hasPermission(String username, String permission) {
        Boolean hasPermission = userPermissions.get(createKey(username, permission));
        return hasPermission != null && hasPermission;
    }

    /**
     * Removes a user permission.
     *
     * @param username The username for whom the permission is being removed.
     * @param permission The permission to be removed.
     */
    public void removePermission(String username, String permission) {
        userPermissions.remove(createKey(username, permission));
    }

    /**
     * Creates a unique key for a user permission based on username and permission.
     *
     * @param username The username.
     * @param permission The permission.
     * @return A unique String key for the permission.
     */
    private String createKey(String username, String permission) {
        return username + "_" + permission;
    }

    // Getters and setters for userPermissions
    public Map<String, Boolean> getUserPermissions() {
        return userPermissions;
    }

    public void setUserPermissions(Map<String, Boolean> userPermissions) {
        this.userPermissions = userPermissions;
    }
}
