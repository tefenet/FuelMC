package fuelMC.clasesDAOimplJPA;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import fuelMC.DAO.ContentVolumeDAO;
import fuelMC.model.ContentVolume;
@Repository
public class ContentVolumeDAOImplJPA extends GenericDAOImplJPA<ContentVolume> implements ContentVolumeDAO {

	public ContentVolumeDAOImplJPA(){
		super(ContentVolume.class);
	}

	@Override
	public List<ContentVolume> getHistory(Long id) {
		String consulta = "select c from ContentVolume c where fuelTank_ID = :id";
		Query query = this.getEntityManager().createQuery(consulta);
		query.setParameter("id", id);
		return query.getResultList();
	}

}
