package com.sistemaHotel.repositorios;

import com.sistemaHotel.modelos.TipoHabitacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITipoHabitacionRepository extends JpaRepository<TipoHabitacion, Integer> {
}
