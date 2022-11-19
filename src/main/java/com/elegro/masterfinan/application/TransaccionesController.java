package com.elegro.masterfinan.application;

import com.elegro.masterfinan.domain.service.TransaccionService;
import com.elegro.masterfinan.infraestructura.entity.Transaccion;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping("/transacciones")
public class TransaccionesController {

    @Autowired
    TransaccionService transaccionService;

    @GetMapping("/todo")
    public List<Transaccion> listarTransacciones() throws SQLException, DaoException {
        return transaccionService.renderLista();
    }

    @GetMapping("/crear")
    public String crear(){
        transaccionService.crea();
        return "";
    }

    @GetMapping("/borrar")
    public String borrar(){
        transaccionService.borra();
        return "";
    }

    @GetMapping("/actualizar")
    public String actualiza(){
        transaccionService.actualiza();
        return "";
    }

    @GetMapping("/buscar")
    public String buscar(){
        transaccionService.busca();
        return "";
    }

}
