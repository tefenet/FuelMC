package fuelMC.DAO;


import java.util.Set;

import fuelMC.model.FuelTank;
import fuelMC.model.Fueling;

public interface FuelTankDAO extends GenericDAO<FuelTank> {

    public boolean fuelTankExist(String name);
    public Set<Fueling> getLoads(Long id);
}
