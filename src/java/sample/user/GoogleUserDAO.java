package sample.user;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import sample.security.GoogleConstants;
import sample.untils.DBUntils;

public class GoogleUserDAO {

    private static final String LOGIN = "SELECT fullName, roleID FROM tblUsers WHERE userID = ? AND email = ? AND isGoogleAccount = 1";
    private static final String INSERT = "INSERT INTO tblUsers(userID, fullName, roleID, email"
            + ", isGoogleAccount) VALUES(?,?,?,?, 1)";

    public String getToken(final String code) throws ClientProtocolException, IOException {
        String response = Request.Post(GoogleConstants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", GoogleConstants.GOOGLE_CLIENT_ID_SIGNIN)
                        .add("client_secret", GoogleConstants.GOOGLE_CLIENT_SECRET_SIGNIN)
                        .add("redirect_uri", GoogleConstants.GOOGLE_REDIRECT_URI_SIGNIN)
                        .add("code", code)
                        .add("grant_type", GoogleConstants.GOOGLE_GRANT_TYPE)
                        .build())
                .execute().returnContent().asString();
        JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jsonObject.get("access_token")
                .toString().replaceAll("\"", "");
        return accessToken;
    }
    
    public String getTokenSignUp(final String code) throws ClientProtocolException, IOException {
        String response = Request.Post(GoogleConstants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", GoogleConstants.GOOGLE_CLIENT_ID_SIGNUP)
                        .add("client_secret", GoogleConstants.GOOGLE_CLIENT_SECRET_SIGNUP)
                        .add("redirect_uri", GoogleConstants.GOOGLE_REDIRECT_URI_SIGNUP)
                        .add("code", code)
                        .add("grant_type", GoogleConstants.GOOGLE_GRANT_TYPE)
                        .build())
                .execute().returnContent().asString();
        JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jsonObject.get("access_token")
                .toString().replaceAll("\"", "");
        return accessToken;
    }

    public GoogleUserDTO getUserInfo(final String accessToken) throws IOException {
        String link = GoogleConstants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute()
                .returnContent().asString();
        GoogleUserDTO googleUser = new Gson().fromJson(response, GoogleUserDTO.class);
        System.err.println(googleUser);
        return googleUser;
    }

    public UserDTO checkLogin(String userID, String email) throws SQLException, NamingException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUntils.getConnection();
            ptm = conn.prepareStatement(LOGIN);
            ptm.setString(1, userID);
            ptm.setString(2, email);
            rs = ptm.executeQuery();

            if (rs.next()) {
                String fullName = rs.getString("fullName");
                String roleID = rs.getString("roleID");
                String password = "***";
                user = new UserDTO(userID, fullName, roleID, email, password);
            }
        } finally {
            DBUntils.quietClose(conn, ptm, rs);
        }
        return user;
    }

    public boolean insertGoogleUser(GoogleUserDTO googleUser) throws SQLException, NamingException {
        boolean checkUpdate = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUntils.getConnection();
            ptm = conn.prepareStatement(INSERT);
            ptm.setString(1, googleUser.getName());
            ptm.setString(2, googleUser.getGiven_name());
            ptm.setString(3, "US");
            ptm.setString(4, googleUser.getEmail());
            checkUpdate = ptm.executeUpdate() > 0;
        } finally {
            DBUntils.quietClose(conn, ptm);
        }
        return checkUpdate;
    }
}
