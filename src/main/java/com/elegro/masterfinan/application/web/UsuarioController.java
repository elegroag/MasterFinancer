package com.elegro.masterfinan.application.web;

import com.elegro.masterfinan.domain.service.UsuarioService;
import com.elegro.masterfinan.infraestructura.entity.Usuario;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/index")
    public Optional<List<Usuario>> listaUsuarios() throws DaoException {
        return usuarioService.getUsuarios();
    }

    @GetMapping("/buscar")
    public ResponseEntity<Optional<Usuario>> buscar(@RequestParam String username){
        return usuarioService.buscarUsername(username).map(_user -> new ResponseEntity<>(Optional.of(_user), HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.FORBIDDEN));
    }
}
