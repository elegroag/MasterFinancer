package com.elegro.masterfinan.application;

import com.elegro.masterfinan.domain.repository.Mediator;
import com.elegro.masterfinan.domain.repository.UsuarioRepository;
import com.elegro.masterfinan.infraestructura.entity.Usuario;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/index")
    public List<Usuario> saludar() throws SQLException, DaoException {
        Mediator main = new Mediator();
        List<Usuario> usuarios = main.getUsuario().findAll();
        return usuarios;
    }
}
