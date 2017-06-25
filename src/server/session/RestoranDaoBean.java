package server.session;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import server.entity.Restoran;

@Stateless
@Local(RestoranDaoLocal.class)
public class RestoranDaoBean extends GenericDaoBean<Restoran, Integer>
implements RestoranDaoLocal  {

	@Override
	public List<Restoran> showRestaurants() {
		Query q = em.createNamedQuery("showRestaurants");
		@SuppressWarnings("unchecked")
		List<Restoran> result = (List<Restoran>) q.getResultList();
		if(result.size() == 0){
			System.out.println("------------------------------------------NEEEEEEEEEEEE");
			return null;
			
			
		}
		else{
			return result;
		}
		
	}

}
