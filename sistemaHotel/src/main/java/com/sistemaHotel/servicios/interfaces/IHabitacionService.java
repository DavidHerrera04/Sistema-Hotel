package com.sistemaHotel.servicios.interfaces;

import com.sistemaHotel.modelos.Cliente;
import com.sistemaHotel.modelos.Habitacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IHabitacionService {

    List<Habitacion> obtenerTodos();

    Page<Habitacion> buscarTodosPaginados(Pageable pPageable);

    Habitacion buscarPorId(Integer pId);

    Habitacion createOEditar(Habitacion habitacion);

    void eliminarPorId(Integer pId);
}
