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
import ridesharing.domain.Reserve;
import ridesharing.domain.Ride;

/**
 *class provides functionalities that makes it easier to operate with reserve database table
 * 
 * 
 * @author ottlasma
 */
@Component
public class ReserveDao implements RideSharingDao<Reserve, Integer> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     *method create new reserve and add it to database table reserve
     * 
     * @param reserve
     * @throws SQLException
     */
    @Override
    public void create(Reserve reserve) throws SQLException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Reserve"
                    + " (departurelocation, destinationlocation, price, seats, date, userId, available)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, reserve.getDeparturelocation());
            stmt.setString(2, reserve.getDestinationlocation());
            stmt.setInt(3, reserve.getPrice());
            stmt.setInt(4, reserve.getSeats());
            stmt.setString(5, reserve.getDate());
            stmt.setInt(6, reserve.getUserId());
            stmt.setInt(7, reserve.getAvailable());
            return stmt;
        }, keyHolder);
        reserve.setId(keyHolder.getKey().intValue());
    }

    /**
     * method returns reserve that matches with the given parameter
     *
     * @param key
     * @return reserve
     * @throws SQLException
     */
    @Override
    public Reserve read(Integer key) throws SQLException {
        Reserve reserve = jdbcTemplate.queryForObject(
                "SELECT * FROM Reserve WHERE id = ?",
                new BeanPropertyRowMapper<>(Reserve.class),
                key);

        return reserve;
    }

    /**
     * method updates the gives reserve with provided new information
     * @param reserve
     * @return
     * @throws SQLException
     */
    @Override
    public Reserve update(Reserve reserve) throws SQLException {
        jdbcTemplate.update("UPDATE Reserve SET departurelocation = ?, destinationlocation = ?, price = ?, seats = ?, date = ?, userId = ?, available = ? WHERE id = ?",
                reserve.getDeparturelocation(),
                reserve.getDestinationlocation(),
                reserve.getPrice(),
                reserve.getSeats(),
                reserve.getDate(),
                reserve.getUserId(),
                reserve.getAvailable(),
                reserve.getId());

        return reserve;
    }

    /**
     *method returns list of all reserves that have been added to the database
     * @return
     * @throws SQLException
     */
    @Override
    public List<Reserve> list() throws SQLException {
        return jdbcTemplate.query(
                "SELECT * FROM Reserve",
                new BeanPropertyRowMapper<>(Reserve.class));
        
    }
}


