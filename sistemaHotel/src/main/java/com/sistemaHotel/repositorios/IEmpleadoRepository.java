package com.sistemaHotel.repositorios;

import com.sistemaHotel.modelos.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmpleadoRepository extends JpaRepository<Empleado, Integer> {
}
