package fuelMC.services;

import org.springframework.stereotype.Service;

@Service
public class ApplicationService {
	
	public Boolean validarToken(String cadena, long id) {
		String token= this.getToken(cadena);
		Long id_usuario= this.getId(cadena);
		return(this.verificarToken(token) && id_usuario.equals(id));
	}
	
	private String getToken(String cadena) {
		return cadena.substring(cadena.length()-6);
	}
	
	public Boolean verificarToken(String cadena) {
		return this.getToken(cadena).equals("123456");
	}
	
	public Long getId(String cadena) {
		return Long.parseLong(cadena.substring(0, cadena.length()-6));
	}
	
	

}
