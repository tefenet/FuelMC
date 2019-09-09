package fuelMC.DAO;

import java.util.List;

import fuelMC.model.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario>{
	
	public Usuario recuperarPorUsername(String nombre);
	
	public List<?> findAllUsers();
	
	public boolean userExist(String nombre);
	
	public Usuario autenticacion(String nombre, String pass);
	
	public Usuario actualizarUsuario(Usuario userViejo);
	
	public Boolean isAdmin(Long id);
}
