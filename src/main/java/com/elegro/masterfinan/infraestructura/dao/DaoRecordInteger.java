package com.elegro.masterfinan.infraestructura.dao;

import com.elegro.masterfinan.infraestructura.excepetion.DaoException;

import java.util.List;

public interface DaoRecordInteger<T, Integer> {

    List<T> findAll() throws DaoException;

    T findById(Integer id) throws DaoException;

    T findOne(Integer id) throws DaoException;

    T findFirst() throws DaoException;

    T findLast() throws DaoException;

    List<T> findSql(String sql) throws DaoException;

    Integer getInsertId();
}
