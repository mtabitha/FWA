package edu.school21.cinema.dao;

import edu.school21.cinema.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Optional;

public class UserDao {

    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private String SQL_SAVE_USER = "INSERT INTO usr VALUES (?, ?, ? ,?)";
    //language=SQL
    private String SQL_FIND_BY_EMAIL = "SELECT * FROM usr WHERE email=?";

    private RowMapper<User> userRowMapper = (rs, rowNum) -> {
        return new User(
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("email"),
                    rs.getString("password")
        );
    };

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(User user) {
        jdbcTemplate.update(SQL_SAVE_USER,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword()
        );
    }

    public Optional<User> findByEmail(String email) {
        return jdbcTemplate.query(SQL_FIND_BY_EMAIL, userRowMapper, email).stream().findAny();
    }
}
