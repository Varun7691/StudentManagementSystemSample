package varun.com.studentmanagementsystemsample.utils;

/**
 * Created by VarunBarve on 12/05/2016.
 */
public class User {

    private int userId, roleId, userType;
    private String userName;

    public User(int userId, int roleId, int userType, String userName) {
        this.userId = userId;
        this.roleId = roleId;
        this.userType = userType;
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
