package com.sistemaHotel.servicios.interfaces;


import com.sistemaHotel.modelos.TipoHabitacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ITipoHabitacionService {

    Page<TipoHabitacion> buscarTodosPaginados(Pageable pageable);

    List<TipoHabitacion> obtenerTodos();

    Optional<TipoHabitacion> buscarPorId(Integer id);

    TipoHabitacion createOEdit(TipoHabitacion tipoHabitacion);

    void eliminarPorId(Integer id);
}
