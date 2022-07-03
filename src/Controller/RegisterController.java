/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UsersList;
import Entity.Users;
import FindDigit.Sayimi;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import util.DbHelper;

/**
 * FXML Controller class
 *
 * @author Eyup Dal
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField txt_username;

    @FXML
    private TextField txt_password;

    @FXML
    private TextField txt_tc;

    @FXML
    private Label lbl_uyarı;

    @FXML
    private Button btn_register;

    @FXML
    void Back_Login(ActionEvent event) throws IOException, SQLException {
        Sayimi sayi = new Sayimi();
        ArrayList<Users> arrayList;
        Users users = new Users();
        UsersList usersList ;
        if (txt_tc.getText().isEmpty() || txt_password.getText().isEmpty() || txt_username.getText().isEmpty()) {
            lbl_uyarı.setText("lutfen boş alan bırakmayını!!!");

        } else if (sayi.isNumber(txt_tc.getText()) == false) {

            lbl_uyarı.setText("tc kimlik no sadece rakam olabilir");
        } else {
                int tc = Integer.parseInt(txt_tc.getText());
                String pass = txt_password.getText();
                String user = txt_username.getText();
            
                Users simpleUsers = new Users(tc, user, pass);
                usersList = new UsersList();
                String s =usersList.KullaniciVarmi(simpleUsers);
            
                if (s.contains("basarili")) {
                    
                    JOptionPane.showMessageDialog(null,simpleUsers.getUsername()+ "Kayıt Basarili.");
                    usersList.InsertUser(simpleUsers);
                    
                    
                    Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                    Scene scene = new Scene(root);

                    Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    primaryStage.setScene(scene);
                    primaryStage.show();
                
                }
                else{
                 lbl_uyarı.setText(s);
                }
 

            
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
