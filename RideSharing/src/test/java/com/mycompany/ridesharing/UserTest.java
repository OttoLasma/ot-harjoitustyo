/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ridesharing;



/**
 *
 * @author ottlasma
 */

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    
    @Test
    public void equalWhenSameUsername() {
        User u1 = new User("Otto", "Lasma", "0458848862", "otto.lasma@aalto.fi", "ottomaani");
        User u2 = new User("Otto", "Lasma", "0458848862", "otto.lasma@aalto.fi", "ottomaani");
        assertTrue(u1.equals(u2));
    }

    @Test
    public void nonEqualWhenDifferentUsername() {
        User u1 = new User("Otto", "Lasma", "0458848862", "otto.lasma@aalto.fi", "ottomaani");
        User u2 = new User("Otto", "Lasma", "0458848862", "otto.lasma@aalto.fi", "ottomaani2");
        assertFalse(u1.equals(u2));
    } 
    
    @Test
    public void nonEqualWhenDifferentType() {
        User u2 = new User("Otto", "Lasma", "0458848862", "otto.lasma@aalto.fi", "ottomaani");
        Object o = new Object();
        assertFalse(u2.equals(o));
    }     
}
