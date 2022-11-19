package com.elegro.masterfinan.infraestructura.entity;

import java.util.List;

public class Gasto {

    protected Long id;
    protected Integer transaccion;
    protected String medioPago;
    protected Integer gastoCategoria;
    protected List<Gasto> gastos;

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

    public Integer getGastoCategoria() {
        return gastoCategoria;
    }

    public void setGastoCategoria(Integer gastoCategoria) {
        this.gastoCategoria = gastoCategoria;
    }

    public List<Gasto> getGastos() {
        return gastos;
    }

    public void setGastos(List<Gasto> gastos) {
        this.gastos = gastos;
    }
}
