package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.*;

import java.sql.Connection;

abstract class ManagerRepository {

    protected UsuarioDaoRepository usuarios = null;
    protected PersonaDaoRepository personas = null;
    protected CompraDaoRepository compras = null;
    protected TransaccionDaoRepository transacciones = null;
    protected AbonoDaoRepository abonos = null;
    protected PagoDaoRepository pagos = null;
    protected GastoCategoriaDaoRepository gastoCategorias = null;
    protected IngresoCategoriaDaoRepository ingresoCategorias = null;
    protected ReferenciaProductoDaoRepository referenciaProductos = null;
    protected CompraDetalleDaoRepository compraDetalles = null;
    protected GastoDaoRepository gastos = null;
    protected PaisDaoRepository paises = null;
    protected DepartamentoDaoRepository departamentos = null;
    protected CiudadDaoRepository ciudades = null;

    protected TipoIdentificacionDaoRepository tipoIdentificaciones = null;

    public abstract UsuarioDaoRepository entityUsuario();
    public abstract PersonaDaoRepository entityPersona();
    public abstract CompraDaoRepository entityCompra();
    public abstract TransaccionDaoRepository entityTransaccion();
    public abstract AbonoDaoRepository entityAbonos();
    public abstract PagoDaoRepository entityPagos();
    public abstract GastoCategoriaDaoRepository entityGastoCategorias();
    public abstract IngresoCategoriaDaoRepository entityIngresoCategorias();
    public abstract  ReferenciaProductoDaoRepository entityReferenciaProducto();
    public abstract CompraDetalleDaoRepository entityCompraDetalle();
    public abstract GastoDaoRepository entityGasto();
    public abstract  PaisDaoRepository entityPais();
    public abstract  DepartamentoDaoRepository entityDepartamento();
    public abstract  CiudadDaoRepository entityCiudad();
    public abstract  TipoIdentificacionDaoRepository entityTipoIndetificacion();

    protected Connection conn;
}
