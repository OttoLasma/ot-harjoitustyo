package ridesharing.ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sqlite.JDBC;


public class Main {

    static Connection conn;
    static String fullname;
    public static void main(String[] args) throws SQLException {  
        Scanner scanner = new Scanner(System.in);
        System.out.println("Name the used database");
        String tietokanta = scanner.nextLine();
        fullname = "jdbc:sqlite:" + tietokanta + ".db";
        conn = getConnection();
        createTables();
        TextUserInterface textInterface = new TextUserInterface();
        textInterface.start(scanner, conn);
    }
    public static Connection getConnection() {
        if (conn == null) {
            String url = fullname;
            try {
                DriverManager.registerDriver(new JDBC());
                conn = DriverManager.getConnection(url);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return conn;
    }
    public static void createTables() {

        String url = fullname;
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
}
