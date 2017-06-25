package server.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import server.entity.Gost;
import server.entity.Restoran;
import server.entity.Rezervacija;
import server.session.RezervacijaDaoLocal;

public class CreateRezervacijaController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	RezervacijaDaoLocal rezervacijaDao;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("gost") != null){
			Rezervacija rezervacija = new Rezervacija();
			String vremeod = (String) session.getAttribute("vremeOd");
			String vremedo = (String) session.getAttribute("vremeDo");
			System.out.println(vremeod + "       " + vremedo);
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.util.Date parsed = null;
			try {
				parsed = formatter.parse(vremeod);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			java.sql.Date date1 = new java.sql.Date(parsed.getTime());
			java.util.Date parsed2 = null;
			try {
				parsed2 = formatter.parse(vremedo);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			java.sql.Date date2 = new java.sql.Date(parsed2.getTime());
			
			Restoran restoran = (Restoran) session.getAttribute("izabraniRestoran");
			Gost gost = (Gost) session.getAttribute("gost");
			rezervacija.setGost(gost);
			rezervacija.setRestoran(restoran);
			rezervacija.setDatumDolaskaRezervacija(date1);
			rezervacija.setRezervacija_duzina_boravka(date2);
			rezervacijaDao.persist(rezervacija);
			
			
			
			
		} 
		
		else{
			
			RequestDispatcher disp = request.getRequestDispatcher("./login.jsp");
			disp.forward(request, response);
		}
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
