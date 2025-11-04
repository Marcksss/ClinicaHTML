package HerramientasDesarrollo.Herramientas;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryReporteMedico extends JpaRepository<ReporteMedico, Integer> {
    List<ReporteMedico> findByDatosPacienteContainingIgnoreCase(String datosPaciente);
}
