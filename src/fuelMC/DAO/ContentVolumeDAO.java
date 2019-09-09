package fuelMC.DAO;

import java.util.List;

import fuelMC.model.ContentVolume;

public interface ContentVolumeDAO extends GenericDAO<ContentVolume> {

		public List<ContentVolume> getHistory(Long id);
}
