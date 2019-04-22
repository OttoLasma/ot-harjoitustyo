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
import ridesharing.dao.ReserveDao;
import ridesharing.dao.RideDao;
import ridesharing.dao.UserDao;

/**
 * this class provides some methods that are needed in interface
 * 
 *
 * @author ottlasma
 */
@Component
public class RidesharingService {

    @Autowired
    RideDao rideDao;
    @Autowired
    UserDao userDao;
    @Autowired
    ReserveDao reserveDao;

    /**
     * method returns true in case username has already been taken otherwise method returns false
     * 
     * 
     * @param username
     * @return true or false
     * @throws SQLException
     */
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
     *method returns list of rides that haven't been taken by other users
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
     *method returns list of rides that has been added by the user in question
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
     *method returns list of reserves that has been made by the user in question
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
