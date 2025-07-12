package com.pahanaedu.service;

import com.pahanaedu.dao.UserDao;
import com.pahanaedu.model.User;
import java.sql.Connection;
import java.sql.SQLException;

public class UserService {
    private UserDao userDao;

    public UserService(Connection connection) {
        this.userDao = new UserDao(connection);
    }

    public User authenticate(String username, String password) throws SQLException {
        return userDao.getUserByUsernameAndPassword(username, password);
    }
}

