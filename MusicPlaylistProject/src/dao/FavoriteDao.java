package dao;

import java.sql.SQLException;
import java.util.List;

public interface FavoriteDao {
    void addFavorite(String email, int musicId) throws SQLException;
    void removeFavorite(String email, int delId) throws SQLException;
    List<Integer> getFavorites(String email) throws SQLException;
}
