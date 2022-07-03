/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Bilet;
import java.util.ArrayList;
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
public class BiletDaoTest {
    
    public BiletDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        System.out.println("metod  öncesi");
    }
    
    @After
    public void tearDown() {
        System.out.println("metod  sonrası");
    }

    @Test
    public void testAdd() throws Exception {
    }

    @Test
    public void testGetirBilet() throws Exception {
       ArrayList<Bilet> arr = new ArrayList<>();
       
        if (arr.equals(null)) {
            
            System.out.println("Arraylsit boş");
            
        }
        else
        {
        
            System.out.println("arr dolu");
        }
        
    }

    @Test
    public void testDeleteUser() throws Exception {
    }

    @Test
    public void testUpdateUser() throws Exception {
    }
    
}
