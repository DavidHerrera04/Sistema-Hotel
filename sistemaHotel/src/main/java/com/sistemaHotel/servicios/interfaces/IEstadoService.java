package com.sistemaHotel.servicios.interfaces;

import com.sistemaHotel.modelos.Estado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IEstadoService {

    Page<Estado> buscarTodosPaginados(Pageable pageable);

    List<Estado> obtenerTodos();

    Optional<Estado> buscarPorId(Integer id);

    Estado createEdit(Estado estado);

    void eliminarPorId(Integer id);
}
