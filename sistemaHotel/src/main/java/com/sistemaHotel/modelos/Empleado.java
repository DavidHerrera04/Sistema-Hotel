package com.sistemaHotel.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "empleados")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Nombre es requerdio")
    private String nombre;

    @NotBlank(message = "Apellido es requerido")
    private String apellido;

    @NotBlank(message = "Direccion es requerido")
    private String direccion;

    @NotBlank(message = "Dui es requerido")
    private String dui;

    @NotBlank(message = "Telefono es requerido")
    private String telefono;

    @NotBlank(message = "Estado es requerido")
    private String estado;

    @NotBlank(message = "El login de empleado es requerido")
    private String login;

    @NotBlank(message = "La contraseña es requerida")
    private String clave;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "empleado_rol",
            joinColumns = @JoinColumn(name = "empleado_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private List<Rol> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank(message = "Nombre es requerdio") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank(message = "Nombre es requerdio") String nombre) {
        this.nombre = nombre;
    }

    public @NotBlank(message = "Apellido es requerido") String getApellido() {
        return apellido;
    }

    public void setApellido(@NotBlank(message = "Apellido es requerido") String apellido) {
        this.apellido = apellido;
    }

    public @NotBlank(message = "Direccion es requerido") String getDireccion() {
        return direccion;
    }

    public void setDireccion(@NotBlank(message = "Direccion es requerido") String direccion) {
        this.direccion = direccion;
    }

    public @NotBlank(message = "Dui es requerido") String getDui() {
        return dui;
    }

    public void setDui(@NotBlank(message = "Dui es requerido") String dui) {
        this.dui = dui;
    }

    public @NotBlank(message = "Telefono es requerido") String getTelefono() {
        return telefono;
    }

    public void setTelefono(@NotBlank(message = "Telefono es requerido") String telefono) {
        this.telefono = telefono;
    }

    public @NotBlank(message = "Estado es requerido") String getEstado() {
        return estado;
    }

    public void setEstado(@NotBlank(message = "Estado es requerido") String estado) {
        this.estado = estado;
    }

    public @NotBlank(message = "El login de empleado es requerido") String getLogin() {
        return login;
    }

    public void setLogin(@NotBlank(message = "El login de empleado es requerido") String login) {
        this.login = login;
    }

    public @NotBlank(message = "La contraseña es requerida") String getClave() {
        return clave;
    }

    public void setClave(@NotBlank(message = "La contraseña es requerida") String clave) {
        this.clave = clave;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public void agregar(Rol tempRol) {
        if (roles == null) {
            roles = new LinkedList<>();
        }
        roles.add(tempRol);
    }
}
