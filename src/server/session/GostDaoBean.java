package server.session;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import server.entity.Gost;

@Stateless
@Local(GostDaoLocal.class)
public class GostDaoBean extends GenericDaoBean<Gost, Integer>
implements GostDaoLocal{

	public List<Gost> showGosti(){
		Query q = em.createNamedQuery("showGosti");
		@SuppressWarnings("unchecked")
		List<Gost> result = (List<Gost>) q.getResultList();
		if(result.size() == 0){
			System.out.println("------------------------------------------NEEEEEEEEEEEE");
			return null;
			
			
		}
		else{
			return result;
		}
		
		
		
	}




}
