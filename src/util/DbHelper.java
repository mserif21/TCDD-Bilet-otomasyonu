/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Eyup Dal
 */
public class DbHelper {
         private String userName="root";
         private String password="12345";
         private String dbUrl ="jdbc:mysql://localhost:3306/data";
         
         public  Connection getConnection()throws SQLException
         {
         
         return DriverManager.getConnection(dbUrl,userName,password);
         
         }
         
         
         public void showErrorMessage(SQLException exception)
         {
             System.out.println("Error: "+exception.getMessage());
             System.out.println("Error code: "+exception.getErrorCode());
         }
}
