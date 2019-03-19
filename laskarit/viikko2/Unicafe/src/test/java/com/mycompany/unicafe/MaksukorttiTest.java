package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    @Test
    public void kortinSaldoAlussaOikein(){
        int testi = kortti.saldo();
        assertEquals("saldo: 10.00", kortti.toString());
        assertEquals(1000, testi);
    }
    @Test
    public void kortilleVoiLadataRahaa() {
        kortti.lataaRahaa(25);
        assertEquals("saldo: 35.00", kortti.toString());
    }
    @Test
    public void saldoVaheneeOikeinJosKortillaRahaaJaPaluttaaTruen(){
        boolean totuus = kortti.otaRahaa(5);     
        assertEquals("saldo: 5.00", kortti.toString());
        assertEquals(true, totuus);
    }
    @Test
    public void saldoEiMuutuJosKortillaEiRahaaJaPalauttaaFalsen(){
        boolean totuus = kortti.otaRahaa(15);
        assertEquals("saldo: 10.00", kortti.toString());
        assertEquals(false, totuus);
    }
    
    
    
    
}
