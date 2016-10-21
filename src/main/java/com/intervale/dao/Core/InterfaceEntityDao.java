package com.intervale.dao.Core;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface InterfaceEntityDao<T> {
    T save(T entity) throws SQLException, IOException, ClassNotFoundException;

    void remove(T entity);

    T update(T entity);

    T findById(int id);

    List<T> getAll() throws SQLException, IOException, ClassNotFoundException;
}
