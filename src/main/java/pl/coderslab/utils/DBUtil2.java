package pl.coderslab.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil2 {
    public static Connection connect() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/scrumlab?useSSL=False&characterSet=utf8mb4&allowPublicKeyRetrieval=true","root","coderslab");
        return connection;
    }
}
