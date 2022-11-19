package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.entity.cruds.CompraDaoRepository;
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
    public Compra findById(Long id) throws DaoException {
        return null;
    }

    @Override
    public Compra findOne(Long id) throws DaoException {
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

}
