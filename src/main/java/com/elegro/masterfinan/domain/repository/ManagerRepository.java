package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.CompraDaoRepository;
import com.elegro.masterfinan.infraestructura.cruds.PersonaDaoRepository;
import com.elegro.masterfinan.infraestructura.cruds.TransaccionDaoRepository;
import com.elegro.masterfinan.infraestructura.cruds.UsuarioDaoRepository;

import java.sql.Connection;

abstract class ManagerRepository {

    protected UsuarioDaoRepository usuarios = null;
    protected PersonaDaoRepository personas = null;
    protected CompraDaoRepository compras = null;

    protected TransaccionDaoRepository transacciones = null;

    public abstract UsuarioDaoRepository entityUsuario();
    public abstract PersonaDaoRepository entityPersona();
    public abstract CompraDaoRepository entityCompra();
    public abstract TransaccionDaoRepository entityTransaccion();

    protected Connection conn;
}
