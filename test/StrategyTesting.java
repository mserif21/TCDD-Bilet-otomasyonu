/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Strategy.Context;
import Strategy.IYolcu;
import Strategy.MemurTar;
import Strategy.NormalTar;
import Strategy.OgrenciTar;
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
public class StrategyTesting {
    
    private  Context context ;
    
   
    @Before
    public void set()
    {  
        System.out.println(" test öncesi");
    }
    @After
    public void setup()
    {
        System.out.println("test sonrası");
    }
    @Test
    public void testOgrenciTar()
    {
        context = new  Context(new OgrenciTar());
        assertEquals(50, context.Strategy(100));
        System.out.println("ogrenci tarifesi testi ....");
    }
    @Test
    public void testMemurTar()
    {
        context = new  Context(new MemurTar());
        assertEquals(75, context.Strategy(100));
        System.out.println("memur tarifesi testi ....");
        
    }
    @Test
    public void testNormalTar()
    {
        context = new  Context(new NormalTar());
        assertEquals(100, context.Strategy(100));
        System.out.println("normal yolcu tarifesi testi ....");
        
    }
    
}
