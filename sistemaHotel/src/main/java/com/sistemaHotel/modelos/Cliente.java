package com.sistemaHotel.modelos;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "nacionalidad_id")
    private Nacionalidad nacionalidad;

    @NotBlank(message = "El nombre es requerido")
    private String nombre;

    @NotBlank(message = "El apellido es requerido")
    private String apellido;

    @NotBlank(message = "La direccion es requerido")
    private String direccion;

    @NotBlank(message = "El documento es requerido")
    private String documento;

    @NotBlank(message = "El telefono es requerido")
    private String telefono;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Nacionalidad getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Nacionalidad nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public @NotBlank(message = "El nombre es requerido") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank(message = "El nombre es requerido") String nombre) {
        this.nombre = nombre;
    }

    public @NotBlank(message = "El apellido es requerido") String getApellido() {
        return apellido;
    }

    public void setApellido(@NotBlank(message = "El apellido es requerido") String apellido) {
        this.apellido = apellido;
    }

    public @NotBlank(message = "La direccion es requerido") String getDireccion() {
        return direccion;
    }

    public void setDireccion(@NotBlank(message = "La direccion es requerido") String direccion) {
        this.direccion = direccion;
    }

    public @NotBlank(message = "El documento es requerido") String getDocumento() {
        return documento;
    }

    public void setDocumento(@NotBlank(message = "El documento es requerido") String documento) {
        this.documento = documento;
    }

    public @NotBlank(message = "El telefono es requerido") String getTelefono() {
        return telefono;
    }

    public void setTelefono(@NotBlank(message = "El telefono es requerido") String telefono) {
        this.telefono = telefono;
    }
}
