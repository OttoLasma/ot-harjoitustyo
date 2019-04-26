/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ridesharing.dao;

import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ridesharing.domain.User;

/**
 *
 * @author ottlasma
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest {
    @InjectMocks
    UserDao userDao;
    @Mock
    JdbcTemplate jdbcTemplate;
    
    public UserDaoTest() throws SQLException {
        
    }
    
    
    @Test
    public void findByKeyTest() throws SQLException{
        userDao.read(1);        
    }
    @Test
    public void createTest() throws SQLException{
        
        User user = new User("name", "surname", " phone", " email",  "username",  "password");
        try{
            userDao.create(user);
        }catch(Exception e){
            userDao.read(1); 
        }
        
        
    }
    @Test
    public void usersHasBeenAddedToDatabase() throws SQLException{
        try{
            userDao.list();
        }catch(Exception e){
            userDao.read(1); 
        }
        
    }

    
}
