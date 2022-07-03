package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DAO.UsersList;
import Entity.Users;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.DbHelper;
import util.SingletonDBConnection;

/**
 * FXML Controller class
 *
 * @author Eyup Dal
 */
public class AdminController implements Initializable {

    @FXML
    private TableView<Users> table_users;

    @FXML
    private TableColumn<Users, Integer> col_id;

    @FXML
    private TableColumn<Users, String> col_username;

    @FXML
    private TableColumn<Users, String> col_pass;

    @FXML
    private Button btn_guncelle;

    @FXML
    private Button btn_sil;

    @FXML
    private Button btn_ekle;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_pass;

    @FXML
    private TextField txt_id;

    @FXML
    private Label lbl_info;

    public ObservableList<Users> getUsersList() throws SQLException {

        ObservableList<Users> usersList = FXCollections.observableArrayList();

        SingletonDBConnection single = SingletonDBConnection.getInstance();

        String query = "SELECT * FROM users ";
        Statement st;
        ResultSet rs;
        Users user = new Users();

        try {
            st = single.getConnection().createStatement();
            rs = st.executeQuery(query);
            Users users;
            while (rs.next()) {

                users = new Users(rs.getInt("Id"), rs.getString("username"), rs.getString("password"));
                usersList.add(users);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

// -------------------------------------------------------------//
//        Connection connection ;
//        DbHelper helper = new DbHelper();
//        connection = helper.getConnection();
//        //--------------- sQL -------------------//
//    	String query = "SELECT * FROM users ";
//    	Statement st;
//    	ResultSet rs;
//    	try {
//			st = connection.createStatement();
//			rs = st.executeQuery(query);
//			Users users;
//			while(rs.next()) {
//				users = new Users(rs.getInt("Id"),rs.getString("username"),rs.getString("password"));
//				usersList.add(users);
//				}
//		} catch (SQLException exception) {
//			helper.showErrorMessage(exception);
//		}
        return usersList;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ShowUsers();

        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.btn_guncelle.setDisable(true);
        this.btn_sil.setDisable(true);

    }

    @FXML
    public void onTableItemSelect() {

        if (table_users.getSelectionModel().getSelectedItem() != null) {
            this.btn_guncelle.setDisable(false);
            this.btn_sil.setDisable(false);
            Users users = new Users();
            users = table_users.getSelectionModel().getSelectedItem();

            txt_id.setText(String.valueOf(users.getId()));
            txt_name.setText(users.getUsername());
            txt_pass.setText(users.getPassword());

        }
    }

    public void ShowUsers() throws SQLException {
        ObservableList<Users> list = getUsersList();

        col_id.setCellValueFactory(new PropertyValueFactory<Users, Integer>("id"));
        col_username.setCellValueFactory(new PropertyValueFactory<Users, String>("username"));
        col_pass.setCellValueFactory(new PropertyValueFactory<Users, String>("password"));

        table_users.setItems(list);
    }

    @FXML
    void AddUser(ActionEvent event) throws SQLException {

        Users user;
        UsersList us;

        if (txt_id.getText().isEmpty() || txt_name.getText().isEmpty() || txt_pass.getText().isEmpty()) {

            lbl_info.setText("lütfen boş bırakmayınız..");
            
        } else {
            user = new Users();
            us = new UsersList();

            user.setId(Integer.parseInt(txt_id.getText()));
            user.setUsername(txt_name.getText());
            user.setPassword(txt_pass.getText());

            us.InsertUser(user);

            ShowUsers();

        }
        ShowUsers();
    }

    public void deleteFromTable(ActionEvent event) throws SQLException {

        String sql = "DELETE FROM Users WHERE username=?";

        DbHelper helper = new DbHelper();
        Connection conn = helper.getConnection();

        PreparedStatement statement = conn.prepareStatement(sql);

        Users users = new Users();
        users = table_users.getSelectionModel().getSelectedItem();

        statement.setString(1, users.getUsername());

        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            lbl_info.setText(users.getUsername() + " kullanıcı silindi");
            System.out.println("user silindi!");
            ShowUsers();
        }
    }
    
    @FXML
    void Guncelle(ActionEvent event) throws SQLException {
        
        Users temp = new Users();
        temp = table_users.getSelectionModel().getSelectedItem();
        
        Users newUsers = new Users();
        newUsers.setId(Integer.parseInt(txt_id.getText()));
        newUsers.setUsername(txt_name.getText());
        newUsers.setPassword(txt_pass.getText());
        
        UsersList op = new UsersList();
        
        op.UpdateUser(temp, newUsers);
        
        ShowUsers();

    }
}
