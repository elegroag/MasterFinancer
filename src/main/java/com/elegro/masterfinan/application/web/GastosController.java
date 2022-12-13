package com.elegro.masterfinan.application.web;

import com.elegro.masterfinan.application.response.IResponseApi;
import com.elegro.masterfinan.application.response.ResponseApi;
import com.elegro.masterfinan.domain.service.GastoService;
import com.elegro.masterfinan.infraestructura.entity.Compra;
import com.elegro.masterfinan.infraestructura.entity.Gasto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/gastos")
public class GastosController {

    @Autowired
    private ResponseApi<Compra> response;

    @Autowired
    private GastoService gastoService;

    @GetMapping("/listar")
    public ResponseEntity<List<Gasto>> listaGastos(){
        try {
            return new ResponseEntity<>(gastoService.listaGastos(), HttpStatus.OK);
        } catch (Exception err){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/crear")
    public  ResponseEntity<Optional<Gasto>> crear(@RequestBody Gasto gasto){
        return gastoService.crear(gasto).map(_gasto -> new ResponseEntity<>(Optional.of(_gasto), HttpStatus.OK)).orElse( new ResponseEntity<>(HttpStatus.FORBIDDEN));
    }

    @GetMapping("/buscar")
    public ResponseEntity<Optional<Gasto>> buscar(@RequestParam String id){
        Long _id = Long.parseLong(id);
        return gastoService.buscar(_id).map(_gasto -> new ResponseEntity<>(Optional.of(_gasto), HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.FORBIDDEN));
    }

    @DeleteMapping("/borrar")
    public ResponseEntity<Optional<IResponseApi>> borrar(@RequestBody Map<String, String> request){
        Long _id = Long.parseLong(request.get("id"));
        if (gastoService.borrar(_id))
        {
            response.setMessage("El proceso se completo con éxito");
            response.setSuccess(true);
            response.setData(Optional.empty());
        }else{
            response.setMessage("Error no se puede borrar, ha generado un error");
            response.setSuccess(false);
        }
        return new ResponseEntity<>(Optional.of(response), HttpStatus.OK);
    }
}
