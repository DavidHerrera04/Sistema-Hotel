package com.sistemaHotel.repositorios;

import com.sistemaHotel.modelos.Cliente;
import com.sistemaHotel.modelos.Nacionalidad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INacionalidadRepository extends JpaRepository<Nacionalidad, Integer> {
    Page<Nacionalidad> findByOrderByIdDesc(Pageable pageable);

}
