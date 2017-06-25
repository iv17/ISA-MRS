package server.session;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import server.entity.Prijatelj;

@Stateless
@Local(PrijateljDaoLocal.class)
public class PrijateljDaoBean extends GenericDaoBean<Prijatelj, Integer>
implements PrijateljDaoLocal{

	public List<Prijatelj> findFriends(Integer id, String prihvacen) {
		// TODO Auto-generated method stub
		Query q = em
				.createNamedQuery("findFriends");
		
		q.setParameter("ima_prijatelja", id);
		q.setParameter("prihvacenPrijatelj", prihvacen);
		@SuppressWarnings("unchecked")
		List<Prijatelj> result = (List<Prijatelj>) q.getResultList();
		if(result.size() == 0){
			System.out.println("------------------------------------------NEEEEEEEEEEEE");
			return result;
			
			
		}
		else{
			return result;
		}
		
	}

	@Override
	public List<Prijatelj> removeFriend(Integer id, Integer idb) {
		// TODO Auto-generated method stub
		Query q = em
				.createNamedQuery("removeFriend");
		q.setParameter("ima_prijatelja", id);
		q.setParameter("zaBrisanje", idb);
		@SuppressWarnings("unchecked")
		List<Prijatelj> result = (List<Prijatelj>) q.getResultList();
		if(result.size() == 0){
			System.out.println("------------------------------------------NEEEEEEEEEEEE");
			return null;
			
			
		}
		else{
			return result;
		}
	}
	
	public void remove(Prijatelj v) {
		System.out.println("AAAAAAAAAA");
		em.remove(em.merge(v));
	}

	@Override
	public List<Prijatelj> findAll(Integer id) {
		// TODO Auto-generated method stub
		Query q = em
				.createNamedQuery("findAll");
		
		q.setParameter("ima_prijatelja", id);
		@SuppressWarnings("unchecked")
		List<Prijatelj> result = (List<Prijatelj>) q.getResultList();
		if(result.size() == 0){
			System.out.println("------------------------------------------NEEEEEEEEEEEE");
			return result;
			
			
		}
		else{
			return result;
		}
	}

	@Override
	public List<Prijatelj> findRequests(Integer id, String prihvacen) {
		// TODO Auto-generated method stub
		Query q = em
				.createNamedQuery("findRequests");
		
		q.setParameter("zahtevp", id);
		q.setParameter("prihvacenPrijatelj", prihvacen);
		@SuppressWarnings("unchecked")
		List<Prijatelj> result = (List<Prijatelj>) q.getResultList();
		if(result.size() == 0){
			System.out.println("------------------------------------------NEEEEEEEEEEEE");
			return result;
			
			
		}
		else{
			return result;
		}
	}

	

}
