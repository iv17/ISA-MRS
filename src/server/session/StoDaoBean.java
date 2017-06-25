package server.session;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import server.entity.Sto;

@Stateless
@Local(StoDaoLocal.class)
public class StoDaoBean extends GenericDaoBean<Sto, Integer>
implements StoDaoLocal {

	@Override
	public List<Sto> getTablesForRestaurant(Integer id) {
		Query q = em.createNamedQuery("getTablesForRestaurant");
		q.setParameter("restoranrez", id);
		
		@SuppressWarnings("unchecked")
		List<Sto> result = (List<Sto>) q.getResultList();
		if(result.size() == 0){
			System.out.println("------------------------------------------NEEEEEEEEEEEE");
			return null;
			
			
		}
		else{
			return result;
		}
	}

}
