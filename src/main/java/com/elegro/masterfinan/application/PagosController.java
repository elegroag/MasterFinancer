package com.elegro.masterfinan.application;


import com.elegro.masterfinan.domain.service.PagoService;
import com.elegro.masterfinan.infraestructura.entity.Pago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/pagos")
public class PagosController {

    @Autowired
    ResponseApi<Pago> response;

    @Autowired
    PagoService pagoService;

    @GetMapping("/todo")
    public List<Pago> listarPagos(){
        return pagoService.listaPagos();
    }

    @PostMapping("/crear")
    public IResponseApi crear(@RequestBody Pago pago){
        return pagoService.crear(pago).map(_pago -> {
            response.setSuccess(true);
            response.setMessage("Registro completado con éxito");
            response.setData(Optional.ofNullable(_pago));
            return response;
        }).orElseGet(() -> {
            response.setSuccess(false);
            response.setMessage("Error el registro no es posible");
            return response;
        });
    }

    @DeleteMapping("/borrar")
    public IResponseApi borrar(@RequestBody Map<String, String> request){
        Long _id = Long.parseLong(request.get("id"));
        if (pagoService.borrar(_id)) {
            response.setMessage("El proceso se completo con éxito");
            response.setSuccess(true);
        }else{
            response.setMessage("Error no se puede borrar, ha generado un error");
            response.setSuccess(false);
        }
        return response;
    }

    @PutMapping("/actualizar")
    public IResponseApi actualiza(@RequestBody Pago pago, @RequestParam String id){
        Long _id = Long.parseLong(id);
        if (pagoService.actualiza(pago, _id))
        {
            response.setMessage("El proceso se completo con éxito");
            response.setSuccess(true);
        }else{
            response.setMessage("Error no se pueden actualizar, ha generado un error");
            response.setSuccess(false);
        }
        return response;
    }

    @GetMapping("/buscar")
    public Optional<Pago> buscar(@RequestParam String id){
        Long _id = Long.parseLong(id);
        return pagoService.buscar(_id);
    }

}
