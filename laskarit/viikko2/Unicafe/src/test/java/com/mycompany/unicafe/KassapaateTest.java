/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ottlasma
 */
public class KassapaateTest {

    Kassapaate paate;

    @Before
    public void setUp() {
        paate = new Kassapaate();
    }
    @Test
    public void luotuPaateOnOlemassa() {
        assertTrue(paate!=null);      
    }
    @Test
    public void paatteenSaldoJaMyytyjenLounaidenMaaraAlussaOikein(){
        int testi = paate.kassassaRahaa();
        int testi2 = paate.maukkaitaLounaitaMyyty() + paate.edullisiaLounaitaMyyty();
        assertEquals(100000, testi);   
        assertEquals(0, testi);
    }
    @Test
    public void maksuEiRiittavaEdullisesti(){
        int luku = paate.syoEdullisesti(200);
        int luku2 = paate.edullisiaLounaitaMyyty();
        assertEquals(200, luku);   
        assertEquals(0, luku2);
    }
    @Test
    public void maksuEiRiittavaMaukkaasti(){
        int luku = paate.syoMaukkaasti(200);
        int luku2 = paate.maukkaitaLounaitaMyyty();
        assertEquals(200, luku);   
        assertEquals(0, luku2);
    }
    @Test
    public void maksuRiittavaEdullisesti(){
        
        int luku = paate.syoEdullisesti(260);
        int luku2 = paate.edullisiaLounaitaMyyty();
        int luku3 = paate.kassassaRahaa();
        assertEquals(20, luku);   
        assertEquals(1, luku2);
        assertEquals(100240, luku3);
    }
    @Test
    public void maksuRiittavaMaukkaasti(){
        int luku = paate.syoMaukkaasti(450);
        int luku2 = paate.maukkaitaLounaitaMyyty();
        int luku3 = paate.kassassaRahaa();
        assertEquals(50, luku);   
        assertEquals(1, luku2);
        assertEquals(100400, luku3);
    }
    public void 
    
    
    
    
    
    

    public KassapaateTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
