package com.elegro.masterfinan.domain.service;

import com.elegro.masterfinan.domain.repository.Models;
import com.elegro.masterfinan.infraestructura.entity.CompraDetalle;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraDetalleService {

    @Autowired
    private Models models;

    public List<CompraDetalle> listaDetalles(){
        try{
            return models.entityCompraDetalle().findAll();
        }catch (DaoException err ){
            return null;
        }
    }

    public Optional<CompraDetalle> crear(CompraDetalle compraDetalle){
        try{
            models.entityCompraDetalle().insert(compraDetalle);
            Long id = models.entityCompraDetalle().getInsertId();
            compraDetalle.setId(id);
            return Optional.of(compraDetalle);
        }catch (DaoException er){
            return Optional.empty();
        }
    }

    public boolean borrar(Long compraDetalleId){
        return buscar(compraDetalleId).map(compraDetalle -> {
            try {
                models.entityCompraDetalle().delete(compraDetalle);
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }
            return true;
        }).orElse(false);
    }

    public boolean actualiza(CompraDetalle compraDetalle, Long id){
        return buscar(id).map(_compraDetalle -> {
            try {
                _compraDetalle.setCompra(compraDetalle.getCompra());
                _compraDetalle.setPrecioDetalle(compraDetalle.getPrecioDetalle());
                _compraDetalle.setProducto(compraDetalle.getProducto());
                _compraDetalle.setCantidad(compraDetalle.getCantidad());
                return models.entityCompraDetalle().update(_compraDetalle);
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }
        }).orElse(false);
    }

    public Optional<CompraDetalle> buscar(Long compraDetalleId){
        try{
            return Optional.of(models.entityCompraDetalle().findById(compraDetalleId));
        }catch (DaoException err){
            return Optional.empty();
        }
    }
}
