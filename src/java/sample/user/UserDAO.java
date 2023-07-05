package sample.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sample.untils.DBUntils;

/**
 *
 * @author DELL
 */
public class UserDAO {

    private static final String CHECK_DUPLICATE = "SELECT fullName, roleID FROM tblUsers WHERE userID = ?";

    private static final String LOGIN = "SELECT fullName, roleID FROM tblUsers WHERE userID = ? AND password = ?";

    private static final String SEARCH = "SELECT userID, fullName, roleID, email FROM tblUsers WHERE fullName like ?";

    private static final String DELETE = "DELETE tblUsers WHERE userID = ?";

    private static final String UPDATE = "UPDATE tblUsers SET fullName = ?, roleID = ?, email = ? WHERE userID = ?";

    private static final String INSERT = "INSERT INTO tblUsers(userID, fullName, roleID, status, email, password) VALUES(?,?,?, 0,?,?)";
    
    private static final String SAVE_TOKEN = "UPDATE tblUsers SET token = ? WHERE userID = ?";

    private static final String VERIFY = "UPDATE tblUsers SET status = 1 WHERE email = ? AND token = ?";
    
    public UserDTO checkLogin(String userID, String password) throws SQLException, NamingException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUntils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LOGIN);
                ptm.setString(1, userID);
                ptm.setString(2, password);
                rs = ptm.executeQuery();

                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    user = new UserDTO(userID, fullName, roleID, "", "");
                }
            }
        } finally {
            DBUntils.quietClose(conn, ptm, rs);
        }

        return user;
    }

    public List<UserDTO> getListUser(String search) throws SQLException, NamingException {
        List<UserDTO> listUser = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUntils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();

                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    String email = rs.getString("email");
                    String password = "***";
                    listUser.add(new UserDTO(userID, fullName, roleID, email, password));
                }
            }
        } finally {
            DBUntils.quietClose(conn, ptm, rs);
        }

        return listUser;
    }

    public boolean delete(String userID) throws SQLException, NamingException {
        boolean checkDelete = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUntils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setString(1, userID);
                checkDelete = ptm.executeUpdate() > 0;
            }
        } finally {
            DBUntils.quietClose(conn, ptm);
        }
        return checkDelete;
    }

    public boolean update(UserDTO user) throws SQLException, NamingException {
        boolean checkUpdate = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUntils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, user.getFullName());
                ptm.setString(2, user.getRoleID());
                ptm.setString(3, user.getEmail());
                ptm.setString(4, user.getUserID());
                checkUpdate = ptm.executeUpdate() > 0;
            }
        } finally {
            DBUntils.quietClose(conn, ptm);
        }
        return checkUpdate;
    }

    public boolean checkDuplicate(String userID) throws SQLException, NamingException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUntils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, userID);
                rs = ptm.executeQuery();

                if (rs.next()) {
                    check = true;
                }
            }
        } finally {
            DBUntils.quietClose(conn, ptm, rs);

        }
        return check;
    }

    public boolean insert(UserDTO user) throws SQLException {
        boolean checkUpdate = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUntils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT);
                ptm.setString(1, user.getUserID());
                ptm.setString(2, user.getFullName());
                ptm.setString(3, user.getRoleID());
                ptm.setString(4, user.getEmail());
                ptm.setString(5, user.getPassword());
                checkUpdate = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUntils.quietClose(conn, ptm);
        }
        return checkUpdate;
    }

    public boolean insertV2(UserDTO user) throws SQLException, NamingException {
        boolean checkUpdate = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUntils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT);
                ptm.setString(1, user.getUserID());
                ptm.setString(2, user.getFullName());
                ptm.setString(3, user.getRoleID());
                ptm.setString(4, user.getEmail());
                ptm.setString(5, user.getPassword());
                checkUpdate = ptm.executeUpdate() > 0 ? true : false;
            }
        } finally {
            DBUntils.quietClose(conn, ptm);
        }
        return checkUpdate;
    }

    public boolean saveToken(UserDTO user) throws SQLException, NamingException {
        boolean checkSave = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUntils.getConnection();
            ptm = conn.prepareStatement(SAVE_TOKEN);
            ptm.setString(1, user.getToken());
            ptm.setString(2, user.getUserID());
            checkSave = ptm.executeUpdate() > 0;
        } finally {
            DBUntils.quietClose(conn, ptm);
        }
        return checkSave;
    }
    
    public boolean verify(String token, String email) throws SQLException, NamingException {
        boolean checkVerify = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUntils.getConnection();
            ptm = conn.prepareStatement(VERIFY);
            ptm.setString(1, email);
            ptm.setString(2, token);
            checkVerify = ptm.executeUpdate() > 0;
        } finally {
            DBUntils.quietClose(conn, ptm);
        }
        return checkVerify;
    }
}