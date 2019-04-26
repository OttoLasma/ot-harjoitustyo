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
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ridesharing.dao.ReserveDao;
import ridesharing.dao.RideDao;
import ridesharing.dao.UserDao;

/**
 *
 * @author ottlasma
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class RidesharingServiceTest {
    @InjectMocks
    RidesharingService service;
    @Mock
    RideDao rideDao;
    @Mock
    UserDao userDao;
    @Mock
    ReserveDao reserveDao;
    public RidesharingServiceTest() {
    }
    
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testaus(){
        
    }
}
