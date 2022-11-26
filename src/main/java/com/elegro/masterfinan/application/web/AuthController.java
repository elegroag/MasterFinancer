package com.elegro.masterfinan.application.web;

import com.elegro.masterfinan.application.exception.DebugException;
import com.elegro.masterfinan.application.response.AuthRegistrationRequest;
import com.elegro.masterfinan.application.security.JWTUtil;
import com.elegro.masterfinan.application.response.AuthenticationRequest;
import com.elegro.masterfinan.application.response.AuthenticationResponse;

import com.elegro.masterfinan.domain.service.MasterUserDetailsService;
import com.elegro.masterfinan.domain.service.UsuarioService;
import com.elegro.masterfinan.infraestructura.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MasterUserDetailsService masterUserDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails userDetails = masterUserDetailsService.loadUserByUsername(request.getUsername());
            String jwt = jwtUtil.generateToken(userDetails);
            return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
        } catch (BadCredentialsException err){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<AuthenticationResponse> registrationAuth(@RequestBody AuthRegistrationRequest request){
        try {
            try {
                Map<String, String> messages = validationRequire(request);
                DebugException.setVariables(messages);
                if(messages.size() > 0){
                    throw new DebugException("Error de validación en los datos de registro");
                }
                Usuario _user;
                if (usuarioService.buscar(request.getDocumento()).isPresent()) {
                    _user = usuarioService.buscar(request.getDocumento()).get();
                } else {
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
                    usuarioService.crear(_user);
                }
                try {
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(_user.getUsername(), _user.getPassword()));
                    UserDetails userDetails = masterUserDetailsService.loadUserByUsername(_user.getUsername());
                    String jwt = jwtUtil.generateToken(userDetails);
                    return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
                } catch (BadCredentialsException err) {
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }
            }catch (DebugException debug)
            {
                System.out.println(debug.getMessage());
                System.out.println(DebugException.getVariables());
                return new ResponseEntity<>( HttpStatus.FORBIDDEN);
            }
        }catch (HttpClientErrorException.BadRequest err){
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
            if(matcher.matches()){
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
}
