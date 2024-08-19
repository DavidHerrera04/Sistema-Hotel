package com.sistemaHotel.controladores;

import com.sistemaHotel.modelos.Empleado;
import com.sistemaHotel.modelos.Estado;
import com.sistemaHotel.servicios.interfaces.IEstadoService;
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
@RequestMapping("/estados")
public class EstadoController {
    @Autowired
    private IEstadoService estadoService;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size)
    {
        int currentPage = page.orElse(1)-1;
        int pageSize = size.orElse(5);
        Pageable pageable = (Pageable) PageRequest.of(currentPage, pageSize);

        Page<Estado> estados = estadoService.buscarTodosPaginados(pageable);
        model.addAttribute("estados", estados);

        int totalPage = estados.getTotalPages();
        if(totalPage > 0)
        {
            List<Integer> pageNumber = IntStream.rangeClosed(1, totalPage)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumber);
        }
        return "estado/index";
    }

    @GetMapping("/create")
    public String create(Estado estado)

    {
        return "estado/create";
    }

    @PostMapping("/save")
    public String save(Estado pEstado, BindingResult result, Model model, RedirectAttributes attributes)
    {
    if(result.hasErrors())
    {
        model.addAttribute(pEstado);
        return "estado/create";
    }

    estadoService.createEdit(pEstado);
    attributes.addFlashAttribute("msg", "Estado creado correctamente");
    return "redirect:/estados";
    }
    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model)
    {
        Estado estado = estadoService.buscarPorId(id).get();
        model.addAttribute("estado", estado);
        return "estado/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model)
    {
        Estado estado = estadoService.buscarPorId(id).get();
        model.addAttribute("estado", estado);
        return "estado/edit";
    }
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model)
    {
        Estado estado = estadoService.buscarPorId(id).get();
        model.addAttribute("estado", estado);
        return "estado/delete";
    }

    @PostMapping("/delete")
    public String delete(Estado estado, RedirectAttributes attributes)
    {
        estadoService.eliminarPorId(estado.getId());
        attributes.addFlashAttribute("msg", "Estado eliminado correctamente");
        return "redirect:/estados";
    }
}
