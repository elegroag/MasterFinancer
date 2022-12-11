package com.elegro.masterfinan.domain.service;

import com.elegro.masterfinan.domain.repository.Models;
import com.elegro.masterfinan.infraestructura.entity.Gasto;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GastoService {

    @Autowired
    private Models models;

    public List<Gasto> listaGastos(){
        try{
            return models.entityGasto().findAll();
        }catch (DaoException err){
            return null;
        }
    }

    public Optional<Gasto> crear(Gasto gasto){
        try{
            models.entityGasto().insert(gasto);
            Long id = models.entityGasto().getInsertId();
            gasto.setId(id);
            return Optional.of(gasto);
        }catch (DaoException err){
            return Optional.empty();
        }
    }

    public boolean borrar(Long gastoId){
        return buscar(gastoId).map(_gasto -> {
            try {
                models.entityGasto().delete(_gasto);
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }
            return true;
        }).orElse(false);
    }

    public boolean actualiza(Gasto gasto, Long id){
        return buscar(id).map(_gasto -> {
            try {
                _gasto.setMedioPago(gasto.getMedioPago());
                _gasto.setGastoCategoria(gasto.getGastoCategoria());
                return models.entityGasto().update(_gasto);
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }
        }).orElse(false);
    }

    public Optional<Gasto> buscar(Long gastoId){
        try {
            return Optional.of(models.entityGasto().findById(gastoId));
        }catch (DaoException err){
            return Optional.empty();
        }
    }
}
