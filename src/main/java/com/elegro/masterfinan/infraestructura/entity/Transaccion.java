package com.elegro.masterfinan.infraestructura.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaccion {

    protected Integer id;
    protected LocalDate fecha;
    protected LocalTime hora;
    protected Long usuario;
    protected Double valor;
    protected String estado;
    protected String tipoTransaccion;
    protected Usuario entityUsuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public Usuario getEntityUsuario() {
        return entityUsuario;
    }

    public void setEntityUsuario(Usuario entityUsuario) {
        this.entityUsuario = entityUsuario;
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", usuario=" + usuario +
                ", valor=" + valor +
                ", estado='" + estado + '\'' +
                ", tipoTransaccion='" + tipoTransaccion + '\'' +
                '}';
    }
}
