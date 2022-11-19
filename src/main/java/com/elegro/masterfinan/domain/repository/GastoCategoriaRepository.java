package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.GastoCategoriaDaoRepository;
import com.elegro.masterfinan.infraestructura.entity.GastoCategoria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GastoCategoriaRepository extends AbsRecordLong<GastoCategoria> implements GastoCategoriaDaoRepository {

    private static final String SQl_SELECT = "SELECT id, nombres, apellidos, username, password, saldo, tipo_identificacion FROM gastos_categorias WHERE 1;";

    private static final String SQl_INSERT = "INSERT INTO gastos_categorias (id, nombres, apellidos, username, password, saldo, tipo_identificacion)VALUES (?, ?, ?, ?, ?, ?, ?);";

    private static final String SQl_UPDATE = "UPDATE gastos_categorias SET nombres=? , apellidos=?, username=?, password=?, saldo=?, tipo_identificacion=? WHERE id=?;";

    private static final String SQl_DELETE = "DELETE FROM gastos_categorias WHERE id=?;";
    public GastoCategoriaRepository(Connection conn) {
        this.connectionTransactional = conn;
        this.table = "gastos_categorias";
        this.primaryKey = "id";
        this.fillable = new String[] { "id", "nombres", "apellidos", "username", "password", "saldo", "tipo_identificacion"};
        this.query.put("SQl_SELECT", SQl_SELECT);
        this.query.put("SQl_INSERT", SQl_INSERT);
        this.query.put("SQl_UPDATE", SQl_UPDATE);
        this.query.put("SQl_DELETE", SQl_DELETE);
    }

    @Override
    public GastoCategoria recordModel(ResultSet rs) throws SQLException {
        return null;
    }
}
