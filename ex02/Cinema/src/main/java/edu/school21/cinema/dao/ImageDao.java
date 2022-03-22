package edu.school21.cinema.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class ImageDao {

    private JdbcTemplate jdbcTemplate;

    public ImageDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
