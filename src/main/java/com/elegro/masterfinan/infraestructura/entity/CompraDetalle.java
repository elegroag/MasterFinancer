package com.elegro.masterfinan.infraestructura.entity;


public class CompraDetalle {

    private Long id;
    private Integer producto;
    private Integer compra;
    private Integer cantidad;
    private Double precioDetalle;
    private Compra entityCompra;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProducto() {
        return producto;
    }

    public void setProducto(Integer producto) {
        this.producto = producto;
    }

    public Integer getCompra() {
        return compra;
    }

    public void setCompra(Integer compra) {
        this.compra = compra;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioDetalle() {
        return precioDetalle;
    }

    public void setPrecioDetalle(Double precioDetalle) {
        this.precioDetalle = precioDetalle;
    }

    public Compra getEntityCompra() {
        return entityCompra;
    }

    public void setEntityCompra(Compra entityCompra) {
        this.entityCompra = entityCompra;
    }
}
