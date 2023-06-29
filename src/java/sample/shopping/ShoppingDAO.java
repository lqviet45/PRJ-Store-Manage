package sample.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import sample.untils.DBUntils;

public class ShoppingDAO {

    private static final String SAVE_ORDER = "SET IDENTITY_INSERT tblOrder ON \n" + "INSERT INTO tblOrder(orderID, userID, [date], total)\n"
            + "VALUES (?,?,?,?)";

    private static final String CHECK_ORDER_ID = "select MAX(orderID) as orderID from tblOrder";

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
}
