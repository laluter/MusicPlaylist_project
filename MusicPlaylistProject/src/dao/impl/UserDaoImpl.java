package dao.impl;

import dao.UserDao;


import model.User;
import java.sql.*;
import java.util.*;

public class UserDaoImpl implements UserDao {
    private Connection conn;

    public UserDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public User findByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String password = rs.getString("password");
                boolean isAdmin = rs.getInt("is_admin") == 1;
                // 즐겨찾기, 최근검색어 등은 별도 쿼리 필요
                return new User(email, password, isAdmin);
            }
        }
        return null;
    }

    @Override
    public List<User> findAll() throws SQLException {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String email = rs.getString("email");
                String password = rs.getString("password");
                boolean isAdmin = rs.getInt("is_admin") == 1;
                users.add(new User(email, password, isAdmin));
            }
        }
        return users;
    }

    @Override
    public void save(User user) throws SQLException {
        String sql = "INSERT INTO users (email, password, is_admin) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.isAdmin() ? 1 : 0);
            stmt.executeUpdate();
        }
    }

    @Override
    public void update(User user) throws SQLException {
        String sql = "UPDATE users SET password = ?, is_admin = ? WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getPassword());
            stmt.setInt(2, user.isAdmin() ? 1 : 0);
            stmt.setString(3, user.getEmail());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(String email) throws SQLException {
        String sql = "DELETE FROM users WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.executeUpdate();
        }
    }
}

