package com.elegro.masterfinan.infraestructura.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class Transaccion {

    private Integer id;
    private LocalDate fecha;
    private LocalTime hora;
    private Long usuario;
    private Double valor;
    private String estado;
    private String tipoTransaccion;
    private Usuario entityUsuario;

}
