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
 * @author ottlasma
 */
@Component
public class RideDao implements RideSharingDao<Ride, Integer> {

    @Autowired
    JdbcTemplate jdbcTemplate;
//onn.prepareStatement("DROP TABLE Ride IF EXISTS;").executeUpdate();
//conn.prepareStatement("CREATE TABLE Ride(id integer auto_increment, departurelocation varchar(255), destinationlocation varchar(255), price integer, seats integer, date varchar(255), user_id integer, available integer, primary key (id), foreign key (user_id) references User(id));").executeUpdate();

    @Override
    public void create(Ride ride) throws SQLException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Ride"
                    + " (departurelocation, destinationlocation, price, seats, date, user_id, available)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, ride.getDeparturelocation());
            stmt.setString(2, ride.getDestinationlocation());
            stmt.setInt(3, ride.getPrice());
            stmt.setInt(4, ride.getSeats());
            stmt.setString(5, ride.getDate());
            stmt.setInt(6, ride.getUser_id());
            stmt.setInt(7, ride.getAvailable());
            return stmt;
        }, keyHolder);
        ride.setId(keyHolder.getKey().intValue());
    }

    @Override
    public Ride read(Integer key) throws SQLException {
        Ride ride = jdbcTemplate.queryForObject(
                "SELECT * FROM Ride WHERE id = ?",
                new BeanPropertyRowMapper<>(Ride.class),
                key);

        return ride;
    }

    @Override
    public Ride update(Ride ride) throws SQLException {
        jdbcTemplate.update("UPDATE Ride SET departurelocation = ?, destinationlocation = ?, price = ?, seats = ?, date = ?, user_id = ?, available = ? WHERE id = ?",
                ride.getDeparturelocation(),
                ride.getDestinationlocation(),
                ride.getPrice(),
                ride.getSeats(),
                ride.getDate(),
                ride.getUser_id(),
                ride.getAvailable(),
                ride.getId());

        return ride;
    }

    @Override
    public List<Ride> list() throws SQLException {
        return jdbcTemplate.query(
                "SELECT * FROM Ride",
                new BeanPropertyRowMapper<>(Ride.class));
        
    }
}
/*
conn.prepareStatement("DROP TABLE User IF EXISTS;").executeUpdate();
conn.prepareStatement("CREATE TABLE User(id integer auto_increment, name varchar(255),surname varchar(255), phone varchar(255), email varchar(255), username varchar(255), password varchar(255), primary key(id));").executeUpdate();
conn.prepareStatement("DROP TABLE Ride IF EXISTS;").executeUpdate();
conn.prepareStatement("CREATE TABLE Ride(id integer auto_increment, departurelocation varchar(255), destinationlocation varchar(255), price integer, seats integer, date varchar(255),user_id integer, foreign key (user_id) references User(id), primary key(id));").executeUpdate();

*/
