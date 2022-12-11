package com.elegro.masterfinan.domain.service;
import com.elegro.masterfinan.domain.repository.Models;
import com.elegro.masterfinan.infraestructura.entity.Departamento;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {

    @Autowired
    private Models models;

    public List<Departamento> listarDepartamentos(){
        try{
            return models.entityDepartamento().findAll();
        }catch (DaoException err){
            return null;
        }
    }
}
