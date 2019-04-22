/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ridesharing.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ridesharing.domain.Ride;

/**
 *
 * class provides certain functionalities that makes it easier to get access to database
 * 
 * @author ottlasma
 */
@Component
public class RideDao implements RideSharingDao<Ride, Integer> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     *method enables to insert new ride to the database. values are taken from provided parameters
     * 
     * 
     * @param ride
     * @throws SQLException
     */
    @Override
    public void create(Ride ride) throws SQLException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Ride"
                    + " (departurelocation, destinationlocation, price, seats, date, userId, available)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, ride.getDeparturelocation());
            stmt.setString(2, ride.getDestinationlocation());
            stmt.setInt(3, ride.getPrice());
            stmt.setInt(4, ride.getSeats());
            stmt.setString(5, ride.getDate());
            stmt.setInt(6, ride.getUserId());
            stmt.setInt(7, ride.getAvailable());
            return stmt;
        }, keyHolder);
        ride.setId(keyHolder.getKey().intValue());
    }

    /**
     *method returns ride which has the same key as provided 
     * 
     * 
     * @param key
     * @return ride
     * @throws SQLException
     */
    @Override
    public Ride read(Integer key) throws SQLException {
        Ride ride = jdbcTemplate.queryForObject(
                "SELECT * FROM Ride WHERE id = ?",
                new BeanPropertyRowMapper<>(Ride.class),
                key);

        return ride;
    }

    /**
     *method makes it possible to update one specific ride with given information
     * 
     * 
     * @param ride
     * @return
     * @throws SQLException
     */
    @Override
    public Ride update(Ride ride) throws SQLException {
        jdbcTemplate.update("UPDATE Ride SET departurelocation = ?, destinationlocation = ?, price = ?, seats = ?, date = ?, userId = ?, available = ? WHERE id = ?",
                ride.getDeparturelocation(),
                ride.getDestinationlocation(),
                ride.getPrice(),
                ride.getSeats(),
                ride.getDate(),
                ride.getUserId(),
                ride.getAvailable(),
                ride.getId());

        return ride;
    }

    /**
     *method returns list of rides that have been added to database
     * 
     * 
     * @return
     * @throws SQLException
     */
    @Override
    public List<Ride> list() throws SQLException {
        return jdbcTemplate.query(
                "SELECT * FROM Ride",
                new BeanPropertyRowMapper<>(Ride.class));
        
    }
}

