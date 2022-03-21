package edu.school21.cinema.dao;

import edu.school21.cinema.models.User;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {

    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private String SQL_SAVE = "INSERT INTO usr VALUES (?, ?, ? ,?)";
    //language=SQL
    private String SQL_FIND_BY_EMAIL = "SELECT * FROM usr WHERE email=?";

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(User user) {
        jdbcTemplate.update(SQL_SAVE,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword()
        );
    }

//    public Optional<User> findByEmail(String email) {
//        jdbcTemplate.query(SQL_FIND_BY_EMAIL, email, );
//    }
}
