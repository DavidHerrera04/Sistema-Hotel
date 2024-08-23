package com.sistemaHotel.servicios.interfaces;

import com.sistemaHotel.modelos.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClienteService {

    List<Cliente> obtenerTodos();

    Page<Cliente> buscarTodosPaginados(Pageable pPageable);

    Cliente buscarPorId(Integer pId);

    Cliente createOEditar(Cliente cliente);

    void eliminarPorId(Integer pId);
}
