package com.intervale.dao;

import java.io.IOException;
import java.sql.SQLException;


public class JDBCWrapperImplManager {
    public static JDBCWrapperImpl getJDBCWrapper() throws SQLException, IOException, ClassNotFoundException {
        return new JDBCWrapperImpl();
    }
}
