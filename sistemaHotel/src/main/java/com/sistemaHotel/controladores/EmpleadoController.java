package com.sistemaHotel.controladores;

import com.sistemaHotel.modelos.Empleado;
import com.sistemaHotel.modelos.Rol;
import com.sistemaHotel.repositorios.IRolRepository;
import com.sistemaHotel.servicios.interfaces.IEmpleadoService;
import com.sistemaHotel.servicios.interfaces.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private IRolService rolService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
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
    public String create(Empleado empleado, Model model)
    {
        model.addAttribute("roles", rolService.obtenerTodos());
        return "empleado/create";
    }

    @PostMapping("/save")
    public String save(@RequestParam("rol") Integer rol, Empleado empleado, BindingResult result, Model model, RedirectAttributes attributes){
        if(result.hasErrors()){
            model.addAttribute(empleado);
            model.addAttribute("roles", rolService.obtenerTodos());
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "empleado/create";
        }

        String password = passwordEncoder.encode(empleado.getClave());
        Rol perfil = new Rol();
        perfil.setId(rol);

        empleado.setEstado(String.valueOf(1));
        empleado.agregar(perfil);
        empleado.setClave(password);
        empleadoService.createEdit(empleado);
        attributes.addFlashAttribute("msg", "Usuario creado correctamente");
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
        model.addAttribute("roles", rolService.obtenerTodos());
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
