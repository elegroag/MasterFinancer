package com.elegro.masterfinan.domain.service;

import com.elegro.masterfinan.domain.repository.Models;
import com.elegro.masterfinan.infraestructura.entity.Pago;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PagoService {

    @Autowired
    Models models;
    public Optional<Pago> crear(Pago pago){
        try{
            return Optional.ofNullable(models.entityPagos().insert(pago));
        }catch (DaoException er){
            return null;
        }
    }
}
