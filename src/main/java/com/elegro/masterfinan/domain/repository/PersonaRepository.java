package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.entity.cruds.PersonaDaoRepository;
import com.elegro.masterfinan.infraestructura.entity.Persona;

import java.sql.Connection;
import java.util.List;

public class PersonaRepository implements PersonaDaoRepository {

    protected  Connection connectionTransactional;
    public PersonaRepository(Connection conn){
        this.connectionTransactional = conn;
    }

    @Override
    public List<Persona> findAll() {
        return null;
    }

    @Override
    public Persona findById(Long id) {
        return null;
    }

    @Override
    public Persona findOne(Long id) {
        return null;
    }

    @Override
    public Persona findFirst() {
        return null;
    }

    @Override
    public Persona findLast() {
        return null;
    }

}
