/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ridesharing.domain;




import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class RideTest {
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
    @Test
    public void testSetId(){
        Ride ride = new Ride();
        ride.setId(2);
        assertTrue(ride.getId() == 2);
    }
    @Test
    public void testSetDeparture(){
        Ride ride = new Ride();
        ride.setDeparturelocation("otto");
        assertTrue(ride.getDeparturelocation().equals("otto"));
    }
    @Test
    public void testSetDestination(){
        Ride ride = new Ride();
        ride.setDestinationlocation("otto");
        assertTrue(ride.getDestinationlocation().equals("otto"));
    }
    @Test
    public void testSetDate(){
        Ride ride = new Ride();
        ride.setDate("otto");
        assertTrue(ride.getDate().equals("otto"));
    }
    @Test
    public void testSetPrice(){
        Ride ride = new Ride();
        ride.setPrice(2);
        assertTrue(ride.getPrice()== 2);
    }
    @Test
    public void testSetSeats(){
        Ride ride = new Ride();
        ride.setSeats(2);
        assertTrue(ride.getSeats()== 2);
    }
    @Test
    public void testSetAvailable(){
        Ride ride = new Ride();
        ride.setAvailable(2);
        assertTrue(ride.getAvailable()== 2);
    }
    @Test
    public void testSetUserId(){
        Ride ride = new Ride();
        ride.setUserId(2);
        assertTrue(ride.getUserId()== 2);
    }
    @Test
    public void testToString(){
        Ride ride = new Ride();
        ride.setUserId(2);
        Ride ride2 = new Ride();
        ride2.setUserId(2);
        assertTrue(ride.toString().equals(ride2.toString()));
    }
}
