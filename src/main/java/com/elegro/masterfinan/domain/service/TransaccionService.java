package com.elegro.masterfinan.domain.service;

import com.elegro.masterfinan.domain.repository.Models;
import com.elegro.masterfinan.infraestructura.entity.Transaccion;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransaccionService {

    @Autowired
    Models models;

    public List<Transaccion> renderLista() throws DaoException {
        return this.models.entityTransaccion().findAll();
    }

    public void crea(){

    }

    public void borra(){

    }

    public void actualiza(){

    }

    public void busca(){

    }

}
