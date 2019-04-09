
package RideSharing;



/**
 *
 * @author ottlasma
 */

import RideSharing.User;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    
    @Test
    public void equalWhenSameUsername() {
        User u1 = new User("Otto", "Lasma", "0458848862", "otto.lasma@aalto.fi", "ottomaani", "password");
        User u2 = new User("Otto", "Lasma", "0458848862", "otto.lasma@aalto.fi", "ottomaani", "password");
        assertTrue(u1.equals(u2));
    }

    @Test
    public void nonEqualWhenDifferentUsername() {
        User u1 = new User("Otto", "Lasma", "0458848862", "otto.lasma@aalto.fi", "ottomaani", "password");
        User u2 = new User("Otto", "Lasma", "0458848862", "otto.lasma@aalto.fi", "ottomaani2", "password");
        assertFalse(u1.equals(u2));
    } 
    
    @Test
    public void nonEqualWhenDifferentType() {
        User u2 = new User("Otto", "Lasma", "0458848862", "otto.lasma@aalto.fi", "ottomaani", "password");
        Object o = new Object();
        assertFalse(u2.equals(o));
    }     
}
