package com.sistemaHotel.repositorios;

import com.sistemaHotel.modelos.Habitacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHabitacionRepository extends JpaRepository<Habitacion, Integer> {
    Page<Habitacion> findByOrderByTipoHabitacionDesc(Pageable pageable);
}
