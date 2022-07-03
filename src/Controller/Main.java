package Controller;

import DAO.BiletDao;
import DAO.UsersList;
import Entity.Bilet;
import Entity.Users;
import FindDigit.Sayimi;
import Strategy.Context;
import Strategy.IYolcu;
import Strategy.MemurTar;
import Strategy.NormalTar;
import Strategy.OgrenciTar;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.DbHelper;
import util.SingletonDBConnection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Eyup Dal
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) throws SQLException {
        launch(args);
      
       
      

//        BiletDao bl = new BiletDao();
//        
//        ArrayList<Bilet> tickets = new ArrayList<Bilet>();
//        tickets = bl.GetirBilet();
//        
//        for (int i = 0; i < tickets.size(); i++) {
//             Bilet obj = new Bilet();
//             obj = tickets.get(i);
//            System.out.println(obj.getTc());
//            
//        }
//        
//        Temp tmp = new Temp();
//        Users user = new  Users();
//        
//        ArrayList<Users> arr = new ArrayList<>();
//        arr = tmp.tmp2();
//        user = arr.get(0);
//        
//        System.out.println("us users   "+user.getUsername());
//        
//        String sql = "SELECT * FROM temp";
//        DbHelper helper = new DbHelper();
//        Connection conn = helper.getConnection();
//        Statement statement;
//        ResultSet rs ;
//        Users us = new Users();
//        
//        statement = conn.createStatement();
//        rs = statement.executeQuery(sql);
// 
//        rs.next();
//        us.setId(rs.getInt("id"));
//        us.setUsername(rs.getString("username"));
//        us.setPassword(rs.getString("password"));
//        System.out.println(us.getId()); 
//        System.out.println(us.getUsername()); 
//        System.out.println(us.getPassword());
//        
            

//------------------- Strategy ------------------------------//
//    Context context = new Context(new OgrenciTar());
//    System.out.println("100  tl lik biletin öğrenci tarifesi:  " + context.Strategy(100));
//    
//    context = new Context(new MemurTar());
//    System.out.println("100 tl lik biletin memur tarifesi: " + context.Strategy(100));
//    
//    context = new Context(new NormalTar());
//    System.out.println("100 tl lik biletin Normal Yolcu tarifesi: "+ context.Strategy(100));
//
//
//------------------ Decorator  --------------------------//
//
//        IYolcu ogrenci = new  OgrenciYolcu(new NormalYolcu());
//        ogrenci.Ucret_hesapla();
//        System.out.println("----------------");
//        
//        IYolcu yasli = new YasliOzelYolcu(new NormalYolcu());
//        yasli.Ucret_hesapla();
//------------------ Decorator  --------------------------//
//----------- SingletonDBConnection-----------------------------//    
//        SingletonDBConnection single = SingletonDBConnection.getInstance(); /// new kullanılmamış.
//        
//        
//          String query = "SELECT * FROM users ";
//    	  Statement st;
//    	  ResultSet rs;
//          Users user = new  Users();
//        
//        try {
//            st = single.getConnection().createStatement();
//            rs = st.executeQuery(query);
//            int i=0;
//            while(rs.next())
//            {
//                System.out.print(rs.getInt("Id"));
//                System.out.print("  ");
//                System.out.print(rs.getString("username"));
//                System.out.print("  ");
//                System.out.print(rs.getString("password"));
//                System.out.println("");
//               
//            }
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        //----------- SingletonDBConnection--------------------------// 
        //-----------Arrayliist data ekleme-----------//
//        ArrayList<Users> liste = new ArrayList<Users>();
//        UsersList usersList = new UsersList();
//        
//        liste =  usersList.getUserlist();
//        int i=0;
//        Users us = new Users();
//        System.out.println(liste.size());
//        
//        while (i<liste.size()) {
//            
//            us= liste.get(i);
//            System.out.println(us.getId()+" "+us.getUsername()+" "+us.getPassword());
//           
//            i++;
//        }
//        Connection connection = null;
//        DbHelper helper = new DbHelper();
//        
//        Statement stmt = null;
//        try {
//            
//            connection = helper.getConnection();
//            
//            System.out.println("Connected");
//            
//            stmt = connection.createStatement();
//------------  insert  ----------------//            
//            String sql = "INSERT INTO users " +
//                   "VALUES (100, 'Zara', '12345')";
//            
//            stmt.executeUpdate(sql)
//------------  update  ----------------//
//            String sql = "UPDATE users " +
//                   "SET username = user1 WHERE id in (100, 101)";
//            stmt.executeUpdate(sql);
//------------  delete  ----------------//
//                String sql = "DELETE FROM users " +
//                   "WHERE id = 100";
//                stmt.executeUpdate(sql);
//            }
//         catch (SQLException ex) {
//            helper.showErrorMessage(ex);
//            
//            }
//        finally{
//            
//        connection.close();
//
//        }
    }

}
