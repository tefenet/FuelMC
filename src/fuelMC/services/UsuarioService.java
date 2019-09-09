package fuelMC.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fuelMC.DAO.UsuarioDAO;
import fuelMC.model.FuelTank;
import fuelMC.model.Usuario;
import fuelMC.serviceInterfaces.IUsuarioService;

@Service
@Transactional
public class UsuarioService extends GenericService<Usuario> implements IUsuarioService {

	
	@Autowired
	UsuarioDAO usuarioDAO;
	@Autowired
	ApplicationService applicationService;
	@Autowired
	TokenService tokenservice;

	public Usuario validarDatos(Usuario userViejo, Usuario datosNuevos) {
		if (datosNuevos.getApellido() != null) {
			userViejo.setApellido(datosNuevos.getApellido());
		}
		if (datosNuevos.getNombre() != null) {
			userViejo.setNombre(datosNuevos.getNombre());
		}
		if (datosNuevos.getContraseña() != null) {
			userViejo.setContraseña(datosNuevos.getContraseña());
		}
		if (datosNuevos.getEmail() != null) {
			userViejo.setEmail(datosNuevos.getEmail());
		}
		if (datosNuevos.getUsername() != null) {
			userViejo.setUsername(datosNuevos.getUsername());				
		}
		return userViejo;
	}

	public Boolean esAdmin(Long id) {
		return usuarioDAO.isAdmin(id);
	}

	public boolean canAddFuelTank(String token) {
		return tokenservice.validateToken(token) && esAdmin(tokenservice.getId(token));
	}

	@Override
	public List<Usuario> findAllUsers() {
		return (List<Usuario>) usuarioDAO.findAllUsers();
	}

	@Override
	public boolean userExist(String username) {
		return usuarioDAO.userExist(username);
	}

	@Override
	public Usuario autenticacion(String username, String clave) {
		return usuarioDAO.autenticacion(username, clave);
	}

	@Override
	public Usuario actualizarUsuario(Usuario usuarioNuevo) {
		return usuarioDAO.actualizarUsuario(usuarioNuevo);
	}
}
