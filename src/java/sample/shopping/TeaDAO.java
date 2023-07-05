package sample.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sample.untils.DBUntils;

public class TeaDAO {

    private static final String GET_PRODUCT_PAGING = "SELECT * FROM tblProduct ORDER BY productID\n"
            + "	OFFSET ? ROWS  \n"
            + " FETCH NEXT ? ROWS ONLY";

    private static final String NUMBER_OF_PAGE = "SELECT COUNT(productID) as numberOfPage FROM tblProduct";

    private static final String GET_PRODUCT = "SELECT productID, [name], price, quantity\n"
            + ", [image] FROM tblProduct WHERE [name] LIKE ?";

    private static final String UPDATE_PRODUCT = "UPDATE tblProduct SET [name] = ?, price = ?, quantity = ?, [image] = ?\n"
            + "WHERE productID = ?";

    private static final String INSERT_PRODUCT = "INSERT INTO tblProduct(productID, [name], price, quantity, [image])\n"
            + "VALUES (?,?,?,?,?)";

    private static final String DELETE_PRODUCT = "DELETE tblProduct WHERE productID = ?";
    
    public List<Tea> getAllProductPaging(int offSet, int noOfRecord) throws SQLException, NamingException {
        List<Tea> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUntils.getConnection();
            ptm = conn.prepareStatement(GET_PRODUCT_PAGING);
            ptm.setInt(1, offSet);
            ptm.setInt(2, noOfRecord);
            rs = ptm.executeQuery();
            while (rs.next()) {
                Tea tea = new Tea(rs.getString("productID"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getString("image")
                );
                list.add(tea);
            }
        } finally {
            DBUntils.quietClose(conn, ptm, rs);
        }
        return list;
    }

    public int getNumberOfPage() throws SQLException, NamingException {
        int numberOfPage = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUntils.getConnection();
            ptm = conn.prepareStatement(NUMBER_OF_PAGE);
            rs = ptm.executeQuery();
            if (rs.next()) {
                numberOfPage = rs.getInt("numberOfPage");
            }
        } finally {
            DBUntils.quietClose(conn, ptm, rs);
        }
        return numberOfPage;
    }

    public List<Tea> getAllProduct(String pName) throws SQLException, NamingException {
        List<Tea> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUntils.getConnection();
            ptm = conn.prepareStatement(GET_PRODUCT);
            ptm.setString(1, "%" + pName + "%");
            rs = ptm.executeQuery();
            while (rs.next()) {
                Tea tea = new Tea(rs.getString("productID"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getString("image")
                );
                list.add(tea);
            }
        } finally {
            DBUntils.quietClose(conn, ptm, rs);
        }
        return list;
    }

    public boolean Update(Tea tea) throws SQLException, NamingException {
        boolean checkUpdate = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUntils.getConnection();
            ptm = conn.prepareStatement(UPDATE_PRODUCT);
            ptm.setString(1, tea.getName());
            ptm.setDouble(2, tea.getPrice());
            ptm.setInt(3, tea.getQuantity());
            ptm.setString(4, tea.getImg());
            ptm.setString(5, tea.getId());
            checkUpdate = ptm.executeUpdate() > 0;
        } finally {
            DBUntils.quietClose(conn, ptm);
        }
        return checkUpdate;
    }

    public boolean insertProduct(Tea tea) throws SQLException, NamingException {
        boolean checkInsert = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUntils.getConnection();
            ptm = conn.prepareStatement(INSERT_PRODUCT);
            ptm.setString(1, tea.getId());
            ptm.setString(2, tea.getName());
            ptm.setDouble(3, tea.getPrice());
            ptm.setInt(4, tea.getQuantity());
            ptm.setString(5, tea.getImg());
            checkInsert = ptm.executeUpdate() > 0;
        } finally {
            DBUntils.quietClose(conn, ptm);
        }
        return checkInsert;
    }
    
    public boolean deleteProduct(String teaID) throws SQLException, NamingException {
        boolean checkDelete = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUntils.getConnection();
            ptm = conn.prepareStatement(DELETE_PRODUCT);
            ptm.setString(1, teaID);
            checkDelete = ptm.executeUpdate() > 0;
        } finally {
            DBUntils.quietClose(conn, ptm);
        }      
        return checkDelete;
    }
}