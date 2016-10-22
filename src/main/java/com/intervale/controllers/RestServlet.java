package com.intervale.controllers;

import com.google.gson.Gson;
import com.intervale.dao.CommissionDaoImpl;
import com.intervale.models.Brand;
import com.intervale.models.Commission;
import com.intervale.models.MoneyTransfer;
import com.intervale.services.CalcService;
import com.intervale.services.CommissionService;
import com.intervale.services.MoneyTransferService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class RestServlet extends HttpServlet {

    private static Logger logger = LogManager.getLogger(RestServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            MoneyTransfer moneyTransfer = getObject(req);

            if (moneyTransfer == null) {
                throw new IllegalArgumentException("Bad request.");
            }

            String deleter[] = req.getRequestURI().split(req.getServletPath());
            String method = (deleter.length > 1) ? deleter[1] : "";

            resp.setContentType("application/json; charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
            switch (method) {
                case "/commission": {
                    resp.getWriter().println(new Gson().toJson(calc(moneyTransfer)));
                    return;
                }
                case "/put": {
                    resp.getWriter().println(new Gson().toJson(putCommission(moneyTransfer)));
                    return;
                }
                case "/commissions": {
                    resp.getWriter().println(new Gson().toJson(commissionsGetAll()));
                    return;
                }
                case "/transfers": {
                    resp.getWriter().println(new Gson().toJson(moneyTransferGetAll()));
                    return;
                }
                default: {
                    throw new IllegalArgumentException("Bad request.");
                }
            }
        } catch (Exception e) {
            logger.info("Error convert data.", e);
            resp.setContentType("text/html; charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("BAD REQUEST");
        }
    }

    private MoneyTransfer getObject(HttpServletRequest req) throws IOException {
        String objJson = null;
        if ("POST".equalsIgnoreCase(req.getMethod())) {
            objJson = req.getReader().lines()
                    .collect(Collectors
                            .joining(System.lineSeparator()));
        }
        MoneyTransfer moneyTransfer = new Gson().fromJson(objJson, MoneyTransfer.class);
        moneyTransfer.getClientCard().setBrand(
                Brand.getBrandByCardNumber(moneyTransfer.getClientCard().getNumber()));
        moneyTransfer.getSubClientCard().setBrand(
                Brand.getBrandByCardNumber(moneyTransfer.getSubClientCard().getNumber()));

//            logger.info("Error received data.");
        return moneyTransfer;
    }

    /**
     * Метод заполняет объект расчитанными данными из класса CalcService.
     *
     * @param moneyTransfer Объект, для которого расчитывается комиссия.
     * @return Возвращает объект с расчитанными данными.
     * Все ошидки логируются.
     * Логируются также полученные и отправленные данные.
     */
//    @Request("commission")
    private MoneyTransfer calc(MoneyTransfer moneyTransfer) throws SQLException, IOException, ClassNotFoundException {
        moneyTransfer.status = null;
        logger.info("Received: " + new Gson().toJson(moneyTransfer));
        moneyTransfer = CalcService.calcCommissionForMoneyTransfer(moneyTransfer);
        logger.info("Send: " + new Gson().toJson(moneyTransfer));
        return moneyTransfer;
    }

    /**
     * Метод добавляет операцию в базу.
     *
     * @param moneyTransfer Объект, который записывается в базу.
     *                      TODO добавить проверку корректности данных
     *                      т.к. проверка не производится или должна производится на странице.
     * @return Значение результата операции: Success = успешная запись в базу.
     * False = ошидка при записи.
     */
//    @PutMapping("put")
    private String putCommission(/*@RequestBody */MoneyTransfer moneyTransfer) throws SQLException, IOException, ClassNotFoundException {

        logger.info("Received: " + new Gson().toJson(moneyTransfer));

        MoneyTransferService moneyTransferService = new MoneyTransferService();
        MoneyTransfer moneyTransfer1 = null;

        moneyTransfer1 = moneyTransferService.save(moneyTransfer);

        if (moneyTransfer1 != null || moneyTransfer1.getTransferId() != 0) {
            logger.info("Saved: " + new Gson().toJson(moneyTransfer1));
            return "Success";
        } else return "False";
    }

    /**
     * Возвращает Все комиссии из базы.
     *
     * @return
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    //   @PostMapping("commissions")
    private List<Commission> commissionsGetAll() throws SQLException, IOException, ClassNotFoundException {
        CommissionService commissionService = new CommissionService(new CommissionDaoImpl());
        return commissionService.getAll();
    }

    /**
     * Возвращает Все операции по переводу из базы.
     *
     * @return
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    //   @PostMapping("transfers")
    private List<MoneyTransfer> moneyTransferGetAll() throws SQLException, IOException, ClassNotFoundException {
        MoneyTransferService moneyTransferService = new MoneyTransferService();
        return moneyTransferService.getAll();
    }

}