package com.sistemaHotel.repositorios;

import com.sistemaHotel.modelos.Reservacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReservacionRepository extends JpaRepository<Reservacion, Integer> {

    Page<Reservacion> findByOrderByIdAsc(Pageable pageable);

}
