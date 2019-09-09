package fuelMC.DAO;

import java.util.List;

import javax.persistence.EntityManager;

public interface GenericDAO<T> {
	public void setEntityManager(EntityManager em);
	public EntityManager getEntityManager();
	public T update(T entity);

	public void borrar(T entity);

	public T borrar(Long id);

	public boolean existe(Long id);

	public T persistir(T entity);

	public T recuperar(Long id);
	
	public List <T> recuperarTodos(String s);
}
