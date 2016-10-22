package com.intervale.dao;

import com.google.gson.Gson;
import com.intervale.dao.Core.InterfaceEntityDao;
import com.intervale.models.Card;
import com.intervale.models.Currency;
import com.intervale.models.MoneyTransfer;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MoneyTransferDaoImpl implements InterfaceEntityDao<MoneyTransfer> {

    private final static String TABLE_NAME = "TRANSFERS";

    private final static String INSERT_QUERY =
            "insert into " + TABLE_NAME + " (" +
                    "`transfer_from_card`, `transfer_to_card`, " +
                    "`transfer_datetime`, `transfer_currency`, " +
                    "`transfer_amount`, `transfer_commission`" + ") " +
                    "values (?,?,?,?,?,?)";

    private final static String SELECT_ALL_QUERY =
            "select * from " + TABLE_NAME;


    private JDBCWrapperImpl jdbcWrapper;

    public MoneyTransferDaoImpl() throws SQLException, IOException, ClassNotFoundException {
        jdbcWrapper = JDBCWrapperImplManager.getJDBCWrapper();
    }

    @Override
    public MoneyTransfer save(MoneyTransfer entity) throws SQLException, IOException, ClassNotFoundException {
        entity.setDateTime(Calendar.getInstance().getTimeInMillis());
        jdbcWrapper.preparedStatementUpdate(INSERT_QUERY,
                new Gson().toJson(entity.getClientCard()),
                new Gson().toJson(entity.getSubClientCard()),
                entity.getDateTime(),
                entity.getCurrency(),
                entity.getAmount(),
                entity.getCommission()
        );
        List<MoneyTransfer> moneyTransfers = getAll();

        for (MoneyTransfer moneyTransfer: moneyTransfers) {
            if (moneyTransfer.equals(entity)) {
                return moneyTransfer;
            }
        }
        return null;
    }

    @Override
    public void remove(MoneyTransfer entity) {

    }

    @Override
    public MoneyTransfer update(MoneyTransfer entity) {

        return entity;
    }

    @Override
    public MoneyTransfer findById(int id) {

        return null;
    }

    @Override
    public List<MoneyTransfer> getAll() throws SQLException, IOException, ClassNotFoundException {
        ResultSet resultSet = jdbcWrapper.preparedStatementQuery(SELECT_ALL_QUERY);
        List<MoneyTransfer> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(getFromResult(resultSet));
        }
        return list;
    }

    private MoneyTransfer getFromResult(ResultSet result) throws SQLException {
        Gson gson = new Gson();
        SimpleDateFormat format = new SimpleDateFormat();
        return new MoneyTransfer(
                result.getInt("transferId"),
                gson.fromJson(result.getString("transfer_from_card"), Card.class),
                gson.fromJson(result.getString("transfer_to_card"), Card.class),
                result.getLong("transfer_datetime"),
                Enum.valueOf(Currency.class, result.getString("transfer_currency")),
                result.getBigDecimal("transfer_amount"),
                result.getBigDecimal("transfer_commission")
        );
    }
}
