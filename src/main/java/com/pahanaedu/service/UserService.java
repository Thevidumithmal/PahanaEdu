package com.pahanaedu.service;


import com.pahanaedu.dao.UserDao;
import com.pahanaedu.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    private final UserDao userDao;

    public UserService(Connection connection) {
        this.userDao = new UserDao(connection);
    }

    public User login(String username, String password, String role) throws SQLException {
        return userDao.login(username, password, role);
    }

    public boolean addUser(User user) throws SQLException {
        return userDao.addUser(user);
    }

    public List<User> getUsersByRole(String role) throws SQLException {
        return userDao.getUsersByRole(role);
    }

    public User getUserById(int id) throws SQLException {
        return userDao.getUserById(id);
    }

    public boolean updateUser(User user) throws SQLException {
        return userDao.updateUser(user);
    }

    public boolean deleteUser(int id) throws SQLException {
        return userDao.deleteUser(id);
    }


}


