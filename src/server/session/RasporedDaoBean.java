package server.session;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import server.entity.RasporedStolova;

@Stateless
@Local(RasporedDaoLocal.class)
public class RasporedDaoBean extends GenericDaoBean<RasporedStolova, Integer>
implements RasporedDaoLocal {

	@Override
	public List<RasporedStolova> showRestaurants(Integer res_id) {
		Query q = em
				.createNamedQuery("getRasporedZaRestoran");
		q.setParameter("res_id", res_id);
		@SuppressWarnings("unchecked")
		List<RasporedStolova> result = q.getResultList();
		//System.out.println("--------------" + result.get(0));
		if(result.size() == 0){
			System.out.println("------------------------------------------NEEEEEEEEEEEE");
			return null;
			
			
		}
		else{
			return result;
		}
	}

}
