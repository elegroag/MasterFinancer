package com.elegro.masterfinan.domain.service;

import com.elegro.masterfinan.domain.repository.Models;
import com.elegro.masterfinan.infraestructura.entity.Usuario;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private Models models;

    public Optional<List<Usuario>> getUsuarios() throws DaoException {
        return Optional.ofNullable(this.models.entityUsuario().findAll());
    }

    public Optional<Usuario> crear(Usuario user){
        try {
            models.entityUsuario().insert(user);
            Long id = models.entityUsuario().getInsertId();
            user.setId(id);
            return Optional.of(user);
        }catch (DaoException err ){
            return Optional.empty();
        }
    }

    public boolean borrar(Long userId) {
        return buscar(userId).map(user -> {
            try {
                models.entityUsuario().delete(user);
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }
            return true;
        }).orElse(false);
    }

    public boolean actualiza(Usuario user, Long id){
        return buscar(id).map(_user -> {
            try {
                _user.setNombres(user.getNombres());
                _user.setApellidos(user.getApellidos());
                _user.setPassword(user.getApellidos());
                _user.setUsername(user.getUsername());
                return models.entityUsuario().update(user);
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }
        }).orElse(false);
    }

    public Optional<Usuario> buscar(Long userId){
        try {
            return Optional.of(models.entityUsuario().findById(userId));
        }catch (DaoException err){
            return Optional.empty();
        }
    }
}
