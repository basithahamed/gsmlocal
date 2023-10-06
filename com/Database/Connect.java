package com.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

// import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class Connect {
    private static BasicDataSource connectionPool;
    static {
        connectionPool = new BasicDataSource();
        connectionPool.setUrl("jdbc:mysql://localhost:3306/gsm");
        connectionPool.setDriverClassName("com.mysql.cj.jdbc.Driver");
        connectionPool.setUsername("todoadmins");
        connectionPool.setPassword("todo@111");

        connectionPool.setMinIdle(5);
        connectionPool.setMaxIdle(10);
        // connectionPool.setMaxWaitMillis(5000);
    }

    public static Connection getConnection() {
        try {
            return connectionPool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();

            // String url = "jdbc:sqlite:path-to-db/chinook/chinook.db";
            try (Connection conn = DriverManager.getConnection("com.mysql.cj.jdbc.Driver","todoadmins","todo@111")) {

                System.out.println("Got it!");
                return conn;
            } catch (SQLException ex) {
                throw new Error("Problem", ex);
            }

        }

        // return null;
    }

}