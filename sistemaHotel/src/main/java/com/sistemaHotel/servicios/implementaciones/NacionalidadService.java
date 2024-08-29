package com.sistemaHotel.servicios.implementaciones;

import com.sistemaHotel.modelos.Nacionalidad;
import com.sistemaHotel.repositorios.INacionalidadRepository;
import com.sistemaHotel.servicios.interfaces.INacionalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NacionalidadService implements INacionalidadService {
    @Autowired
    private INacionalidadRepository nacionalidadRepository;

    @Override
    public Page<Nacionalidad> buscarTodosPaginados(Pageable pPageable) {
        return nacionalidadRepository.findByOrderByIdDesc(pPageable);
    }

    @Override
    public List<Nacionalidad> obtenerTodos() {
        return nacionalidadRepository.findAll();
    }

    @Override
    public Optional<Nacionalidad> buscarPorId(Integer pId) {
        return nacionalidadRepository.findById(pId);
    }

    @Override
    public Nacionalidad createEdit(Nacionalidad pNacionalidad) {
        return nacionalidadRepository.save(pNacionalidad);
    }

    @Override
    public void eliminarPorId(Integer pId) {
        nacionalidadRepository.deleteById(pId);
    }

}
