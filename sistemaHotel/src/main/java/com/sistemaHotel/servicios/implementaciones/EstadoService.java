package com.sistemaHotel.servicios.implementaciones;

import com.sistemaHotel.modelos.Estado;
import com.sistemaHotel.repositorios.IEstadoRepository;
import com.sistemaHotel.servicios.interfaces.IEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService implements IEstadoService {
    @Autowired
    private IEstadoRepository estadoRepository;

    @Override
    public Page<Estado> buscarTodosPaginados(Pageable pPageable) {
        return estadoRepository.findAll(pPageable);
    }

    @Override
    public List<Estado> obtenerTodos() {
        return estadoRepository.findAll();
    }

    @Override
    public Optional<Estado> buscarPorId(Integer pId) {
        return estadoRepository.findById(pId);
    }

    @Override
    public Estado createEdit(Estado pEstado) {
        return estadoRepository.save(pEstado);
    }

    @Override
    public void eliminarPorId(Integer pId) {
        estadoRepository.deleteById(pId);
    }
}
