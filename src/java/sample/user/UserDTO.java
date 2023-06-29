package sample.user;

public class UserDTO {

    private String userID;
    private String fullName;
    private String roleID;
    private String email;
    private String password;
    private String token;

    public UserDTO() {
        this.userID = "";
        this.fullName = "";
        this.roleID = "";
        this.email = "";
        this.password = "";
    }

    public UserDTO(String userID, String fullName, String roleID, String email,String password) {
        this.userID = userID;
        this.fullName = fullName;
        this.roleID = roleID;
        this.email = email;
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String SroleID) {
        this.roleID = SroleID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
