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
import ridesharing.domain.User;

/**
 *class offer different methods that makes it more practical to get access to the database table User
 * 
 * 
 * @author ottlasma
 */
@Component
public class UserDao implements RideSharingDao<User, Integer> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     *method enables to insert new user to database user. 
     * 
     * @param user
     * @throws SQLException
     */
    @Override
    public void create(User user) throws SQLException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO User"
                    + " (name, surname, phone, email, username, password)"
                    + " VALUES (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getPhone());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getUsername());
            stmt.setString(6, user.getPassword());
            return stmt;
        }, keyHolder);
        user.setId(keyHolder.getKey().intValue());

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
        User user = jdbcTemplate.queryForObject(
                "SELECT * FROM User WHERE id = ?",
                new BeanPropertyRowMapper<>(User.class),
                key);

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
        jdbcTemplate.update("UPDATE User SET name = ?, surname = ?, phone = ?, email = ?, username = ?, password = ? WHERE id = ?",
                user.getName(),
                user.getSurname(),
                user.getPhone(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                user.getId());

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
        return jdbcTemplate.query(
                "SELECT * FROM User",
                new BeanPropertyRowMapper<>(User.class));
        
    }
}
