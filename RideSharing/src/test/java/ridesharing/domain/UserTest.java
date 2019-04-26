
package ridesharing.domain;



/**
 *
 * @author ottlasma
 */

import ridesharing.domain.User;
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
    @Test
    public void testSetId(){
        User user = new User();
        user.setId(2);
        assertTrue(user.getId() == 2);
    }
    @Test
    public void testSetName(){
        User user = new User();
        user.setName("otto");
        assertTrue(user.getName().equals("otto"));
    }
    @Test
    public void testSetSurname(){
        User user = new User();
        user.setSurname("otto");
        assertTrue(user.getSurname().equals("otto"));
    }
    @Test
    public void testSetPhone(){
        User user = new User();
        user.setPhone("045884");
        assertTrue(user.getPhone().equals("045884"));
    }
    @Test
    public void testSetEmail(){
        User user = new User();
        user.setEmail(".gmail");
        assertTrue(user.getEmail().equals(".gmail"));
    }
    @Test
    public void testSetUsername(){
        User user = new User();
        user.setUsername("username");
        assertTrue(user.getUsername().equals("username"));
    }
    @Test
    public void testSetPassword(){
        User user = new User();
        user.setPassword("password");
        assertTrue(user.getPassword().equals("password"));
    }
    
    
    
    
}
