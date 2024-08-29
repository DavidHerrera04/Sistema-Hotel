package com.sistemaHotel.servicios.implementaciones;

import com.sistemaHotel.modelos.Empleado;
import com.sistemaHotel.repositorios.IEmpleadoRepository;
import com.sistemaHotel.servicios.interfaces.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService implements IEmpleadoService {
    @Autowired
    private IEmpleadoRepository empleadoRepository;

    @Override
    public Page<Empleado> buscarTodosPaginados(Pageable pPageable) {
        return empleadoRepository.findByOrderByIdDesc(pPageable);
    }

    @Override
    public List<Empleado> obtenerTodos() {
        return empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> buscarPorId(Integer pId) {
        return empleadoRepository.findById(pId);
    }

    @Override
    public Empleado createEdit(Empleado pEmpleado) {
        return empleadoRepository.save(pEmpleado);
    }

    @Override
    public void eliminarPorId(Integer pId) {
        empleadoRepository.deleteById(pId);
    }
}
