package fuelMC.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fuelMC.DAO.ContentVolumeDAO;
import fuelMC.DAO.FuelTankDAO;
import fuelMC.model.ContentVolume;
import fuelMC.model.FuelTank;
import fuelMC.model.Fueling;
import fuelMC.serviceInterfaces.IFuelTankService;
@Service
@Transactional
public class FuelTankService extends GenericService<FuelTank> implements IFuelTankService {
	
	@Autowired
	ContentVolumeDAO historyService;
	@Autowired
	FuelTankDAO ftdao;
	
	public FuelTank validateData(FuelTank oldTank, FuelTank newTank) {
		if (newTank.getCapacity() != null) {
			oldTank.setCapacity(newTank.getCapacity());
		}
		if (newTank.getContent() != null) {
			oldTank.setContent(newTank.getContent());
		}
		if (newTank.getName() != null) {
			oldTank.setName(newTank.getName());
		}
		return oldTank;
	}

	public List<ContentVolume> getHistory(Long id) {
		return historyService.getHistory(id);
		
	}
	
	public List<FuelTank> getAll() {
		return ftdao.recuperarTodos("id");
	}

	@Override
	public void delete(long id) {
		ftdao.borrar(id);		
	}
	public boolean fuelTankExist(String nombre) {
		return ftdao.fuelTankExist(nombre);
	}
	public Set<Fueling> getLoads(Long id){
		return ftdao.getLoads(id);
	}
}
