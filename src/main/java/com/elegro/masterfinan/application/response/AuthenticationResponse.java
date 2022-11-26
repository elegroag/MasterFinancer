package com.elegro.masterfinan.application.response;

public class AuthenticationResponse {

    private String jwt;

    public AuthenticationResponse(String _jwt) {
        this.jwt = _jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
