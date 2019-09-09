package fuelMC.clasesDAOimplJPA;

import org.springframework.stereotype.Repository;

import fuelMC.DAO.VehicleDAO;
import fuelMC.model.Vehicle;
@Repository
public class VehicleDAOImplJPA extends GenericDAOImplJPA<Vehicle> implements VehicleDAO {

	public VehicleDAOImplJPA() {
		super(Vehicle.class);
	}

}
