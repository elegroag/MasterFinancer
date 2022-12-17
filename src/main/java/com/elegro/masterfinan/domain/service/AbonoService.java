package com.elegro.masterfinan.domain.service;

import com.elegro.masterfinan.domain.repository.Models;
import com.elegro.masterfinan.infraestructura.entity.Abono;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbonoService {

    @Autowired
    private Models models;

    public List<Abono> listaAbonos(){
        try{
            return models.entityAbonos().findAll();
        }catch (DaoException err){
            return null;
        }
    }

    public Optional<Abono> crear(Abono abono){
        try{
            models.entityAbonos().insert(abono);
            Long id = models.entityAbonos().getInsertId();
            abono.setId(id);
            return Optional.of(abono);
        }catch (DaoException err){
            return Optional.empty();
        }
    }

    public boolean borrar(Long abonoId){
        return buscar(abonoId).map(_abono -> {
            try {
                models.entityAbonos().delete(_abono);
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }
            return true;
        }).orElse(false);
    }
    
    public Optional<Abono> buscar(Long abonoId){
        try {
            return Optional.ofNullable(models.entityAbonos().findById(abonoId));
        }catch (DaoException err){
            return Optional.empty();
        }
    }

    public boolean actualiza(Abono abono, Long id){
        return buscar(id).map(_abono -> {
            try {
                _abono.setPersona(abono.getPersona());
                _abono.setMedioPago(abono.getMedioPago());
                _abono.setCategoriaIngreso(abono.getCategoriaIngreso());
                return models.entityAbonos().update(_abono);
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }
        }).orElse(false);
    }
}
