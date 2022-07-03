package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import DAO.UsersList;
import Entity.Users;

import com.mysql.cj.protocol.Resultset;


import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import util.DbHelper;



public class LoginController implements Initializable {
@FXML
    private TextField txt_user;

    @FXML
    private PasswordField txt_pass;

    @FXML
    private Button btn_login;

    @FXML
    private Button btn_register;
    
    @FXML
    private Label lbl_uyari;
    
    DbHelper helper;
    Connection connect;
    PreparedStatement pst;
    
    
    
    @FXML
    void Login(ActionEvent event) throws IOException {
        String user = txt_user.getText();
        String pass = txt_pass.getText();                
        if (user.contains("admin") && pass.contains("admin")) {
            
                Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
		Scene scene = new Scene(root);
		
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(scene);
                primaryStage.show();
            
        }
        
        else if (user.equals("") && pass.equals("")) {
            
            
            JOptionPane.showMessageDialog(null, "bos bırakılamaz.");
            System.out.println("boş bırakıldı");
        }
        
        else
        {
        
            try {
                 helper = new DbHelper();
                 connect = helper.getConnection();
                 
                 pst = connect.prepareStatement("SELECT *from users where username=? and password=?");
                 
                 pst.setString(1, user);
                 pst.setString(2, pass);
                 
                 ResultSet rs = pst.executeQuery();
                
                 if (rs.next()) 
                 {
                    // --------- gecici login kullanıcı verisi silindi----------//
                    helper = new DbHelper();
                    Connection conn = helper.getConnection();

                    String sql = "DELETE FROM temp  ";
                    PreparedStatement statement = conn.prepareStatement(sql);
                    int rowsDeleted = statement.executeUpdate();
                        if (rowsDeleted > 0) {
                            System.out.println("temp verileri silindi!");
                        }
                    
                     
                     Users tt = new Users();
                     
                     tt.setId(rs.getInt("id"));
                     tt.setUsername(rs.getString("username"));
                     tt.setPassword(rs.getString("password"));
                 // --------- gecici login kullanıcı verisi eklendi----------//
                     String sqll = "INSERT INTO temp (id ,username, password ) VALUES (?, ?, ?)";
                     
                     statement = conn.prepareStatement(sqll);
                     statement.setInt(1, tt.getId());
                     statement.setString(2, tt.getUsername());
                     statement.setString(3, tt.getPassword());
                     int rowsInserted = statement.executeUpdate();
                            if (rowsInserted > 0) {
                                System.out.println("temp kisi eklendi!");
                            }
                     
                  
                      
                     
                     //---------------------------------------------------//
                        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
                        Scene scene = new Scene(root);

                        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        primaryStage.setScene(scene);
                        primaryStage.show();
                
                     //-----------------------------------------------------------//
                     
                }
                 
                 else
                 {
                     JOptionPane.showMessageDialog(null, "login basarisiz");
                     txt_user.setText("");
                     txt_pass.setText("");
                     txt_user.requestFocus();
                     
                 }
                 
            } catch (Exception e) {
                
            }
        }

    }
    
//    @FXML
//    void admin(ActionEvent event) throws IOException, SQLException {
//        
//                //        ArrayList<Users> userses = new ArrayList<>();
//                //        UsersList us = new UsersList();
//                //        userses = us.getUserlist();
//        
//        
                
//
//    }
    
    @FXML
    void Register_Panel(ActionEvent event) throws IOException {

                Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
		Scene scene = new Scene(root);
		
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(scene);
                primaryStage.show();
                

    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
