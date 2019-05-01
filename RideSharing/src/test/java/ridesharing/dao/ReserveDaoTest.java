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

/**
 *
 * @author ottlasma
 */
public class ReserveDaoTest {
    ReserveDao reserveDao;
    Connection conn;
    String filename;
    public ReserveDaoTest() {
        filename = "jdbc:sqlite:testiReserve.db";
    }
    
    @Before
    public void before() {
        conn = getConnection();
        reserveDao = new ReserveDao(conn);
        String url = filename;
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
        File newFile = new File("testiReserve.db");
        newFile.delete();
        conn.close();
    }
    @Test
    public void createTestEnvironment(){
        File newFile = new File("testiReserve.db");
        boolean created = newFile.exists();
        assertTrue(created);
    }
    
    
    @Test
    public void createNewReserveListSizeIncreasesTest()throws SQLException{
        Reserve reserve = new Reserve("otto5555", "lasma11", 2, 1, "fjkdallaf11", 1);
        reserveDao.create(reserve);
        int varibale = reserveDao.list().size();
        Reserve reserve2 = new Reserve("otto55fdsa55", "lasmfaa11", 2, 1, "fjkdfaallaf11", 1);
        reserveDao.create(reserve2);
        int varibale2 = reserveDao.list().size();
        assertTrue((varibale2 - varibale) == 1);
        
    }
    @Test
    public void updateWorksProperlyWhenMakingChangesTest()throws SQLException{
        Reserve reserve = new Reserve("otto5555", "lasma11", 2, 1, "fjkdallaf11", 1);
        reserveDao.create(reserve);
        reserve.setDeparturelocation("heija");
        Reserve reserve2 = reserveDao.update(reserve);
        assertTrue(reserve2.getDeparturelocation().equals("heija"));
        
    }
    @Test
    public void createNewReserveTestThatAttributesWorkCorrectlyTest()throws SQLException{
        Reserve reserve = new Reserve("otto5555", "lasma11", 2, 1, "fjkdallaf11", 1);
        reserveDao.create(reserve);
        Reserve reserveTest = reserveDao.read(reserve.getId());
        assertTrue(reserveTest.getDeparturelocation().equals("otto5555"));
        assertTrue(reserveTest.getDestinationlocation().equals("lasma11"));
        assertTrue(reserveTest.getDate().equals("fjkdallaf11"));
        
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
