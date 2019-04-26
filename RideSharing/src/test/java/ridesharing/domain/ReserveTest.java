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
        Reserve t1 = new Reserve("helsinki", "tampere", 4, 3, "840925", 3);
        Reserve t2 = new Reserve("helsinki", "tampere", 4, 3, "840925", 3);
        assertTrue(t1.equals(t2));
    }
  
    @Test
    public void notEqualWhenDifferentDeparture() {
        Reserve t1 = new Reserve("helsinki", "tampere", 4, 3, "840925", 3);
        Reserve t2 = new Reserve("turku", "tampere", 4, 3, "840925", 3);
        assertFalse(t1.equals(t2));
    }   
    
    @Test
    public void nonEqualWhenDifferentType() {
        Reserve t1 = new Reserve("helsinki", "tampere", 4, 3, "840925", 3);
        Object o = new Object();
        assertFalse(t1.equals(o));
    }      
    @Test
    public void testSetId(){
        Reserve reserve = new Reserve();
        reserve.setId(2);
        assertTrue(reserve.getId() == 2);
    }
    @Test
    public void testSetDeparture(){
        Reserve reserve = new Reserve();
        reserve.setDeparturelocation("otto");
        assertTrue(reserve.getDeparturelocation().equals("otto"));
    }
    @Test
    public void testSetDestination(){
        Reserve reserve = new Reserve();
        reserve.setDestinationlocation("otto");
        assertTrue(reserve.getDestinationlocation().equals("otto"));
    }
    @Test
    public void testSetDate(){
        Reserve reserve = new Reserve();
        reserve.setDate("otto");
        assertTrue(reserve.getDate().equals("otto"));
    }
    @Test
    public void testSetPrice(){
        Reserve reserve = new Reserve();
        reserve.setPrice(2);
        assertTrue(reserve.getPrice()== 2);
    }
    @Test
    public void testSetSeats(){
        Reserve reserve = new Reserve();
        reserve.setSeats(2);
        assertTrue(reserve.getSeats()== 2);
    }
    @Test
    public void testSetAvailable(){
        Reserve reserve = new Reserve();
        reserve.setAvailable(2);
        assertTrue(reserve.getAvailable()== 2);
    }
    @Test
    public void testSetUserId(){
        Reserve reserve = new Reserve();
        reserve.setUserId(2);
        assertTrue(reserve.getUserId()== 2);
    }
    @Test
    public void testToString(){
        Reserve reserve = new Reserve();
        reserve.setUserId(2);
        Reserve reserve2 = new Reserve();
        reserve2.setUserId(2);
        assertTrue(reserve.toString().equals(reserve2.toString()));
    }
    
}

