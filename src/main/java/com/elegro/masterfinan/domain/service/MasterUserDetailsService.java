package com.elegro.masterfinan.domain.service;

import com.elegro.masterfinan.domain.repository.Models;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;


@Service
public class MasterUserDetailsService  implements UserDetailsService {

    @Autowired
    Models models;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return models.entityUsuario().findByUsername(username)
            .map(usuario -> new User(usuario.getUsername(), "{noop}"+usuario.getPassword(), new ArrayList<>()))
            .orElse( null);
    }


}
