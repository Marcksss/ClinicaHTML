package HerramientasDesarrollo.Herramientas;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class ReporteMedico {

    @Id
    @GeneratedValue(generator="sec_reportes_medicos")
    @SequenceGenerator(name="sec_reportes_medicos", sequenceName="sec_reportes_medicos", allocationSize=1)
    private int id;

    private String institucion;           
    private String datosPaciente;         
    private String fechaLugar;            
    private String motivoConsulta;        
    private String antecedentes;          
    private String examenFisico;          
    private String examenesAuxiliares;   
    private String diagnostico;          
    private String tratamiento;           
    private String evolucion;            
    private String recomendaciones;       
    private String conclusion;            
    private String firmaMedico;          

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getInstitucion() { return institucion; }
    public void setInstitucion(String institucion) { this.institucion = institucion; }

    public String getDatosPaciente() { return datosPaciente; }
    public void setDatosPaciente(String datosPaciente) { this.datosPaciente = datosPaciente; }

    public String getFechaLugar() { return fechaLugar; }
    public void setFechaLugar(String fechaLugar) { this.fechaLugar = fechaLugar; }

    public String getMotivoConsulta() { return motivoConsulta; }
    public void setMotivoConsulta(String motivoConsulta) { this.motivoConsulta = motivoConsulta; }

    public String getAntecedentes() { return antecedentes; }
    public void setAntecedentes(String antecedentes) { this.antecedentes = antecedentes; }

    public String getExamenFisico() { return examenFisico; }
    public void setExamenFisico(String examenFisico) { this.examenFisico = examenFisico; }

    public String getExamenesAuxiliares() { return examenesAuxiliares; }
    public void setExamenesAuxiliares(String examenesAuxiliares) { this.examenesAuxiliares = examenesAuxiliares; }

    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }

    public String getTratamiento() { return tratamiento; }
    public void setTratamiento(String tratamiento) { this.tratamiento = tratamiento; }

    public String getEvolucion() { return evolucion; }
    public void setEvolucion(String evolucion) { this.evolucion = evolucion; }

    public String getRecomendaciones() { return recomendaciones; }
    public void setRecomendaciones(String recomendaciones) { this.recomendaciones = recomendaciones; }

    public String getConclusion() { return conclusion; }
    public void setConclusion(String conclusion) { this.conclusion = conclusion; }

    public String getFirmaMedico() { return firmaMedico; }
    public void setFirmaMedico(String firmaMedico) { this.firmaMedico = firmaMedico; }
}
