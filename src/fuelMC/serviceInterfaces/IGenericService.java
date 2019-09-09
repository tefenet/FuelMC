package fuelMC.serviceInterfaces;

import java.util.List;

public interface IGenericService<T> {
	
	public T update(T entity);

	public void borrar(T entity);

	public T borrar(Long id);

	public boolean existe(Long id);

	public T persistir(T entity);

	public T recuperar(Long id);
	
	public List <T> recuperarTodos(String s);
}
