/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.DbHelper;

/**
 *
 * @author Eyup Dal
 */
public class Kullanici {
    
    DbHelper helper;
    Connection conn;
    
    
    public int Getir_kullanici() throws SQLException
    {
        String sql = "SELECT * FROM Temp";
 
        helper = new DbHelper();
        conn = helper.getConnection();
        
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        
        int count = 0;
        int id = 0 ;
        while (result.next()){
            
            count++;
            id = result.getInt("id");
            String username = result.getString("username");
            String pass = result.getString("password");
            
            System.out.println(count+ " kisi ");
            
        }
        
    
        return id;
        
    }
    
    
    
}
