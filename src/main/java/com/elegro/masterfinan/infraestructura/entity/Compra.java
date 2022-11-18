package com.elegro.masterfinan.infraestructura.entity;


import java.time.LocalDate;
import java.util.List;
public class Compra {

    private Integer id;
    private LocalDate fechaInicial;
    private LocalDate fechaFinal;
    private String estadoCredito;
    private String tipoPago;
    private Integer persona;
    private Double saldoPendiente;
    private Integer valorCompra;
    private Long usuario;
    private Usuario entityUsuario;
    private Persona entityPersona;
    private List<CompraDetalle> detalles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(LocalDate fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getEstadoCredito() {
        return estadoCredito;
    }

    public void setEstadoCredito(String estadoCredito) {
        this.estadoCredito = estadoCredito;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public Integer getPersona() {
        return persona;
    }

    public void setPersona(Integer persona) {
        this.persona = persona;
    }

    public Double getSaldoPendiente() {
        return saldoPendiente;
    }

    public void setSaldoPendiente(Double saldoPendiente) {
        this.saldoPendiente = saldoPendiente;
    }

    public Integer getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(Integer valorCompra) {
        this.valorCompra = valorCompra;
    }

    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }

    public Usuario getEntityUsuario() {
        return entityUsuario;
    }

    public void setEntityUsuario(Usuario entityUsuario) {
        this.entityUsuario = entityUsuario;
    }

    public Persona getEntityPersona() {
        return entityPersona;
    }

    public void setEntityPersona(Persona entityPersona) {
        this.entityPersona = entityPersona;
    }

    public List<CompraDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<CompraDetalle> detalles) {
        this.detalles = detalles;
    }
}