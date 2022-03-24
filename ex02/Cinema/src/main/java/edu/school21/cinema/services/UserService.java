package edu.school21.cinema.services;

import edu.school21.cinema.dao.UserDao;
import edu.school21.cinema.models.User;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public String newUser(User user) {

        String path = "/signUp";
        if (!getUser(user.getEmail()).isPresent()) {
            userDao.save(user);
            path = "/signIn";
        }
        return path;
    }

    public Optional<User> getUser(String email) {
        return userDao.findByEmail(email);
    }
}
