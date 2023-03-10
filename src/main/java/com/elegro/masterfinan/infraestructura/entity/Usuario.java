package com.elegro.masterfinan.infraestructura.entity;

import java.util.List;


public class Usuario {

    protected Long id;
    protected String nombres;
    protected String apellidos;
    protected String username;
    protected String password;
    protected Double saldo;
    protected Integer tipoIdentificacion;
    protected List<Compra> compras;
    protected List<Transaccion> transacciones;
    protected String email;
    protected boolean terminos_condiciones;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isTerminos_condiciones() {
        return terminos_condiciones;
    }

    public void setTerminos_condiciones(boolean terminos_condiciones) {
        this.terminos_condiciones = terminos_condiciones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Integer getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(Integer tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", saldo=" + saldo +
                ", tipoIdentificacion=" + tipoIdentificacion +
                ", compras=" + compras +
                ", transacciones=" + transacciones +
                ", email='" + email + '\'' +
                ", terminos_condiciones=" + terminos_condiciones +
                '}';
    }
}