package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    // Change DB credentials if needed
    private static final String URL = "jdbc:mysql://localhost:3306/notesdb";
    private static final String USER = "root";
    private static final String PASSWORD = "tisu11@mysql";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

