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

/**
 * class provides functionalities that makes it easier to operate with reserve
 * database table
 *
 *
 * @author ottlasma
 */
public class ReserveDao implements RideSharingDao<Reserve, Integer> {

    private Connection conn;

    public ReserveDao(Connection conn) {
        this.conn = conn;
    }

    /**
     * method create new reserve and add it to database table reserve
     *
     * @param reserve
     * @throws SQLException
     */
    @Override
    public void create(Reserve reserve) throws SQLException {
        String sql = "INSERT INTO RESERVE(departurelocation, destinationlocation, price, seats, date, userId, available) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, reserve.getDeparturelocation());
        pstmt.setString(2, reserve.getDestinationlocation());
        pstmt.setInt(3, reserve.getPrice());
        pstmt.setInt(4, reserve.getSeats());
        pstmt.setString(5, reserve.getDate());
        pstmt.setInt(6, reserve.getUserId());
        pstmt.setInt(7, reserve.getAvailable());  
        pstmt.executeUpdate();
        String sql2 = "select last_insert_rowid()";
        PreparedStatement pstmt2 = conn.prepareStatement(sql2);
        ResultSet rs = pstmt2.executeQuery();
        reserve.setId(rs.getInt(1));
        
        
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
        String sql = "SELECT * FROM Reserve WHERE id = " + key;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        Reserve reserve = new Reserve(rs.getString("departurelocation"), rs.getString("destinationlocation"), rs.getInt("price"), rs.getInt("seats"), rs.getString("date"), rs.getInt("userId"));
        reserve.setId(rs.getInt("id"));
        return reserve;
    }

    /**
     * method updates the gives reserve with provided new information
     *
     * @param reserve
     * @return
     * @throws SQLException
     */
    @Override
    public Reserve update(Reserve reserve) throws SQLException {
        String sql = "UPDATE Reserve SET departurelocation = ?, destinationlocation = ?, price = ?, seats = ?, date = ?, userId = ?, available = ? WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, reserve.getDeparturelocation());
        pstmt.setString(2, reserve.getDestinationlocation());
        pstmt.setInt(3, reserve.getPrice());
        pstmt.setInt(4, reserve.getSeats());
        pstmt.setString(5, reserve.getDate());
        pstmt.setInt(6, reserve.getUserId());
        pstmt.setInt(7, reserve.getAvailable());
        pstmt.setInt(8, reserve.getId());
        pstmt.executeUpdate();
        return reserve;
    }

    /**
     * method returns list of all reserves that have been added to the database
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Reserve> list() throws SQLException {
        String sql = "SELECT * FROM Reserve";
        List<Reserve> reserves = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Reserve reserve = new Reserve(rs.getString("departurelocation"), rs.getString("destinationlocation"), rs.getInt("price"), rs.getInt("seats"), rs.getString("date"), rs.getInt("userId"));
            reserve.setId(rs.getInt("id"));
            reserves.add(reserve);
        }
        return reserves;

    }
}
