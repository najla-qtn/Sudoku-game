/*
    الاتصال بالقواعد البيانات
 */
package sudokugame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *<p> 
 *This Class Will Connect us to the DataBase , So we can execute and update the query on the DataBase .
 *
 * @version  8.0.2
 * @since  8.0.2
 * @author  Group 13
 */
public class DBManager {
    
     public static final String USER_NAME = "root"; 
     public static final String PSSWORD = "root" ; 
     public static final  String url = "jdbc:mysql://127.0.0.1:3306/lab8?autoReconnect=true&useSSL=false";
     
     
     /** 
      * This method is used to allow to connect with database
      * @return connection object to connected with schema on data base
      */
     
    public static Connection getConnection(){
        Connection con = null;
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception ex){
            System.out.println("something wrong with driver");
        }
        
        try{
                con =DriverManager.getConnection(url,USER_NAME,PSSWORD);
            
        }
        catch(SQLException ex){
            System.err.println(ex);
        }
        return con;
    }
    
    
}
