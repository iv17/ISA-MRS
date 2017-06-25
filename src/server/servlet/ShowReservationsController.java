package server.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import server.entity.Gost;
import server.entity.Rezervacija;
import server.session.RezervacijaDaoLocal;

public class ShowReservationsController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ShowReservationsController.class);
	
	@EJB
	RezervacijaDaoLocal rezervacijaDao;
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if ((request.getSession().getAttribute("gost")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			if ((request.getAttribute("moje_rezervacije")) != null) {
				
				request.removeAttribute("moje_rezervacije");
			}
			
			//za ispis notifikacije ako je pogresno vreme za brisanje rezervacije
			if(request.getSession().getAttribute("bad_time1") != null){
				request.getSession().removeAttribute("bad_time1");
				
			}
			
			Boolean p = (Boolean) request.getAttribute("bad_time");
			if(p != null){
				request.getSession().setAttribute("bad_time1", p);
				
			}
			
			//za ispis notifikacije ako je otkazana rez
			if(request.getSession().getAttribute("cancel_res1") != null){
				request.getSession().removeAttribute("cancel_res1");
				
			}
			
			Boolean a = (Boolean) request.getAttribute("cancel_res");
			if(a != null){
				request.getSession().setAttribute("cancel_res1", a);
				
			}
			
			
			Gost kor = (Gost)request.getSession().getAttribute("gost");
			List<Rezervacija> rezervacije = new ArrayList<Rezervacija>();
			if(rezervacijaDao.getRezervacijeZaGosta(kor.getKorisnik_id()) != null){
				rezervacije.addAll(rezervacijaDao.getRezervacijeZaGosta(kor.getKorisnik_id()));
			}
			//ne prikazuj rezrevacije koje su se vec desile
			System.out.println("aaaaa");
			Date curr_date = new java.util.Date();
			
			Iterator<Rezervacija> iter = rezervacije.iterator();

			while (iter.hasNext()) {
				Rezervacija rez = iter.next();

			    if (rez.getDatumDolaskaRezervacija().before(curr_date))
			        iter.remove();
			}
			request.setAttribute("moje_rezervacije", rezervacije);
			
			System.out.println("BBBBBBBBBBBBBB");
			List<Rezervacija> sveRezervacije = new ArrayList<Rezervacija>();
			if(rezervacijaDao.getRezervacije() != null){
				sveRezervacije.addAll(rezervacijaDao.getRezervacije());
			}
			System.out.println("GGGGGGGGG");	
			//isto i za pozivnice
			Iterator<Rezervacija> iter2 = sveRezervacije.iterator();

			while (iter2.hasNext()) {
				Rezervacija rez2 = iter2.next();

			    if (rez2.getDatumDolaskaRezervacija().before(curr_date))
			        iter2.remove();
			}
			System.out.println("MMMMMMMMMMMMM");
			List<Rezervacija> pozivnice = new ArrayList<Rezervacija>();
			for(Rezervacija r : sveRezervacije){
				if(r.getPrihvaceniGosti().contains(kor)){
					pozivnice.add(r);
				}
				
			}
			request.setAttribute("rezervacije_pozvani", pozivnice);
			
			
			getServletContext().getRequestDispatcher("/show_reservations.jsp").forward(request, response);
				//response.sendRedirect(response.encodeURL("./show_restaurants.jsp"));
		} catch (IOException e) {
			log.error(e);
			throw e;
		}		
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

	
	

}
