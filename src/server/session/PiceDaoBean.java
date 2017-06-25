package server.session;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import server.entity.Pice;

@Stateless
@Local(PiceDaoLocal.class)
public class PiceDaoBean extends GenericDaoBean<Pice, Integer>
implements PiceDaoLocal {

	@Override
	public List<Pice> getPicaKarte(Integer id_karte) {
		Query q = em.createNamedQuery("getPicaKarte");
		q.setParameter("karta_id", id_karte);
		
		@SuppressWarnings("unchecked")
		List<Pice> result = (List<Pice>) q.getResultList();
		if(result.size() == 0){
			System.out.println("------------------------------------------NEEEEEEEEEEEE");
			return null;
			
			
		}
		else{
			System.out.println("AJDEEEEEEEEEEE");
			return result;
		}
	}

}
