package server.session;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import server.entity.Jelo;

@Stateless
@Local(JeloDaoLocal.class)
public class JeloDaoBean extends GenericDaoBean<Jelo, Integer>
implements JeloDaoLocal {

	@Override
	public List<Jelo> getJelaJelovnika(Integer id_jelovnika) {
		Query q = em.createNamedQuery("getJelaJelovnika");
		q.setParameter("jelovnik_id", id_jelovnika);
		
		@SuppressWarnings("unchecked")
		List<Jelo> result = (List<Jelo>) q.getResultList();
		if(result.size() == 0){
			System.out.println("------------------------------------------NEEEEEEEEEEEE");
			return null;
			
			
		}
		else{
			return result;
		}
	}

}
