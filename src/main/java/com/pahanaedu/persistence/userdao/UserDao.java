package com.pahanaedu.persistence.userdao;


import com.pahanaedu.business.user.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private final Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public User login(String username, String password, String role) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ? AND role = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User.Builder()
                            .setId(rs.getInt("id"))
                            .setUsername(rs.getString("username"))
                            .setPassword(rs.getString("password"))
                            .setRole(rs.getString("role"))
                            .setPhone(rs.getString("phone"))
                            .setAddress(rs.getString("address"))
                            .build();

                    return user;
                }
            }
        }
        return null;
    }

    public boolean addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, role, phone, address, name, nic_no) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            stmt.setString(4, user.getPhone());
            stmt.setString(5, user.getAddress());
            stmt.setString(6, user.getName());
            stmt.setString(7, user.getNicNo());
            return stmt.executeUpdate() > 0;
        }
    }

    public List<User> getUsersByRole(String role) throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE role = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, role);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    User user = new User.Builder()
                            .setId(rs.getInt("id"))
                            .setUsername(rs.getString("username"))
                            .setPhone(rs.getString("phone"))
                            .setAddress(rs.getString("address"))
                            .setName(rs.getString("name"))
                            .setNicNo(rs.getString("nic_no"))
                            .build();


                    users.add(user);
                }
            }
        }
        return users;
    }

    public User getUserById(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User.Builder()
                            .setId(rs.getInt("id"))
                            .setUsername(rs.getString("username"))
                            .setPhone(rs.getString("phone"))
                            .setAddress(rs.getString("address"))
                            .setName(rs.getString("name"))
                            .setNicNo(rs.getString("nic_no"))
                            .build();
;
                    return user;
                }
            }
        }
        return null;
    }

    public boolean updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET username = ?, phone = ?, address = ?, name = ?, nic_no = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPhone());
            stmt.setString(3, user.getAddress());
            stmt.setString(4, user.getName());        // new
            stmt.setString(5, user.getNicNo());
            stmt.setInt(6, user.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }


    public boolean resetPassword(String username, String phone, String newPassword) throws SQLException {
        String sql = "UPDATE users SET password = ? WHERE username = ? AND phone = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newPassword);
            stmt.setString(2, username);
            stmt.setString(3, phone);
            return stmt.executeUpdate() > 0;
        }
    }

    // In UserDao.java
    public boolean isPhoneExists(String phone) throws SQLException {
        String sql = "SELECT COUNT(*) FROM users WHERE phone = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, phone);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public boolean isNicNoExists(String nicNo) throws SQLException {
        String sql = "SELECT COUNT(*) FROM users WHERE nic_no = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nicNo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }


    public boolean isPhoneExistsForOtherUser(String phone, int excludeUserId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM users WHERE phone = ? AND id != ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, phone);
            stmt.setInt(2, excludeUserId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public boolean isNicNoExistsForOtherUser(String nicNo, int excludeUserId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM users WHERE nic_no = ? AND id != ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nicNo);
            stmt.setInt(2, excludeUserId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }




}




