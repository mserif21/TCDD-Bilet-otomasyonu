/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;import util.DbHelper;
import util.SingletonDBConnection;

/**
 *
 * @author Eyup Dal
 */
public class UsersList {
    
    ArrayList<Users> usersList;
    Connection connection ;
    DbHelper helper;
    
    public ArrayList<Users> getUserlist() throws SQLException
    {
    
        usersList= new ArrayList<Users>();
    	
        
        helper = new DbHelper();
        connection = helper.getConnection();
        //--------------- sQL -------------------//
    	String query = "SELECT * FROM users ";
    	Statement st;
    	ResultSet rs;
    	
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Users users;
			while(rs.next()) {
				users = new Users(rs.getInt("Id"),rs.getString("username"),rs.getString("password"));
				usersList.add(users);
				}
		} catch (SQLException exception) {
			helper.showErrorMessage(exception);
		}
    	return usersList; 
    }
    
    public String KullaniciVarmi(Users users) throws SQLException
    {
            ArrayList<Users> arrayList = getUserlist();
        
            
            for(Users str : arrayList)
            {
                if ( users.getId() == str.getId()) {
                    
                    return "tc hatalı";
                    
                } 
                else if (users.getUsername().equals(str.getUsername())) {
                    
                    return "kullanıcı adı alınmıs ";
                }
               
                
            }
            
        return "basarili";
      
    }
    
    public void InsertUser(Users users) throws SQLException
    {
        helper = new DbHelper();
        connection = helper.getConnection();
        String sql = "INSERT INTO users( id, username , password ) values (?,?,?)";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, users.getId());
        statement.setString(2, users.getUsername());
        statement.setString(3, users.getPassword());

        int i = statement.executeUpdate();
        if (i > 0) {
            
            System.out.println(i+" eklendi.. ");
        }
        
    
    }
    
    public String DeleteUser(Users users) throws SQLException
    {
        SingletonDBConnection single = SingletonDBConnection.getInstance();
        
        String sql = "DELETE FROM Users WHERE id=?";
        PreparedStatement statement = single.getConnection().prepareStatement(sql);
        statement.setInt(1, users.getId());
        
        int i = statement.executeUpdate();
        
        if (i>0) {
            System.out.println(users.getUsername() + " silindi..");
            return users.getUsername() + " silindi..";
            
        }
    
        return "hata" ;
    }
    
    public void UpdateUser(Users eski , Users yeni ) throws SQLException
    {
        String sql = "UPDATE users SET id=?, username=?, password=?   WHERE id=?" ;
        
        SingletonDBConnection single = SingletonDBConnection.getInstance();
       
        PreparedStatement statement = single.getConnection().prepareStatement(sql);
        statement.setInt(1, yeni.getId());
        statement.setString(2, yeni.getUsername());
        statement.setString(3, yeni.getPassword());
        statement.setInt(4, eski.getId());
        
        int i ;
        i = statement.executeUpdate();
        if (i > 0 ) {
               System.out.println( i+" güncellendi.. ");
        }
    
    
    
    }
    
}
