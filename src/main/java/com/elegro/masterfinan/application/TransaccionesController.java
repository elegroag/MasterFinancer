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
import java.util.Optional;


@RestController
@RequestMapping("/transacciones")
public class TransaccionesController {

    @Autowired
    TransaccionService transaccionService;

    @GetMapping("/todo")
    public List<Transaccion> listarTransacciones(){
        return transaccionService.renderLista();
    }

    @GetMapping("/crear")
    public Optional<Transaccion> crear(Transaccion transaccion){
        return transaccionService.crear(transaccion);
    }

    @GetMapping("/borrar")
    public Boolean borrar(int id){
        return transaccionService.borrar(id);
    }

    @GetMapping("/actualizar")
    public Boolean actualiza(Transaccion transaccion, Integer id){
        return transaccionService.actualiza(transaccion, id);
    }

    @GetMapping("/buscar")
    public Optional<Transaccion> buscar(int id){
        return transaccionService.buscar(id);
    }

}
