package com.intervale.services;

import com.google.inject.Inject;
import com.intervale.dao.JDBCWrapperImpl;
import com.intervale.models.Commission;
import com.intervale.models.modelsXML.Commissions;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class InitBaseService implements InterfaceInitBaseService {

    private JDBCWrapperImpl implDB;

    @Inject
    public InitBaseService(JDBCWrapperImpl implDB) {
        this.implDB = implDB;
    }


    public void start(String fileName) throws ClassNotFoundException, SQLException, JAXBException, IOException {

        Commissions commissions = readCommissions(fileName);
        if (!implDB.isExist("commissions".toUpperCase())) {
            implDB.preparedStatement("CREATE TABLE `commissions` (" +
                    "`commissionId` INT(11) NOT NULL, " +
                    "`brand` VARCHAR(50) NOT NULL, " +
                    "`currency` VARCHAR(10) NOT NULL," +
                    "`value` DOUBLE(10) NOT NULL" +
                    ");"
            );
            for (Commission commission : commissions.getCommissions()) {
                implDB.preparedStatementUpdate("INSERT INTO `commissions` " +
                                "(`commissionId`, `brand`, `currency`, `value`) " +
                                "VALUES (?,?,?,?);",
                        commission.getId(),
                        commission.getBrand(),
                        commission.getCurrency(),
                        commission.getValue()
                );
            }
        }
        if (!implDB.isExist("transfers".toUpperCase())) {
            implDB.preparedStatement("CREATE TABLE `transfers` (" +
                    "`transferId` INT(11) NOT NULL AUTO_INCREMENT, " +
                    "`transfer_from_card` VARCHAR(255) NOT NULL, " +
                    "`transfer_to_card` VARCHAR(255) NOT NULL," +
                    "`transfer_datetime` LONG NOT NULL," +
                    "`transfer_currency` VARCHAR(10) NOT NULL," +
                    "`transfer_amount` DECIMAL NOT NULL," +
                    "`transfer_commission` DECIMAL NOT NULL," +
                    ");"
            );
        }
    }

    private void saveCommissions(Commissions commissions) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Commissions.class, Commission.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.marshal(commissions, new File("commissions.xml"));
    }

    private static Commissions readCommissions(String fileName) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Commissions.class, Commission.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (Commissions) unmarshaller.unmarshal(new File(fileName));
    }

}
