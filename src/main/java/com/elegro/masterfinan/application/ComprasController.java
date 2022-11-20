package com.elegro.masterfinan.application;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@RestController
@RequestMapping("/compras")
public class ComprasController {


    @PostMapping("/crear")
    public Map<String, String> crearTransaccionCompra(@RequestBody Map<String, String> request){
        String nombre = request.get("nombre");
        Double valor = Double.parseDouble(request.get("valor"));
        boolean estado = request.get("estado") == "1";
        return request;
    }

    @GetMapping("/buscar")
    public String buscar(){
        return "";
    }
}
