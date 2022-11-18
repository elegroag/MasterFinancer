package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.CompraDaoRepository;
import com.elegro.masterfinan.infraestructura.entity.Compra;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;

import java.sql.Connection;
import java.util.List;

public class CompraRepository implements CompraDaoRepository {

    protected Connection connectionTransactional;

    public CompraRepository(Connection conn){
        this.connectionTransactional = conn;
    }

    @Override
    public List<Compra> findAll() throws DaoException {
        return null;
    }

    @Override
    public Compra findById(Integer id) throws DaoException {
        return null;
    }

    @Override
    public Compra findOne(Integer id) throws DaoException {
        return null;
    }

    @Override
    public Compra findFirst() throws DaoException {
        return null;
    }

    @Override
    public Compra findLast() throws DaoException {
        return null;
    }

    @Override
    public void insert(Compra use) throws DaoException {

    }

    @Override
    public void update(Compra use) throws DaoException {

    }

    @Override
    public void delete(Compra use) throws DaoException {

    }
}
