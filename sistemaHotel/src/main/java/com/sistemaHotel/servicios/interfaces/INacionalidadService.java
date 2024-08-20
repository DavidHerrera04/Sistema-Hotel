package com.sistemaHotel.servicios.interfaces;

import com.sistemaHotel.modelos.Nacionalidad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface INacionalidadService {
    Page<Nacionalidad> buscarTodosPaginados(Pageable pageable);

    List<Nacionalidad> obtenerTodos();

    Optional<Nacionalidad> buscarPorId(Integer id);

    Nacionalidad createEdit(Nacionalidad nacionalidad);

    void eliminarPorId(Integer id);
}
