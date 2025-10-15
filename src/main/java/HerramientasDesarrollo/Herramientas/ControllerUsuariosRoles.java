package HerramientasDesarrollo.Herramientas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControllerUsuariosRoles {

    @Autowired
    private UsuarioRolesRepository repoUsuariosRoles;

    // Mostrar formulario para registrar una nueva relación usuario-rol
    @GetMapping("/usuariosRoles/nuevo")
    public String nuevoUsuariosRoles(Model model) {
        model.addAttribute("usuariosRoles", new UsuariosRoles());
        return "usuariosRolesNuevo";
    }

    // Insertar una nueva relación usuario-rol
    @PostMapping("/usuariosRoles/insertar")
    public String insertarUsuariosRoles(@ModelAttribute UsuariosRoles usuariosRoles) {
        repoUsuariosRoles.save(usuariosRoles);
        return "redirect:/usuariosRoles/todos";
    }

    // Listar todas las relaciones usuario-rol
    @GetMapping("/usuariosRoles/todos")
    public String listarUsuariosRoles(Model model) {
        List<UsuariosRoles> lista = repoUsuariosRoles.findAll();
        model.addAttribute("listaUsuariosRoles", lista);
        return "usuariosRolesTodos";
    }

    // Mostrar formulario para editar una relación usuario-rol existente
    @GetMapping("/usuariosRoles/edicion")
    public String editarUsuariosRoles(@RequestParam("id") int id, Model model) {
        UsuariosRoles usuariosRoles = repoUsuariosRoles.findById(id).orElse(null);
        if (usuariosRoles == null) {
            return "redirect:/usuariosRoles/todos"; // Si no existe, vuelve al listado
        }
        model.addAttribute("usuariosRoles", usuariosRoles);
        return "usuariosRolesEdicion";
    }

    // Actualizar una relación usuario-rol
    @PostMapping("/usuariosRoles/actualizar")
    public String actualizarUsuariosRoles(@ModelAttribute UsuariosRoles usuariosRoles) {
        System.out.println("ACTUALIZAR: " + usuariosRoles.getId());
        repoUsuariosRoles.save(usuariosRoles);
        return "redirect:/usuariosRoles/todos";
    }

    // Eliminar una relación usuario-rol
    @PostMapping("/usuariosRoles/eliminar")
    public String eliminarUsuariosRoles(@RequestParam("id") int id) {
        if (repoUsuariosRoles.existsById(id)) {
            repoUsuariosRoles.deleteById(id);
        }
        return "redirect:/usuariosRoles/todos";
    }
}
