package sample.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import sample.untils.DBUntils;
import sample.user.UserDTO;

public class ShoppingDAO {

    private static final String SAVE_ORDER = "SET IDENTITY_INSERT tblOrder ON \n" + "INSERT INTO tblOrder(orderID, userID, [date], total)\n"
            + "VALUES (?,?,?,?)";

    private static final String REMOVE_ORDER = "delete tblOrder where orderID = ?";

    private static final String CHECK_ORDER_ID = "select MAX(orderID) as orderID from tblOrder";

    private static final String CHECK_ORDER_DETAIL_ID = "select MAX(orderDetailID) as orderID from tblOrderDetail";

    private static final String CHECK_QUANTITY = "SELECT quantity FROM tblProduct WHERE productID = ?";

    private static final String CHECK_SAVE_ORDER_DETAIL = "SET IDENTITY_INSERT tblOrderDetail ON\n"
            + "insert into tblOrderDetail(orderDetailID, productID, orderID, price, quantity)\n"
            + "values(?, ?, ?, ?, ?)";

    private static final String UPDATE_PRODUCT_QUANTITY = "Update tblProduct set quantity = quantity - \n"
            + "(select od.quantity from tblOrderDetail od where od.productID = ?)\n"
            + "where productID = ?";

    public boolean saveOrder(Order order) throws SQLException, NamingException {
        boolean checkSave = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUntils.getConnection();
            ptm = conn.prepareStatement(SAVE_ORDER);
            ptm.setInt(1, order.getOrderID());
            ptm.setString(2, order.getUserID());
            ptm.setDate(3, order.getDate());
            ptm.setDouble(4, order.getTotal());
            checkSave = ptm.executeUpdate() > 0;
        } finally {
            DBUntils.quietClose(conn, ptm, rs);
        }
        return checkSave;
    }

    public boolean removeOrder(Order order) throws SQLException, NamingException {
        boolean checkRemove = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUntils.getConnection();
            ptm = conn.prepareStatement(REMOVE_ORDER);
            ptm.setInt(1, order.getOrderID());
            checkRemove = ptm.executeUpdate() > 0;
        } finally {
            DBUntils.quietClose(conn, ptm, rs);
        }
        return checkRemove;
    }

    public int getOrderID() throws SQLException, NamingException {
        int orderID = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUntils.getConnection();
            ptm = conn.prepareStatement(CHECK_ORDER_ID);
            rs = ptm.executeQuery();
            if (rs.next()) {
                orderID = rs.getInt("orderID");
                if (orderID == 0) {
                    return orderID = 1;
                }
            }
        } finally {
            DBUntils.quietClose(conn, ptm, rs);
        }
        return orderID + 1;
    }

    public int checkProductQuantity(String productID) throws SQLException, NamingException {
        int quantity = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUntils.getConnection();
            ptm = conn.prepareStatement(CHECK_QUANTITY);
            ptm.setString(1, productID);
            rs = ptm.executeQuery();
            if (rs.next()) {
                quantity = rs.getInt("quantity");
            }
        } finally {
            DBUntils.quietClose(conn, ptm, rs);
        }
        return quantity;
    }

    public boolean saveOrderDetail(int orderDetailID, Order order, Tea tea) throws SQLException, NamingException {
        boolean checkSave = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUntils.getConnection();
            ptm = conn.prepareStatement(CHECK_SAVE_ORDER_DETAIL);
            ptm.setInt(1, orderDetailID);
            ptm.setString(2, tea.getId());
            ptm.setInt(3, order.getOrderID());
            ptm.setDouble(4, tea.getPrice() * tea.getQuantity());
            ptm.setInt(5, tea.getQuantity());
            checkSave = ptm.executeUpdate() > 0;
        } finally {
            DBUntils.quietClose(conn, ptm);
        }

        return checkSave;
    }

    public int getOrderDetailID() throws SQLException, NamingException {
        int orderDetailID = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUntils.getConnection();
            ptm = conn.prepareStatement(CHECK_ORDER_DETAIL_ID);
            rs = ptm.executeQuery();
            if (rs.next()) {
                orderDetailID = rs.getInt("orderID");
                if (orderDetailID == 0) {
                    return orderDetailID = 1;
                }
            }
        } finally {
            DBUntils.quietClose(conn, ptm, rs);
        }
        return orderDetailID + 1;
    }
    
    public boolean updateProductQuantity(String productID) throws SQLException, NamingException {
        boolean checkUpdate = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUntils.getConnection();
            ptm = conn.prepareStatement(UPDATE_PRODUCT_QUANTITY);
            ptm.setString(1, productID);
            ptm.setString(2, productID);
            checkUpdate = ptm.executeUpdate() > 0;
        } finally {
            DBUntils.quietClose(conn, ptm);
        }
        
        return checkUpdate;
    }

}
