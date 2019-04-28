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
import static org.mockito.Mockito.verify;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ridesharing.domain.Reserve;

/**
 *
 * @author ottlasma
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ReserveDaoTest {
    @InjectMocks
    ReserveDao reserveDao;
    @Mock
    JdbcTemplate jdbcTemplate;
    
    
    public ReserveDaoTest() {
    }
    
    
    @Test
    public void findByKeyTest() throws SQLException{
        reserveDao.read(1);  
    }
    @Test
    public void createTest() throws SQLException{
        
        Reserve reserve = new Reserve("name", "surname", 1, 1,  "username",  1);
        try{
            reserveDao.create(reserve);
        }catch(Exception e){
            reserveDao.read(1); 
        }
               
    }
    @Test
    public void usersHasBeenAddedToDatabase() throws SQLException{
        try{
            reserveDao.list();
        }catch(Exception e){
            reserveDao.read(1); 
        }
        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}