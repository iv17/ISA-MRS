package server.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import server.entity.Gost;
import server.entity.Jelo;
import server.entity.Jelovnik;
import server.entity.KartaPica;
import server.entity.Narudzbina;
import server.entity.Obrok;
import server.entity.Pice;
import server.entity.PorucenoPice;
import server.entity.Restoran;
import server.entity.Rezervacija;
import server.session.JeloDaoLocal;
import server.session.NarudzbinaDaoLocal;
import server.session.PiceDaoLocal;
import server.session.RezervacijaDaoLocal;

public class CreateOrderController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(CreateOrderController.class);
	
	@EJB
	NarudzbinaDaoLocal narudzbinaDao;
	
	@EJB
	RezervacijaDaoLocal rezervacijaDao;
	
	@EJB
	JeloDaoLocal jeloDao;
	
	@EJB
	PiceDaoLocal piceDao;
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if ((request.getSession().getAttribute("gost")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			if ((request.getSession().getAttribute("narudzbina")) != null) {
				
				request.getSession().removeAttribute("narudzbina");
			}
			
			if ((request.getSession().getAttribute("ponudjena_jela")) != null) {
				
				request.getSession().removeAttribute("ponudjena_jela");
			}
			if ((request.getSession().getAttribute("ponudjena_pica")) != null) {
				
				request.getSession().removeAttribute("ponudjena_pica");
			}
			
			if ((request.getSession().getAttribute("no_order")) != null) {
				request.getSession().removeAttribute("no_order");
			}
			
			if ((request.getSession().getAttribute("bad_time")) != null) {
				request.getSession().removeAttribute("bad_time");
			}
			if ((request.getSession().getAttribute("cancel_res")) != null) {
				request.getSession().removeAttribute("cancel_res");
			}
			Gost kor = (Gost)request.getSession().getAttribute("gost");
			String id_rez = request.getParameter("rezId");
			System.out.println("MMMMMMMMMMMMMMMMMMM");
			Rezervacija rez = rezervacijaDao.findById(Integer.parseInt(id_rez));
			
			Date datum_rez = rez.getDatumDolaskaRezervacija();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            java.util.Date curr_date = new java.util.Date();
            String datetime = dateFormat.format(curr_date);
            System.out.println("Current Date Time : " + datetime);
          //provera da li su isti datumi za danas i datum sa rezervacije
            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
            if(fmt.format(datum_rez).equals(fmt.format(curr_date)) == true){
            	//provera da li je ostalo manje od 30 min
            	if ( datum_rez.getTime() - curr_date.getTime() < 30*60*1000) {
            		long a = curr_date.getTime() - datum_rez.getTime();
            		
            		System.out.println("AAAAAAAAAAAAAAAbbbbbbbb");
            		System.out.println(a);
            		Boolean bad_time = true;
            		request.getSession().setAttribute("no_order", bad_time);
            		getServletContext().getRequestDispatcher("/ShowReservationsController").forward(request, response);
            		return;
            	}
            }
			
            
			
			request.getSession().setAttribute("for_reservation", rez);
			//ako porucuje
			Restoran restoran = rez.getRestoran();
			System.out.println(restoran);
			Jelovnik jelovnik = restoran.getJelovnik();
			System.out.println(jelovnik);
			KartaPica karta_pica = restoran.getKartaPica();
			System.out.println(karta_pica);
			List<Jelo> listaJela = new ArrayList<Jelo>();
			if(jeloDao.getJelaJelovnika(jelovnik.getIdJelovnika()) != null){
				System.out.println(".................................");
				listaJela = jeloDao.getJelaJelovnika(jelovnik.getIdJelovnika());
			}
			
			request.getSession().setAttribute("ponudjena_jela", listaJela);
			System.out.println("]]]]]]]]]]]]]]]]]]]]]]]");
			List<Pice> listaPica = new ArrayList<Pice>();
			System.out.println(karta_pica.getIdKartaPica());
			if(piceDao.getPicaKarte(karta_pica.getIdKartaPica()) != null){
				System.out.println("*********************");
				
				listaPica = piceDao.getPicaKarte(karta_pica.getIdKartaPica());
			}
			request.getSession().setAttribute("ponudjena_pica", listaPica);
			System.out.println("RRRRRRRRRRRRRRRRRRRRR");
			
			if(narudzbinaDao.getNarudzbineZaGostaZaRezervaciju(rez.getIdRezervacija(), kor.getKorisnik_id()) == null){
				Narudzbina narudzbina = new Narudzbina();
				Obrok obrok = new Obrok();
				Set<Jelo> jela = new HashSet<Jelo>();
				obrok.setJela(jela);
				PorucenoPice pice = new PorucenoPice();
				Set<Pice> pica = new HashSet<Pice>();
				pice.setPica(pica);
				narudzbina.setObrok(obrok);
				narudzbina.setPorucenoPice(pice);
				narudzbina.setGost(kor);
				narudzbina.setRezervacija(rez);
				narudzbina.setRestoran(restoran);
				System.out.println("RRRRjjjjjjjjjjjjjjjjjj");
				//dodati konobara
				//rez.getSto().getReon();
				narudzbinaDao.persist(narudzbina);
				request.getSession().setAttribute("narudzbina", narudzbina);
				
				
			}
			else{
				List<Narudzbina> nar = narudzbinaDao.getNarudzbineZaGostaZaRezervaciju(rez.getIdRezervacija(), kor.getKorisnik_id());
				Narudzbina vec_postoji = nar.get(0);
				request.getSession().setAttribute("narudzbina", vec_postoji);
				
			}
			
			getServletContext().getRequestDispatcher("/create_order.jsp").forward(request, response);
			
			
		
		} catch (IOException e) {
			log.error(e);
			throw e;
		}		
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}



	
	

}
