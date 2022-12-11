package com.elegro.masterfinan.application.web;

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
}
