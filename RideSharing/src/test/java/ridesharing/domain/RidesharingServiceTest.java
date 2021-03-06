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
import java.util.Scanner;
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

    /**
     *creates connection to database
     * @return
     */
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

    /**
     *create test environment
     */
    @Before
    public void before() {
        conn = getConnection();
        serviceDao = new RidesharingService(conn);
        rideDao = new RideDao(conn);
        reserveDao = new ReserveDao(conn);
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

    /**
     *method tests whether method returnListofUsersReserves works properly
     * @throws SQLException
     */
    @Test
    public void returnListofUsersReservesTest()throws SQLException{
        User user = new User("otto", "lasma", "0458848862", "otto.lasma@aalto.fi", "ottola", "kala");
        userDao.create(user);
        int userId = user.getId();
        List<Reserve> list = serviceDao.returnListofUsersReserves(userId);
        assertTrue(list.size() == 0);
    }

    /**
     *method tests whether correct amount rides are returned 
     * @throws SQLException
     */
    @Test
    public void returnListofUsersRidesTest()throws SQLException{
        User user = new User("otto", "lasma", "0458848862", "otto.lasma@aalto.fi", "ottola", "kala");
        userDao.create(user);
        int userId = user.getId();
        List<Ride> list = serviceDao.returnListofUsersRides(userId);
        assertTrue(list.size() == 0);
    }

    /**
     *methdod returnListofAvailableRides tests whether correct amount of rides is returned and inserted to database 
     * @throws SQLException
     */
    @Test
    public void returnListofAvailableRidesTest()throws SQLException{
        Ride t1 = new Ride("helsinki", "tampere", 4, 3, "840925", 3);
        
        rideDao.create(t1);
        List<Ride> list = serviceDao.returnListofAvailableRides();
        assertTrue(list.size() == 1);
    }

    /**
     *method test whether method correctCredentials return error value when credentialsare not found
     * @throws SQLException
     */
    @Test
    public void correctCredentialsTest()throws SQLException{
        User user = new User("otto", "lasma", "0458848862", "otto.lasma@aalto.fi", "ottola", "kala");
        userDao.create(user);
        
        int test = serviceDao.correctCredentials("kako", "vaara");
        assertTrue(test == -10);
    }

    /**
     *method tests whether sendemail method works properly and send email to my email address
     * @throws SQLException
     */
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
    @Test
    public void createReserveTestThroughService()throws SQLException{
        Reserve reserve = new Reserve("otto5555", "lasma11", 2, 1, "fjkdallaf11", 1);
        serviceDao.createReserve(reserve);
        int varibale = reserveDao.list().size();
        Reserve reserveTest = new Reserve("otto5555", "lasma11", 2, 1, "fjkdallaf11", 1);
        serviceDao.createReserve(reserveTest);
        int varibale2 = reserveDao.list().size();
        assertTrue((varibale2 - varibale) == 1);
        
    }
    @Test
    public void createRideTestThroughService()throws SQLException{
        Ride ride = new Ride("otto5555", "lasma11", 2, 1, "fjkdallaf11", 1);
        serviceDao.createRide(ride);
        int varibale = rideDao.list().size();
        Ride rideTest = new Ride("otto55fda55", "lasma11", 2, 1, "fjkdfadallaf11", 1);
        serviceDao.createRide(rideTest);
        int varibale2 = rideDao.list().size();
        assertTrue((varibale2 - varibale) == 1);
        
    }
    @Test
    public void readUserTestThroughService()throws SQLException{
        User user = new User("kovanen", "lasma", "43820943", "fjdlaksf", "fjkdallaf", "jfdlkfsa");
        serviceDao.createUser(user);
        User userTest  = serviceDao.readUser(user.getId());
        assertTrue(user.getName().equals(userTest.getName()));
        
    }

    /**
     *method checks whether unique username is returned 
     * @throws SQLException
     */
    @Test
    public void checkWhetherUsernameHasAlreadyBeenTakenTest()throws SQLException{
        User user = new User("kovanen", "lasma", "43820943", "fjdlaksf", "fjkdallaf", "jfdlkfsa");
        serviceDao.createUser(user);
        Scanner scanner = new Scanner(System.in);
        String usernameTest  = serviceDao.usernameHasAlreadyTaken("plahahah", scanner);
        assertTrue(usernameTest.equals("plahahah"));
        
    }

    /**
     *tests whether method returnListofUsersRides returns correct rides 
     * @throws SQLException
     */
    @Test
    public void returnListOfUsersRidesTestUserIdFound()throws SQLException{
        User user = new User("kofdavanen", "lasmfdaa", "4382094fas3", "fjdlafaksf", "fjkdafallaf", "jfdlfakfsa");
        serviceDao.createUser(user);
        Ride ride = new Ride("otto55fsa55", "lasma11", 2, 1, "fjkdallaf11", 1);
        serviceDao.createRide(ride);
        List<Ride> list = serviceDao.returnListofUsersRides(user.getId());
        assertTrue(list.size() == 1);
        
    }

    /**
     *test whether returnlistofusersreserves returns correst rides 
     * @throws SQLException
     */
    @Test
    public void returnListOfUsersReservesTestUserIdFound()throws SQLException{
        User user = new User("kovanen", "lasma", "43820943", "fjdljhaksf", "fjkdallfasaf", "jfdlkffasa");
        serviceDao.createUser(user);
        Reserve reserve = new Reserve("otto55fds55", "lasmafdajfs11", 2, 1, "fjkdaljflaf11", 1);
        serviceDao.createReserve(reserve);
        List<Reserve> list = serviceDao.returnListofUsersReserves(user.getId());
        assertTrue(list.size() == 1);
        
    }

    /**
     *method test whether correctCredentials return correct id when given credentials are correct
     * @throws SQLException
     */
    @Test
    public void correctCredentialsGivenTest()throws SQLException{
        User user = new User("otto", "lasma", "0458848862", "otto.lasma@aalto.fi", "ottafdola", "kfasala");
        userDao.create(user);
        int test = serviceDao.correctCredentials("ottafdola", "kfasala");
        assertTrue(test == user.getId());
    }
    
    /**
     *method deletes the used database file after testing has been finished
     * @throws SQLException
     */
    @After
    public void after() throws SQLException{
        File newFile = new File("testiService.db");
        newFile.delete();
        conn.close();
    }

    /**
     *method creates test environment
     */
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
