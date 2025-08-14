package com.pahanaedu.business.user.service;


import com.pahanaedu.persistence.userdao.UserDao;
import com.pahanaedu.business.user.model.User;

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

    public boolean resetPassword(String username, String phone, String newPassword) throws SQLException {
        return userDao.resetPassword(username, phone, newPassword);
    }

    public boolean isPhoneExists(String phone) throws SQLException {
        return userDao.isPhoneExists(phone);
    }

    public boolean isNicNoExists(String nicNo) throws SQLException {
        return userDao.isNicNoExists(nicNo);
    }

    public boolean isPhoneExistsForOtherUser(String phone, int excludeUserId) throws SQLException {
        return userDao.isPhoneExistsForOtherUser(phone, excludeUserId);
    }

    public boolean isNicNoExistsForOtherUser(String nicNo, int excludeUserId) throws SQLException {
        return userDao.isNicNoExistsForOtherUser(nicNo, excludeUserId);
    }




}


