package server.session;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import server.entity.Rezervacija;

@Stateless
@Local(RezervacijaDaoLocal.class)
public class RezervacijaDaoBean extends GenericDaoBean<Rezervacija, Integer>
implements RezervacijaDaoLocal{

	@Override
	public List<Rezervacija> getRezervacije() {
		Query q = em.createNamedQuery("getRezervacije");
		@SuppressWarnings("unchecked")
		List<Rezervacija> result = (List<Rezervacija>) q.getResultList();
		if(result.size() == 0){
			System.out.println("------------------------------------------NEEEEEEEEEEEE");
			return null;
			
			
		}
		else{
			return result;
		}
		
	}
	
	public void remove(Rezervacija rez) {
		System.out.println("AAAAAAAAAA");
		em.remove(em.merge(rez));
	}

	@Override
	public List<Rezervacija> getRezervacijeRestorana(Integer id) {
		Query q = em.createNamedQuery("getRezervacijeRestorana");
		q.setParameter("restoranrez", id);
		
		@SuppressWarnings("unchecked")
		List<Rezervacija> result = (List<Rezervacija>) q.getResultList();
		if(result.size() == 0){
			System.out.println("------------------------------------------NEEEEEEEEEEEE");
			return null;
			
			
		}
		else{
			return result;
		}
	}

	@Override
	public List<Rezervacija> getRezervacijeZaSto(Integer id_sto) {
		Query q = em.createNamedQuery("getRezervacijeZaSto");
		q.setParameter("storez", id_sto);
		
		@SuppressWarnings("unchecked")
		List<Rezervacija> result = (List<Rezervacija>) q.getResultList();
		if(result.size() == 0){
			System.out.println("------------------------------------------NEEEEEEEEEEEE");
			return null;
			
			
		}
		else{
			return result;
		}
	}

	@Override
	public List<Rezervacija> getRezervacijeZaGosta(Integer id_gosta) {
		Query q = em.createNamedQuery("getRezervacijeGost");
		q.setParameter("grez", id_gosta);
		
		@SuppressWarnings("unchecked")
		List<Rezervacija> result = (List<Rezervacija>) q.getResultList();
		if(result.size() == 0){
			System.out.println("------------------------------------------NEEEEEEEEEEEE");
			return null;
			
			
		}
		else{
			return result;
		}
	}

}
