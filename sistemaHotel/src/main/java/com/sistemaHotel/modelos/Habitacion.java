package com.sistemaHotel.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Entity
@Table(name = "habitaciones")
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tipoHabitacion_id")
    private TipoHabitacion tipoHabitacion;

    @NotBlank(message = "Número es requerido")
    private String numero;

    @NotBlank(message = "Estado es requerido")
    private String estado;

    @Column(nullable = false)
    @NotNull(message = "El costo es obligatorio")
    @Positive(message = "El costo debe ser mayor que cero")
    private BigDecimal costo;

    @NotBlank(message = "Descripción es requerido")
    private String descripcion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public @NotBlank(message = "Número es requerido") String getNumero() {
        return numero;
    }

    public void setNumero(@NotBlank(message = "Número es requerido") String numero) {
        this.numero = numero;
    }

    public @NotBlank(message = "Estado es requerido") String getEstado() {
        return estado;
    }

    public void setEstado(@NotBlank(message = "Estado es requerido") String estado) {
        this.estado = estado;
    }

    public @NotNull(message = "El costo es obligatorio") @Positive(message = "El costo debe ser mayor que cero") BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(@NotNull(message = "El costo es obligatorio") @Positive(message = "El costo debe ser mayor que cero") BigDecimal costo) {
        this.costo = costo;
    }

    public @NotBlank(message = "Descripción es requerido") String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NotBlank(message = "Descripción es requerido") String descripcion) {
        this.descripcion = descripcion;
    }
}
