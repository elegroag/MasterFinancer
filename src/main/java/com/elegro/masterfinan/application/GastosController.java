package com.elegro.masterfinan.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gastos")
public class GastosController {

    @GetMapping("/crear")
    public String crear(){

        return "";
    }

    @GetMapping("/borrar")
    public String borrar(){

        return "";
    }

    @GetMapping("/actualizar")
    public String actualizar(){

        return "";
    }

    @GetMapping("/buscar")
    public String buscar(){

        return "";
    }
}
