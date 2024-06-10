/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edutrack;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admins
 */
public class mysql 
{
    public Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/"; 
        String dbName = "edutrack"; 
        String strUnicode = "?useUnicode=true&characterEncoding=utf8";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root"; 
        String password = "";
        
    public mysql()
    {
        try {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url+dbName+strUnicode,userName,password);
          
        }catch(SQLException se)
            {
               se.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }
    }
}
