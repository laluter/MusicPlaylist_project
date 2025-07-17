package dao;

import model.User;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    User findByEmail(String email) throws SQLException;
    List<User> findAll() throws SQLException;
    void save(User user) throws SQLException;
    void update(User user) throws SQLException;
    void delete(String email) throws SQLException;
}
