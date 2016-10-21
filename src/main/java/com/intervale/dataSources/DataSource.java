package com.intervale.dataSources;

import java.util.ResourceBundle;

public class DataSource {

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getDriver() {
        return driver;
    }

    private String url;
    private String user;
    private String password;
    private String driver;

    public DataSource() {
        ResourceBundle bundle = ResourceBundle.getBundle("db");
        this.url = bundle.getString("db.url");
        this.user = bundle.getString("db.user");
        this.password = bundle.getString("db.password");
        this.driver = bundle.getString("db.driver");
    }

}
