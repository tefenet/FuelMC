package fuelMC.serviceInterfaces;

import java.util.List;
import java.util.Set;

import fuelMC.model.ContentVolume;
import fuelMC.model.FuelTank;
import fuelMC.model.Fueling;

public interface IFuelTankService extends IGenericService<FuelTank>{
	public FuelTank validateData(FuelTank oldTank, FuelTank newTank);
	public List<FuelTank> getAll();
	public List<ContentVolume> getHistory(Long id);
	public void delete(long id);
	public boolean fuelTankExist(String nombre);
	public Set<Fueling> getLoads(Long id);
}
