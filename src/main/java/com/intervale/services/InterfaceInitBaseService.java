package com.intervale.services;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.sql.SQLException;

public interface InterfaceInitBaseService {
    void start(String fileName) throws ClassNotFoundException, SQLException, JAXBException, IOException;
}
