package com.sistemaHotel.servicios.implementaciones;


import com.sistemaHotel.modelos.Rol;
import com.sistemaHotel.repositorios.IRolRepository;
import com.sistemaHotel.servicios.interfaces.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService implements IRolService {
    @Autowired
    private IRolRepository rolRepository;

    @Override
    public List<Rol> obtenerTodos() {
        return rolRepository.findAll();
    }}
