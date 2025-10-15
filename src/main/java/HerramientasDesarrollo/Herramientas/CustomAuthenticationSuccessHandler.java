package HerramientasDesarrollo.Herramientas;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority authority : authorities) {
            String role = authority.getAuthority();

            // Redirección según el rol
            switch (role) {
                case "ADMIN":
                    response.sendRedirect("/MenuAdmin.html"); // Redirección al archivo HTML de ADMIN
                    return;
                case "PACIENTE":
                    response.sendRedirect("/MenuPaciente.html"); // Redirección al archivo HTML de PACIENTE
                    return;
                case "MEDICO":
                    response.sendRedirect("/menumedico.html"); // Redirección al archivo HTML de MEDICO
                    return;
                case "VISITANTE":
                    response.sendRedirect("/visitante.html"); // Redirección al archivo HTML de VISITANTE (si aplica)
                    return;
            }
        }

        // Redirección por defecto si no coincide ningún rol
        response.sendRedirect("/index.html");
    }
}