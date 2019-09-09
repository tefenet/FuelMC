package fuelMC.serviceInterfaces;

import java.util.List;

import fuelMC.model.Usuario;

public interface IUsuarioService extends IGenericService<Usuario>{
	public Usuario validarDatos(Usuario userViejo, Usuario datosNuevos);
	public boolean canAddFuelTank(String token);
	public List<Usuario> findAllUsers();
	public boolean userExist(String username);
	public Usuario autenticacion(String username, String clave);
	public Usuario actualizarUsuario(Usuario usuarioNuevo);
}
