package com.elegro.masterfinan.infraestructura.entity;

import lombok.Data;

@Data
public class CompraDetalle {

    private Long id;
    private Integer producto;
    private Integer compra;
    private Integer cantidad;
    private Double precioDetalle;
    private Compra entityCompra;
}
