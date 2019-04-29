/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ridesharing.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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

    public RidesharingServiceTest() {
        filename = "jdbc:sqlite:testiService.db";
    }


    public Connection getConnection() {
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
        return conn;
    }

    @Before
    public void setUp() {
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

    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
