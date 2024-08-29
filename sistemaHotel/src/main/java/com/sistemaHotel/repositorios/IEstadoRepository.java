package com.sistemaHotel.repositorios;

import com.sistemaHotel.modelos.Cliente;
import com.sistemaHotel.modelos.Estado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEstadoRepository extends JpaRepository<Estado, Integer> {
    Page<Estado> findByOrderByIdDesc(Pageable pageable);
}
