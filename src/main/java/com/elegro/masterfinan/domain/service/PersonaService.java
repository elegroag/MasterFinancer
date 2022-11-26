package com.elegro.masterfinan.domain.service;

import com.elegro.masterfinan.domain.repository.Models;
import com.elegro.masterfinan.infraestructura.entity.Persona;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    Models models;

    public List<Persona> listaTerceros() {
        try{
            return models.entityPersona().findAll();
        }catch (DaoException err ){
            return null;
        }
    }

    public Optional<Persona> crear(Persona persona){
        try {
            models.entityPersona().insert(persona);
            Long id = models.entityPersona().getInsertId();
            persona.setId(id);
            return Optional.of(persona);
        }catch (DaoException err ){
            return Optional.empty();
        }
    }

    public boolean borrar(Long personaId) {
        return buscar(personaId).map(persona -> {
            boolean flag = false;
            try {
                flag = models.entityPersona().delete(persona);
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }
            return flag;
        }).orElse(false);
    }

    public boolean actualiza(Persona persona, Long id){
        return buscar(id).map(_persona -> {
            try {
                _persona.setNombres(persona.getNombres());
                return models.entityPersona().update(_persona);
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }
        }).orElse(false);
    }

    public Optional<Persona> buscar(Long personaId){
        try {
            return Optional.of(models.entityPersona().findById(personaId));
        }catch (DaoException err){
            return Optional.empty();
        }
    }
}
