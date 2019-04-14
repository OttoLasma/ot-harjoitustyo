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
 *
 * @author ottlasma
 */
@Component
public class ReserveDao implements RideSharingDao<Reserve, Integer> {

    @Autowired
    JdbcTemplate jdbcTemplate;
//onn.prepareStatement("DROP TABLE Ride IF EXISTS;").executeUpdate();
//conn.prepareStatement("CREATE TABLE Ride(id integer auto_increment, departurelocation varchar(255), destinationlocation varchar(255), price integer, seats integer, date varchar(255), user_id integer, available integer, primary key (id), foreign key (user_id) references User(id));").executeUpdate();

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

    @Override
    public Reserve read(Integer key) throws SQLException {
        Reserve reserve = jdbcTemplate.queryForObject(
                "SELECT * FROM Reserve WHERE id = ?",
                new BeanPropertyRowMapper<>(Reserve.class),
                key);

        return reserve;
    }

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

    @Override
    public List<Reserve> list() throws SQLException {
        return jdbcTemplate.query(
                "SELECT * FROM Reserve",
                new BeanPropertyRowMapper<>(Reserve.class));
        
    }
}
/*
conn.prepareStatement("DROP TABLE User IF EXISTS;").executeUpdate();
conn.prepareStatement("CREATE TABLE User(id integer auto_increment, name varchar(255),surname varchar(255), phone varchar(255), email varchar(255), username varchar(255), password varchar(255), primary key(id));").executeUpdate();
conn.prepareStatement("DROP TABLE Ride IF EXISTS;").executeUpdate();
conn.prepareStatement("CREATE TABLE Ride(id integer auto_increment, departurelocation varchar(255), destinationlocation varchar(255), price integer, seats integer, date varchar(255),user_id integer, foreign key (user_id) references User(id), primary key(id));").executeUpdate();

*/

