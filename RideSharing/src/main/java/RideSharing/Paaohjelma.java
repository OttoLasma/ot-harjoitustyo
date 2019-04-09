/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RideSharing;

/**
 *
 * @author ottlasma
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;





public class Paaohjelma  {
    
    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);
        kokeiluKayttoliittyma kokeKayttoliittyma = new kokeiluKayttoliittyma(scanner);
        kokeiluKayttoliittyma.start();
    }
    
    
    
    

    /*private static void prepareDatabases() {
        
        try (Connection conn = DriverManager.getConnection("jdbc:h2:./RideSharingDatabases", "sa", "")) {         
            conn.prepareStatement("DROP TABLE User IF EXISTS;").executeUpdate();
            conn.prepareStatement("CREATE TABLE User(id integer auto_increment, name varchar(255), email varchar(255), phone varchar(255), primary key(id));").executeUpdate();
            conn.prepareStatement("DROP TABLE Ride IF EXISTS;").executeUpdate();
            conn.prepareStatement("CREATE TABLE Ride(id integer auto_increment, departure_location varchar(255), destination_location varchar(255), free_spots integer, price integer, user_id integer, foreign key (user_id) references User(id), primary key(id));").executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AppUi.class.getName()).log(Level.SEVERE, null, ex);

        }
    }*/
}
