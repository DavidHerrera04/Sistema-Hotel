package com.sistemaHotel.servicios.implementaciones;

import com.sistemaHotel.modelos.Reservacion;
import com.sistemaHotel.repositorios.IReservacionRepository;
import com.sistemaHotel.servicios.interfaces.IReservacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservacionService implements IReservacionService {

    @Autowired
    private IReservacionRepository reservacionRepository;

    @Override
    public List<Reservacion> obtenerTodos() {
        return reservacionRepository.findAll();
    }

    @Override
    public Page<Reservacion> buscarTodosPaginados(Pageable pPageable) {
        return reservacionRepository.findByOrderByIdAsc(pPageable);
    }

    @Override
    public Reservacion buscarPorId(Integer id) {
        return reservacionRepository.findById(id).get();
    }

    @Override
    public Reservacion createOEditar(Reservacion reservacion) {
        return reservacionRepository.save(reservacion);
    }

    @Override
    public void eliminarPorId(Integer pId) {
        reservacionRepository.deleteById(pId);
    }
}
