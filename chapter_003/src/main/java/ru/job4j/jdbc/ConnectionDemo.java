package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Проверка соединения с базой данных. Настройки через файл app.properties
 */
public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        ClassLoader classLoader = ConnectionDemo.class.getClassLoader();
        try (InputStream in = classLoader.getResourceAsStream("app.properties")) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Class.forName("org.postgresql.Driver");
        String url = properties.getProperty("datasource.url");
        String login = properties.getProperty("datasource.username");
        String password = properties.getProperty("datasource.password");
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
