/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Users;
import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import util.DbHelper;

/**
 * FXML Controller class
 *
 * @author Eyup Dal
 */
public class MenuController implements Initializable {

    @FXML
    private Label lbl_isim;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            try {
                Show_name();
                
            } catch (SQLException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        
    }   
    

    void Show_name() throws SQLException
    {
        String sql = "SELECT * FROM temp";
        DbHelper helper = new DbHelper();
        Connection conn = helper.getConnection();
        Statement statement;
        ResultSet rs ;
        Users us = new Users();
        
        statement = conn.createStatement();
        rs = statement.executeQuery(sql);
 
        rs.next();
        us.setId(rs.getInt("id"));
        us.setUsername(rs.getString("username"));
        us.setPassword(rs.getString("password"));
        
        System.out.println(us.getId()); 
        System.out.println(us.getUsername()); 
        System.out.println(us.getPassword());
        
        String txt = us.getUsername();
        System.out.println("txt  "+txt);
        lbl_isim.setText("merhaba "+txt);
        
        
    }
    
    
    @FXML
    void Bilet_al(ActionEvent event) throws IOException {
        
            Parent root = FXMLLoader.load(getClass().getResource("/Controller2/biletal.fxml"));
            Scene scene = new Scene(root);

            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();

    }

    @FXML
    void Info_kisi(ActionEvent event) throws IOException {

         
    }

    @FXML
    void Locatiıon(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/Bilgileriim/Tren_gps.fxml"));
            Scene scene = new Scene(root);

            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();

    }

    @FXML
    void Tickets(ActionEvent event) throws IOException {
        
            Parent root = FXMLLoader.load(getClass().getResource("/Bilgileriim/Biletlerim.fxml"));
            Scene scene = new Scene(root);

            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();

    }
    
}
