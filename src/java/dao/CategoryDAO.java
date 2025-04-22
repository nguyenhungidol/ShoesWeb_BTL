package dao;

import context.DBContext;
import entity.Category;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends DAO{

    public CategoryDAO() {
    }
    
    
    // Lay ra tat ca danh muc
    public List<Category> getAllCategory()  {
        List<Category> list = new ArrayList<>();
        String query = "select * from Category";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1),
                        rs.getString(2)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    
}
