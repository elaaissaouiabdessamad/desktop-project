/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author hp
 */
public class DataBase {
    
    public static Connection connectDB()
    {
        try
        {
             Class.forName("com.mysql.cj.jdbc.Driver");
             Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
             return connect ;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null ;
       
        
    }
    
    
    
}
