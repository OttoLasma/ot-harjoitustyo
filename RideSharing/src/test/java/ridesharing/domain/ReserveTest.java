/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ridesharing.domain;

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
public class ReserveTest {
    
    public ReserveTest() {
    }
    
    


//    public Ride(String departurelocation, String destinationlocation, int price, int seats, String date, int userId) {
    @Test
    public void equalWhenSame() {
        Ride t1 = new Ride("helsinki", "tampere", 4, 3, "840925", 3);
        Ride t2 = new Ride("helsinki", "tampere", 4, 3, "840925", 3);
        assertTrue(t1.equals(t2));
    }
  
    @Test
    public void notEqualWhenDifferentDeparture() {
        Ride t1 = new Ride("helsinki", "tampere", 4, 3, "840925", 3);
        Ride t2 = new Ride("turku", "tampere", 4, 3, "840925", 3);
        assertFalse(t1.equals(t2));
    }   
    
    @Test
    public void nonEqualWhenDifferentType() {
        Ride t1 = new Ride("helsinki", "tampere", 4, 3, "840925", 3);
        Object o = new Object();
        assertFalse(t1.equals(o));
    }      
}

