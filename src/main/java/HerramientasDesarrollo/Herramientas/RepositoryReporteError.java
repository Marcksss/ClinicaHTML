package HerramientasDesarrollo.Herramientas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryReporteError extends JpaRepository<ReporteError, Integer> {

    public List<ReporteError> findByErrorSistemaContainingIgnoreCase(String errorSistema);
}
