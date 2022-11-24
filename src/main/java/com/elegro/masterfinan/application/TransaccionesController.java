package com.elegro.masterfinan.application;

import com.elegro.masterfinan.domain.service.TransaccionService;
import com.elegro.masterfinan.infraestructura.entity.Transaccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/transacciones")
public class TransaccionesController {

    @Autowired
    ResponseApi<Transaccion> response;

    @Autowired
    TransaccionService transaccionService;

    @GetMapping("/todo")
    public List<Transaccion> listarTransacciones(){
        return transaccionService.renderLista();
    }

    @PostMapping("/crear")
    public Optional<Transaccion> crear(@RequestBody Transaccion transaccion){
        return transaccionService.crear(transaccion);
    }

    @DeleteMapping("/borrar")
    public IResponseApi borrar(@RequestBody Map<String, String> request){
        Integer _id = Integer.parseInt(request.get("id"));
        if (transaccionService.borrar(_id)) {
            response.setMessage("El proceso se completo con éxito");
            response.setSuccess(true);
        }else{
            response.setMessage("Error no se puede borrar, ha generado un error");
            response.setSuccess(false);
        }
        return response;
    }

    @PutMapping("/actualizar")
    public IResponseApi actualiza(@RequestBody Transaccion transaccion, @RequestParam String id){
        Integer _id = Integer.parseInt(id);
        if (transaccionService.actualiza(transaccion, _id))
        {
            response.setMessage("El proceso se completo con éxito");
            response.setSuccess(true);
        }else{
            response.setMessage("Error no se pueden actualizar, ha generado un error");
            response.setSuccess(true);
        }
        return response;
    }

    @GetMapping("/buscar")
    public Optional<Transaccion> buscar(@RequestParam String id){
        Integer _id = Integer.parseInt(id);
        return transaccionService.buscar(_id);
    }

}
