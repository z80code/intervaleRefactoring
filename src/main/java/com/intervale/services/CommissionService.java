package com.intervale.services;

import com.intervale.dao.CommissionDaoImpl;
import com.intervale.models.Brand;
import com.intervale.models.Commission;
import com.intervale.models.Currency;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommissionService {

    private CommissionDaoImpl commissionDao;

    public CommissionService(CommissionDaoImpl commissionDao) {
        this.commissionDao = commissionDao;
    }

    public List<Commission> getAll() throws SQLException, IOException, ClassNotFoundException {
        return commissionDao.getAll();
    }

    public List<Commission> getByBrandAndCurrency(Brand brand, Currency currency) throws SQLException, IOException, ClassNotFoundException {
        List<Commission> commissions =
                Arrays
                        .stream(getAll().toArray())
                        .map(x -> (Commission) x)
                        .filter(x -> (x.getBrand().equals(brand) && x.getCurrency().equals(currency)))
                        .collect(Collectors.toList());
        return commissions;
    }

}
