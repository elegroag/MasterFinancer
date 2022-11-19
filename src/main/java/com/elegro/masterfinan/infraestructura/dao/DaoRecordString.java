package com.elegro.masterfinan.infraestructura.dao;

import com.elegro.masterfinan.infraestructura.excepetion.DaoException;

import java.util.List;

public interface DaoRecordString<T, String>{

    List<T> findAll() throws DaoException;

    T findById(String id) throws DaoException;

    T findOne(String id)throws DaoException;

    T findFirst()throws DaoException;

    T findLast()throws DaoException;
}
