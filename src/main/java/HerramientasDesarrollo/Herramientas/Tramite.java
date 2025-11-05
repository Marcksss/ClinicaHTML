package HerramientasDesarrollo.Herramientas;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Tramite {

    @Id
    @GeneratedValue(generator="sec_tramite")
    @SequenceGenerator(name="sec_tramite", sequenceName="sec_tramite", allocationSize=1)
    private int id;

    private String nombreAtendido;
    private String apellidoAtendido;
    private String dniAtendido;
    private String telefonoAtendido;

    private String nombreDoctor;
    private String apellidoDoctor;
    private String especialidadDoctor;
    private String consultorioDoctor;

    private String fechaHora;  // Entrada de texto para fecha y hora

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombreAtendido() { return nombreAtendido; }
    public void setNombreAtendido(String nombreAtendido) { this.nombreAtendido = nombreAtendido; }

    public String getApellidoAtendido() { return apellidoAtendido; }
    public void setApellidoAtendido(String apellidoAtendido) { this.apellidoAtendido = apellidoAtendido; }

    public String getDniAtendido() { return dniAtendido; }
    public void setDniAtendido(String dniAtendido) { this.dniAtendido = dniAtendido; }

    public String getTelefonoAtendido() { return telefonoAtendido; }
    public void setTelefonoAtendido(String telefonoAtendido) { this.telefonoAtendido = telefonoAtendido; }

    public String getNombreDoctor() { return nombreDoctor; }
    public void setNombreDoctor(String nombreDoctor) { this.nombreDoctor = nombreDoctor; }

    public String getApellidoDoctor() { return apellidoDoctor; }
    public void setApellidoDoctor(String apellidoDoctor) { this.apellidoDoctor = apellidoDoctor; }

    public String getEspecialidadDoctor() { return especialidadDoctor; }
    public void setEspecialidadDoctor(String especialidadDoctor) { this.especialidadDoctor = especialidadDoctor; }

    public String getConsultorioDoctor() { return consultorioDoctor; }
    public void setConsultorioDoctor(String consultorioDoctor) { this.consultorioDoctor = consultorioDoctor; }

    public String getFechaHora() { return fechaHora; }
    public void setFechaHora(String fechaHora) { this.fechaHora = fechaHora; }
}
