package com.sistemaHotel.controladores;

import com.sistemaHotel.modelos.*;
import com.sistemaHotel.servicios.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/reservaciones")
public class ReservacionController {

    @Autowired
    private IReservacionService reservacionService;

    @Autowired
    private IEstadoService estadoService;

    @Autowired
    private IEmpleadoService empleadoService;

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IHabitacionService habitacionService;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1) - 1; //si no esta seteado se asigna 0
        int pageSize = size.orElse(5); // tamaño de la pagina, se asigna 5
        Pageable pageable = (Pageable) PageRequest.of(currentPage, pageSize);

        Page<Reservacion> reservaciones = reservacionService.buscarTodosPaginados(pageable);
        model.addAttribute("reservaciones", reservaciones);

        int totalPage = reservaciones.getTotalPages();
        if (totalPage > 0)
        {
            List<Integer> pageNumber = IntStream.rangeClosed(1, totalPage)
                    .boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumber);
        }
        return "reservacion/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("estados", estadoService.obtenerTodos());
        model.addAttribute("empleados", empleadoService.obtenerTodos());
        model.addAttribute("clientes", clienteService.obtenerTodos());
        model.addAttribute("habitaciones", habitacionService.obtenerTodos());
        return "reservacion/create";
    }

    @PostMapping("/save")
    public String save(@RequestParam Integer estadoId, @RequestParam Integer empleadoId,
                       @RequestParam Integer clienteId, @RequestParam Integer habitacionId,
                       @RequestParam LocalDateTime fechaHoraEntrada, @RequestParam LocalDateTime fechaHoraSalida,
                       @RequestParam BigDecimal costoTotal, @RequestParam String observacion,
                       RedirectAttributes attributes){

        Estado estado = estadoService.buscarPorId(estadoId).get();
        Empleado empleado = empleadoService.buscarPorId(empleadoId).get();
        Cliente cliente = clienteService.buscarPorId(clienteId);
        Habitacion habitacion = habitacionService.buscarPorId(habitacionId);

        if(estado != null & empleado != null & cliente != null & habitacion != null){
            Reservacion reservacion = new Reservacion();
            reservacion.setEstado(estado);
            reservacion.setEmpleado(empleado);
            reservacion.setCliente(cliente);
            reservacion.setHabitacion(habitacion);
            reservacion.setFechaHoraEntrada(fechaHoraEntrada);
            reservacion.setFechaHoraSalida(fechaHoraSalida);
            reservacion.setCostoTotal(costoTotal);
            reservacion.setObservacion(observacion);

            reservacionService.createOEditar(reservacion);
            attributes.addFlashAttribute("msg", "Reservación creada correctamente");
        }

        return "redirect:/reservaciones";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model) {

        Reservacion reservacion = reservacionService.buscarPorId(id);
        model.addAttribute("reservacion", reservacion);
        return "reservacion/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Reservacion reservacion = reservacionService.buscarPorId(id);
        model.addAttribute("estados", estadoService.obtenerTodos());
        model.addAttribute("empleados", empleadoService.obtenerTodos());
        model.addAttribute("clientes", clienteService.obtenerTodos());
        model.addAttribute("habitaciones", habitacionService.obtenerTodos());
        model.addAttribute("reservacion", reservacion);
        return "reservacion/edit";
    }

    @PostMapping("/update")
    public String update(@RequestParam Integer id, @RequestParam Integer estadoId,
                         @RequestParam Integer empleadoId, @RequestParam Integer clienteId,
                         @RequestParam Integer habitacionId, @RequestParam LocalDateTime fechaHoraEntrada,
                         @RequestParam LocalDateTime fechaHoraSalida, @RequestParam BigDecimal costoTotal,
                         @RequestParam String observacion, RedirectAttributes attributes){

        Estado estado = estadoService.buscarPorId(estadoId).get();
        Empleado empleado = empleadoService.buscarPorId(empleadoId).get();
        Cliente cliente = clienteService.buscarPorId(clienteId);
        Habitacion habitacion = habitacionService.buscarPorId(habitacionId);

        if(estado != null & empleado != null & cliente != null & habitacion != null){
            Reservacion reservacion = new Reservacion();
            reservacion.setId(id);
            reservacion.setEstado(estado);
            reservacion.setEmpleado(empleado);
            reservacion.setCliente(cliente);
            reservacion.setHabitacion(habitacion);
            reservacion.setFechaHoraEntrada(fechaHoraEntrada);
            reservacion.setFechaHoraSalida(fechaHoraSalida);
            reservacion.setCostoTotal(costoTotal);
            reservacion.setObservacion(observacion);

            reservacionService.createOEditar(reservacion);
            attributes.addFlashAttribute("msg", "Reservación modificada correctamente");
        }

        return "redirect:/reservaciones";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model) {
        Reservacion reservacion = reservacionService.buscarPorId(id);
        model.addAttribute("reservacion", reservacion);
        return "reservacion/delete";
    }

    @PostMapping("/delete")
    public String delete(Reservacion reservacion, RedirectAttributes attributes) {
        reservacionService.eliminarPorId(reservacion.getId());
        attributes.addFlashAttribute("msg", "Reservación eliminada correctamente");
        return "redirect:/reservaciones";
    }

}
