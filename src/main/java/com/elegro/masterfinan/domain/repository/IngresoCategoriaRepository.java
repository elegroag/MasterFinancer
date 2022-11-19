package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.IngresoCategoriaDaoRepository;
import com.elegro.masterfinan.infraestructura.entity.IngresoCategoria;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;

import java.sql.Connection;
import java.util.List;

public class IngresoCategoriaRepository implements IngresoCategoriaDaoRepository {
    public IngresoCategoriaRepository(Connection conn) {

    }

    @Override
    public List<IngresoCategoria> findAll() throws DaoException {
        return null;
    }

    @Override
    public IngresoCategoria findById(Long id) throws DaoException {
        return null;
    }

    @Override
    public IngresoCategoria findOne(Long id) throws DaoException {
        return null;
    }

    @Override
    public IngresoCategoria findFirst() throws DaoException {
        return null;
    }

    @Override
    public IngresoCategoria findLast() throws DaoException {
        return null;
    }

}
