package com.elegro.masterfinan.application.web;

import com.elegro.masterfinan.application.response.IResponseApi;
import com.elegro.masterfinan.application.response.ResponseApi;
import com.elegro.masterfinan.domain.service.ReferenciaProductoService;
import com.elegro.masterfinan.infraestructura.entity.ReferenciaProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/referencia_productos")
public class ReferenciaProductosController {

    @Autowired
    private ResponseApi<ReferenciaProducto> response;

    @Autowired
    private ReferenciaProductoService referenciaProductoService;

    @GetMapping("/todo")
    public List<ReferenciaProducto> listarProdcutos(){
        return referenciaProductoService.listaReferencias();
    }

    @PostMapping("/crear")
    public IResponseApi crear(@RequestBody ReferenciaProducto referenciaProducto){
        return referenciaProductoService.crear(referenciaProducto).map(_referenciaProducto -> {
            response.setSuccess(true);
            response.setMessage("Registro completado con éxito");
            response.setData(Optional.of(_referenciaProducto));
            return response;
        }).orElseGet(() -> {
            response.setSuccess(false);
            response.setMessage("Error el registro no es posible");
            response.setData(Optional.empty());
            return response;
        });
    }

    @DeleteMapping("/borrar")
    public IResponseApi borrar(@RequestBody Map<String, String> request){
        Long _id = Long.parseLong(request.get("id"));
        if (referenciaProductoService.borrar(_id)) {
            response.setMessage("El proceso se completo con éxito");
            response.setSuccess(true);
        }else{
            response.setMessage("Error no se puede borrar, ha generado un error");
            response.setSuccess(false);
        }
        return response;
    }

    @PutMapping("/actualizar")
    public IResponseApi actualiza(@RequestBody ReferenciaProducto referenciaProducto, @RequestParam String id){
        Long _id = Long.parseLong(id);
        if (referenciaProductoService.actualiza(referenciaProducto, _id))
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
    public Optional<ReferenciaProducto> buscar(@RequestParam String id){
        Long _id = Long.parseLong(id);
        return referenciaProductoService.buscar(_id);
    }

}
