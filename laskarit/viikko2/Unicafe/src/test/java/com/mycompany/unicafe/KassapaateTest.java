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
    Maksukortti kortti;

    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(1000);
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
        assertEquals(0, testi2);
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
    @Test
    public void kortillaTarpeeksiRahaaEdullinen(){
        boolean luku = paate.syoEdullisesti(kortti);
        int luku2 = kortti.saldo();
        
        assertEquals(true, luku);
        assertEquals(760, luku2);
    }
    @Test
    public void kortillaTarpeeksiRahaaMaukkaasti(){
        boolean luku = paate.syoEdullisesti(kortti);
        int luku2 = kortti.saldo();
        assertEquals(true, luku);
        assertEquals(600, luku2);
    }
    @Test
    public void kortillaTarpeeksiRahaaEdullinenLounaidenMaaraKasvaa(){
        boolean luku = paate.syoEdullisesti(kortti);
        boolean luku2 = paate.syoMaukkaasti(kortti);
        int luku3 = paate.edullisiaLounaitaMyyty() + paate.maukkaitaLounaitaMyyty();
        assertEquals(2, luku3);
        
    }
    @Test
    public void kortillaEitarpeeksiRahaaArvoEiMuutuJaPalauttaaFalseEdullisesti(){
        
        paate.syoEdullisesti(kortti);
        paate.syoEdullisesti(kortti);
        paate.syoEdullisesti(kortti);
        paate.syoEdullisesti(kortti);
        boolean luku = paate.syoEdullisesti(kortti);
        int luku2 = kortti.saldo();
        assertEquals(false, luku);
        assertEquals(40, luku2);
    }
    @Test
    public void kortillaEitarpeeksiRahaaArvoEiMuutuJaPalauttaaFalseMaukkaasti(){
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        boolean luku = paate.syoMaukkaasti(kortti);   
        int luku2 = kortti.saldo();
        assertEquals(false, luku);
        assertEquals(200, luku2);
    }
    @Test
    public void kortillaEitarpeeksiRahaaLouanidenMaaraEiMuutu(){
        paate.syoEdullisesti(kortti);
        paate.syoEdullisesti(kortti);
        paate.syoEdullisesti(kortti);
        paate.syoEdullisesti(kortti);
        paate.syoEdullisesti(kortti);
        paate.syoMaukkaasti(kortti);
        int luku2 = paate.edullisiaLounaitaMyyty() + paate.maukkaitaLounaitaMyyty();
        assertEquals(4, luku2);
        
    }
    @Test
    public void kassassaOlevaSaldoEiMuutuKunOstetaanKortilla(){
        paate.syoEdullisesti(kortti);
        paate.syoEdullisesti(kortti);
        paate.syoEdullisesti(kortti);
        paate.syoEdullisesti(kortti);
        paate.syoEdullisesti(kortti);
        paate.syoMaukkaasti(kortti);
        int luku = paate.kassassaRahaa();
        assertEquals(100000, luku);
    }
    @Test
    public void kassassaOlevaRahaMaaraMuuttuuJaKortillaOlevaRahaMaaraMuuttuKunLadataanRahaa(){
        paate.lataaRahaaKortille(kortti, 1000);
        int luku1 = kortti.saldo();
        int luku2 = paate.kassassaRahaa();
        assertEquals(2000, luku1);
        assertEquals(101000, luku2);
    }
    @Test
    public void kortillaOlevaMaaraJaKassassaOlevaMaaraEiMuutuKunLadataanNegatiivinenSumma(){
        
        paate.lataaRahaaKortille(kortti, -1000);
        int luku1 = kortti.saldo();
        int luku2 = paate.kassassaRahaa();
        assertEquals(1000, luku1);
        assertEquals(100000, luku2);
    }
    
    
    
    
}
