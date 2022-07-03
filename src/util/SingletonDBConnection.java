/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;



public class SingletonDBConnection {
    
    
    private static SingletonDBConnection instance ;
    private  Connection connection;
    private String userName="root";
    private String password="12345";
    private String dbUrl ="jdbc:mysql://localhost:3306/data";
    
    private SingletonDBConnection() throws SQLException{
    
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(dbUrl,userName,password);
            System.out.println("Bağlantı oluştu" );
        } catch (ClassNotFoundException ex) {
            System.out.println("Veritabanı Bağlantısı Oluşturulamadı :"+ex.getMessage());
        }
    
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    public static SingletonDBConnection getInstance() throws SQLException{
	  if (instance == null) {
            instance = new SingletonDBConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new SingletonDBConnection();
        }
        
	
        return instance;
    }
    
    
	
}
