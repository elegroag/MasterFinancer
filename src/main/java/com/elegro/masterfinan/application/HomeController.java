package com.elegro.masterfinan.application;

import com.elegro.masterfinan.domain.service.UsuarioService;
import com.elegro.masterfinan.infraestructura.entity.Usuario;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/index")
    public Optional<List<Usuario>> saludar() throws DaoException {
        return usuarioService.getUsuarios();
    }
}
