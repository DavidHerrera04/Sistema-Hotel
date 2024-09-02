package com.sistemaHotel.repositorios;

import com.sistemaHotel.modelos.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolRepository extends JpaRepository<Rol, Integer> {
}