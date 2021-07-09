package ru.job4j.jdbc;

import java.sql.Connection;
import java.util.Properties;
import java.util.StringJoiner;

/**
 * Класс редактирования таблицы.
 */
public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        connection = null;
    }

    /**
     * Создает пустую таблицу без столбцов с указанным именем.
     *
     * @param tableName Имя таблицы.
     */
    public void createTable(String tableName) {
    }

    /**
     * Удаляет таблицу по указанному имени.
     *
     * @param tableName Имя таблицы.
     */
    public void dropTable(String tableName) {
    }

    /**
     * Добавляет столбец в таблицу.
     *
     * @param tableName  Имя таблицы.
     * @param columnName Имя столбца.
     * @param type       Тип столбца.
     */
    public void addColumn(String tableName, String columnName, String type) {
    }

    /**
     * Удаляет столбец из таблицы.
     *
     * @param tableName  Имя таблицы.
     * @param columnName Имя столбца.
     */
    public void dropColumn(String tableName, String columnName) {
    }

    /**
     * Переименовывает стобец.
     *
     * @param tableName     Имя таблицы.
     * @param columnName    Имя столбца
     * @param newColumnName Новое имя столбца.
     */
    public void renameColumn(String tableName, String columnName, String newColumnName) {
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
}
