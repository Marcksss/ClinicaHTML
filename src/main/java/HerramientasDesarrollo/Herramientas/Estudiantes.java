package HerramientasDesarrollo.Herramientas;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Estudiantes {

	@Id
	@GeneratedValue(generator="sec_estudiantes") 
	@SequenceGenerator(name="sec_estudiantes",sequenceName="sec_estudiantes", allocationSize=1)
	private int id;
	
	private String nombre;
	private String email;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
