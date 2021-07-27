package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

/**
 * Класс редактирования таблицы.
 */
public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String url = properties.getProperty("datasource.url");
        String login = properties.getProperty("datasource.username");
        String password = properties.getProperty("datasource.password");
        connection = DriverManager.getConnection(url, login, password);
    }

    private void executeSql(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Создает пустую таблицу без столбцов с указанным именем.
     *
     * @param tableName Имя таблицы.
     */
    public void createTable(String tableName) {
        executeSql(String.format("create table if not exists %s();", tableName));
    }

    /**
     * Удаляет таблицу по указанному имени.
     *
     * @param tableName Имя таблицы.
     */
    public void dropTable(String tableName) {
        executeSql(String.format("drop table if exists %s;", tableName));
    }

    /**
     * Добавляет столбец в таблицу.
     *
     * @param tableName  Имя таблицы.
     * @param columnName Имя столбца.
     * @param type       Тип столбца.
     */
    public void addColumn(String tableName, String columnName, String type) {
        executeSql(String.format("alter table if exists %s add %s %s;", tableName, columnName, type));
    }

    /**
     * Удаляет столбец из таблицы.
     *
     * @param tableName  Имя таблицы.
     * @param columnName Имя столбца.
     */
    public void dropColumn(String tableName, String columnName) {
        executeSql(String.format("alter table if exists %s drop %s;", tableName, columnName));
    }

    /**
     * Переименовывает стобец.
     *
     * @param tableName     Имя таблицы.
     * @param columnName    Имя столбца
     * @param newColumnName Новое имя столбца.
     */
    public void renameColumn(String tableName, String columnName, String newColumnName) {
        executeSql(String.format("alter table if exists %s rename %s to %s;", tableName, columnName, newColumnName));
    }

    public Connection getConnection() {
        return connection;
    }

    /**
     * Вывод схемы таблицы.
     *
     * @param connection Соединение.
     * @param tableName  Имя таблицы.
     * @return Текстовое представление таблицы.
     * @throws Exception Возвращаем ошибку.
     */
    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        Properties properties = new Properties();
        ClassLoader classLoader = ConnectionDemo.class.getClassLoader();
        try (InputStream in = classLoader.getResourceAsStream("app.properties")) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String tableName = "try_table";
        String[] column = new String[]{"id", "serial primary key"};
        String[] column1 = new String[]{"name", "varchar(255)"};
        String newNameColumn = "last_name";
        try (TableEditor tableEditor = new TableEditor(properties)) {
            tableEditor.createTable(tableName);
            System.out.println(getTableScheme(tableEditor.getConnection(), tableName));
            tableEditor.addColumn(tableName, column[0], column[1]);
            System.out.println(getTableScheme(tableEditor.getConnection(), tableName));
            tableEditor.addColumn(tableName, column1[0], column1[1]);
            System.out.println(getTableScheme(tableEditor.getConnection(), tableName));
            tableEditor.dropColumn(tableName, column[0]);
            System.out.println(getTableScheme(tableEditor.getConnection(), tableName));
            tableEditor.renameColumn(tableName, column1[0], newNameColumn);
            System.out.println(getTableScheme(tableEditor.getConnection(), tableName));
            tableEditor.dropColumn(tableName, newNameColumn);
            System.out.println(getTableScheme(tableEditor.getConnection(), tableName));
            tableEditor.dropTable(tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
