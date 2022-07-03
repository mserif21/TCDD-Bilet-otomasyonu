/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eyup Dal
 */
public class SingletonDBConnectionTest {
    
    public SingletonDBConnectionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetConnection() throws SQLException {
        SingletonDBConnection single = SingletonDBConnection.getInstance();
        
        if (single.getConnection() == null) {
            
            System.out.println("bağlantı oluşmadı");
            
        }
        else
        {
        
            System.out.println("bağlanti baasarili");
        }
        
    }

    @Test
    public void testGetInstance() throws Exception {
    }
    
}
