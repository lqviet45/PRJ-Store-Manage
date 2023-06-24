
package sample.security;


public class GoogleConstants {
    public static final String GOOGLE_CLIENT_ID_SIGNIN = "725358676694-p17kmkch5e7snmmvic2mftgfa337vkf2.apps.googleusercontent.com";
    public static final String GOOGLE_CLIENT_SECRET_SIGNIN = "secret";
    public static final String GOOGLE_REDIRECT_URI_SIGNIN = "http://localhost:8080/PRJ301_T2S2_JSP/MainController?action=LoginGoogle";
    
    public static final String GOOGLE_CLIENT_ID_SIGNUP = "725358676694-le4itfrb327t4k6fe91h79t1qbddpsu0.apps.googleusercontent.com";
    public static final String GOOGLE_CLIENT_SECRET_SIGNUP = "secret";
    public static final String GOOGLE_REDIRECT_URI_SIGNUP = "http://localhost:8080/PRJ301_T2S2_JSP/MainController?action=CreateGoogle";
    
    public static final String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
    public static final String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
    public static final String GOOGLE_GRANT_TYPE = "authorization_code";
}
