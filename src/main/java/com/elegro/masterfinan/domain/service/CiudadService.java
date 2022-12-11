package com.elegro.masterfinan.domain.service;
import com.elegro.masterfinan.domain.repository.Models;
import com.elegro.masterfinan.infraestructura.entity.Ciudad;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CiudadService {

    @Autowired
    private Models models;

    public List<Ciudad> listarCiudades(){
        try{
            return models.entityCiudad().findAll();
        }catch (DaoException err){
            return null;
        }
    }
}
