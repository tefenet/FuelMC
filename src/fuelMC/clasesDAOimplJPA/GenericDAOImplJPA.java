package fuelMC.clasesDAOimplJPA;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import fuelMC.DAO.GenericDAO;
@Primary
@Repository
public class GenericDAOImplJPA<T> implements GenericDAO<T> {

	protected Class<T> persistentClass;
	private EntityManager entityManager;

	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
	}
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public GenericDAOImplJPA() {
	}

	public GenericDAOImplJPA(Class<T> clase) {
		this.persistentClass = clase;
	}

	
	public T persistir(T entity) {
		this.getEntityManager().persist(entity);
		return entity;
	}

	public T update(T entity) {
		this.getEntityManager().merge(entity);
		return entity;
	}

	
	public void borrar(T entity) {
			this.getEntityManager().remove(entity);		
	}

	public T borrar(Long id) {
		T entity = this.getEntityManager().find(this.getPersistentClass(), id);
		if (entity != null) {
			this.getEntityManager().remove(entity);
		}
		return entity;
	}

	public List<T> recuperarTodos(String columnOrder) {
		Query consulta = this.getEntityManager().createQuery(
				"select e from " + getPersistentClass().getSimpleName() + " e order by e	." + columnOrder);		
		System.out.print("recuperartodos GenericdaoImp \n");
		@SuppressWarnings("unchecked")
		List<T> resultado = (List<T>) consulta.getResultList();
		return resultado;
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	
	public boolean existe(Long id) {
		return null != recuperar(id);
		
	}

	
	public T recuperar(Long id) {
		T objeto = this.getEntityManager().find(getPersistentClass(), id);		
		return objeto;
		
	}
		
}
