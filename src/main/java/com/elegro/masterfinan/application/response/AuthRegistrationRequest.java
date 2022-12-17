package com.elegro.masterfinan.application.response;

public class AuthRegistrationRequest {

    private String usuario;
    private String clave;
    private boolean terminos_condiciones;
    private Integer tipo_documento;
    private Long documento;
    private String email;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean isTerminos_condiciones() {
        return terminos_condiciones;
    }

    public void setTerminos_condiciones(boolean terminos_condiciones) {
        this.terminos_condiciones = terminos_condiciones;
    }

    public Integer getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(Integer tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "AuthRegistrationRequest{" +
                "usuario='" + usuario + '\'' +
                ", clave='" + clave + '\'' +
                ", terminos_condiciones=" + terminos_condiciones +
                ", tipo_documento=" + tipo_documento +
                ", documento=" + documento +
                ", email='" + email + '\'' +
                '}';
    }
}
