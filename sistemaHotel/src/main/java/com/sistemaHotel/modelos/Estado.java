package com.sistemaHotel.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "estados")
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Nombre es requerido")
    private String nombre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank(message = "Nombre es requerido") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank(message = "Nombre es requerido") String nombre) {
        this.nombre = nombre;
    }
}
