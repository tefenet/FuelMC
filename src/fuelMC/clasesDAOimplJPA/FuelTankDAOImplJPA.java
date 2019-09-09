package fuelMC.clasesDAOimplJPA;

import java.util.List;
import java.util.Set;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import fuelMC.DAO.FuelTankDAO;
import fuelMC.model.ContentVolume;
import fuelMC.model.FuelTank;
import fuelMC.model.Fueling;

@Repository
public class FuelTankDAOImplJPA extends GenericDAOImplJPA<FuelTank> implements FuelTankDAO {

	public FuelTankDAOImplJPA() {
		super(FuelTank.class);
	}

	public boolean fuelTankExist(String nombre) {
		String consulta = "select ft from FuelTank ft "+						
						"where ft.name = :nom";
		Query query = this.getEntityManager().createQuery(consulta);
		query.setParameter("nom", nombre);
		try {
			return query.getSingleResult() != null;
		} catch (Exception e) {
			return false;
		}
	}
//	@Override
//	public List<FuelTank> recuperarTodos(String columnOrder) {
//		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
//		CriteriaQuery<FuelTank> query = cb.createQuery(FuelTank.class);
//		Root<FuelTank> root = query.from(FuelTank.class);
//		query.select(root);
//		Fetch<FuelTank,Fueling> join= root.fetch("fuelingsDone");
//		root.fetch("fuelingsReceived");
//		root.fetch("history");
//		query.where(cb.equal(root.get("id"), id));
//		Query consulta = this.getEntityManager().createQuery(
//				"select ft from FuelTank ft " +
//				"inner join fetch ft.fuelingsDone " +
//				"inner join fetch ft.fuelingsReceived "+				
//				"order by ft	." + columnOrder,FuelTank.class);		
//		System.out.print("recuperartodos FTdaoImp \n");
//		return (List<FuelTank>) this.getEntityManager().createQuery(query).getResultList();
////		@SuppressWarnings("unchecked")
////		List<FuelTank> resultado = (List<FuelTank>) consulta.getResultList();
////		return resultado;
//	}
	public Set<Fueling> getLoads(Long id){
		String consulta = "select c from Fuelings c where destination_ID = :id";
		Query query = this.getEntityManager().createQuery(consulta);
		query.setParameter("id", id);
		return (Set<Fueling>) query.getResultList();		
	}
	
}
