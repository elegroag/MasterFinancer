package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.IngresoCategoriaDaoRepository;
import com.elegro.masterfinan.infraestructura.entity.IngresoCategoria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IngresoCategoriaRepository extends AbsRecordLong<IngresoCategoria> implements IngresoCategoriaDaoRepository {

    private static final String SQl_SELECT = "SELECT id, nombres, apellidos, username, password, saldo, tipo_identificacion FROM ingreso_categorias WHERE 1;";

    private static final String SQl_INSERT = "INSERT INTO ingreso_categorias (id, nombres, apellidos, username, password, saldo, tipo_identificacion)VALUES (?, ?, ?, ?, ?, ?, ?);";

    private static final String SQl_UPDATE = "UPDATE ingreso_categorias SET nombres=? , apellidos=?, username=?, password=?, saldo=?, tipo_identificacion=? WHERE id=?;";

    private static final String SQl_DELETE = "DELETE FROM ingreso_categorias WHERE id=?;";

    public IngresoCategoriaRepository(Connection conn) {
        this.connectionTransactional = conn;
        this.table = "ingreso_categorias";
        this.primaryKey = "id";
        this.fillable = new String[] { "id", "nombres", "apellidos", "username", "password", "saldo", "tipo_identificacion"};
        this.query.put("SQl_SELECT", SQl_SELECT);
        this.query.put("SQl_INSERT", SQl_INSERT);
        this.query.put("SQl_UPDATE", SQl_UPDATE);
        this.query.put("SQl_DELETE", SQl_DELETE);
    }

    @Override
    public IngresoCategoria recordModel(ResultSet rs) throws SQLException {
        return null;
    }
}
