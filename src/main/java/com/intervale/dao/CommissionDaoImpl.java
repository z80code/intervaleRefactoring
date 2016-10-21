package com.intervale.dao;

import com.intervale.dao.Core.InterfaceEntityDao;
import com.intervale.models.Brand;
import com.intervale.models.Commission;
import com.intervale.models.Currency;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommissionDaoImpl implements InterfaceEntityDao<Commission> {

    private final static String TABLE_NAME = "commissions";
    private final static String SELECT_ALL_QUERY =
            "select * from " + TABLE_NAME;

    private JDBCWrapperImpl jdbcWrapper;

    public CommissionDaoImpl() throws SQLException, IOException, ClassNotFoundException {
        this.jdbcWrapper = JDBCWrapperImplManager.getJDBCWrapper();
    }

    @Override
    public Commission save(Commission entity) throws SQLException, IOException, ClassNotFoundException {

        return entity;
    }

    @Override
    public void remove(Commission entity) {

    }

    @Override
    public Commission update(Commission entity) {

        return entity;
    }

    @Override
    public Commission findById(int id) {

        return null;
    }

    @Override
    public List<Commission> getAll() throws SQLException, IOException, ClassNotFoundException {
        List<Commission> list = new ArrayList<>();
        ResultSet resultSet = jdbcWrapper.preparedStatementQuery(SELECT_ALL_QUERY);
        while (resultSet.next()) {
            list.add(getFromResult(resultSet));
        }
        return list;
    }

    private Commission getFromResult(ResultSet result) throws SQLException {
        return new Commission(
                result.getInt("commissionId"),
                Brand.valueOf(result.getString("brand")),
                Currency.valueOf(Currency.class, result.getString("currency")),
                result.getDouble("value")
        );
    }
}
