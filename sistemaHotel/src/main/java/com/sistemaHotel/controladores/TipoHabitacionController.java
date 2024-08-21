package com.sistemaHotel.controladores;

import com.sistemaHotel.modelos.TipoHabitacion;
import com.sistemaHotel.servicios.interfaces.ITipoHabitacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/tiposhabitacion")
public class TipoHabitacionController {
    @Autowired
    private ITipoHabitacionService tipoHabitacionService ;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size)
    {
        int currentPage = page.orElse(1)-1;
        int pageSize = size.orElse(5);
        Pageable pageable = (Pageable) PageRequest.of(currentPage, pageSize);

        Page<TipoHabitacion> tiposhabitacion = tipoHabitacionService.buscarTodosPaginados(pageable);
        model.addAttribute("tiposhabitacion", tiposhabitacion);

        int totalPage = tiposhabitacion.getTotalPages();
        if(totalPage > 0)
        {
            List<Integer> pageNumber = IntStream.rangeClosed(1, totalPage)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumber);
        }
        return "tipohabitacion/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("tipohabitacion", new TipoHabitacion());
        return "tipohabitacion/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute @Valid TipoHabitacion tipohabitacion, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "tipohabitacion/create";
        }

        tipoHabitacionService.createOEdit(tipohabitacion);
        attributes.addFlashAttribute("msg", "Tipo Habitacion creado correctamente");
        return "redirect:/tiposhabitacion";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model)
    {
        TipoHabitacion tipoHabitacion = tipoHabitacionService.buscarPorId(id).get();
        model.addAttribute("tipohabitacion", tipoHabitacion);
        return "tipoHabitacion/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model)
    {
        TipoHabitacion tipoHabitacion = tipoHabitacionService.buscarPorId(id).get();
        model.addAttribute("tipohabitacion", tipoHabitacion);
        return "tipohabitacion/edit";
    }
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model)
    {
        TipoHabitacion tipoHabitacion = tipoHabitacionService.buscarPorId(id).get();
        model.addAttribute("tipohabitacion", tipoHabitacion);
        return "tipohabitacion/delete";
    }

    @PostMapping("/delete")
    public String delete(TipoHabitacion tipoHabitacion, RedirectAttributes attributes)
    {
        tipoHabitacionService.eliminarPorId(tipoHabitacion.getId());
        attributes.addFlashAttribute("msg", "tipo habitacion eliminado correctamente");
        return "redirect:/tiposhabitacion";
    }
}
