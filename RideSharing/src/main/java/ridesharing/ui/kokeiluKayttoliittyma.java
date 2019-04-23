package ridesharing.ui;

import ridesharing.domain.Ride;
import ridesharing.dao.RideDao;
import ridesharing.domain.User;
import ridesharing.dao.UserDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import ridesharing.dao.ReserveDao;
import ridesharing.domain.Reserve;
import ridesharing.domain.RidesharingService;

/**
 *provides interface for the application
 * @author ottlasma
 */
@Component
public class kokeiluKayttoliittyma {

    @Autowired
    RideDao rideDao;
    @Autowired
    UserDao userDao;
    @Autowired
    ReserveDao reserveDao;
    @Autowired
    RidesharingService serviceDao;
    private Scanner scanner;

    public kokeiluKayttoliittyma() {

    }

    /**
     *actual interface 
     * 
     * @param scanner
     * @throws SQLException
     */
    public void start(Scanner scanner) throws SQLException {
        this.scanner = scanner;

        while (true) {
            System.out.println("Commands: ");
            System.out.println(" x - exit");
            System.out.println(" 1 - Sign up");
            System.out.println(" 2 - Log in");
            String command = scanner.nextLine();
            if (command.equals("x")) {
                System.exit(0);
            }
            if (command.equals("1")) {

                System.out.println("Provide below requested information in order to sign in");
                System.out.println("Name");
                String name = scanner.nextLine();
                System.out.println("Surname");
                String surname = scanner.nextLine();
                System.out.println("email");
                String email = scanner.nextLine();
                System.out.println("Phone");
                String phone = scanner.nextLine();
                System.out.println("Username");
                String username = scanner.nextLine();   
                username = serviceDao.usernameHasAlreadyTaken(username, scanner);
                System.out.println("Password");
                String password = scanner.nextLine();
                User user = new User(name, surname, phone, email, username, password);
                serviceDao.createUser(user);
                features(user);
            }
            if (command.equals("2")) {
                System.out.println("Username");
                String username = scanner.nextLine();
                System.out.println("password");
                String password = scanner.nextLine();
                int variableId = serviceDao.correctCredentials(username, password);
                if (variableId == -10) {
                    System.out.println("Incorrect username or password");
                    start(scanner);
                } else {
                    features(serviceDao.readUser(variableId));
                }
            }
        }
    }
    /**
     *actual interface although highlighting features for logged in user
     * 
     * @param user
     * @throws SQLException
     */
    private void features(User user) throws SQLException {
        while (true) {
            System.out.println("Commands: ");
            System.out.println(" 1 - Add new ride");
            System.out.println(" 2 - List all available rides");
            System.out.println(" 3 - List rides that have been added by you");
            System.out.println(" 4 - Reserve a ride from available rides");
            System.out.println(" 5 - List rides that have been reserved by you");
            System.out.println(" 6 - Summary and details of: " + user.getName());
            System.out.println(" x - Log out");
            String command = scanner.nextLine();
            if (command.equals("x")) {
                start(scanner);
            }
            if (command.equals("1")) {
                System.out.println("Provide the needed information below in order to add new ride:");
                System.out.println("Location of departure");
                String departure = scanner.nextLine();
                System.out.println("Location of destination");
                String destination = scanner.nextLine();
                System.out.println("Total price for the ride");
                int price = Integer.parseInt(scanner.nextLine());
                System.out.println("Number of available rides");
                int seats = Integer.parseInt(scanner.nextLine());
                System.out.println("Estimated Time and date of the departure (mm/dd-hh/mm)");
                String date = scanner.nextLine();
                Ride ride = new Ride(departure, destination, price, seats, date, user.getId());
                serviceDao.createRide(ride);
            }
            if (command.equals("2")) {
                System.out.println("List of all available rides: ");
                List<Ride> list = serviceDao.returnListofAvailableRides();
                for (Ride ride : list) {
                    System.out.println(ride);
                }
            }
            if (command.equals("3")) {
                System.out.println("List of all the rides that have been added by you:");
                List<Ride> list = serviceDao.returnListofUsersRides(user.getId());
                for (Ride ride : list) {
                    System.out.println(ride);
                }
            }
            if (command.equals("4")) {
                List<Ride> list = serviceDao.returnListofAvailableRides();
                System.out.println("Choose preferred ride (" + 1 + "-" + list.size() + ") from below listed rides" + ". In case you cannot find suitable ride press 'x'.");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i + 1 + ". " + list.get(i));
                }
                String variable = scanner.nextLine();
                if (variable.equals("x")) {
                    features(user);
                }            
                serviceDao.reserveRideAndReserve(list, variable, user.getId());
                System.out.println("Ride has been reserved!");
            }
            if (command.equals("5")) {
                System.out.println("List of all the rides that have been reserved by you: ");
                List<Reserve> list = serviceDao.returnListofUsersReserves(user.getId());
                for (Reserve reserve : list) {
                    System.out.println(reserve);
                }
            }

        }
    }
}
