package ridesharing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ridesharing.domain.User;

/**
 *class offer different methods that makes it more practical to get access to the database table User
 * 
 * 
 * @author ottlasma
 */

public class UserDao implements RideSharingDao<User, Integer> {

    private Connection conn;
    public UserDao(Connection conn) {
        this.conn = conn;
    }
    /**
     *method enables to insert new user to database user. 
     * 
     * @param user
     * @throws SQLException
     */
    @Override
    public void create(User user) throws SQLException {
        String sql = "INSERT INTO USER(name, surname, phone, email, username, password) VALUES(?,?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getName());
        pstmt.setString(2, user.getSurname());
        pstmt.setString(3, user.getPhone());
        pstmt.setString(4, user.getEmail());
        pstmt.setString(5, user.getUsername());
        pstmt.setString(6, user.getPassword());
        pstmt.executeUpdate();
        String sql2 = "select last_insert_rowid()";
        PreparedStatement pstmt2 = conn.prepareStatement(sql2);
        ResultSet rs = pstmt2.executeQuery();
        user.setId(rs.getInt(1));
    }

    /**
     *method enables to search specific user from user table based on given key
     * 
     * 
     * @param key
     * @return
     * @throws SQLException
     */
    @Override
    public User read(Integer key) throws SQLException {
        String sql = "SELECT * FROM User WHERE id = " + key;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        User user = new User(rs.getString("name"), rs.getString("surname"), rs.getString("phone"), rs.getString("email"), rs.getString("username"), rs.getString("password"));
        user.setId(rs.getInt("id"));
        return user;
    }

    /**
     *method enables to update the information of certain user in database
     * 
     * @param user
     * @return
     * @throws SQLException
     */
    @Override
    public User update(User user) throws SQLException {
        String sql = "UPDATE User SET name = ?, surname = ?, phone = ?, email = ?, username = ?, password = ? WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getName());
        pstmt.setString(2, user.getSurname());
        pstmt.setString(3, user.getPhone());
        pstmt.setString(4, user.getEmail());
        pstmt.setString(5, user.getUsername());
        pstmt.setString(6, user.getPassword());
        pstmt.setInt(7, user.getId());
        pstmt.executeUpdate();
        return user;
    }

    /**
     *method provides list of all the users added to database table user
     * 
     * @return
     * @throws SQLException
     */
    @Override
    public List<User> list() throws SQLException {
        String sql = "SELECT * FROM User";
        List<User> users = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            User user = new User(rs.getString("name"), rs.getString("surname"), rs.getString("phone"), rs.getString("email"), rs.getString("username"), rs.getString("password"));
            user.setId(rs.getInt("id"));
            users.add(user);
        }
        return users;
    }
}
