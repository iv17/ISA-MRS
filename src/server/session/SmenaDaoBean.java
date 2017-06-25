package server.session;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import server.entity.Smena;

@Stateless
@Local(SmenaDaoLocal.class)
public class SmenaDaoBean  extends GenericDaoBean<Smena, Integer>
implements SmenaDaoLocal {

	
	
	public Smena findSmena(Date smena_od, Date smena_do) {
		Query q = em
				.createNamedQuery("findSmena");
		q.setParameter("smena_od", smena_od);
		q.setParameter("smena_do", smena_do);
		
		List<Smena> result = q.getResultList();
		if(result.size() == 0){
			
			return null;
			
			
		}
		else{
			return result.get(0);
		}
	}

	
}
