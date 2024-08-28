

package com.sistemaHotel.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity {
    @Bean
    public UserDetailsManager customUsers(DataSource dataSource){
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.setUsersByUsernameQuery("select login, clave, estado from empleados where login = ?");
        users.setAuthoritiesByUsernameQuery("select u.login, r.nombre from empleado_rol ur " +
                "inner join empleados u on u.id = ur.empleado_id " +
                "inner join roles r on r.id = ur.rol_id " +
                "where u.login = ?");

        return users;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(authorize -> authorize
                        // aperturar el acceso a los recursos estáticos
                        .requestMatchers("/assets/**", "/css/**", "/js/**", "/lib/**", "/mail/**", "/img/**").permitAll()
                        // las vistas públicas no requieren autenticación
                        .requestMatchers("/", "/privacy", "/terms").permitAll()

                        // Asignar permisos a URLs por ROLES
                        .requestMatchers("/estado/**").hasAnyAuthority("admin")
                        .requestMatchers("/empleados/**").hasAnyAuthority("admin")
                        .requestMatchers("/reservaciones/**").hasAnyAuthority("admin", "registrador")
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true) // Redirige a la página de inicio al éxito
                        .failureUrl("/login?error=true") // Redirige a la página de inicio de sesión con mensaje de error en caso de fallo
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll());

        // todas las demás vistas requieren autenticación
        http.formLogin(form -> form.loginPage("/login").permitAll());

        return http.build();
    }
}
