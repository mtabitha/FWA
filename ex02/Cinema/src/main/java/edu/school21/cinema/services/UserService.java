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

    public void newUser(User user, HttpServletResponse resp) {
        userDao.save(user);
    }

    public Optional<User> getUser(String email) {
        return userDao.findByEmail(email);
    }
}
