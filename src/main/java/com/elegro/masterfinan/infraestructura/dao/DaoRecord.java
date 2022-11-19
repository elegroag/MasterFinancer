package com.elegro.masterfinan.infraestructura.dao;

import com.elegro.masterfinan.infraestructura.excepetion.DaoException;

public interface DaoRecord<T> {

    void insert(T use) throws DaoException;

    void update(T use) throws DaoException;

    void delete(T use) throws DaoException;

    void search(T use) throws DaoException;
}
