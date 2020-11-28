package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.*;
import java.util.ArrayList;

/**
 * Web application lifecycle listener.
 *
 * @author isame
 */
public class DBListener implements ServletContextListener {
    public static final String DRIVER_CLASS ="org.sqlite.JDBC";
    public static final String DATABASE_URL ="jdbc:sqlite:aula12.db";
    
    public static Exception e = null;
    
    public static Connection getConnection() throws Exception{
        return DriverManager.getConnection(DATABASE_URL);
    }
    
    public static int getUsersCount(){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int count = 0;
        try{
             Class.forName(DRIVER_CLASS);
               con = getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT COUNT(*) as count FROM users");
                if(rs.next()){
                    count = rs.getInt("count");
                }
            
            rs.close();
            stmt.close();
            con.close();
            
        }catch(Exception ex){
            e = ex;
            try{rs.close();}catch(Exception ex2){}
            try{stmt.close();}catch(Exception ex2){}
            try{con.close();}catch(Exception ex2){}          
        }
        return count;
    }
    
    public static ArrayList<String> getUsersEmail(){
        ArrayList<String> list = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int count = 0;
        try{
             Class.forName(DRIVER_CLASS);
               con = getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT email FROM users");
                while(rs.next()){
                   list.add(rs.getString("email"));
                }
            
            rs.close();
            stmt.close();
            con.close();
            
        }catch(Exception ex){
            e = ex;
            try{rs.close();}catch(Exception ex2){}
            try{stmt.close();}catch(Exception ex2){}
            try{con.close();}catch(Exception ex2){}          
        }
        return list;
    }
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Connection con = null;
        Statement stmt = null;
        
        try{
             Class.forName(DRIVER_CLASS);
               con = getConnection();
                stmt = con.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS users("
                    + "name VARCHAR(200) NOT NULL,"
                    + "email VARCHAR(250) PRIMARY KEY,"
                    + "password_hash INTEGER"
                    + " )");
            
           if(getUsersCount()==0){
               stmt.execute("INSERT INTO users VALUES("
                       + "'adm',"
                       + "'admin@admin.com',"
                       + "1234".hashCode()
                       +")");
           }
            stmt.close();
            con.close();
        }catch(Exception ex){
            e = ex;
            try{stmt.close();}catch(Exception ex2){}
            try{con.close();}catch(Exception ex2){}
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
