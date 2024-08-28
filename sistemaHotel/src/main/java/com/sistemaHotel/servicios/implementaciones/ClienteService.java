package com.sistemaHotel.servicios.implementaciones;


import com.sistemaHotel.modelos.Cliente;
import com.sistemaHotel.repositorios.IClienteRepository;
import com.sistemaHotel.servicios.interfaces.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private IClienteRepository clienteRepository;

    @Override
    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Page<Cliente> buscarTodosPaginados(Pageable pageable) {
        return clienteRepository.findByOrderByIdAsc(pageable);
    }

    @Override
    public Cliente buscarPorId(Integer id) {
        return clienteRepository.findById(id).get();
    }

    @Override
    public Cliente createOEditar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void eliminarPorId(Integer id) {
        clienteRepository.deleteById(id);
    }
}
