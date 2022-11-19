package com.elegro.masterfinan.infraestructura.entity;

import java.util.List;

public class Abono {

    protected Long id;
    protected Integer transaccion;
    protected String medioPago;
    protected Long categoriaIngreso;
    protected Long persona;

    protected List<IngresoCategoria> ingresos;

    public List<IngresoCategoria> getIngresos() {
        return ingresos;
    }

    public void setIngresos(List<IngresoCategoria> ingresos) {
        this.ingresos = ingresos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Integer transaccion) {
        this.transaccion = transaccion;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public Long getCategoriaIngreso() {
        return categoriaIngreso;
    }

    public void setCategoriaIngreso(Long categoriaIngreso) {
        this.categoriaIngreso = categoriaIngreso;
    }

    public Long getPersona() {
        return persona;
    }

    public void setPersona(Long persona) {
        this.persona = persona;
    }


}
