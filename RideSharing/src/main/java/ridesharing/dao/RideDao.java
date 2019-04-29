/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ridesharing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ridesharing.domain.Reserve;
import ridesharing.domain.Ride;
import ridesharing.domain.User;
import ridesharing.domain.Ride;

/**
 *
 * class provides certain functionalities that makes it easier to get access to database
 * 
 * @author ottlasma
 */

public class RideDao implements RideSharingDao<Ride, Integer> {

    private Connection conn;
    public RideDao(Connection conn){
        this.conn = conn;
    }
    /**
     *method enables to insert new ride to the database. values are taken from provided parameters
     * 
     * 
     * @param ride
     * @throws SQLException
     */
    @Override
    public void create(Ride ride) throws SQLException {
        String sql = "INSERT INTO Ride(departurelocation, destinationlocation, price, seats, date, userId, available) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, ride.getDeparturelocation());
        pstmt.setString(2, ride.getDestinationlocation());
        pstmt.setInt(3, ride.getPrice());
        pstmt.setInt(4, ride.getSeats());
        pstmt.setString(5, ride.getDate());
        pstmt.setInt(6, ride.getUserId());
        pstmt.setInt(7, ride.getAvailable());
        pstmt.executeUpdate();
        String sql2 = "select last_insert_rowid()";
        PreparedStatement pstmt2 = conn.prepareStatement(sql2);
        ResultSet rs = pstmt2.executeQuery();
        ride.setId(rs.getInt(1));
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
        String sql = "SELECT * FROM Ride WHERE id = " + key;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        Ride ride = new Ride(rs.getString("departurelocation"), rs.getString("destinationlocation"), rs.getInt("price"), rs.getInt("seats"), rs.getString("date"), rs.getInt("userId"), rs.getInt("available"));
        ride.setId(rs.getInt("id"));
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
        String sql = "UPDATE Ride SET departurelocation = ?, destinationlocation = ?, price = ?, seats = ?, date = ?, userId = ? , available = ? WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, ride.getDeparturelocation());
        pstmt.setString(2, ride.getDestinationlocation());
        pstmt.setInt(3, ride.getPrice());
        pstmt.setInt(4, ride.getSeats());
        pstmt.setString(5, ride.getDate());
        pstmt.setInt(6, ride.getUserId());
        pstmt.setInt(7, ride.getAvailable());
        pstmt.setInt(8, ride.getId());
        pstmt.executeUpdate();
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
        String sql = "SELECT * FROM Ride";
        List<Ride> rides = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Ride ride = new Ride(rs.getString("departurelocation"), rs.getString("destinationlocation"), rs.getInt("price"), rs.getInt("seats"), rs.getString("date"), rs.getInt("userId"), rs.getInt("available"));
            ride.setId(rs.getInt("id"));
            rides.add(ride);
        }
        return rides;
        
    }
}

