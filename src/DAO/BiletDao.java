
package DAO;

import Entity.Bilet;
import Entity.Users;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.SingletonDBConnection;


public class BiletDao  {
    
    ArrayList<Bilet> biletlist ;
    SingletonDBConnection single ; /// new kullanılmamış.
    
    
    public void add(Bilet bilet) throws SQLException
    {
    
        biletlist = new ArrayList<>();
        biletlist.add(bilet);
        
        String sql = "INSERT INTO bilet (tc, no, ucret, tarih, tarife, tren_ad, koltuk_no) VALUES (?, ?, ?, ?, ?, ?, ?)";
            
        PreparedStatement statement = single.getConnection().prepareStatement(sql);
        
            statement.setInt(1, bilet.getTc()); // id
            statement.setInt(2, bilet.getNo()); // no
            statement.setInt(3, bilet.getUcret());// ucret
            statement.setString(4, bilet.getTarih() );
            statement.setString(5, bilet.getTarife());
            statement.setString(6, bilet.getTren_ad());
            statement.setString(7, bilet.getKoltuk_no());
            
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("bilet ekleme basarili!");
                }    
        
    }
    
    public ArrayList<Bilet> GetirBilet() throws SQLException
    {
        single = SingletonDBConnection.getInstance();
        biletlist = new ArrayList<>();
        Statement st;
    	ResultSet rs;
        Bilet bilet;
    
        String sql = "SELECT * FROM Bilet";
        try {
			st = single.getConnection().createStatement();
			rs = st.executeQuery(sql);
			
                        
			while(rs.next()) {
				bilet = new Bilet(rs.getInt("tc"), rs.getInt("no"), rs.getInt("ucret"), rs.getString("tarih")
                                                , rs.getString("tarife"), rs.getString("tren_ad"), rs.getString("koltuk_no"));
				biletlist.add(bilet);
				}
		} catch (SQLException exception) {
			System.out.println("hatta bilet eklenemedi...");
		}
        
        return biletlist;
    }
    
    public void DeleteUser(Bilet bilet) throws SQLException
    {
        SingletonDBConnection single = SingletonDBConnection.getInstance();
        
        String sql = "DELETE FROM Bilet WHERE id=?";
        PreparedStatement statement = single.getConnection().prepareStatement(sql);
        
        statement.setInt(1, bilet.getTc());
        
        int i = statement.executeUpdate();
        
        if (i>0) {
            System.out.println(bilet.getNo() + "bilet  silindi..");
            
            
        }
    
        
    }
    
    public void UpdateUser(Bilet eski , Bilet yeni ) throws SQLException
    {
        String sql = "UPDATE Bilet SET id=?, username=?, password=?   WHERE tc=?" ;
        
        SingletonDBConnection single = SingletonDBConnection.getInstance();
       
        PreparedStatement statement = single.getConnection().prepareStatement(sql);
//        statement.setInt(1, yeni.getId());
//        statement.setString(2, yeni.getUsername());
//        statement.setString(3, yeni.getPassword());
//        statement.setInt(4, eski.getId());
        
        int i ;
        i = statement.executeUpdate();
        if (i > 0 ) {
               System.out.println( i+" bilet  güncellendi.. ");
        }
    
    
    
    }
    
    
    
    
    
}
