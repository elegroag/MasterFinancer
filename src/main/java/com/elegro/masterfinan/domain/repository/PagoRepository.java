package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.PagoDaoRepository;
import com.elegro.masterfinan.infraestructura.entity.Pago;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;

import java.sql.Connection;
import java.util.List;

public class PagoRepository implements PagoDaoRepository {
    public PagoRepository(Connection conn) {

    }

    @Override
    public List<Pago> findAll() throws DaoException {
        return null;
    }

    @Override
    public Pago findById(Long id) throws DaoException {
        return null;
    }

    @Override
    public Pago findOne(Long id) throws DaoException {
        return null;
    }

    @Override
    public Pago findFirst() throws DaoException {
        return null;
    }

    @Override
    public Pago findLast() throws DaoException {
        return null;
    }
}
