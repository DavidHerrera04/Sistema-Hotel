package com.sistemaHotel.controladores;

import com.sistemaHotel.modelos.Cliente;
import com.sistemaHotel.modelos.Nacionalidad;
import com.sistemaHotel.servicios.interfaces.IClienteService;
import com.sistemaHotel.servicios.interfaces.INacionalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private IClienteService clienteService;

    @Autowired
    private INacionalidadService nacionalidadService;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1) - 1; //si no esta seteado se asigna 0
        int pageSize = size.orElse(5); // tamaño de la pagina, se asigna 5
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<Cliente> clientes = clienteService.buscarTodosPaginados(pageable);
        model.addAttribute("clientes", clientes);

        int totalPage = clientes.getTotalPages();
        if (totalPage > 0)
        {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage)
                    .boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);


        }
        return "cliente/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("nacionalidades", nacionalidadService.obtenerTodos());
        return "cliente/create";
    }

    @PostMapping("/save")
    public String save(@RequestParam Integer nacionalidadId, @RequestParam String nombre, @RequestParam String apellido,
                       @RequestParam String direccion, @RequestParam String documento,
                       @RequestParam String telefono,
                       RedirectAttributes attributes){
        Nacionalidad nacionalidad = nacionalidadService.buscarPorId(nacionalidadId).get();

        if(nacionalidad != null){
            Cliente cliente = new Cliente();
            cliente.setNacionalidad(nacionalidad);
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setDireccion(direccion);
            cliente.setDocumento(documento);
            cliente.setTelefono(telefono);

            clienteService.createOEditar(cliente);
            attributes.addFlashAttribute("msg", "Cliente creada correctamente");
        }

        return "redirect:/clientes";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model) {
        Cliente cliente = clienteService.buscarPorId(id);
        model.addAttribute("cliente", cliente);
        return "cliente/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Cliente cliente = clienteService.buscarPorId(id);
        model.addAttribute("nacionalidades", nacionalidadService.obtenerTodos());
        model.addAttribute("cliente", cliente);
        return "cliente/edit";
    }

    @PostMapping("/update")
    public String update(@RequestParam Integer id, @RequestParam Integer nacionalidadId, @RequestParam String nombre, @RequestParam String apellido,
                       @RequestParam String direccion, @RequestParam String documento,
                       @RequestParam String telefono, RedirectAttributes attributes){
        Nacionalidad nacionalidad = nacionalidadService.buscarPorId(nacionalidadId).get();

        if(nacionalidad != null){
            Cliente cliente = new Cliente();
            cliente.setId(id);
            cliente.setNacionalidad(nacionalidad);
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setDireccion(direccion);
            cliente.setDocumento(documento);
            cliente.setTelefono(telefono);

            clienteService.createOEditar(cliente);
            attributes.addFlashAttribute("msg", "Cliente modificado correctamente");
        }

        return "redirect:/clientes";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model) {
        Cliente cliente = clienteService.buscarPorId(id);
        model.addAttribute("cliente", cliente);
        return "cliente/delete";
    }

    @PostMapping("/delete")
    public String delete(Cliente cliente, RedirectAttributes attributes) {
        clienteService.eliminarPorId(cliente.getId());
        attributes.addFlashAttribute("msg", "cliente eliminado correctamente");
        return "redirect:/clientes";
    }
}
