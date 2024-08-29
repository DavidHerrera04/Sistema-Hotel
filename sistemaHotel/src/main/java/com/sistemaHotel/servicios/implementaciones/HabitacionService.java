package com.sistemaHotel.servicios.implementaciones;

import com.sistemaHotel.modelos.Habitacion;
import com.sistemaHotel.repositorios.IHabitacionRepository;
import com.sistemaHotel.servicios.interfaces.IHabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitacionService implements IHabitacionService {
    @Autowired
    private IHabitacionRepository habitacionRepository;

    @Override
    public List<Habitacion> obtenerTodos() {
        return habitacionRepository.findAll();
    }

    @Override
    public Page<Habitacion> buscarTodosPaginados(Pageable pPageable) {
        return habitacionRepository.findByOrderByIdDesc(pPageable);
    }

    @Override
    public Habitacion buscarPorId(Integer id) {
        return habitacionRepository.findById(id).get();
    }

    @Override
    public Habitacion createOEditar(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    @Override
    public void eliminarPorId(Integer id) {
        habitacionRepository.deleteById(id);
    }
}
