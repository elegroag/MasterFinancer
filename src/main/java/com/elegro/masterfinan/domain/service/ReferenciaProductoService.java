package com.elegro.masterfinan.domain.service;

import com.elegro.masterfinan.domain.repository.Models;
import com.elegro.masterfinan.infraestructura.entity.ReferenciaProducto;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReferenciaProductoService {

    @Autowired
    Models models;

    public List<ReferenciaProducto> listaReferencias(){
        try{
            return models.entityReferenciaProducto().findAll();
        }catch (DaoException err ){
            return null;
        }
    }

    public Optional<ReferenciaProducto> crear(ReferenciaProducto referenciaProducto){
        try{
            models.entityReferenciaProducto().insert(referenciaProducto);
            Long id = models.entityReferenciaProducto().getInsertId();
            referenciaProducto.setId(id);
            return Optional.ofNullable(referenciaProducto);
        }catch (DaoException er){
            return Optional.empty();
        }
    }

    public boolean borrar(Long referenciaProductoId){
        return buscar(referenciaProductoId).map(referenciaProducto -> {
            try {
                models.entityReferenciaProducto().delete(referenciaProducto);
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }
            return true;
        }).orElse(false);
    }

    public boolean actualiza(ReferenciaProducto referenciaProducto, Long id){
        return buscar(id).map(_referenciaProducto -> {
            try {
                _referenciaProducto.setDetalle(referenciaProducto.getDetalle());
                _referenciaProducto.setPersona(referenciaProducto.getPersona());
                return models.entityReferenciaProducto().update(_referenciaProducto);
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }
        }).orElse(false);
    }

    public Optional<ReferenciaProducto> buscar(Long referenciaProductoId){
        try{
            return Optional.ofNullable(models.entityReferenciaProducto().findById(referenciaProductoId));
        }catch (DaoException err){
            return Optional.empty();
        }
    }

}
