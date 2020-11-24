
package com.base;

import org.testng.annotations.AfterSuite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDB {

    public static Connection connection = null;
    public static Statement statement = null;

   /* static {
        try {
            connection = getConnection("root", "root1234", "batch4");
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }*/

    public static Connection getConnection(String username, String password, String databaseName) throws SQLException {
        String url = Utilities.getPropertyFromConfig("dbUrl") + databaseName + "?serverTimezone=UTC";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    @AfterSuite
    public static void cleanUpDatabase(Statement statement, Connection connection) {
        try {
            statement.close();
            connection.close();
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }
}