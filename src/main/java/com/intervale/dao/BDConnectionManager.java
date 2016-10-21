package com.intervale.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.intervale.dataSources.DataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Singleton
public class BDConnectionManager {

     private DataSource dataSource;

     private Connection connection;

@Inject
    public BDConnectionManager() {
        this.dataSource = new DataSource();
    }

    public Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        if (connection == null || connection.isClosed()) {
            Class.forName(dataSource.getDriver());
            connection = DriverManager.getConnection(
                    dataSource.getUrl(),
                    dataSource.getUser(),
                    dataSource.getPassword());
        }
        return connection;
    }
}
