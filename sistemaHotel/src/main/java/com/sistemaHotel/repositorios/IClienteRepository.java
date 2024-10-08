package com.sistemaHotel.repositorios;

import com.sistemaHotel.modelos.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<Cliente , Integer> {
    Page<Cliente> findByOrderByIdDesc(Pageable pageable);
}
