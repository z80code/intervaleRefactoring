package com.intervale.services;

import com.intervale.dao.CommissionDaoImpl;
import com.intervale.models.Commission;
import com.intervale.models.MoneyTransfer;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static java.math.BigDecimal.valueOf;

public class CalcService {

   public static MoneyTransfer calcCommissionForMoneyTransfer(MoneyTransfer moneyTransfer) throws SQLException, IOException, ClassNotFoundException {

       CommissionService commissionService = new CommissionService(new CommissionDaoImpl());

       List<Commission> commissions = commissionService.getByBrandAndCurrency(
               moneyTransfer.getClientCard().getBrand(),
               moneyTransfer.getCurrency());

       List<Commission> commission = commissions.stream()
               .sorted((x, y) -> Double.compare(x.getValue(), y.getValue()))
               .collect(Collectors.toList());

       if (commission.size() > 0) {

           Commission commission1 = commission.get(0);
           moneyTransfer.setCommission(new BigDecimal(commission1.getValue()));

           BigDecimal value = valueOf(commission1.getValue() / 100).multiply(moneyTransfer.getAmount());

           moneyTransfer.commissionOfAmount = value.doubleValue();

           moneyTransfer.resultOperation = moneyTransfer.commissionOfAmount + moneyTransfer.getAmount().doubleValue();
           moneyTransfer.isError = false;
       } else {
           moneyTransfer.status = "Данного вида операции не поддерживаются. Приносим извинения за неудобства.";
           moneyTransfer.isError = true;
       }
       return moneyTransfer;
   }


}
