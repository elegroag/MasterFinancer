package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.entity.cruds.GastoDaoRepository;
import com.elegro.masterfinan.infraestructura.entity.Gasto;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;

import java.sql.Connection;
import java.util.List;

public class GastoRepository  implements GastoDaoRepository {
    public GastoRepository(Connection conn) {

    }

    @Override
    public List<Gasto> findAll() throws DaoException {
        return null;
    }

    @Override
    public Gasto findById(Long id) throws DaoException {
        return null;
    }

    @Override
    public Gasto findOne(Long id) throws DaoException {
        return null;
    }

    @Override
    public Gasto findFirst() throws DaoException {
        return null;
    }

    @Override
    public Gasto findLast() throws DaoException {
        return null;
    }

}
