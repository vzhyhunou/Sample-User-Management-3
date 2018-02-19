package com.epam.brest.cource;

import java.sql.*;

public class DBUtils {

    public Connection getConnection() throws ClassNotFoundException, SQLException {

        System.out.println("Connect to DB.");
        String databaseURL = "jdbc:h2:mem:test_db;MODE=MYSQL;DB_CLOSE_DELAY=-1";
        Class.forName("org.h2.Driver");
        Connection connection =
                DriverManager.getConnection(databaseURL,"sa", "");
        return connection;
    }

    public void createUserTable(Connection connection) throws SQLException {

        System.out.println("Create app_user table.");
        String createTable =
                "CREATE TABLE app_user (" +
                        "user_id INT NULL," +
                        "login VARCHAR(255) NOT NULL," +
                        "password VARCHAR(255) NOT NULL," +
                        "description VARCHAR(255) NULL," +
                        "PRIMARY KEY (user_id))";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTable);
        }
    }

    public void addUser(Connection connection, String login, String password, String description) throws SQLException {

        System.out.println(String.format("Add user: %s", login));

        String newUser = "INSERT INTO app_user (password, description) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(newUser);
        //preparedStatement.setString(1, login);
        preparedStatement.setString(1, password);
        preparedStatement.setString(2, description);
        preparedStatement.executeUpdate();

    }

    public void getUsers(Connection connection) throws SQLException {

        System.out.println("Get users: ");

        String getRecords = "SELECT user_id, login, description " +
                "FROM app_user ORDER BY user_id";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(getRecords);
        while (resultSet.next()) {
            System.out.println(String.format("User: %s, %s, %s",
                    resultSet.getInt("user_id"),
                    resultSet.getString("login"),
                    resultSet.getString("description")
                    ));
        }

    }

}
