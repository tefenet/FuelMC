package fuelMC.model;
import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


enum Profile {
    ADMIN,
    SENSOR,
    CLIENT,    
    OPERATOR;
}

@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="usuario_id")
	private Long id;
	private String nombre;
	private String contraseña;
	private String apellido;
	@Enumerated(EnumType.ORDINAL)    
	private Profile profile;
	private String username;
	private String email;	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setProfile(int p) {
		switch(p) {
			case 0: this.profile = Profile.ADMIN;
					break;
			case 1: this.profile = Profile.CLIENT;
					break;
			case 2: this.profile = Profile.OPERATOR;
					break;
			case 3: this.profile = Profile.SENSOR;
					break;
			default:this.profile = Profile.CLIENT;
					break; 
		}
	}
	public int getProfile() {
		return Arrays.asList(Profile.values()).indexOf(this.profile);
	}
	public Boolean isAdmin() {
		return (profile == Profile.ADMIN);
	}
	
}
