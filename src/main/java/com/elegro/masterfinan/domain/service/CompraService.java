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

    public List<Compra> renderLista(){
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


}
