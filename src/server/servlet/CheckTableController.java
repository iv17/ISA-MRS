package server.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.entity.Gost;
import server.entity.Prijatelj;
import server.entity.Restoran;
import server.entity.Rezervacija;
import server.entity.Sto;
import server.session.GostDaoLocal;
import server.session.PrijateljDaoLocal;
import server.session.RezervacijaDaoLocal;
import server.session.StoDaoLocal;

public class CheckTableController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	StoDaoLocal stoDao;
	
	@EJB
	RezervacijaDaoLocal rezervacijaDao;
	
	@EJB
	PrijateljDaoLocal prijateljDao;
	
	@EJB
	GostDaoLocal gostDao;
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
			
			Gost kor = (Gost)request.getSession().getAttribute("gost");
			if ((request.getSession().getAttribute("gost")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			
			if(request.getSession().getAttribute("izabraniSto") != null){
				request.getSession().removeAttribute("izabraniSto");
			}
		
			String stoX = (String) request.getParameter("sto_x");
			String stoY = (String) request.getParameter("sto_y");
			
			Restoran res = (Restoran)request.getSession().getAttribute("izabraniRestoran");
			System.out.println(res.getIdRestorana() + "nnnnnnnnnnn");
			Integer id = res.getIdRestorana();
			List<Sto> stolovi = stoDao.getTablesForRestaurant(id);
			System.out.println(stolovi.size() + "aaaaaaaaaaa");
			for(Sto s : stolovi){
				if(s.getStoJson() != null){
					if(s.getStoJson().contains(stoY) && s.getStoJson().contains(stoX)){
						
						request.getSession().setAttribute("izabraniSto", s);
						System.out.println(s.getIdStola());
					}
				}
			}
			
			//provera da li je selektovani sto slobodan
			String vremeod = (String) request.getSession().getAttribute("vremeOd");
			String vremedo = (String) request.getSession().getAttribute("vremeDo");
			System.out.println(vremeod + "       " + vremedo);
			vremeod = vremeod.replace("/", "-");
			vremedo = vremedo.replace("/", "-");
			
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			System.out.println("aaaaaaaaaaaaaaaaaa");
			java.util.Date parsed = null;
			try {
				parsed = formatter.parse(vremeod);
				System.out.println("BBbbbbbbbbbbbbbb");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(parsed.toString());
			java.util.Date parsed2 = null;
			try {
				parsed2 = formatter.parse(vremedo);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			Sto proveraStola = (Sto) request.getSession().getAttribute("izabraniSto");
			
			List<Rezervacija> sveRezervacije = rezervacijaDao.getRezervacijeZaSto(proveraStola.getIdStola());
			if(sveRezervacije != null){
				System.out.println("AAAAAA");
				for(Rezervacija r : sveRezervacije){
					System.out.println(r.getDatumDolaskaRezervacija().toString());
					if((parsed.compareTo(r.getDatumDolaskaRezervacija()) >= 0 && parsed.compareTo(r.getRezervacija_duzina_boravka()) <= 0) ||
							(parsed2.compareTo(r.getDatumDolaskaRezervacija()) >= 0 && parsed2.compareTo(r.getRezervacija_duzina_boravka()) <= 0) ||
							(parsed.compareTo(r.getDatumDolaskaRezervacija()) <= 0 && parsed2.compareTo(r.getRezervacija_duzina_boravka()) >= 0)){ 
						Boolean zauzeto = true;
						System.out.println("DDDDDDDDDD");
						
						request.getSession().setAttribute("zauzeto", zauzeto);
						response.sendRedirect("./choose_time.jsp");
						return;
					}	
				}
				
			}
			
			Rezervacija rezervacija = new Rezervacija();
			rezervacija.setGost(kor);
			rezervacija.setRestoran(res);
			rezervacija.setDatumDolaskaRezervacija(parsed);
			rezervacija.setRezervacija_duzina_boravka(parsed2);
			rezervacija.setSto(proveraStola);
			rezervacijaDao.persist(rezervacija);
			
			
			//prikupi sve prijatelje
			List<Prijatelj> prijatelji = prijateljDao.findFriends(kor.getKorisnik_id(),"da");
			List<Gost> sviPrijatelji = new ArrayList<Gost>();
			for(Prijatelj p : prijatelji){
				System.out.println("gostiiii");
				int idp = p.getGlavniPrijatelj();
				Gost g = gostDao.findById(idp);
				sviPrijatelji.add(g);
				
				
			}
			if(request.getSession().getAttribute("poslat_zahtev") != null){
				request.getSession().removeAttribute("poslat_zahtev");
				
			}
			request.setAttribute("prijatelji_pozivi", sviPrijatelji);
			request.getSession().setAttribute("rezervacija", rezervacija);
			request.getSession().setAttribute("svi_prijatelji", sviPrijatelji);
			getServletContext().getRequestDispatcher("/invite_friends.jsp").forward(request, response);
			
				
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
