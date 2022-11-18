package com.elegro.masterfinan.domain.service;

import com.elegro.masterfinan.domain.repository.Models;
import com.elegro.masterfinan.infraestructura.entity.Usuario;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    Models mod;
    public UsuarioService() {
        try {
            this.mod = new Models();
        }catch (SQLException ex){
            System.out.println(ex);
        }
    }
    public Optional<List<Usuario>> getUsuarios() throws DaoException {
        return Optional.ofNullable(this.mod.entityUsuario().findAll());
    }
}
