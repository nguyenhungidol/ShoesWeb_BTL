package context;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
    private final String serverName = "localhost";
    private final String portNumber = "3306";
    private final String dbName = "ShoesWeb";
    private final String userID = "root";
    private final String password = "hung123321@";
    
    public Connection getConnection()throws Exception, ClassNotFoundException {
        try {
        String url = "jdbc:mysql://"+serverName+":"+portNumber+"/"+dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, userID, password);
        
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }   
}
