package com.elegro.masterfinan.application.web;

import com.elegro.masterfinan.domain.service.CiudadService;
import com.elegro.masterfinan.domain.service.DepartamentoService;
import com.elegro.masterfinan.domain.service.PaisService;
import com.elegro.masterfinan.infraestructura.entity.Ciudad;
import com.elegro.masterfinan.infraestructura.entity.Departamento;
import com.elegro.masterfinan.infraestructura.entity.Pais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/location")
public class UbicacionController {

    @Autowired
    private PaisService paisService;

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private CiudadService ciudadService;

    @GetMapping("/paises")
    public ResponseEntity<List<Pais>> listarPaises(){
        try {
            return new ResponseEntity<>(paisService.listarPaises(), HttpStatus.OK);
        } catch (Exception err){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/departamentos")
    public ResponseEntity<List<Departamento>> listarDepartamentos(){
        try {
            return new ResponseEntity<>(departamentoService.listarDepartamentos(), HttpStatus.OK);
        } catch (Exception err){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/ciudades")
    public ResponseEntity<List<Ciudad>> listarCiudades(){
        try {
            return new ResponseEntity<>(ciudadService.listarCiudades(), HttpStatus.OK);
        } catch (Exception err){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }


}
