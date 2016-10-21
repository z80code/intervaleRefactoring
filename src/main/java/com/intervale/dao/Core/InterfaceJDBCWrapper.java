package com.intervale.dao.Core;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface InterfaceJDBCWrapper {
    boolean isExist(String tableName) throws SQLException, IOException, ClassNotFoundException;

    int preparedStatementUpdate(String sqlString, Object... parameters) throws SQLException, IOException, ClassNotFoundException;

    ResultSet preparedStatementQuery(String sqlString, Object... parameters) throws SQLException, IOException, ClassNotFoundException;

    boolean preparedStatement(String sqlString, Object... parameters) throws SQLException, IOException, ClassNotFoundException;

}
