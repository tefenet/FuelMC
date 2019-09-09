package fuelMC.clasesDAOimplJPA;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import fuelMC.DAO.UsuarioDAO;
import fuelMC.clasesDAOimplJPA.UsuarioDAOImplJPA;
import fuelMC.model.Usuario;
@Repository
public class UsuarioDAOImplJPA extends GenericDAOImplJPA<Usuario> implements UsuarioDAO{
	
	public UsuarioDAOImplJPA() {
		super(Usuario.class);
	}
	public Usuario recuperarPorUsername(String nombre) {
		
		String consulta= "select u from Usuario u where username = :nom";
		Query query= this.getEntityManager().createQuery(consulta);
		query.setParameter("nom", nombre); 
		try{
			Usuario resultado =(Usuario) query.getSingleResult();
			return resultado;
		}
		catch(Exception e){
			return null;
		}
	}
	
	public List<?> findAllUsers(){
		String consulta = "select u from Usuario u";
		return (List<?>) this.getEntityManager().createQuery(consulta).getResultList();
		
	}
	
	
	public boolean userExist(String nombre) {
		return null != this.recuperarPorUsername(nombre);
	}
	
	public Usuario autenticacion(String nombre, String pass) {
		Usuario u= this.recuperarPorUsername(nombre);
		if(u != null) {
			if (u.getContraseña().equals(pass)) {
				return u;
			}	
			else return null;
		}
		else {
			return null;
		}
	}
	
	public Usuario actualizarUsuario(Usuario userNuevo) {
		this.getEntityManager().merge(userNuevo);
		return userNuevo;
	}
	
	public Boolean isAdmin(Long id) {		
		Usuario user = this.recuperar(id);
		return user.isAdmin();
	}

}	
