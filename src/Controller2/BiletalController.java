/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller2;

import Entity.Bilet;
import Entity.Users;
import Strategy.Context;
import Strategy.MemurTar;
import Strategy.NormalTar;
import Strategy.OgrenciTar;
import Strategy.YasliTar;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
public class BiletalController implements Initializable {

    @FXML
    private DatePicker date_tarih;

    @FXML
    private ComboBox comb_tarife;

    @FXML
    private TextField txt_tarife;

    @FXML
    private TextField txt_nerden;

    @FXML
    private TextField txt_nereye;

    @FXML
    private TextField txt_tarih;

    @FXML
    private TextField txt_saat;

    @FXML
    private TextField txt_kol_no;

    @FXML
    private TextField txt_ucret;
    
    @FXML
    private ComboBox comb_nereye;

    @FXML
    private Label lbl_ucret;
    
    public void ShowTarih(ActionEvent event)
    {
        LocalDate idDate = date_tarih.getValue();
        txt_tarih.setText(idDate.toString());
    
    }
    
    @FXML
    void Nereye_Sec(ActionEvent event) {
       
        String s = comb_nereye.getSelectionModel().getSelectedItem().toString();
        
        
        txt_nereye.setText(s);
        
    }

    @FXML
    void Tarife_Sec(ActionEvent event) {
        
        String s = comb_tarife.getSelectionModel().getSelectedItem().toString();
        
        
        txt_tarife.setText(s);

    }
    
    
    
    
    
    public void Koltuk_secim(String tex)
    {
        txt_kol_no.setText(tex);
    }
    
    @FXML
    void Koltuk_sec(ActionEvent event) throws IOException {

                Parent root = FXMLLoader.load(getClass().getResource("Koltuk.fxml"));
		Scene scene = new Scene(root);
		
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(scene);
                primaryStage.show();
    }
    
//-------------------------Bilet al bölümü----------------------///
    
