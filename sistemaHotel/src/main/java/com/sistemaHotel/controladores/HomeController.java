package com.sistemaHotel.controladores;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String index(){
        return "home/Principal";
    }

    @GetMapping("/login")
    public String mostrarLogin(HttpServletRequest request){
        // Verificar si hay un parámetro de error en la solicitud
        String errorMessage = request.getParameter("error");
        if ("true".equals(errorMessage)) {
            request.setAttribute("errorMessage", "Credenciales incorrectas. Intenta nuevamente.");
        }

        return "home/formLogin";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, null, null);
        return "redirect:/";
    }
}
