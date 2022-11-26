package com.elegro.masterfinan.application.web;

import com.elegro.masterfinan.application.response.IResponseApi;
import com.elegro.masterfinan.application.response.ResponseApi;
import com.elegro.masterfinan.domain.service.PersonaService;
import com.elegro.masterfinan.infraestructura.entity.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("personas")
public class PersonaController {

    @Autowired
    private ResponseApi<Persona> response;

    @Autowired
    private PersonaService personaService;

    @GetMapping("/todo")
    public List<Persona> listar(){
        return personaService.listaTerceros();
    }

    @PostMapping("/crear")
    public Optional<Persona> crear(@RequestBody Persona persona){
        return personaService.crear(persona);
    }

    @DeleteMapping("/borrar")
    public IResponseApi borrar(@RequestBody Map<String, String> request){
        Long _id = Long.parseLong(request.get("id"));
        if (personaService.borrar(_id)) {
            response.setMessage("El proceso se completo con éxito");
            response.setSuccess(true);
        }else{
            response.setMessage("Error no se puede borrar, ha generado un error");
            response.setSuccess(false);
        }
        return response;
    }

    @PutMapping("/actualizar")
    public IResponseApi actualiza(@RequestBody Persona persona, @RequestParam String id){
        Long _id = Long.parseLong(id);
        if (personaService.actualiza(persona, _id))
        {
            response.setMessage("El proceso se completo con éxito");
            response.setSuccess(true);
            persona.setId(_id);
            response.setData(Optional.of(persona));
        }else{
            response.setMessage("Error no se pueden actualizar, ha generado un error");
            response.setSuccess(false);
            response.setData(Optional.empty());
        }
        return response;
    }

    @GetMapping("/buscar")
    public Optional<Persona> buscar(@RequestParam String id){
        Long _id = Long.parseLong(id);
        return personaService.buscar(_id);
    }
}
