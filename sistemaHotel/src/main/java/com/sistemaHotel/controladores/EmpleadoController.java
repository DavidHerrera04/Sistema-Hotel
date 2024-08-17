package com.sistemaHotel.controladores;

import com.sistemaHotel.modelos.Empleado;
import com.sistemaHotel.servicios.interfaces.IEmpleadoService;
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
@RequestMapping("/empleados")
public class EmpleadoController {
    @Autowired
    private IEmpleadoService empleadoService;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size)
    {
        int currentPage = page.orElse(1)-1;
        int pageSize = size.orElse(5);
        Pageable pageable = (Pageable) PageRequest.of(currentPage, pageSize);

        Page<Empleado> empleados = empleadoService.buscarTodosPaginados(pageable);
        model.addAttribute("empleados", empleados);

        int totalPage = empleados.getTotalPages();
        if(totalPage > 0)
        {
            List<Integer> pageNumber = IntStream.rangeClosed(1, totalPage)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumber);
        }
        return "empleado/index";
    }

    @GetMapping("/create")
    public String create(Empleado empleado)

    {
        return "empleado/create";
    }

    @PostMapping("/save")
    public String save(Empleado pEmpleado, BindingResult result, Model model, RedirectAttributes attributes)
    {
        if(result.hasErrors())
        {
            model.addAttribute(pEmpleado);
            return "empleado/create";
        }

        empleadoService.createEdit(pEmpleado);
        attributes.addFlashAttribute("msg", "Empleado creado correctamente");
        return "redirect:/empleados";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model)
    {
        Empleado empleado = empleadoService.buscarPorId(id).get();
        model.addAttribute("empleado", empleado);
        return "empleado/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model)
    {
        Empleado empleado = empleadoService.buscarPorId(id).get();
        model.addAttribute("empleado", empleado);
        return "empleado/edit";
    }
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model)
    {
        Empleado empleado = empleadoService.buscarPorId(id).get();
        model.addAttribute("empleado", empleado);
        return "empleado/delete";
    }

    @PostMapping("/delete")
    public String delete(Empleado empleado, RedirectAttributes attributes)
    {
        empleadoService.eliminarPorId(empleado.getId());
        attributes.addFlashAttribute("msg", "Empleado eliminado correctamente");
        return "redirect:/empleados";
    }
}
