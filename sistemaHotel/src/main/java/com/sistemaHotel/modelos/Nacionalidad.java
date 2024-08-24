package com.sistemaHotel.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "nacionalidades")
public class Nacionalidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Pais es requerdio")
    private String pais;

    @NotBlank(message = "Nacionalidad es requerido")
    private String nombre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank(message = "Pais es requerdio") String getPais() {
        return pais;
    }

    public void setPais(@NotBlank(message = "Pais es requerdio") String pais) {
        this.pais = pais;
    }

    public @NotBlank(message = "Nacionalidad es requerido") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank(message = "Nacionalidad es requerido") String nombre) {
        this.nombre = nombre;
    }
}
