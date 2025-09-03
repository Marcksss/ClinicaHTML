package HerramientasDesarrollo.Herramientas;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
    Usuarios findByNombre(String nombre);  // <--- AGREGAR ESTE MÉTODO
}
