package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.CompraDaoRepository;
import com.elegro.masterfinan.infraestructura.cruds.PersonaDaoRepository;
import com.elegro.masterfinan.infraestructura.cruds.UsuarioDaoRepository;
import com.elegro.masterfinan.infraestructura.dao.MysqlConnector;

import java.sql.SQLException;

public class Models extends ManagerRepository {

    public Models() throws SQLException {
        if(this.conn == null) {
            this.conn = MysqlConnector.getConnection();
        }
    }

    @Override
    public UsuarioDaoRepository entityUsuario() {
        if(usuarios == null){
            usuarios = new UsuarioRepository(conn);
        }
        return usuarios;
    }

    @Override
    public PersonaDaoRepository entityPersona() {
        if(personas == null){
            personas = new PersonaRepository(conn);
        }
        return personas;
    }

    @Override
    public CompraDaoRepository entityCompra() {
        if(compras == null){
            compras = new CompraRepository(conn);
        }
        return compras;
    }
}
