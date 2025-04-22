package dao;

import context.DBContext;
import entity.History;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HistoryDAO {

    public boolean executeUpdate(Connection conn, String command) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(command); // This will throw a SQLException if it fails
            return true;
        } finally {

            // This will run whether we throw an exception or not
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void insert(String date, String user, String product, int quantity, double totalprice)
            throws ClassNotFoundException, Exception {
        try {
            Connection conn = new DBContext().getConnection();
            String query = "INSERT INTO history (date, user, product, quantity, totalprice) "
                    + "VALUES ("
                    + "'" + date + "', "
                    + "'" + user + "', "
                    + "'" + product + "', "
                    + "'" + quantity + "', "
                    + "'" + totalprice + "')";
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("ERROR: Could not insert the record");
            e.printStackTrace();
            return;
        }
    }

    public List<History> getAllAccount() throws ClassNotFoundException {
        List<History> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String query = "select * from history ORDER BY date DESC";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                list.add(new History(
                        rs.getString("date"),
                        rs.getString("user"),
                        rs.getString("product"),
                        rs.getInt("quantity"),
                        rs.getDouble("totalprice")));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
