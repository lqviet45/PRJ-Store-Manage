/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.untils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author DELL
 */
public class DBUntils {

    private final static String DB = "UserManagement";
    private final static String USER_NAME = "sa";
    private final static String PASSWORD = "12345";

    public static Connection getConnection1() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databasename=" + DB;
        conn = DriverManager.getConnection(url, USER_NAME, PASSWORD);
        return conn;
    }
    
    public static Connection getConnection() throws SQLException, NamingException {
        Connection conn = null;
        Context context = new InitialContext();
        Context end = (Context) context.lookup("java:comp/env");
        DataSource ds = (DataSource) end.lookup("DBCon");
        conn = ds.getConnection();
        return conn;
    }
    
    public static void quietClose(Connection conn, PreparedStatement ptm) throws SQLException {
        if(conn != null) conn.close();
        if(ptm != null) ptm.close();
    }
    
    public static void quietClose(Connection conn, PreparedStatement ptm, ResultSet rs) throws SQLException {
        if(conn != null) conn.close();
        if(ptm != null) ptm.close();
        if(rs != null) rs.close();
    }
}
