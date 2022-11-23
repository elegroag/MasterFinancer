package com.elegro.masterfinan.domain.service;

import com.elegro.masterfinan.domain.repository.Models;
import com.elegro.masterfinan.infraestructura.entity.Compra;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

    @Autowired
    Models models;

    public List<Compra> listaCompras(){
        try{
            return models.entityCompra().findAll();
        }catch (DaoException err ){
            return null;
        }
    }

    public Optional<Compra> crear(Compra compra){
        try{
            return Optional.ofNullable(models.entityCompra().insert(compra));
        }catch (DaoException err ){
            return null;
        }
    }

    public boolean borrar(Integer compraId) {
        return buscar(compraId).map(_compra -> {
            try {
                models.entityCompra().delete(_compra);
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }
            return true;
        }).orElse(false);
    }

    public boolean actualiza(Compra compra, Integer id){
        return buscar(id).map(_compra -> {
            try {
                _compra.setEstadoCredito(compra.getEstadoCredito());
                _compra.setPersona(compra.getPersona());
                _compra.setSaldoPendiente(compra.getSaldoPendiente());
                _compra.setFechaInicial(compra.getFechaInicial());
                _compra.setFechaFinal(compra.getFechaFinal());
                _compra.setTransacciones(compra.getTransacciones());
                _compra.setValorCompra(compra.getValorCompra());
                return models.entityCompra().update(_compra);
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }
        }).orElse(false);
    }

    public Optional<Compra> buscar(Integer compraId){
        try {
            return Optional.ofNullable(models.entityCompra().findById(compraId));
        }catch (DaoException err){
            return null;
        }
    }


}
