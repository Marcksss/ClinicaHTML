package HerramientasDesarrollo.Herramientas;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.SequenceGenerator;

@Entity
public class UsuariosRoles {

    @Id
    @GeneratedValue(generator = "sec_usuarios_roles")
    @SequenceGenerator(name = "sec_usuarios_roles", sequenceName = "sec_usuarios_roles", allocationSize = 1)
    private int id;

    private int idUsuario;

    private int idRol;

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
}