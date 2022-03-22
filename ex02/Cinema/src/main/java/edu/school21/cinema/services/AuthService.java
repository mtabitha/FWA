package edu.school21.cinema.services;

import edu.school21.cinema.dao.AuthDao;
import edu.school21.cinema.models.Auth;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AuthService {

    private AuthDao authDao;

    private Comparator<Auth> comp = Comparator.comparing(Auth::getDateTime).reversed();

    public AuthService(AuthDao authDao) {
        this.authDao = authDao;
    }

    public List<Auth> getAuthentications() {
        List<Auth> authList = authDao.findAll();
        authList.sort(comp);
        return authList;
    }

    public void newAuth(Auth auth) {
        authDao.save(auth);
    }
}
