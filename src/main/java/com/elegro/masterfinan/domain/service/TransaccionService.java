package com.elegro.masterfinan.domain.service;

import com.elegro.masterfinan.domain.repository.Models;
import com.elegro.masterfinan.infraestructura.entity.Transaccion;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransaccionService {

    @Autowired
    Models models;

    public List<Transaccion> renderLista() throws DaoException {
        return models.entityTransaccion().findAll();
    }

    public Optional<Transaccion> getTransaccion(Integer id) throws DaoException {
        return Optional.ofNullable(models.entityTransaccion().findById(id));
    }

    public Optional<List<Transaccion>> getByIngresoCategoria(Long idCategoria) throws DaoException {
        return Optional.ofNullable(models.entityTransaccion().findByIngresoCategoria(idCategoria));
    }

    public Optional<Transaccion> crea(Transaccion transaccion) throws DaoException {
        return Optional.ofNullable(models.entityTransaccion().insert(transaccion));
    }

    public void borra(){
    }

    public void actualiza(){
    }

    public void busca(){
    }

}
