package dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.FavoriteDao;

public class FavoriteDaoImpl implements FavoriteDao {
    private Connection conn;
    public FavoriteDaoImpl(Connection conn) { this.conn = conn; }

    @Override
    public void addFavorite(String email, int musicId) throws SQLException {
        String sql = "INSERT INTO favorite (email, music_id) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setInt(2, musicId);
            stmt.executeUpdate();
        }
    }

    @Override
    public void removeFavorite(String email, int musicId) throws SQLException {
        String sql = "DELETE FROM favorite WHERE email = ? AND music_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setInt(2, musicId);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Integer> getFavorites(String email) throws SQLException {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT music_id FROM favorite WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("music_id"));
            }
        }
        return list;
    }
}
