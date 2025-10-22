package HerramientasDesarrollo.Herramientas;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class ReporteErrorClinica {

    @Id
    @GeneratedValue(generator="sec_reporte_error_clinica") 
    @SequenceGenerator(name="sec_reporte_error_clinica", sequenceName="sec_reporte_error_clinica", allocationSize=1)
    private int id;

    private String nombreMedico;
    private String correoElectronico;
    private String fallaClinica;
    private String errorSistema;
    private String faltaImplementacionMedica;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }
    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getFallaClinica() {
        return fallaClinica;
    }
    public void setFallaClinica(String fallaClinica) {
        this.fallaClinica = fallaClinica;
    }

    public String getErrorSistema() {
        return errorSistema;
    }
    public void setErrorSistema(String errorSistema) {
        this.errorSistema = errorSistema;
    }

    public String getFaltaImplementacionMedica() {
        return faltaImplementacionMedica;
    }
    public void setFaltaImplementacionMedica(String faltaImplementacionMedica) {
        this.faltaImplementacionMedica = faltaImplementacionMedica;
    }
}
