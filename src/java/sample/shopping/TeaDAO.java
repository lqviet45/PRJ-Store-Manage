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

    private static final String GET_PRODUCT = "select * from tblProduct ORDER BY productID\n"
            + "	OFFSET ? ROWS  \n"
            + " FETCH NEXT ? ROWS ONLY";

    private static final String NUMBER_OF_PAGE = "SELECT COUNT(productID) as numberOfPage FROM tblProduct";
    
    public List<Tea> getAllProduct(int offSet, int noOfRecord) throws SQLException, NamingException {
        List<Tea> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUntils.getConnection();
            ptm = conn.prepareStatement(GET_PRODUCT);
            ptm.setInt(1, offSet);
            ptm.setInt(2, noOfRecord);
            rs = ptm.executeQuery();
            while (rs.next()) {
                Tea tea = new Tea(rs.getString("productID"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                );
                tea.setImg(rs.getString("image"));
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
}
