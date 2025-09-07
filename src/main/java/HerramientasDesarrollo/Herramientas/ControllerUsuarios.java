package HerramientasDesarrollo.Herramientas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ControllerUsuarios {

    @Autowired
    private RepositoryUsuarios repoUsuarios;

    @GetMapping("/usuarios/nuevo")
    public String nuevoUsuario(Model model) {
        model.addAttribute("usuario", new Usuarios());
        return "usuariosNuevo";
    }

    @PostMapping("/usuarios/insertar")
    public String insertarUsuario(@ModelAttribute Usuarios usuario) {
        repoUsuarios.save(usuario);
        return "redirect:/usuarios/todos";
    }

    @GetMapping("/usuarios/todos")
    public String listarUsuarios(Model model) {
        List<Usuarios> lista = repoUsuarios.findAll();
        model.addAttribute("listaUsuarios", lista);
        return "usuariosTodos";
    }

    @GetMapping("/usuarios/edicion")
    public String editarUsuario(@RequestParam("id") int id, Model model) {
        Usuarios usuario = repoUsuarios.findById(id).orElse(null);
        if (usuario == null) {
            return "redirect:/usuarios/todos";
        }
        model.addAttribute("usuario", usuario);
        return "usuariosEdicion";
    }

    @PostMapping("/usuarios/actualizar")
    public String actualizarUsuario(@ModelAttribute Usuarios usuario) {
        System.out.println("ACTUALIZAR: " + usuario.getId() + " - " + usuario.getNombre());
        repoUsuarios.save(usuario);
        return "redirect:/usuarios/todos";
    }

    @PostMapping("/usuarios/eliminar")
    public String eliminarUsuario(@RequestParam("id") int id) {
        if (repoUsuarios.existsById(id)) {
            repoUsuarios.deleteById(id);
        }
        return "redirect:/usuarios/todos";
    }
}