    @FXML
    void Bilet_Al(ActionEvent event) throws SQLException, IOException {

        boolean  koltuk = txt_kol_no.getText().trim().isEmpty();
        boolean  nereye = txt_nereye.getText().trim().isEmpty();
        boolean  tarife = txt_tarife.getText().trim().isEmpty();
        boolean  tarih = txt_tarih.getText().trim().isEmpty();
        boolean  ucret = txt_ucret.getText().trim().isEmpty();
        
        if ( koltuk || nereye || tarife || tarih || ucret) {
            
            JOptionPane.showMessageDialog(null, "bos bırakılamaz.");
            
            System.out.println("lütfen boş alan bırakmayınız..");
            System.out.println("koltuk "+koltuk);
            System.out.println("nereye "+nereye);
            System.out.println("tarife "+tarife);
            System.out.println("tarih "+tarih);
            System.out.println("ucret "+ucret);
        }
        
        else
        {
            Users us = new Users();
            DbHelper helper = new DbHelper();
            Connection connect = helper.getConnection();
            
            String sq = "SELECT * FROM temp";
            
            Statement st = null;
            ResultSet rs;
            
            try {
                st = connect.createStatement();
                rs = st.executeQuery(sq);
                
                rs.next();
                
                us.setId(rs.getInt("id"));
                us.setUsername(rs.getString("username"));
                us.setPassword(rs.getString("password"));
                
            } catch (Exception e) {
                
                helper.showErrorMessage((SQLException) e);
                System.out.println("  hataa  ");
            }
            
            //---------------------Bilet bilgisi ekleme ----------------////
            
            String sql = "INSERT INTO bilet (tc, no, ucret, tarih, tarife, tren_ad, koltuk_no) VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement statement = connect.prepareStatement(sql);
            
            
            statement.setInt(1, us.getId()); // id
            statement.setInt(2, us.getId()); // no
            statement.setInt(3, Integer.parseInt(txt_ucret.getText()));// ucret
            statement.setString(4, txt_tarih.getText() );
            statement.setString(5, txt_tarife.getText());
            statement.setString(6, "Güney kurtalan express");
            statement.setString(7, txt_kol_no.getText());
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("basarili!");
            }
            System.out.println(" kayıt edilebilir.");
            
            //--------------- Kart Paneli ---------------////
            
            Kart_Panel(event);
            
        }
        
    }
    
    
    @FXML
    void Kart_Panel(ActionEvent event) throws IOException {

                Parent root = FXMLLoader.load(getClass().getResource("Kart_info.fxml"));
		Scene scene = new Scene(root);
		
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(scene);
                primaryStage.show();
                

    }
    
    
    @FXML
    void Ucret_hesapla(ActionEvent event) {

        String s = txt_nereye.getText();
        String tarife = txt_tarife.getText();
        
    ///--------------- diyarbakır-----------------------////////////
            if (s.contains("Elazig")) {
                
                    //--- elazığ bilet fiyatı---//
                    int uc = 20; 

                    if (tarife.contains("memur")) {

                        Context context = new Context(new MemurTar());
                        System.out.println("20  tl lik biletin memur tarifesi:  " + context.Strategy(uc));

                        int i = context.Strategy(20);

                        txt_ucret.setText(Integer.toString(i));

                    }
                    else if (tarife.contains("öğrenci")) {

                        Context context = new Context(new OgrenciTar());
                        System.out.println("20  tl lik biletin öğrenci tarifesi:  " + context.Strategy(uc));

                        int i = context.Strategy(uc);

                        txt_ucret.setText(Integer.toString(i));
                    }
                    else if (tarife.contains("yasli")) {

                        Context context = new Context(new YasliTar());
                        System.out.println("20  tl lik biletin öğrenci tarifesi:  " + context.Strategy(uc));

                        int i = context.Strategy(uc);

                        txt_ucret.setText(Integer.toString(i));

                    }

                    else{

                        Context context = new Context(new NormalTar());
                        int i = context.Strategy(uc);

                        txt_ucret.setText(Integer.toString(i));

                    }

                
            }
            
            
     ///--------------- diyarbakır-----------------------////////////
            else if (s.contains("Diyarbakir")) {
                
               
                    int uc = 30; 

                    if (tarife.contains("memur")) {

                        Context context = new Context(new MemurTar());
                        System.out.println(uc+"  tl lik biletin memur tarifesi:  " + context.Strategy(uc));

                        int i = context.Strategy(uc);

                        txt_ucret.setText(Integer.toString(i));

                    }
                    else if (tarife.contains("öğrenci")) {

                        Context context = new Context(new OgrenciTar());
                        System.out.println(uc+"  tl lik biletin öğrenci tarifesi:  " + context.Strategy(uc));

                        int i = context.Strategy(uc);

                        txt_ucret.setText(Integer.toString(i));
                    }
                    else if (tarife.contains("yasli")) {

                        Context context = new Context(new YasliTar());
                        System.out.println(uc+"   tl lik biletin öğrenci tarifesi:  " + context.Strategy(uc));

                        int i = context.Strategy(uc);

                        txt_ucret.setText(Integer.toString(i));

                    }

                    else{

                        Context context = new Context(new NormalTar());
                        int i = context.Strategy(uc);

                        txt_ucret.setText(Integer.toString(i));

                    }
                
                
            
        }
            
    ///--------------- batman-----------------------////////////
            else if (s.contains("Batman")) {
                
                
                    int uc = 40; 

                    if (tarife.contains("memur")) {

                        Context context = new Context(new MemurTar());
                        System.out.println(uc+"   tl lik biletin memur tarifesi:  " + context.Strategy(uc));

                        int i = context.Strategy(uc);

                        txt_ucret.setText(Integer.toString(i));

                    }
                    else if (tarife.contains("öğrenci")) {

                        Context context = new Context(new OgrenciTar());
                        System.out.println(uc+"   tl lik biletin öğrenci tarifesi:  " + context.Strategy(uc));

                        int i = context.Strategy(uc);

                        txt_ucret.setText(Integer.toString(i));
                    }
                    else if (tarife.contains("yasli")) {

                        Context context = new Context(new YasliTar());
                        System.out.println(uc+"   tl lik biletin öğrenci tarifesi:  " + context.Strategy(uc));

                        int i = context.Strategy(uc);

                        txt_ucret.setText(Integer.toString(i));

                    }

                    else{

                        Context context = new Context(new NormalTar());
                        int i = context.Strategy(uc);

                        txt_ucret.setText(Integer.toString(i));

                    }
                
                
            
        }        
            ///--------------- siirt-----------------------////////////
            else if (s.contains("Siirt")) {
                
                
                    int uc = 50; 

                    if (tarife.contains("memur")) {

                        Context context = new Context(new MemurTar());
                        System.out.println(uc+"   tl lik biletin memur tarifesi:  " + context.Strategy(uc));

                        int i = context.Strategy(uc);

                        txt_ucret.setText(Integer.toString(i));

                    }
                    else if (tarife.contains("öğrenci")) {

                        Context context = new Context(new OgrenciTar());
                        System.out.println(uc+"   tl lik biletin öğrenci tarifesi:  " + context.Strategy(uc));

                        int i = context.Strategy(uc);

                        txt_ucret.setText(Integer.toString(i));
                    }
                    else if (tarife.contains("yasli")) {

                        Context context = new Context(new YasliTar());
                        System.out.println(uc+"   tl lik biletin öğrenci tarifesi:  " + context.Strategy(uc));

                        int i = context.Strategy(uc);

                        txt_ucret.setText(Integer.toString(i));

                    }

                    else{

                        Context context = new Context(new NormalTar());
                        int i = context.Strategy(uc);

                        txt_ucret.setText(Integer.toString(i));

                    }
                
                
            
        }
    
        
        lbl_ucret.setText("!!!");
        
    }
    
    ObservableList<String> list = FXCollections.observableArrayList("memur","öğrenci","yasli","normal");
    ObservableList<String> sehir = FXCollections.observableArrayList("Elazig","Diyarbakir","Batman","Siirt");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        comb_tarife.setItems(list);
        comb_nereye.setItems(sehir);
    }    
    
}
