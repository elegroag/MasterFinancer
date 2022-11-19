package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.*;
import com.elegro.masterfinan.infraestructura.dao.MysqlConnector;
import com.elegro.masterfinan.infraestructura.entity.cruds.*;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
@Component
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

    @Override
    public TransaccionDaoRepository entityTransaccion() {
       if(transacciones == null){
           transacciones = new TransaccionRepository(conn);
       }
       return transacciones;
    }

    @Override
    public AbonoDaoRepository entityAbonos() {
        return null;
    }

    @Override
    public PagoDaoRepository entityPagos() {
        return null;
    }

    @Override
    public GastoCategoriaDaoRepository entityGastoCategorias() {
        return null;
    }

    @Override
    public IngresoCategoriaDaoRepository entityIngresoCategorias() {
        return null;
    }
}
