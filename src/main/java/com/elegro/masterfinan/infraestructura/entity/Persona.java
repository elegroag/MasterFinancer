package com.elegro.masterfinan.infraestructura.entity;

import lombok.Data;
import java.util.List;

@Data
public class Persona {

    private Long id;
    private Integer identificacion;
    private String nombres;
    private String apellidos;
    private String celular;
    private String email;
    private String telefonoComercial;
    private String direccionComercial;
    private Integer pais;
    private Integer departamento;
    private Integer ciudad;
    private String tipoPersona;
    private Integer tipoIdentificacion;
    private List<Compra> compras;
}
