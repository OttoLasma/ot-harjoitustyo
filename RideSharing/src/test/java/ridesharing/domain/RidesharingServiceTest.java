/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ridesharing.domain;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.EmailException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.sqlite.JDBC;
import ridesharing.dao.ReserveDao;
import ridesharing.dao.RideDao;
import ridesharing.dao.UserDao;

/**
 *
 * @author ottlasma
 */
public class RidesharingServiceTest {

    RideDao rideDao;
    UserDao userDao;
    ReserveDao reserveDao;
    Connection conn;
    String filename;
    RidesharingService serviceDao;

    public RidesharingServiceTest() {
        filename = "jdbc:sqlite:testiService.db";
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

    @Before
    public void before() {
        conn = getConnection();
        serviceDao = new RidesharingService(conn);
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
        String sqlRide = "CREATE TABLE IF NOT EXISTS Ride (\n"
                + " id integer PRIMARY KEY AUTOINCREMENT, \n"
                + " departurelocation text NOT NULL,\n"
                + " destinationlocation text NOT NULL,\n"
                + " price integer NOT NULL, \n"
                + " seats integer NOT NULL,\n"
                + " date text NOT NULL,\n"
                + " userId integer NOT NULL,\n"
                + " available integer NOT NULL,\n"
                + " FOREIGN KEY (userId) references User(id) \n"
                + " );";
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table 
            stmt.execute(sqlRide);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String sqlReserve = "CREATE TABLE IF NOT EXISTS Reserve (\n"
                + " id integer PRIMARY KEY AUTOINCREMENT, \n"
                + " departurelocation text NOT NULL,\n"
                + " destinationlocation text NOT NULL,\n"
                + " price integer NOT NULL, \n"
                + " seats integer NOT NULL,\n"
                + " date text NOT NULL,\n"
                + " userId integer NOT NULL,\n"
                + " available integer NOT NULL,\n"
                + " FOREIGN KEY (userId) references User(id) \n"
                + " );";
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table 
            stmt.execute(sqlReserve);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
    @Test
    public void returnListofUsersReservesTest()throws SQLException{
        User user = new User("otto", "lasma", "0458848862", "otto.lasma@aalto.fi", "ottola", "kala");
        userDao.create(user);
        int userId = user.getId();
        List<Reserve> list = serviceDao.returnListofUsersReserves(userId);
        assertTrue(list.size() == 0);
    }
    @Test
    public void returnListofUsersRidesTest()throws SQLException{
        User user = new User("otto", "lasma", "0458848862", "otto.lasma@aalto.fi", "ottola", "kala");
        userDao.create(user);
        int userId = user.getId();
        List<Ride> list = serviceDao.returnListofUsersRides(userId);
        assertTrue(list.size() == 0);
    }
//    @Test
//    public void returnListofAvailableRidesTest()throws SQLException{
//        Ride t1 = new Ride("helsinki", "tampere", 4, 3, "840925", 3);
//        
//        rideDao.create(t1);
//        List<Ride> list = serviceDao.returnListofAvailableRides();
//        assertTrue(list.size() == 1);
//    }
    @Test
    public void correctCredentialsTest()throws SQLException{
        User user = new User("otto", "lasma", "0458848862", "otto.lasma@aalto.fi", "ottola", "kala");
        userDao.create(user);
        
        int test = serviceDao.correctCredentials("kako", "vaara");
        assertTrue(test == -10);
    }
    @Test
    public void sendEmail()throws SQLException{
        try {
            boolean test = serviceDao.sendEmail("otto.lasma@gmail.com");
            assertTrue(test);
        } catch (EmailException ex) {
            
        }
        
    }
    @Test
    public void createUserTestThroughService()throws SQLException{
        User user = new User("otto", "lasma", "43820943", "fjdlaksf", "fjkdallaf", "jfdlkfsa");
        serviceDao.createUser(user);
        int varibale = userDao.list().size();
        User userTest = new User("otto", "lasma", "43820943", "fjdlaksf", "fjkdallaf", "jfdlkfsa");
        serviceDao.createUser(userTest);
        int varibale2 = userDao.list().size();
        assertTrue((varibale2 - varibale) == 1);
        
    }
//    @Test
//    public void createReserveTestThroughService()throws SQLException{
//        Reserve reserve = new Reserve("otto5555", "lasma11", 2, 1, "fjkdallaf11", 1);
//        serviceDao.createReserve(reserve);
//        int varibale = reserveDao.list().size();
//        Reserve reserveTest = new Reserve("otto5555", "lasma11", 2, 1, "fjkdallaf11", 1);
//        serviceDao.createReserve(reserveTest);
//        int varibale2 = reserveDao.list().size();
//        assertTrue((varibale2 - varibale) == 1);
//        
//    }
//    @Test
//    public void createRideTestThroughService()throws SQLException{
//        Ride ride = new Ride("otto5555", "lasma11", 2, 1, "fjkdallaf11", 1);
//        serviceDao.createRide(ride);
//        int varibale = rideDao.list().size();
//        Ride rideTest = new Ride("otto55fda55", "lasma11", 2, 1, "fjkdfadallaf11", 1);
//        serviceDao.createRide(rideTest);
//        int varibale2 = rideDao.list().size();
//        assertTrue((varibale2 - varibale) == 1);
//        
//    }

   
    @After
    public void after() throws SQLException{
        conn.close();
    }
    @Test
    public void createTestEnvironment(){
        File newFile = new File("testiService.db");
        boolean created = newFile.exists();
        assertTrue(created);
    }
    

    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
