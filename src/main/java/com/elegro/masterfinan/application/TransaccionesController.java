package com.elegro.masterfinan.application;

import com.elegro.masterfinan.domain.repository.Models;
import com.elegro.masterfinan.infraestructura.entity.Transaccion;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping("/transacciones")
public class TransaccionesController {

    @GetMapping("/index")
    public List<Transaccion> renderLista() throws SQLException, DaoException {
        Models model = new Models();
        return model.entityTransaccion().findAll();
    }
}
