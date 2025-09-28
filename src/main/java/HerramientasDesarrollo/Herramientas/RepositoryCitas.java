package HerramientasDesarrollo.Herramientas;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCitas extends JpaRepository<CitaMedica, Integer> {
    List<CitaMedica> findByNombreContainingIgnoreCase(String nombre);
}
