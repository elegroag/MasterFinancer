package com.elegro.masterfinan.infraestructura.entity;
import lombok.Data;

import java.util.List;

@Data
public class Usuario {

    private Long id;
    private String nombres;
    private String apellidos;
    private String username;
    private String password;
    private Double saldo;
    private Integer tipoIdentificacion;
    private List<Compra> compras;
    private List<Transaccion> transacciones;
}