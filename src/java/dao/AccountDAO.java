package dao;

import context.DBContext;
import entity.Account;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO extends DAO {

    public AccountDAO() {
    }

    //Kiem tra dang nhap
    public Account checkLogin(String user, String pass) {
        String query = "select * from account\n"
                + "where user = ? and pass = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)
                );
            }

        } catch (Exception e) {
        }
        return null;
    }

    //Kiem tra tai khoan da ton tai chua?
    public Account checkAccountExist(String user) {
        String query = "select * from account\n"
                + "where user = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)
                );
            }

        } catch (Exception e) {
        }
        return null;
    }

//    public Account getAccountById(String id) {
//        String query = "SELECT * FROM account where uID=?";
//        try {
//            conn = new DBContext().getConnection();//mo ket noi voi sql
//            ps = conn.prepareStatement(query);
//            ps.setString(1, id);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                return new Account(
//                        rs.getInt(1),
//                        rs.getString(2),
//                        rs.getString(3),
//                        rs.getInt(4),
//                        rs.getInt(5),
//                        rs.getString(6),
//                        rs.getString(7),
//                        rs.getString(8)
//                );
//            }
//
//        } catch (Exception e) {
//        }
//        return null;
//    }
    // Sign Up: Them Account moi vao Database
    public void signUp(Account account) {
        String query = "insert into account (user, pass, isSell, isAdmin, name, address, phone)\n"
                + "values (?, ?, 0, 0, ?, ?, ?)"; //0,0 : not Seller, not Admin => Normal User
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, account.getUser());
            ps.setString(2, account.getPass());
            ps.setString(3, account.getName());
            ps.setString(4, account.getAddress());
            ps.setString(5, account.getPhone());

            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<Account> getAllAccount() {
        List<Account> list = new ArrayList<>();
        String query = "select * from account";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Account(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8))
                );
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void update(Account account) {
        String query = "update account set pass = ?, name = ?, address = ?, phone = ? where user = ?;";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, account.getPass());
            ps.setString(2, account.getName());
            ps.setString(3, account.getAddress());
            ps.setString(4, account.getPhone());
            ps.setString(5, account.getUser());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean delete(int id) {
    String query = "DELETE FROM account WHERE id = ?";
    try {
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        
        int result = ps.executeUpdate();
        return result > 0; // Trả về true nếu xóa thành công, ngược lại trả về false
    } catch (Exception e) {
        e.printStackTrace();
        return false; // Trả về false nếu gặp lỗi
    } finally {
        try {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


    public Account getAccountbyId(String id) throws ClassNotFoundException {
        //san pham moi nhat: co ID cao nhat
        String query = "select * from account\n"
                + "where id = ?";
        Account account = new Account();
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                account = new Account(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)
                );
            }
            return account;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account getAccountbyId2(int id) {
        String query = "SELECT * FROM account WHERE id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Account(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)
                );
            }
        } catch (Exception e) {
            e.printStackTrace(); // log lỗi cho dễ debug
        }
        return null;
    }

}
