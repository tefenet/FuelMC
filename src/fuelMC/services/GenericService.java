package fuelMC.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import fuelMC.DAO.GenericDAO;
import fuelMC.clasesDAOimplJPA.GenericDAOImplJPA;
import fuelMC.serviceInterfaces.IGenericService;
@Service
@Transactional 
public class GenericService<T> implements IGenericService<T> {
		
	@Autowired
	GenericDAO<T> gDao;	
	
	
	public GenericService() {}
	
	@Override
	public T update(T entity) {
		return gDao.update(entity);
	}
	@Override
	public void borrar(T entity) {
	 gDao.borrar(entity);		
	}
	@Override
	public T borrar(Long id) {
		return gDao.borrar(id);
	}
	@Override
	public T persistir(T entity) {
		return gDao.persistir(entity);
	}
	@Override
	public List<T> recuperarTodos(String s) {
		return gDao.recuperarTodos(s);
	}	
	public boolean existe(Long id) {
		return gDao.existe(id);		
	}
	
	public T recuperar(Long id) {
		return gDao.recuperar(id);		
	}

}
