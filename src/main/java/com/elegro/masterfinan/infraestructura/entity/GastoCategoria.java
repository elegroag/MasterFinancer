package com.elegro.masterfinan.infraestructura.entity;

public class GastoCategoria {

    protected Long id;
    protected String detalle;
    protected Boolean esfijo;

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

    public Boolean getEsfijo() {
        return esfijo;
    }

    public void setEsfijo(Boolean esfijo) {
        this.esfijo = esfijo;
    }
}
