// dao/MusicDao.java
package dao;

import model.Music;

import java.sql.SQLException;
import java.util.List;

public interface MusicDao {
    Music findById(int id) throws SQLException;
    List<Music> findAll() throws SQLException;
    List<Music> findByKeyword(String keyword) throws SQLException;
    List<Music> findByTitle(String title) throws SQLException;
    List<Music> findByArtist(String artist) throws SQLException;
    void save(Music music) throws SQLException;
    void update(Music music) throws SQLException;
    void delete(int id) throws SQLException;
}
