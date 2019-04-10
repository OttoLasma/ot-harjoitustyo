package ridesharing.ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"ridesharing.ui", "ridesharing.domain", "ridesharing.dao"})
public class Paaohjelma implements CommandLineRunner {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Paaohjelma.class);
    }
    @Autowired
    kokeiluKayttoliittyma tekstikayttoliittyma;

    @Override
    public void run(String... args) throws Exception {
        prepareDatabases();
        Scanner scanner = new Scanner(System.in);
        tekstikayttoliittyma.start(scanner);
    }

    private void prepareDatabases() {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:./RideSharingDatabases", "sa", "")) {
            System.out.println("fjadfa");
            conn.prepareStatement("DROP TABLE User IF EXISTS;").executeUpdate();
            conn.prepareStatement("CREATE TABLE User(id integer auto_increment, name varchar(255), surname varchar(255), phone varchar(255), email varchar(255), username varchar(255), password varchar(255), primary key (id));").executeUpdate();
            conn.prepareStatement("DROP TABLE Ride IF EXISTS;").executeUpdate();
            conn.prepareStatement("CREATE TABLE Ride(id integer auto_increment, departurelocation varchar(255), destinationlocation varchar(255), price integer, seats integer, date varchar(255), user_id integer, available integer, primary key (id), foreign key (user_id) references User(id));").executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Paaohjelma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
