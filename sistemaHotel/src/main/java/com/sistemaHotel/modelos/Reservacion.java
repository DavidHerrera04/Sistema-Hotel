package com.sistemaHotel.modelos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservaciones")
public class Reservacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "habitacion_id")
    private Habitacion habitacion;

    @Column(nullable = false)
    @NotNull(message = "La fecha de entrada es obligatoria")
    @JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss")
    private LocalDateTime fechaHoraEntrada;

    @Column(nullable = false)
    @NotNull(message = "La fecha de salida es obligatoria")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaHoraSalida;

    @Column(nullable = false)
    @NotNull(message = "Costo Total es obligatorio")
    @Positive(message = "Costo Total debe ser mayor que cero")
    private BigDecimal costoTotal;

    @NotBlank(message = "Observación es requerido")
    private String observacion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public @NotNull(message = "La fecha de entrada es obligatoria") LocalDateTime getFechaHoraEntrada() {
        return fechaHoraEntrada;
    }

    public void setFechaHoraEntrada(@NotNull(message = "La fecha de entrada es obligatoria") LocalDateTime fechaHoraEntrada) {
        this.fechaHoraEntrada = fechaHoraEntrada;
    }

    public @NotNull(message = "La fecha de salida es obligatoria") LocalDateTime getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(@NotNull(message = "La fecha de salida es obligatoria") LocalDateTime fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public @NotNull(message = "Costo Total es obligatorio") @Positive(message = "Costo Total debe ser mayor que cero") BigDecimal getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(@NotNull(message = "Costo Total es obligatorio") @Positive(message = "Costo Total debe ser mayor que cero") BigDecimal costoTotal) {
        this.costoTotal = costoTotal;
    }

    public @NotBlank(message = "Número es requerido") String getObservacion() {
        return observacion;
    }

    public void setObservacion(@NotBlank(message = "Número es requerido") String observacion) {
        this.observacion = observacion;
    }
}
