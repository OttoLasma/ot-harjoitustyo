/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ridesharing.domain;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import ridesharing.dao.ReserveDao;
import ridesharing.dao.RideDao;
import ridesharing.dao.UserDao;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * this class provides some methods that are needed in interface
 *
 *
 * @author ottlasma
 */
public class RidesharingService {

    RideDao rideDao;

    UserDao userDao;

    ReserveDao reserveDao;

    public RidesharingService(Connection conn) {
        rideDao = new RideDao(conn);
        userDao = new UserDao(conn);
        reserveDao = new ReserveDao(conn);
    }

    /**
     * method asks username until user provides username that has not been added
     * to the databases. When valid username is given method returns this
     * username.
     *
     *
     * @param username
     * @return username
     * @throws SQLException
     */
    public String usernameHasAlreadyTaken(String username, Scanner scanner) throws SQLException {
        List<User> list = userDao.list();
        List<String> usernames = new ArrayList<>();
        for (User user : list) {
            usernames.add(user.getUsername());
        }
        if (usernames.contains(username)) {
            while (true) {
                System.out.println("This username is already taken. Try again with different username.");
                System.out.println("Username: ");
                username = scanner.nextLine();
                if (!usernames.contains(username)) {
                    return username;
                }
            }
        } else {
            return username;
        }
    }

    /**
     * method creates add new user to database table user
     *
     * @param user
     * @throws SQLException
     */
    public void createUser(User user) throws SQLException {
        userDao.create(user);
    }

    /**
     * method returns user having the id as parameter variableId from database
     *
     * @param variableId
     * @return
     * @throws SQLException
     */
    public User readUser(int variableId) throws SQLException {
        return userDao.read(variableId);
    }

    /**
     * method inserts new ride to database
     *
     * @param ride
     * @throws SQLException
     */
    public void createRide(Ride ride) throws SQLException {
        rideDao.create(ride);
    }

    /**
     * method inserts new reserve to database
     *
     * @param reserve
     * @throws SQLException
     */
    public void createReserve(Reserve reserve) throws SQLException {
        reserveDao.create(reserve);
    }

    /**
     * method updates the available status of chosen ride and in addition
     * creates new reserve for that specific ride
     *
     * @param list
     * @param variable
     * @param userId
     * @throws SQLException
     */
    public void reserveRideAndReserve(List<Ride> list, String variable, int userId) throws SQLException {
        int variable2 = Integer.parseInt(variable);
        Ride ride = list.get(variable2 - 1);
        ride.setAvailable(1);
        rideDao.update(ride);
        Reserve reserve = new Reserve(ride.getDeparturelocation(), ride.getDestinationlocation(), ride.getPrice(), ride.getSeats(), ride.getDate(), userId);
        reserveDao.create(reserve);
        List<User> listUsers = userDao.list();
        String userEmail = "";
        for (User user : listUsers) {
            if (user.getId() == ride.getUserId()) {
                userEmail = user.getEmail();
            }
        }
        boolean emailStatus = false;
        try {
            emailStatus = sendEmail(userEmail);
        } catch (EmailException ex) {
            System.out.println("Notification email cannot be sent");
        }
        System.out.println(emailStatus);
    }

    /**
     * method sends an email to the user that has previously added the ride in
     * question
     *
     * @param userEmail
     * @return
     * @throws EmailException
     */
    public boolean sendEmail(String userEmail) throws EmailException {
        try {

            Email email = new SimpleEmail();
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("ridesharingpirssi@gmail.com", "ridesharing"));

            // Required for gmail
            email.setSSLOnConnect(true);
            // Sender
            email.setFrom("ridesharingpirssi@gmail.com");

            // Email title
            email.setSubject("RideSharing Pirssi");

            // Email message.
            email.setMsg("Your ride has been reserved!");

            // Receiver
            email.addTo(userEmail);
            email.send();
            System.out.println("Sent!!");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Method returns current user's user id in case available.
     *
     * @param username
     * @param password
     * @return user id or error value
     * @throws SQLException
     */
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

    /**
     * method returns list of rides that haven't been taken by other users
     *
     *
     * @return list
     * @throws SQLException
     */
    public List<Ride> returnListofAvailableRides() throws SQLException {
        List<Ride> list = new ArrayList<>();
        for (Ride ride : rideDao.list()) {
            if (ride.getAvailable() == 0) {
                list.add(ride);
            }
        }
        return list;
    }

    /**
     * method returns list of rides that has been added by the user in question
     *
     *
     * @param userId
     * @return list
     * @throws SQLException
     */
    public List<Ride> returnListofUsersRides(int userId) throws SQLException {
        List<Ride> list = new ArrayList<>();
        for (Ride ride : rideDao.list()) {
            if (ride.getUserId() == userId) {
                list.add(ride);
            }
        }
        return list;
    }

    /**
     * method returns list of reserves that has been made by the user in
     * question
     *
     *
     * @param userId
     * @return list
     * @throws SQLException
     */
    public List<Reserve> returnListofUsersReserves(int userId) throws SQLException {
        List<Reserve> list = new ArrayList<>();
        for (Reserve reserve : reserveDao.list()) {
            if (reserve.getUserId() == userId) {
                list.add(reserve);
            }
        }
        return list;
    }

}
