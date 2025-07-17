package dao.impl;

import dao.MusicDao;


import model.Music;
import java.sql.*;
import java.util.*;

public class MusicDaoImpl implements MusicDao {
    private Connection conn;

    public MusicDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Music findById(int id) throws SQLException {
        String sql = "SELECT * FROM music WHERE music_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Music(
                    rs.getInt("music_id"),
                    rs.getString("title"),
                    rs.getString("artist"),
                    rs.getString("album"),
                    rs.getString("youtube_link"),
                    rs.getString("spotify_link")
                );
            }
        }
        return null;
    }

    @Override
    public List<Music> findAll() throws SQLException {
        String sql = "SELECT * FROM music";
        List<Music> musics = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                musics.add(new Music(
                    rs.getInt("music_id"),
                    rs.getString("title"),
                    rs.getString("artist"),
                    rs.getString("album"),
                    rs.getString("youtube_link"),
                    rs.getString("spotify_link")
                ));
            }
        }
        return musics;
    }

    @Override
    public List<Music> findByKeyword(String keyword) throws SQLException {
        String sql = "SELECT * FROM music WHERE title LIKE ? OR artist LIKE ?";
        List<Music> musics = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            String like = "%" + keyword + "%";
            stmt.setString(1, like);
            stmt.setString(2, like);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    musics.add(new Music(
                        rs.getInt("music_id"),
                        rs.getString("title"),
                        rs.getString("artist"),
                        rs.getString("album"),
                        rs.getString("youtube_link"),
                        rs.getString("spotify_link")
                    ));
                }
            }
        }
        return musics;
    }
    
    @Override
    public List<Music> findByTitle(String title) throws SQLException {
        String sql = "SELECT * FROM music WHERE title LIKE ?";
        List<Music> musics = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + title + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                musics.add(new Music(
                    rs.getInt("music_id"),
                    rs.getString("title"),
                    rs.getString("artist"),
                    rs.getString("album"),
                    rs.getString("youtube_link"),
                    rs.getString("spotify_link")
                ));
            }
        }
        return musics;
    }
    
    @Override
    public List<Music> findByArtist(String artist) throws SQLException {
        List<Music> list = new ArrayList<>();
        String sql = "SELECT * FROM music WHERE LOWER(artist) LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + artist.toLowerCase() + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Music(
                    rs.getInt("music_id"),
                    rs.getString("title"),
                    rs.getString("artist"),
                    rs.getString("album"),
                    rs.getString("youtube_link"),
                    rs.getString("spotify_link")
                ));
            }
        }
        return list;
    }

    @Override
    public void save(Music music) throws SQLException {
        String sql = "INSERT INTO music (music_id, title, artist, album, youtube_link, spotify_link) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, music.getId());
            stmt.setString(2, music.getTitle());
            stmt.setString(3, music.getArtist());
            stmt.setString(4, music.getAlbum());
            stmt.setString(5, music.getYoutubeLink());
            stmt.setString(6, music.getSpotifyLink());
            stmt.executeUpdate();
        }
    }

    @Override
    public void update(Music music) throws SQLException {
        String sql = "UPDATE music SET title = ?, artist = ?, album = ?, youtube_link = ?, spotify_link = ? WHERE music_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, music.getTitle());
            stmt.setString(2, music.getArtist());
            stmt.setString(3, music.getAlbum());
            stmt.setString(4, music.getYoutubeLink());
            stmt.setString(5, music.getSpotifyLink());
            stmt.setInt(6, music.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM music WHERE music_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
