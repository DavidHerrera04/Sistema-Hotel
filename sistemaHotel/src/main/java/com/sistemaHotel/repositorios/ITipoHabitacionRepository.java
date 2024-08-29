package com.sistemaHotel.repositorios;

import com.sistemaHotel.modelos.Cliente;
import com.sistemaHotel.modelos.TipoHabitacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITipoHabitacionRepository extends JpaRepository<TipoHabitacion, Integer> {
    Page<TipoHabitacion> findByOrderByIdDesc(Pageable pageable);
}
