package com.sistemaHotel.repositorios;

import com.sistemaHotel.modelos.Cliente;
import com.sistemaHotel.modelos.Empleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmpleadoRepository extends JpaRepository<Empleado, Integer> {
    Page<Empleado> findByOrderByIdDesc(Pageable pageable);

}
