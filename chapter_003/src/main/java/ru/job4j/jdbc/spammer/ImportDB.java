package ru.job4j.jdbc.spammer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class ImportDB {
    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users;
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            users = rd.lines().map(s -> {
                String[] split = s.split(";");
                return new User(split[0], split[1]);
            }).collect(Collectors.toList());
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            createTable(cnt);
            for (User user : users) {
                try (PreparedStatement ps =
                             cnt.prepareStatement("insert into users (name, email) values (?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private void createTable(Connection cnt) throws SQLException {
        try (Statement st = cnt.createStatement()) {
            st.execute(String.format(
                    "create table if not exists %s (%s, %s, %s);",
                    "users",
                    "id serial primary key",
                    "name varchar(255)",
                    "email varchar(255)"
            ));
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("app-spammer.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./files/spammer/dump.txt");
        db.save(db.load());
    }
}
