package com.elegro.masterfinan.application.web;

import com.elegro.masterfinan.application.response.IResponseApi;
import com.elegro.masterfinan.application.response.ResponseApi;
import com.elegro.masterfinan.domain.service.AbonoService;
import com.elegro.masterfinan.infraestructura.entity.Abono;
import com.elegro.masterfinan.infraestructura.entity.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/abonos")
public class AbonosControlller {

    @Autowired
    private AbonoService abonoService;

    @Autowired
    private ResponseApi<Compra> response;

    @GetMapping("/listar")
    public ResponseEntity<List<Abono>> listaAbonos(){
        try {
            return new ResponseEntity<>(abonoService.listaAbonos(), HttpStatus.OK);
        } catch (Exception err){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/crear")
    public  ResponseEntity<Optional<Abono>> crear(@RequestBody Abono abono){
        return abonoService.crear(abono).map(_abono -> new ResponseEntity<>(Optional.of(_abono), HttpStatus.OK)).orElse( new ResponseEntity<>(HttpStatus.FORBIDDEN));
    }

    @GetMapping("/buscar")
    public ResponseEntity<Optional<Abono>> buscar(@RequestParam String id){
        Long _id = Long.parseLong(id);
        return abonoService.buscar(_id).map(_abono -> new ResponseEntity<>(Optional.of(_abono), HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.FORBIDDEN));
    }

    @DeleteMapping("/borrar")
    public ResponseEntity<Optional<IResponseApi>> borrar(@RequestBody Map<String, String> request){
        Long _id = Long.parseLong(request.get("id"));
        if (abonoService.borrar(_id))
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
