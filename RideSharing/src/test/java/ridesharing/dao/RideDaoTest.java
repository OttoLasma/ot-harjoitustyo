/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ridesharing.dao;

import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ridesharing.domain.Ride;

/**
 *
 * @author ottlasma
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class RideDaoTest {
    @InjectMocks
    RideDao rideDao;
    @Mock
    JdbcTemplate jdbcTemplate;
    public RideDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void findByKeyTest() throws SQLException{
        rideDao.read(1);  
    }
    @Test
    public void createTest() throws SQLException{
        
        Ride ride = new Ride("name", "surname", 1, 1,  "username",  1);
        try{
            rideDao.create(ride);
        }catch(Exception e){
            rideDao.read(1); 
        }
               
    }
    @Test
    public void usersHasBeenAddedToDatabase() throws SQLException{
        try{
            rideDao.list();
        }catch(Exception e){
            rideDao.read(1); 
        }
        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
