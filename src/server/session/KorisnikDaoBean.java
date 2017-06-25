package server.session;


import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import server.entity.Korisnik;

@Stateless
@Local(KorisnikDaoLocal.class)
public class KorisnikDaoBean extends GenericDaoBean<Korisnik, Integer>
		implements KorisnikDaoLocal {

	public Korisnik findKorisnikSaKorisnickimImenomILozinkom(
			String email_gosta, String lozinka_gosta) {
		Query q = em
				.createNamedQuery("findKorisnikSaKorisnickimImenomILozinkom");
		q.setParameter("korisnik_email", email_gosta);
		q.setParameter("korisnik_lozinka", lozinka_gosta);
		List<Korisnik> result = q.getResultList();
		//System.out.println("--------------" + result.get(0));
		if(result.size() == 0){
			System.out.println("------------------------------------------NEEEEEEEEEEEE");
			return null;
			
			
		}
		else{
			return result.get(0);
		}
	}

}
