package com.sistemaHotel.repositorios;

import com.sistemaHotel.modelos.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEstadoRepository extends JpaRepository<Estado, Integer> {
}
