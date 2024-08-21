package com.sistemaHotel.servicios.implementaciones;

import com.sistemaHotel.modelos.TipoHabitacion;
import com.sistemaHotel.repositorios.ITipoHabitacionRepository;
import com.sistemaHotel.servicios.interfaces.ITipoHabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoHabitacionService implements ITipoHabitacionService {

    @Autowired
    private ITipoHabitacionRepository tipoHabitacionRepository;

    @Override
    public Page<TipoHabitacion> buscarTodosPaginados(Pageable pageable) {
        return tipoHabitacionRepository.findAll(pageable);
    }

    @Override
    public List<TipoHabitacion> obtenerTodos() {
        return tipoHabitacionRepository.findAll();
    }

    @Override
    public Optional<TipoHabitacion> buscarPorId(Integer id) {
        return tipoHabitacionRepository.findById(id);
    }

    @Override
    public TipoHabitacion createOEdit(TipoHabitacion tipoHabitacion) {
        return tipoHabitacionRepository.save(tipoHabitacion);
    }

    @Override
    public void eliminarPorId(Integer id) {
        tipoHabitacionRepository.deleteById(id);
    }
}
