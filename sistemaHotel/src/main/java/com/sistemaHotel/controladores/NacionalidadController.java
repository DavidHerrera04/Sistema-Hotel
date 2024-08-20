package com.sistemaHotel.controladores;

import com.sistemaHotel.modelos.Nacionalidad;
import com.sistemaHotel.servicios.interfaces.INacionalidadService;
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
@RequestMapping("/nacionalidades")
public class NacionalidadController {
    @Autowired
    private INacionalidadService nacionalidadService;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size)
    {
        int currentPage = page.orElse(1)-1;
        int pageSize = size.orElse(5);
        Pageable pageable = (Pageable) PageRequest.of(currentPage, pageSize);

        Page<Nacionalidad> nacionalidades = nacionalidadService.buscarTodosPaginados(pageable);
        model.addAttribute("nacionalidades", nacionalidades);

        int totalPage = nacionalidades.getTotalPages();
        if(totalPage > 0)
        {
            List<Integer> pageNumber = IntStream.rangeClosed(1, totalPage)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumber);
        }
        return "nacionalidad/index";
    }

    @GetMapping("/create")
    public String create(Nacionalidad nacionalidad)

    {
        return "nacionalidad/create";
    }

    @PostMapping("/save")
    public String save(Nacionalidad pNacionalidad, BindingResult result, Model model, RedirectAttributes attributes)
    {
        if(result.hasErrors())
        {
            model.addAttribute(pNacionalidad);
            return "nacionalidad/create";
        }

        nacionalidadService.createEdit(pNacionalidad);
        attributes.addFlashAttribute("msg", "Nacionalidad creada correctamente");
        return "redirect:/nacionalidades";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model)
    {
        Nacionalidad nacionalidad = nacionalidadService.buscarPorId(id).get();
        model.addAttribute("nacionalidad", nacionalidad);
        return "nacionalidad/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model)
    {
        Nacionalidad nacionalidad = nacionalidadService.buscarPorId(id).get();
        model.addAttribute("nacionalidad", nacionalidad);
        return "nacionalidad/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model)
    {
        Nacionalidad nacionalidad = nacionalidadService.buscarPorId(id).get();
        model.addAttribute("nacionalidad", nacionalidad);
        return "nacionalidad/delete";
    }

    @PostMapping("/delete")
    public String delete(Nacionalidad nacionalidad, RedirectAttributes attributes)
    {
        nacionalidadService.eliminarPorId(nacionalidad.getId());
        attributes.addFlashAttribute("msg", "Nacionalidad eliminada correctamente");
        return "redirect:/nacionalidades";
    }

}
