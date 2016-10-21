package com.intervale.services;

import com.intervale.dao.MoneyTransferDaoImpl;
import com.intervale.models.MoneyTransfer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MoneyTransferService {

    public List<MoneyTransfer> getAll() throws SQLException, IOException, ClassNotFoundException {
        MoneyTransferDaoImpl moneyTransferDao = new MoneyTransferDaoImpl();
        return moneyTransferDao.getAll();
    }

    public MoneyTransfer save(MoneyTransfer moneyTransfer) throws SQLException, IOException, ClassNotFoundException {
        MoneyTransferDaoImpl moneyTransferDao = new MoneyTransferDaoImpl();
        return moneyTransferDao.save(moneyTransfer);
    }
}
