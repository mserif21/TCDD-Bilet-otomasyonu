/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bilgileriim;

import DAO.BiletDao;
import DAO.Kullanici;
import Entity.Bilet;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Eyup Dal
 */
public class BiletlerimController implements Initializable {

   @FXML
    private Label lbl_id;

    @FXML
    private TextField txt_tc;

    @FXML
    private TextField txt_tarife;

    @FXML
    private TextField txt_koltuk;

    @FXML
    private TextField txt_tarih;
    
    @FXML
    private TextField txt_boss;

    
   @FXML
    void Menu_Panel(ActionEvent event) throws IOException {

                Parent root = FXMLLoader.load(getClass().getResource("/Controller/menu.fxml"));
		Scene scene = new Scene(root);
		
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(scene);
                primaryStage.show();
                

    }
    
    @FXML
    void yazi(ActionEvent event) throws SQLException {
         Kullanici obj = new Kullanici();
         int id = obj.Getir_kullanici();
         
         lbl_id.setText(String.valueOf(id));

    }
    
    
    void Info_Show( ) throws IOException, SQLException {

         Kullanici obj = new Kullanici();
         int id = obj.Getir_kullanici();
         BiletDao dao = new BiletDao();
         
         ArrayList<Bilet> arr = new ArrayList<Bilet>();
         arr = dao.GetirBilet();
         
         Bilet bilet = new Bilet();
         
         for (int i = 0 ; i < arr.size() ;i++) {
            
             bilet = arr.get(i);
             
             
             if (bilet.getTc() == id) {
                 txt_koltuk.setText(bilet.getKoltuk_no());
                 txt_tarife.setText(bilet.getTarife());
                 txt_tarih.setText(bilet.getTarih());
                 txt_tc.setText(String.valueOf(bilet.getTc()));
                
                 
                 
                
             }
           
        }
         
         
         

    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            Info_Show();
        } catch (Exception e) {
        }
        
    }    

   
    
}
