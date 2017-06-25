package server.servlet;

import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.entity.Sanker;
import server.session.SankerDaoLocal;

public class UpdateSankerController extends HttpServlet {
	private static final long serialVersionUID = 4676416691336033321L;
	
	@EJB
	private SankerDaoLocal sankerDao;
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sankerId = null;
		String sankerIme = null;
		String sankerPrezime = null;
		String sankerEmail = null;
		String sankerLozinka = null;
		
		Date dat = null;
		/*String dat = "1970-01-01 01:00:01";
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date sankerDatumRodjenja = null;
		try {
			sankerDatumRodjenja = format.parse(dat);
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		String sankerKonfekcijskiBroj = null;
		Integer sankerVelicinaObuce = 0;
		
		if(request.getSession().getAttribute("sanker") != null){
		
			Sanker s = (Sanker) request.getSession().getAttribute("sanker");
			
			sankerId = String.valueOf(s.getKorisnik_id());
			
			if((request.getParameter("form-name-sanker") != null) 
					&& (!request.getParameter("form-name-sanker").equals(""))
					&& (request.getParameter("form-name-sanker").length() > 3)){
				sankerIme = request.getParameter("form-name-sanker");
			}
			if((request.getParameter("form-lastname-sanker") != null) 
					&& (!request.getParameter("form-lastname-sanker").equals(""))
					&& (request.getParameter("form-lastname-sanker").length() > 3)){
				sankerPrezime = request.getParameter("form-lastname-sanker");
			}
			if((request.getParameter("form-email-sanker") != null) 
					&& (!request.getParameter("form-email-sanker").equals(""))
					&& (request.getParameter("form-email-sanker").length() > 3)){
				sankerEmail = request.getParameter("form-email-sanker");
			}
			if((request.getParameter("form-password-sanker") != null) 
					&& (!request.getParameter("form-password-sanker").equals(""))
					&& (request.getParameter("form-password-sanker").length() > 3)){
				sankerLozinka = request.getParameter("form-password-sanker");
			}
			/*if((request.getParameter("form-datum-rodjenja-konobar") != null) 
					&& (!request.getParameter("form-datum-rodjenja-konobar").equals(""))
					&& (request.getParameter("form-datum-rodjenja-konobar").length() > 3)){
				konobarDatumRodjenja = request.getParameter("form-datum-rodjenja-konobar");
			}*/
			if( (request.getParameter("form-date-sanker") != null) 
					&& (!request.getParameter("form-date-sanker").equals(""))
					&& (request.getParameter("form-date-sanker").length() == 10)){
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				try {
					dat = sdf.parse(request.getParameter("form-date-sanker"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if((request.getParameter("form-konfekcijski-broj-sanker") != null)){
				sankerKonfekcijskiBroj = request.getParameter("form-konfekcijski-broj-sanker");
			}
			if((request.getParameter("form-velicina-obuce-sanker") != null) ){
				sankerVelicinaObuce = Integer.parseInt(request.getParameter("form-velicina-obuce-sanker"));
			}
			
			if((sankerId != null) && (!sankerId.equals(""))){

				Sanker sanker = new Sanker();
				
				sanker.setKorisnik_id(Integer.parseInt(sankerId));
				if(sankerIme != null) { sanker.setKorisnik_ime(sankerIme);}
				if(sankerPrezime != null) { sanker.setKorisnik_prezime(sankerPrezime);}
				if(sankerEmail != null) { sanker.setKorisnik_email(sankerEmail);}
				if(sankerLozinka != null) {sanker.setKorisnik_lozinka(sankerLozinka);}
				
				//sanker.setDatumRodjenjaSanker(sankerDatumRodjenja);
				sanker.setDatumRodjenjaSanker(dat);
				if(sankerKonfekcijskiBroj != null) {sanker.setKonfekcijskiBrojSanker(sankerKonfekcijskiBroj);}
				if(sankerVelicinaObuce != null) {sanker.setVelicinaObuceSanker(sankerVelicinaObuce);}
				
				sanker.setKorisnik_uloga("SANKER");
				sanker.setValidiran(true);
				
				sankerDao.merge(sanker);
				System.out.println(sanker);
			}
			response.sendRedirect("./ReadSankerController");
		} else {
			
			RequestDispatcher disp = request.getRequestDispatcher("./login.jsp");
			disp.forward(request, response);
		
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
