package com.sistemaHotel.controladores;

import com.sistemaHotel.modelos.Cliente;
import com.sistemaHotel.modelos.Habitacion;
import com.sistemaHotel.modelos.Nacionalidad;
import com.sistemaHotel.modelos.TipoHabitacion;
import com.sistemaHotel.servicios.interfaces.IClienteService;
import com.sistemaHotel.servicios.interfaces.IHabitacionService;
import com.sistemaHotel.servicios.interfaces.INacionalidadService;
import com.sistemaHotel.servicios.interfaces.ITipoHabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/habitaciones")
public class HabitacionController {
    @Autowired
    private IHabitacionService habitacionService;

    @Autowired
    private ITipoHabitacionService tipoHabitacionService;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1) - 1; //si no esta seteado se asigna 0
        int pageSize = size.orElse(5); // tamaño de la pagina, se asigna 5
        Pageable pageable = (Pageable) PageRequest.of(currentPage, pageSize);

        Page<Habitacion> habitaciones = habitacionService.buscarTodosPaginados(pageable);
        model.addAttribute("habitaciones", habitaciones);

        int totalPage = habitaciones.getTotalPages();
        if (totalPage > 0)
        {
            List<Integer> pageNumber = IntStream.rangeClosed(1, totalPage)
                    .boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumber);
        }
        return "habitacion/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("tiposHabitacion", tipoHabitacionService.obtenerTodos());
        return "habitacion/create";
    }

    @PostMapping("/save")
    public String save(@RequestParam Integer tipoHabitacionId, @RequestParam String numero, @RequestParam String estado,
                       @RequestParam BigDecimal costo, @RequestParam String descripcion,
                       RedirectAttributes attributes){
        TipoHabitacion tipoHabitacion = tipoHabitacionService.buscarPorId(tipoHabitacionId).get();

        if(tipoHabitacion != null){
            Habitacion habitacion = new Habitacion();
            habitacion.setTipoHabitacion(tipoHabitacion);
            habitacion.setNumero(numero);
            habitacion.setEstado(estado);
            habitacion.setCosto(costo);
            habitacion.setDescripcion(descripcion);

            habitacionService.createOEditar(habitacion);
            attributes.addFlashAttribute("msg", "Habitación creada correctamente");
        }
        return "redirect:/habitaciones";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model) {
        Habitacion habitacion = habitacionService.buscarPorId(id);
        model.addAttribute("habitacion", habitacion);
        return "habitacion/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Habitacion habitacion = habitacionService.buscarPorId(id);
        model.addAttribute("tiposHabitacion", tipoHabitacionService.obtenerTodos());
        model.addAttribute("habitacion", habitacion);
        return "habitacion/edit";
    }

    @PostMapping("/update")
    public String update(@RequestParam Integer id, @RequestParam Integer tipoHabitacionId, @RequestParam String numero,
                         @RequestParam String estado, @RequestParam BigDecimal costo, @RequestParam String descripcion,
                         RedirectAttributes attributes){
        TipoHabitacion tipoHabitacion = tipoHabitacionService.buscarPorId(tipoHabitacionId).get();

        if(tipoHabitacion != null){
            Habitacion habitacion = new Habitacion();
            habitacion.setId(id);
            habitacion.setTipoHabitacion(tipoHabitacion);
            habitacion.setNumero(numero);
            habitacion.setEstado(estado);
            habitacion.setCosto(costo);
            habitacion.setDescripcion(descripcion);

            habitacionService.createOEditar(habitacion);
            attributes.addFlashAttribute("msg", "Habitación modificada correctamente");
        }

        return "redirect:/habitaciones";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model) {
        Habitacion habitacion = habitacionService.buscarPorId(id);
        model.addAttribute("habitacion", habitacion);
        return "habitacion/delete";
    }

    @PostMapping("/delete")
    public String delete(Habitacion habitacion, RedirectAttributes attributes) {
        habitacionService.eliminarPorId(habitacion.getId());
        attributes.addFlashAttribute("msg", "Habitación eliminada correctamente");
        return "redirect:/habitaciones";
    }

}
