/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ridesharing.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.sqlite.JDBC;
import ridesharing.domain.Reserve;
import ridesharing.domain.User;

/**
 *
 * @author ottlasma
 */
public class UserDaoTest {
    UserDao userDao;
    Connection conn;
    String filename;
    public UserDaoTest() {
        filename = "jdbc:sqlite:testiUser.db";
    }
    
    @Before
    public void before() {
        conn = getConnection();
        userDao = new UserDao(conn);
        String url = filename;
        String sql = "CREATE TABLE IF NOT EXISTS User (\n"
                + " id integer PRIMARY KEY AUTOINCREMENT,\n"
                + " name text NOT NULL,\n"
                + " surname text NOT NULL,\n"
                + " phone text NOT NULL,\n"
                + " email text NOT NULL,\n"
                + " username text NOT NULL,\n"
                + " password text NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table 
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
     
    }
    public Connection getConnection() {
        if (conn == null) {
            String url = filename;
            try {
                DriverManager.registerDriver(new JDBC());
                conn = DriverManager.getConnection(url);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return conn;
    }
    
    @After
    public void after() throws SQLException{
        conn.close();
    }
    @Test
    public void createTestEnvironment(){
        File newFile = new File("testiUser.db");
        boolean created = newFile.exists();
        assertTrue(created);
    }
   
    
    @Test
    public void createNewReserveListSizeIncreasesTest()throws SQLException{
        User user = new User("otto", "lasma", "43820943", "fjdlaksf", "fjkdallaf", "jfdlkfsa");
        userDao.create(user);
        int varibale = userDao.list().size();
        User userTest = new User("otto", "lasma", "43820943", "fjdlaksf", "fjkdallaf", "jfdlkfsa");
        userDao.create(userTest);
        int varibale2 = userDao.list().size();
        assertTrue((varibale2 - varibale) == 1);
        
    }
    @Test
    public void updateWorksProperlyWhenMakingChangesTest()throws SQLException{
        User user = new User("otto", "lasma", "43820943", "fjdlaksf", "fjkdallaf", "jfdlkfsa");
        userDao.create(user);
        user.setName("heija");
        User userTest = userDao.update(user);
        assertTrue(userTest.getName().equals("heija"));
        
    }
    @Test
    public void createNewReserveTestThatAttributesWorkCorrectlyTest()throws SQLException{
        User user = new User("otto", "lasma", "43820943", "fjdlaksf", "fjkdallaf", "jfdlkfsa");
        userDao.create(user);
        User userTest = userDao.read(user.getId());
        assertTrue(userTest.getName().equals("otto"));
        assertTrue(userTest.getSurname().equals("lasma"));
        assertTrue(userTest.getEmail().equals("fjdlaksf"));
        assertTrue(userTest.getPhone().equals("43820943"));
        assertTrue(userTest.getUsername().equals("fjkdallaf"));
        assertTrue(userTest.getPassword().equals("jfdlkfsa"));
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
