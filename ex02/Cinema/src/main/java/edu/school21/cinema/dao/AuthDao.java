package edu.school21.cinema.dao;

import edu.school21.cinema.models.Auth;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class AuthDao {

    //language=SQL
    private String SQL_FIND_ALL_AUTH = "SELECT * FROM authentications";
    private String SQL_SAVE_AUTH = "INSERT INTO authentications VALUES(?,?)";

    private RowMapper<Auth> authRowMapper = ((rs, rowNum) -> {
        return new Auth(
                rs.getTimestamp("dateTime").toLocalDateTime(),
                rs.getString("ip")
        );
    });

    private JdbcTemplate jdbcTemplate;

    public AuthDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Auth> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL_AUTH, authRowMapper);
    }

    public void save(Auth auth) {
        jdbcTemplate.update(SQL_SAVE_AUTH,
                auth.getDateTime(),
                auth.getIp()
        );
    }
}
