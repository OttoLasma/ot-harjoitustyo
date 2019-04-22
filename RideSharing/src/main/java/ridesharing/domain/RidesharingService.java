/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ridesharing.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ridesharing.dao.RideDao;
import ridesharing.dao.UserDao;

/**
 *
 * @author ottlasma
 */
@Component
public class RidesharingService {

    @Autowired
    RideDao rideDao;
    @Autowired
    UserDao userDao;

    public boolean usernameHasAlreadyTaken(String username) throws SQLException {
        List<User> list = userDao.list();
        List<String> usernames = new ArrayList<>();
        for (User user : list) {
            usernames.add(user.getUsername());
        }
        if (usernames.contains(username)) {
            return true;
        } else {
            return false;
        }

    }

    public int correctCredentials(String username, String password) throws SQLException {
        for (User user : userDao.list()) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    return user.getId();
                }
            }
        }
        return -10;
    }

    public void createNewRide(Scanner scanner, int userId) throws SQLException {
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
        Ride ride = new Ride(departure, destination, price, seats, date, userId);
        rideDao.create(ride);
    }

   

}
