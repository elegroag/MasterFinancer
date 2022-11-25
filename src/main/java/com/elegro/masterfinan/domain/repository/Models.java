package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.*;
import com.elegro.masterfinan.infraestructura.dao.MysqlConnector;
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
        if(abonos == null){
            abonos = new AbonoRepository(conn);
        }
        return abonos;
    }

    @Override
    public PagoDaoRepository entityPagos() {
        if(pagos == null){
            pagos = new PagoRepository(conn);
        }
        return pagos;
    }

    @Override
    public GastoCategoriaDaoRepository entityGastoCategorias() {
        if(gastoCategorias == null){
            gastoCategorias = new GastoCategoriaRepository(conn);
        }
        return gastoCategorias;
    }

    @Override
    public IngresoCategoriaDaoRepository entityIngresoCategorias() {
        if(ingresoCategorias == null){
            ingresoCategorias = new IngresoCategoriaRepository(conn);
        }
        return ingresoCategorias;
    }

    @Override
    public ReferenciaProductoDaoRepository entityReferenciaProducto() {
        if(referenciaProductos == null){
            referenciaProductos = new ReferenciaProductoRepository(conn);
        }
        return referenciaProductos;
    }

    @Override
    public CompraDetalleDaoRepository entityCompraDetalle() {
        if(compraDetalles == null){
            compraDetalles = new CompraDetalleRepository(conn);
        }
        return compraDetalles;
    }
}
