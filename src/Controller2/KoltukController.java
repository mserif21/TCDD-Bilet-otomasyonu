/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller2;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/*
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

*/





public class KoltukController implements Initializable {

    @FXML
    private ComboBox comb_koltuk;
    
    @FXML
    private TextField txt_yazi;
    
    
    @FXML
    void Koltuk_sec(ActionEvent event) throws IOException {
        
                FXMLLoader loader = new FXMLLoader(getClass().getResource("biletal.fxml"));
                Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);
            
                BiletalController control = loader.getController();
                
                String txt = comb_koltuk.getSelectionModel().getSelectedItem().toString();
                control.Koltuk_secim(txt);
            
                Stage primaryStage = new Stage();
		primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(scene);
                primaryStage.show();

    }
    
    ObservableList<String> liste = FXCollections.observableArrayList("31","39");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        comb_koltuk.setItems(liste);
        
    }    

    
    
    
}
