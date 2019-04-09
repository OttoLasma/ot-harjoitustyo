
package RideSharing;

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


@Component
public class UserDao implements RideSharingDao<User, Integer>{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void create(User user) throws SQLException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO User"
                + " (name, surname, phone, email, username, password)"
                + " VALUES (?, ?, ?, ?, ?, ?)",
                 Statement.RETURN_GENERATED_KEYS); 
                stmt.setString(1 ,  user.getName());
                stmt.setString(2 ,  user.getSurname());
                stmt.setString(3 ,  user.getPhone());
                stmt.setString(4 ,  user.getEmail());
                stmt.setString(5 ,  user.getUsername());
                stmt.setString(6 ,  user.getPassword());
                return stmt;         
        },keyHolder);          
        user.setId(keyHolder.getKey().intValue());
    
    }

    @Override
    public User read(Integer key) throws SQLException {
        User user = jdbcTemplate.queryForObject(
                "SELECT * FROM User WHERE id = ?",
                new BeanPropertyRowMapper<>(User.class),
                key);
 
        return user;
    }

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

    @Override
    public List<User> list() throws SQLException {
        List<User> lista =  jdbcTemplate.query(
                "SELECT * FROM User",
                new BeanPropertyRowMapper<>(User.class));
        if(lista.isEmpty()){
            return new ArrayList<User>();
        }
        return lista;
    }
}
/*
conn.prepareStatement("DROP TABLE User IF EXISTS;").executeUpdate();
conn.prepareStatement("CREATE TABLE User(id integer auto_increment, name varchar(255),surname varchar(255), phone varchar(255), email varchar(255), username varchar(255), password varchar(255), primary key(id));").executeUpdate();
conn.prepareStatement("DROP TABLE Ride IF EXISTS;").executeUpdate();
conn.prepareStatement("CREATE TABLE Ride(id integer auto_increment, departurelocation varchar(255), destinationlocation varchar(255), price integer, seats integer, date varchar(255),user_id integer, foreign key (user_id) references User(id), primary key(id));").executeUpdate();

*/
