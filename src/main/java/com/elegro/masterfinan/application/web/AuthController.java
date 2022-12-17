package com.elegro.masterfinan.application.web;

import com.elegro.masterfinan.application.exception.DebugException;
import com.elegro.masterfinan.application.response.AuthRegistrationRequest;
import com.elegro.masterfinan.application.security.JWTUtil;
import com.elegro.masterfinan.application.response.AuthenticationRequest;
import com.elegro.masterfinan.application.response.AuthenticationResponse;

import com.elegro.masterfinan.domain.repository.Models;
import com.elegro.masterfinan.domain.service.MasterUserDetailsService;
import com.elegro.masterfinan.domain.service.PaisService;
import com.elegro.masterfinan.domain.service.TipoIndetificationService;
import com.elegro.masterfinan.infraestructura.entity.Pais;
import com.elegro.masterfinan.infraestructura.entity.TipoIdentificacion;
import com.elegro.masterfinan.infraestructura.entity.Usuario;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    Models models;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MasterUserDetailsService masterUserDetailsService;

    @Autowired
    private TipoIndetificationService tipoIndetificationService;

    @Autowired
    private PaisService paisService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails userDetails = masterUserDetailsService.loadUserByUsername(request.getUsername());
            String jwt = jwtUtil.generateToken(userDetails);
            return new ResponseEntity<>(new AuthenticationResponse(jwt, true, "Ok bienvenido ahora puedes hacer uso del token."), HttpStatus.OK);
        } catch (BadCredentialsException err){
            System.out.println(err.getMessage());
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<AuthenticationResponse> registrationAuth(@RequestBody AuthRegistrationRequest request){
        AuthenticationResponse authResponse = null;
        try {
            try {
                Map<String, String> messages = validationRequire(request);
                DebugException.setVariables(messages);
                if(messages.size() > 0){
                    throw new DebugException("Error de validación en los datos de registro");
                }
                Usuario _user = models.entityUsuario().findById(request.getDocumento());
                if(_user == null){
                     _user = new Usuario();
                    _user.setId(request.getDocumento());
                    _user.setUsername(request.getUsuario());
                    _user.setPassword(request.getClave());
                    _user.setTipoIdentificacion(request.getTipo_documento());
                    _user.setNombres("");
                    _user.setApellidos("");
                    _user.setEmail(request.getEmail());
                    _user.setTerminos_condiciones(request.isTerminos_condiciones());
                    _user.setSaldo(0.0);
                    models.entityUsuario().insert(_user);
                } else {
                    throw new DebugException("El usuario ya se encuentra registrado");
                }
                try {
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(_user.getUsername(), _user.getPassword()));
                    UserDetails userDetails = new User(_user.getUsername(), "{noop}"+_user.getPassword(), new ArrayList<>());
                    String jwt = jwtUtil.generateToken(userDetails);
                    authResponse = new AuthenticationResponse(jwt);
                    authResponse.setSuccess(true);
                    authResponse.setMessage("Solicitud Ok");
                    return new ResponseEntity<>(authResponse, HttpStatus.OK);
                } catch (BadCredentialsException err){
                    System.out.println(err.getMessage());
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }
            }catch (DebugException debug)
            {
                System.out.println(DebugException.getVariables());
                authResponse = new AuthenticationResponse();
                authResponse.setSuccess(false);
                authResponse.setMessage(debug.getMessage());
                return new ResponseEntity<>(authResponse, HttpStatus.FORBIDDEN);
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }
        }catch (HttpClientErrorException.BadRequest err){
            System.out.println(err.getMessage());
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    private Map<String, String> validationRequire(AuthRegistrationRequest request){
        Map<String, String> messages = new HashMap<>();
        if(request.getUsuario().length() == 0){
            messages.put("usuario", " Error el usuario es requerida para continuar");
        }
        if(request.getEmail().length() == 0){
            messages.put("email", " Error el email es requerida para continuar");
        }else{
            String regexEmail = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
            Pattern pattern = Pattern.compile(regexEmail);
            Matcher matcher = pattern.matcher(request.getEmail());
            if(!matcher.matches()){
                messages.put("email", " Error el email no posee un valor valido para continuar");
            }
        }
        if(request.getClave().length() == 0){
            messages.put("clave"," Error la clave es requerida para continuar");
        }
        if(request.getDocumento() == 0){
            messages.put("documento"," Error la clave es requerida para continuar");
        }
        if(request.getTipo_documento() == 0){
            messages.put("tipo_documento"," Error el tipo documento es requerido para continuar");
        }
        return messages;
    }

    @GetMapping("/tipo_documentos")
    private ResponseEntity<List<TipoIdentificacion>> getTipoIdentificacion(){
        try {
            return new ResponseEntity<>(tipoIndetificationService.listaTipos(), HttpStatus.OK);
        } catch (Exception err){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/paises")
    private ResponseEntity<List<Pais>> getNaciones(){
        try {
            return new ResponseEntity<>(paisService.listarPaises(), HttpStatus.ACCEPTED);
        } catch (Exception err){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
