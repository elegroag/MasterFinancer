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
    private Models models;

    public List<Transaccion> renderLista(){
        try{
            return models.entityTransaccion().findAll();
        }catch (DaoException err ){
            return null;
        }
    }

    public Optional<List<Transaccion>> getByIngresoCategoria(Long idCategoria){
        try {
            return Optional.of(models.entityTransaccion().findByIngresoCategoria(idCategoria));
        }catch (DaoException  err){
            return Optional.empty();
        }
    }

    public Optional<Transaccion> crear(Transaccion transaccion){
        try {
            models.entityTransaccion().insert(transaccion);
            Integer id = models.entityTransaccion().getInsertId();
            transaccion.setId(id);
            return Optional.ofNullable(transaccion);
        }catch (DaoException err ){
            return Optional.empty();
        }
    }

    public boolean borrar(Integer transaccionId) {
        return buscar(transaccionId).map(transaccion -> {
            boolean flag = false;
            try {
                flag = models.entityTransaccion().delete(transaccion);
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }
            return flag;
        }).orElse(false);
    }

    public boolean actualiza(Transaccion transaccion, Integer id){
        return buscar(id).map(transa -> {
            try {
                transa.setEstado(transaccion.getEstado());
                transa.setValor(transaccion.getValor());
                transa.setFecha(transaccion.getFecha());
                transa.setHora(transaccion.getHora());
                return models.entityTransaccion().update(transa);
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }
        }).orElse(false);
    }

    public Optional<Transaccion> buscar(Integer transaccionId){
        try {
            return Optional.of(models.entityTransaccion().findById(transaccionId));
        }catch (DaoException err){
            return Optional.empty();
        }
    }

}
