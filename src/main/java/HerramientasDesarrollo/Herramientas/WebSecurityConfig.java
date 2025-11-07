package HerramientasDesarrollo.Herramientas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private CustomAuthenticationSuccessHandler successHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // Deshabilita la protección CSRF (si prefieres usarla puedes eliminar esta línea)
            .authorizeHttpRequests((requests) -> requests
                // Rutas públicas (accesibles sin autenticación)
                .requestMatchers("/", "/index.html", "/login", "/submitForm").permitAll()  // Permite /submitForm

                
              //admin puede ver medicos
                .requestMatchers("/medico/todos").hasAuthority("ADMIN")
                
                .requestMatchers("/medico/eliminar").hasAuthority("ADMIN")
                
                .requestMatchers("/medico/actualizar").hasAuthority("ADMIN")
                
                .requestMatchers("/medico/edicion").hasAuthority("ADMIN")
                
                
                .requestMatchers("/usuarioRoles/todos").hasAuthority("ADMIN")

                
                .requestMatchers("/usuarios/todos").hasAuthority("ADMIN")

    //admin editar usuarios

                .requestMatchers("/usuarios/edicion").hasAuthority("ADMIN")

                
                .requestMatchers("/usuarios/actualizar").hasAuthority("ADMIN")

                
                .requestMatchers("/usuarios/eliminar").hasAuthority("ADMIN")
                
                //admin usuario roles:

                .requestMatchers("/usuariosRoles/edicion").hasAuthority("ADMIN")

                
                .requestMatchers("/usuariosRoles/actualizar").hasAuthority("ADMIN")

                
                .requestMatchers("/usuariosRoles/eliminar").hasAuthority("ADMIN")
                
                


                
                
                
                .requestMatchers("/tramites/buscar").hasAuthority("MEDICO")
                
                .requestMatchers("/tramites/todos").hasAuthority("MEDICO")
                



                
                // Rutas específicas por roles
                .requestMatchers("/MenuAdmin.html").hasAuthority("ADMIN") // Solo ADMIN puede acceder a MenuAdmin.html
                .requestMatchers("/MenuPaciente.html").hasAuthority("PACIENTE") // Solo PACIENTE puede acceder a MenuPaciente.html
                .requestMatchers("/menumedico.html").hasAuthority("MEDICO") // Solo MEDICO puede acceder a menumedico.html

                // Otras rutas según roles
                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                .requestMatchers("/paciente/**").hasAuthority("PACIENTE")
                .requestMatchers("/medico/**").hasAuthority("MEDICO")
                .requestMatchers("/visitante/**").hasAuthority("VISITANTE")

                // Permite POST para rutas específicas (si usas formularios POST)
                .requestMatchers(HttpMethod.POST, "/submitForm").permitAll()  // Permite POST en /submitForm

                // Cualquier otra solicitud debe estar autenticada
                .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                .loginPage("/login")  // Página de login
                .successHandler(successHandler)  // Usar el custom success handler para redirección
                .permitAll()
            )
            .logout((logout) -> logout.permitAll()); // Permitir logout

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }
}