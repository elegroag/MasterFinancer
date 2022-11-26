package com.elegro.masterfinan.domain.service;

import com.elegro.masterfinan.domain.repository.Models;
import com.elegro.masterfinan.infraestructura.entity.Pago;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoService {

    @Autowired
    Models models;

    public List<Pago> listaPagos(){
        try{
            return models.entityPagos().findAll();
        }catch (DaoException err ){
            return null;
        }
    }

    public Optional<Pago> crear(Pago pago){
        try{
            models.entityPagos().insert(pago);
            Long id = models.entityPagos().getInsertId();
            pago.setId(id);
            return Optional.ofNullable(pago);
        }catch (DaoException er){
            return Optional.empty();
        }
    }

    public boolean borrar(Long pagoId){
        return buscar(pagoId).map(pago -> {
            try {
                models.entityPagos().delete(pago);
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }
            return true;
        }).orElse(false);
    }

    public boolean actualiza(Pago pago, Long id){
        return buscar(id).map(_pago -> {
            try {
                _pago.setTransaccion(pago.getTransaccion());
                _pago.setCompra(pago.getCompra());
                _pago.setMedioPago(pago.getMedioPago());
                return models.entityPagos().update(_pago);
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }
        }).orElse(false);
    }

    public Optional<Pago> buscar(Long pagoId){
        try{
            return Optional.ofNullable(models.entityPagos().findById(pagoId));
        }catch (DaoException err){
            return Optional.empty();
        }
    }

}
