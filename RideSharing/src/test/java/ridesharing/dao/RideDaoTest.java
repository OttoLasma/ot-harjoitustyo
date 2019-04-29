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
import ridesharing.domain.Ride;

/**
 *
 * @author ottlasma
 */
public class RideDaoTest {
    RideDao rideDao;
    Connection conn;
    String filename;
    public RideDaoTest() {
        filename = "jdbc:sqlite:testRide.db";
    }
    
    @Before
    public void before() {
        conn = getConnection();
        rideDao = new RideDao(conn);
        String url = filename;
        String sqlReserve = "CREATE TABLE IF NOT EXISTS Ride (\n"
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
    public void createTestEnvironmentTest(){
        File newFile = new File("testRide.db");
        boolean created = newFile.exists();
        assertTrue(created);
    }
    @Test
    public void createNewRideSuccessfullyToDatabaseTest()throws SQLException{
        Ride ride = new Ride("otto5555", "lasma11", 2, 1, "fjkdallaf11", 1);
        rideDao.create(ride);
        rideDao.list();
        String query = "Select * from Ride where destinationlocation = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, "lasma11");
        ResultSet rs = pstmt.executeQuery();
        assertTrue(rs.next());
        
    }
    
    @Test
    public void createNewRideListSizeIncreasesTest()throws SQLException{
        Ride ride = new Ride("otto5555", "lasma11", 2, 1, "fjkdallaf11", 1);
        rideDao.create(ride);
        int varibale = rideDao.list().size();
        Ride ride2 = new Ride("otto55fdsa55", "lasmfaa11", 2, 1, "fjkdfaallaf11", 1);
        rideDao.create(ride);
        int varibale2 = rideDao.list().size();
        assertTrue((varibale2 - varibale) == 1);
        
    }
    @Test
    public void updateWorksProperlyWhenMakingChangesTest()throws SQLException{
        Ride ride = new Ride("otto5555", "lasma11", 2, 1, "fjkdallaf11", 1);
        rideDao.create(ride);
        ride.setDeparturelocation("heija");
        Ride ride2 = rideDao.update(ride);
        assertTrue(ride2.getDeparturelocation().equals("heija"));
        
    }
    @Test
    public void createNewRideTestThatAttributesWorkCorrectlyTest()throws SQLException{
        Ride ride = new Ride("otto5555", "lasma11", 2, 1, "fjkdallaf11", 1);
        rideDao.create(ride);
        Ride rideTest = rideDao.read(ride.getId());
        assertTrue(rideTest.getDeparturelocation().equals("otto5555"));
        assertTrue(rideTest.getDestinationlocation().equals("lasma11"));
        assertTrue(rideTest.getDate().equals("fjkdallaf11"));
        
    }
    

}
