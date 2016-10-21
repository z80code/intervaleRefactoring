package com.intervale.dao;

import com.google.inject.Inject;
import com.intervale.dao.Core.InterfaceJDBCWrapper;

import java.io.IOException;
import java.sql.*;

public class JDBCWrapperImpl implements InterfaceJDBCWrapper {

    private final Connection connection;

    @Inject
    public JDBCWrapperImpl() throws SQLException, IOException, ClassNotFoundException {

        this.connection =new BDConnectionManager().getConnection();
    }

    @Override
    public boolean isExist(String tableName) throws SQLException, IOException, ClassNotFoundException {
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet tables = dbm.getTables(null, null, tableName, null);
        return tables.next();
    }

    @Override
    public int preparedStatementUpdate(String sqlString, Object... parameters) throws SQLException, IOException, ClassNotFoundException {
        PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
        setParameters(preparedStatement, parameters);
        return preparedStatement.executeUpdate();
    }

    @Override
    public ResultSet preparedStatementQuery(String sqlString, Object... parameters) throws SQLException, IOException, ClassNotFoundException {
        PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
        setParameters(preparedStatement, parameters);
        return preparedStatement.executeQuery();
    }

    @Override
    public boolean preparedStatement(String sqlString, Object... parameters) throws SQLException, IOException, ClassNotFoundException {
        PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
        setParameters(preparedStatement, parameters);
        return preparedStatement.execute();
    }

    private PreparedStatement setParameters(PreparedStatement preparedStatement, Object... parameters) throws SQLException {

        int indexer = 1; // нужна для автоматизации перечисления параметров
        for (Object param : parameters) {
            preparedStatement.setString(indexer++, param.toString());
        }
        return preparedStatement;
    }
}
