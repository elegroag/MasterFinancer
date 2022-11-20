package com.elegro.masterfinan.infraestructura.entity;


import java.time.LocalDate;
import java.util.List;
public class Compra {

    private Integer id;
    private LocalDate fechaInicial;
    private LocalDate fechaFinal;
    private String estadoCredito;
    private String tipoPago;
    private Long persona;
    private Double saldoPendiente;
    private Double valorCompra;
    private Long usuario;
    private Usuario entityUsuario;
    private Persona entityPersona;
    private List<CompraDetalle> detalles;

    private List<Transaccion> transacciones;

    private List<Pago> pagos;

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

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

    public Long getPersona() {
        return persona;
    }

    public void setPersona(Long persona) {
        this.persona = persona;
    }

    public Double getSaldoPendiente() {
        return saldoPendiente;
    }

    public void setSaldoPendiente(Double saldoPendiente) {
        this.saldoPendiente = saldoPendiente;
    }

    public Double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(Double valorCompra) {
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