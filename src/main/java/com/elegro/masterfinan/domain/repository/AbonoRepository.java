package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.entity.cruds.AbonoDaoRepository;
import com.elegro.masterfinan.infraestructura.entity.Abono;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;

import java.sql.Connection;
import java.util.List;

public class AbonoRepository  implements AbonoDaoRepository {
    public AbonoRepository(Connection conn) {

    }

    @Override
    public List<Abono> findAll() throws DaoException {
        return null;
    }

    @Override
    public Abono findById(Integer id) throws DaoException {
        return null;
    }

    @Override
    public Abono findOne(Integer id) throws DaoException {
        return null;
    }

    @Override
    public Abono findFirst() throws DaoException {
        return null;
    }

    @Override
    public Abono findLast() throws DaoException {
        return null;
    }

}
