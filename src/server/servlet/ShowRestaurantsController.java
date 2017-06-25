package server.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import server.entity.Gost;
import server.entity.Narudzbina;
import server.entity.Poseta;
import server.entity.Prijatelj;
import server.entity.Restoran;
import server.session.GostDaoLocal;
import server.session.NarudzbinaDaoLocal;
import server.session.PrijateljDaoLocal;
import server.session.RestoranDaoLocal;

public class ShowRestaurantsController extends HttpServlet {
	
	
	/**
	 * 
	 */
	private static Logger log = Logger.getLogger(ShowRestaurantsController.class);

	private static final long serialVersionUID = 1L;
	@EJB
	private RestoranDaoLocal restoranDao;
	
	@EJB
	private NarudzbinaDaoLocal narudzbinaDao;
	
	@EJB
	private PrijateljDaoLocal prijateljDao;
	
	@EJB
	private GostDaoLocal gostDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if ((request.getSession().getAttribute("gost")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			if ((request.getAttribute("restorani")) != null) {
				
				request.removeAttribute("restorani");
			}
			Gost kor = (Gost) request.getSession().getAttribute("gost");
			//trazi se prosek ocena od svih korisnika
			List<Restoran> restorani = restoranDao.showRestaurants();
			for(Restoran res : restorani){
				List<Poseta> posete = new ArrayList<Poseta>();
				double prosek_svih = 0;
				List<Narudzbina> narudzbine_res = narudzbinaDao.getPoseteZaRestoran(res.getIdRestorana());
				if(narudzbine_res == null){
					//upisi prosek ocene null
					res.setOceneSvihKorisnika(0);
					System.out.println("NEMA narudzbina");
				}
				else{
					for(Narudzbina nar : narudzbine_res){
						posete.add(nar.getPoseta());
						
					}
					System.out.println(posete.size());
					for(Poseta p : posete){
						if(p.getOcenaRestoranaPoseta() != null){
							prosek_svih += p.getOcenaRestoranaPoseta();
						}
						
					}
					if(prosek_svih == 0){
						res.setOceneSvihKorisnika(0);
					}
					else{
						prosek_svih = prosek_svih / posete.size();
						prosek_svih = round(prosek_svih,1);
						res.setOceneSvihKorisnika(prosek_svih);
						
						}
				}
				restoranDao.merge(res);
			
			}
			
			//trazi se prosek ocena ulogovanog i njegovih prijatelja
			List<Prijatelj> prijatelji = prijateljDao.findFriends(kor.getKorisnik_id(),"da");
			List<Gost> sviPrijatelji = new ArrayList<Gost>();
			for(Prijatelj p : prijatelji){
				System.out.println("gostiiii");
				int idp = p.getGlavniPrijatelj();
				Gost g = gostDao.findById(idp);
				sviPrijatelji.add(g);	
			}
			//nasao je sve prijatelje korisnika
			
			for(Restoran res : restorani){
				List<Narudzbina> narudzbine_gosti = new ArrayList<Narudzbina>();
				
				List<Poseta> posete = new ArrayList<Poseta>();
				double prosek_svih = 0;
				//za svakog prijatelja koji je imao narudzbinu unesi narudzbine u listu
				for(Gost gost : sviPrijatelji){
					List<Narudzbina> narudzbine = narudzbinaDao.getPoseteZaRestoranZaGosta(res.getIdRestorana(), gost.getKorisnik_id());
					if(narudzbine != null){
						narudzbine_gosti.addAll(narudzbine);
					}	
				}
				//unesi i svoje narudzbine
				List<Narudzbina> moje_narudzbine = narudzbinaDao.getPoseteZaRestoranZaGosta(res.getIdRestorana(), kor.getKorisnik_id());
				if(moje_narudzbine != null){
					narudzbine_gosti.addAll(moje_narudzbine);
				}
				//ako nista nije dodato u listu postavi ocenu na nula
				if(narudzbine_gosti.size() == 0){
					res.setOcenePrijatelja(0);
				}
				else{
					for(Narudzbina nar : narudzbine_gosti){
						posete.add(nar.getPoseta());
						
					}
					System.out.println(posete.size());
					for(Poseta p : posete){
						if(p.getOcenaRestoranaPoseta() != null){
							prosek_svih += p.getOcenaRestoranaPoseta();
						}
						
					}
					System.out.println(prosek_svih + "proseeeeek");
					if(prosek_svih == 0){
						res.setOcenePrijatelja(0);
					}
					else{
						prosek_svih = prosek_svih / posete.size();
						prosek_svih = round(prosek_svih,1);
						res.setOcenePrijatelja(prosek_svih);
						
						System.out.println(prosek_svih + "PROOOSEKK");
					}
				}
				restoranDao.merge(res);
			}
			
			
			
			
			
			
			request.setAttribute("restorani", restoranDao.showRestaurants());
				
			getServletContext().getRequestDispatcher("/show_restaurants.jsp").forward(request, response);
				//response.sendRedirect(response.encodeURL("./show_restaurants.jsp"));
		} catch (IOException e) {
			log.error(e);
			throw e;
		}		
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	

}
