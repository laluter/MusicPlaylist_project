package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 오라클 접속 URL
        String user = "music";
        String password = "4567";
        return DriverManager.getConnection(url, user, password);
    }
}
