package com.elegro.masterfinan.infraestructura.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
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

}