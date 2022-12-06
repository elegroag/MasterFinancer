package com.elegro.masterfinan.application.response;

public class AuthenticationResponse {

    private String message;

    private boolean success;
    private String jwt;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public AuthenticationResponse(String _jwt) {
        this.jwt = _jwt;
    }
    public AuthenticationResponse(){}


    public AuthenticationResponse(String jwt, boolean status, String msj){
        this.jwt = jwt;
        this.message = msj;
        this.success = status;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
