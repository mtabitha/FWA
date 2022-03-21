package edu.school21.cinema.services;

import edu.school21.cinema.dao.UserDao;
import edu.school21.cinema.models.User;

import javax.servlet.http.HttpServletResponse;

public class UserService {

    UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }


    public void newUser(User user, HttpServletResponse resp) {
        userDao.save(user);
    }
}
