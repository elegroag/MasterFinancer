package com.elegro.masterfinan.application.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/abonos")
public class AbonosControlller {

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
