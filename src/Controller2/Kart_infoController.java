/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller2;

import Entity.Users;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import util.DbHelper;

/**
 * FXML Controller class
 *
 * @author Eyup Dal
 */
public class Kart_infoController implements Initializable {

    @FXML
    private TextField txt_KartNo;

    @FXML
    private TextField txt_KartTarih;

    @FXML
    private TextField txt_cvv;

    @FXML
    void Bilet_BilgisiEkle(ActionEvent event) throws SQLException, IOException {

        boolean kartno = txt_KartNo.getText().trim().isEmpty();
        boolean karttarih = txt_KartTarih.getText().trim().isEmpty();
        boolean kartcvv = txt_cvv.getText().trim().isEmpty();

        if (kartno || karttarih || kartcvv) {

            JOptionPane.showMessageDialog(null, "bos bırakılamaz.");

        } else {
            
            
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
            }

            
            String sql = "INSERT INTO kart (tc, kart_no, son_tarih, cvc) VALUES (?, ?, ?, ?)";

            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setInt(1, us.getId());
            statement.setString(2, txt_KartNo.getText());
            statement.setString(3, txt_KartTarih.getText());
            statement.setString(4, txt_cvv.getText());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("kart eklendi.");
            }
            
            Parent root = FXMLLoader.load(getClass().getResource("/Controller/menu.fxml"));
            Scene scene = new Scene(root);

            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
