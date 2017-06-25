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

import server.entity.Konobar;
import server.session.KonobarDaoLocal;

public class UpdateKonobarController extends HttpServlet {
	private static final long serialVersionUID = 4676416691336033321L;
	
	@EJB
	private KonobarDaoLocal konobarDao;
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String konobarId = null;
		String konobarIme = null;
		String konobarPrezime = null;
		String konobarEmail = null;
		String konobarLozinka = null;
		
		Date dat = null;
		/*String dat = "1970-01-01 01:00:01";
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date konobarDatumRodjenja = null;
		try {
			konobarDatumRodjenja = format.parse(dat);
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		String konobarKonfekcijskiBroj = null;
		Integer konobarVelicinaObuce = 0;
		
		if(request.getSession().getAttribute("konobar") != null){
		
			Konobar k = (Konobar) request.getSession().getAttribute("konobar");
			
			konobarId = String.valueOf(k.getKorisnik_id());
			
			if((request.getParameter("form-name-konobar") != null) 
					&& (!request.getParameter("form-name-konobar").equals(""))
					&& (request.getParameter("form-name-konobar").length() > 3)){
				konobarIme = request.getParameter("form-name-konobar");
			}
			if((request.getParameter("form-lastname-konobar") != null) 
					&& (!request.getParameter("form-lastname-konobar").equals(""))
					&& (request.getParameter("form-lastname-konobar").length() > 3)){
				konobarPrezime = request.getParameter("form-lastname-konobar");
			}
			if((request.getParameter("form-email-konobar") != null) 
					&& (!request.getParameter("form-email-konobar").equals(""))
					&& (request.getParameter("form-email-konobar").length() > 3)){
				konobarEmail = request.getParameter("form-email-konobar");
			}
			if((request.getParameter("form-password-konobar") != null) 
					&& (!request.getParameter("form-password-konobar").equals(""))
					&& (request.getParameter("form-password-konobar").length() > 3)){
				konobarLozinka = request.getParameter("form-password-konobar");
			}
			/*if((request.getParameter("form-datum-rodjenja-konobar") != null) 
					&& (!request.getParameter("form-datum-rodjenja-konobar").equals(""))
					&& (request.getParameter("form-datum-rodjenja-konobar").length() > 3)){
				konobarDatumRodjenja = request.getParameter("form-datum-rodjenja-konobar");
			}*/
			if( (request.getParameter("form-date-konobar") != null) 
					&& (!request.getParameter("form-date-konobar").equals(""))
					&& (request.getParameter("form-date-konobar").length() == 10)){
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				try {
					dat = sdf.parse(request.getParameter("form-date-konobar"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if((request.getParameter("form-konfekcijski-broj-konobar") != null)){
				konobarKonfekcijskiBroj = request.getParameter("form-konfekcijski-broj-konobar");
			}
			if((request.getParameter("form-velicina-obuce-konobar") != null) ){
				konobarVelicinaObuce = Integer.parseInt(request.getParameter("form-velicina-obuce-konobar"));
			}
			
			if((konobarId != null) && (!konobarId.equals(""))){

				Konobar konobar = new Konobar();
				
				konobar.setKorisnik_id(Integer.parseInt(konobarId));
				if(konobarIme != null) { konobar.setKorisnik_ime(konobarIme);}
				if(konobarPrezime != null) { konobar.setKorisnik_prezime(konobarPrezime);}
				if(konobarEmail != null) { konobar.setKorisnik_email(konobarEmail);}
				if(konobarLozinka != null) {konobar.setKorisnik_lozinka(konobarLozinka);}
				
				konobar.setDatumRodjenjaKonobar(dat);
				//konobar.setDatumRodjenjaKonobar(konobarDatumRodjenja);
				if(konobarKonfekcijskiBroj != null) {konobar.setKonfekcijskiBrojKonobar(konobarKonfekcijskiBroj);}
				if(konobarVelicinaObuce != null) {konobar.setVelicinaObuceKonobar(konobarVelicinaObuce);}
				
				konobar.setKorisnik_uloga("KONOBAR");
				konobar.setValidiran(true);
				
				konobarDao.merge(konobar);
				System.out.println(konobar);
			}
			response.sendRedirect("./ReadKonobarController");
		} else {
			
			RequestDispatcher disp = request.getRequestDispatcher("./login.jsp");
			disp.forward(request, response);
		
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
