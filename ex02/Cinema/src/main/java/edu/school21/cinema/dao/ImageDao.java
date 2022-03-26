package edu.school21.cinema.dao;

import edu.school21.cinema.models.Image;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

public class ImageDao {

    private JdbcTemplate jdbcTemplate;

    private RowMapper<Image> imageRowMapper = ((rs, rowNum) -> {
       return new Image(
               rs.getString("id"),
               rs.getString("name"),
               rs.getString("size"),
               rs.getString("path")
       );
    });

    //language=SQL
    private String SQL_SAVE_IMAGE = "INSERT INTO image VALUES (?, ?, ?, ?)";
    //language=SQL
    private String SQL_FIND_ALL_IMAGES = "SELECT * FROM image";
    //language=SQL
    private String SQL_FIND_BY_NAME_IMAGES = "SELECT * FROM image WHERE id=?";


    public ImageDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Image image) {
        jdbcTemplate.update(SQL_SAVE_IMAGE,
                image.getId(),
                image.getName(),
                image.getSize(),
                image.getPath()
        );
    }

    public List<Image> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL_IMAGES, imageRowMapper );
    }

    public Optional<Image> findById(String imageId) {
        return jdbcTemplate.query(SQL_FIND_BY_NAME_IMAGES, imageRowMapper, imageId).stream().findAny();
    }
}
