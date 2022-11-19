package com.elegro.masterfinan.infraestructura.dao;

import com.elegro.masterfinan.infraestructura.excepetion.DaoException;

public interface DaoRecord<T> {

    T insert(T use) throws DaoException;

    boolean update(T use) throws DaoException;

    boolean delete(T use) throws DaoException;

    T search(T use) throws DaoException;
}