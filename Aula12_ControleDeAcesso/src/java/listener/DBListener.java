package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.*;

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
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try{
             Class.forName(DRIVER_CLASS);
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS users("
                    + "name VARCHAR(200) NOT NULL,"
                    + "email VARCHAR(250) PRIMARY KEY,"
                    + "password_hash INTEGER"
                    + " )");
            stmt.close();
            con.close();
        }catch(Exception ex){
            e = ex;
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
