package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String DB = "coretemp";
    private static final String USER = "root";
    private static final String PASS = "admin";
    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB + "?useSSL=false";

    public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER_CLASS);
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
