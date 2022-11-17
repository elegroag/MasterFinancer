package com.elegro.masterfinan.infraestructura.dao;

import com.elegro.masterfinan.infraestructura.excepetion.DaoException;

import java.util.List;

public interface DAOLong<T, Long> {

    List<T> findAll()throws DaoException;

    T findById(Long id)throws DaoException;

    T findOne(Long id)throws DaoException;

    T findFirst()throws DaoException;

    T findLast()throws DaoException;

    void insert(T use)throws DaoException;

    void update(T use)throws DaoException;

    void delete(T use)throws DaoException;

}
