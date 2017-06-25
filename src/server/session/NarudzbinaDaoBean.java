package server.session;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import server.entity.Narudzbina;

@Stateless
@Local(NarudzbinaDaoLocal.class)
public class NarudzbinaDaoBean extends GenericDaoBean<Narudzbina, Integer>
implements NarudzbinaDaoLocal {

	@Override
	public List<Narudzbina> getNarudzbineZaGostaZaRezervaciju(Integer id_rez, Integer id_gost) {
		Query q = em.createNamedQuery("getNarudzbineZaGostaZaRezervaciju");
		q.setParameter("gost_rez", id_rez);
		q.setParameter("gost_id", id_gost);
		
		
		@SuppressWarnings("unchecked")
		List<Narudzbina> result = (List<Narudzbina>) q.getResultList();
		if(result.size() == 0){
			System.out.println("------------------------------------------NEEEEEEEEEEEE");
			return null;
			
			
		}
		else{
			return result;
		}
	}

	@Override
	public List<Narudzbina> getRezervacijeZaGosta(Integer id_gost) {
		Query q = em.createNamedQuery("getRezervacijeZaGosta");
		q.setParameter("gost_za_rez", id_gost);
		
		@SuppressWarnings("unchecked")
		List<Narudzbina> result = (List<Narudzbina>) q.getResultList();
		if(result.size() == 0){
			System.out.println("------------------------------------------NEEEEEEEEEEEE");
			return null;
			
			
		}
		else{
			return result;
		}
	}
	
	public void remove(Narudzbina nar) {
		System.out.println("AAAAAAAAAA");
		em.remove(em.merge(nar));
	}

	@Override
	public List<Narudzbina> getPoseteZaGosta(Integer id_gost) {
		Query q = em.createNamedQuery("getPoseteZaGosta");
		q.setParameter("gost_za_rez", id_gost);
		
		@SuppressWarnings("unchecked")
		List<Narudzbina> result = (List<Narudzbina>) q.getResultList();
		if(result.size() == 0){
			System.out.println("------------------------------------------NEEEEEEEEEEEE");
			return null;
			
			
		}
		else{
			return result;
		}
	}

	@Override
	public List<Narudzbina> getPoseteZaRestoran(Integer id_res) {
		Query q = em.createNamedQuery("getPoseteZaRestoran");
		q.setParameter("res_id", id_res);
		
		@SuppressWarnings("unchecked")
		List<Narudzbina> result = (List<Narudzbina>) q.getResultList();
		if(result.size() == 0){
			System.out.println("------------------------------------------NEEEEEEEEEEEE");
			return null;
			
			
		}
		else{
			return result;
		}
	}

	@Override
	public List<Narudzbina> getPoseteZaRestoranZaGosta(Integer id_res, Integer id_gost) {
		Query q = em.createNamedQuery("getPoseteZaRestoranZaGosta");
		q.setParameter("res_id", id_res);
		q.setParameter("gost_za_rez", id_gost);
		
		
		@SuppressWarnings("unchecked")
		List<Narudzbina> result = (List<Narudzbina>) q.getResultList();
		if(result.size() == 0){
			System.out.println("------------------------------------------NEEEEEEEEEEEE");
			return null;
			
			
		}
		else{
			return result;
		}
	}

}
