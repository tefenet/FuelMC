package fuelMC.clasesDAOimplJPA;

import org.springframework.stereotype.Repository;

import fuelMC.DAO.FuelingDAO;
import fuelMC.model.Fueling;
@Repository
public class FuelingDAOImplJPA extends GenericDAOImplJPA<Fueling> implements FuelingDAO {
	
	public FuelingDAOImplJPA() {
		super(Fueling.class);
	}

	
}
