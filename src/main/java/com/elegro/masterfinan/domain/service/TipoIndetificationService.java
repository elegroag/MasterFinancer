package com.elegro.masterfinan.domain.service;

import com.elegro.masterfinan.domain.repository.Models;
import com.elegro.masterfinan.infraestructura.entity.TipoIdentificacion;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoIndetificationService {

    @Autowired
    private Models models;

    public List<TipoIdentificacion> listaTipos(){
        try{
            return models.entityTipoIndetificacion().findAll();
        }catch (DaoException err){
            return null;
        }
    }
}
