package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.CompraDaoRepository;
import com.elegro.masterfinan.infraestructura.cruds.PersonaDaoRepository;
import com.elegro.masterfinan.infraestructura.cruds.UsuarioDaoRepository;
import com.elegro.masterfinan.infraestructura.dao.MysqlConnector;

import java.sql.SQLException;

public class Mediator extends ManagerRepository {

    public Mediator() throws SQLException {
        if(this.conn == null) {
            this.conn = MysqlConnector.getConnection();
        }
    }

    @Override
    public UsuarioDaoRepository getUsuario() {
        if(usuarios == null){
            usuarios = new UsuarioRepository(conn);
        }
        return usuarios;
    }

    @Override
    public PersonaDaoRepository getPersona() {
        if(personas == null){
            personas = new PersonaRepository(conn);
        }
        return personas;
    }

    @Override
    public CompraDaoRepository getCompra() {
        if(compras == null){
            compras = new CompraRepository(conn);
        }
        return compras;
    }
}
