package com.sistemaHotel.servicios.interfaces;

import com.sistemaHotel.modelos.Cliente;
import com.sistemaHotel.modelos.Reservacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IReservacionService {

    List<Reservacion> obtenerTodos();

    Page<Reservacion> buscarTodosPaginados(Pageable pPageable);

    Reservacion buscarPorId(Integer pId);

    Reservacion createOEditar(Reservacion reservacion);

    void eliminarPorId(Integer pId);
}
