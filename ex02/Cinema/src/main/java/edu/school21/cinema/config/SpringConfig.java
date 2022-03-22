package edu.school21.cinema.config;

import edu.school21.cinema.dao.AuthDao;
import edu.school21.cinema.dao.ImageDao;
import edu.school21.cinema.services.AuthService;
import edu.school21.cinema.dao.UserDao;
import edu.school21.cinema.services.ImageService;
import edu.school21.cinema.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.io.File;

@Configuration
@PropertySource("classpath:../application.properties")
public class SpringConfig  {

    @Value("${db.driverClassName}")
    public String driverClassName;
    @Value("${db.url}")
    public String url;
    @Value("${db.username}")
    public String username;
    @Value("${db.password}")
    public String password;
    @Value("${storage.path}")
    private String storagePath;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    DatabasePopulator databasePopulator() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScripts(
                new ClassPathResource("sql/schema.sql"),
                new ClassPathResource("sql/data.sql")
        );
        return databasePopulator;
    }

    @Bean
    DataSourceInitializer dataSourceInitializer() {

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource());
        dataSourceInitializer.setDatabasePopulator(databasePopulator());
        return dataSourceInitializer;
    }

    @Bean
    JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    UserDao userDao() {
        return new UserDao(jdbcTemplate());
    }

    @Bean
    UserService userService() {
        return new UserService(userDao());
    }

    @Bean
    AuthDao authDao() {
        return new AuthDao(jdbcTemplate());
    }

    @Bean
    AuthService authService() {
        return new AuthService(authDao());
    }

    @Bean
    ImageDao    imageDao() {
        return new ImageDao(jdbcTemplate());
    }

    @Bean
    ImageService imageService() {
        return new ImageService(imageDao(), storagePath);
    }


}
