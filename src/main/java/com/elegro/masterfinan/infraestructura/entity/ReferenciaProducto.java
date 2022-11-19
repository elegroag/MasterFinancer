package com.elegro.masterfinan.infraestructura.entity;

public class ReferenciaProducto {

    protected Long id;
    protected String detalle;
    protected Long persona;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Long getPersona() {
        return persona;
    }

    public void setPersona(Long persona) {
        this.persona = persona;
    }
}
