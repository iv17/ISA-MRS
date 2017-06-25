package server.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import server.entity.Konobar;
import server.entity.MenadzerRestorana;
import server.entity.Restoran;
import server.session.KonobarDaoLocal;

/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
public class CreateKonobarController extends HttpServlet {

	private static final long serialVersionUID = -2544396238785425302L;

	@EJB
	private KonobarDaoLocal konobarDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String ime = null;
		String prezime = null;
		String email = null;
		String lozinka = null;
		Date datumRodjenja = null;
		String velicinaOdece = null;
		Integer velicinaObuce = null;
		

		//PROVERA DA LI JE ULOGOVAN MENADZER
		HttpSession session = request.getSession();
		if(session.getAttribute("menadzer_restorana") != null){
		
			MenadzerRestorana menadzer = (MenadzerRestorana) session.getAttribute("menadzer_restorana");
			Restoran restoran = menadzer.getRestoran();
			
			if( (request.getParameter("form-name-waiter") != null) 
					&& (!request.getParameter("form-name-waiter").equals(""))
					&& (request.getParameter("form-name-waiter").length() > 3)){
				ime = request.getParameter("form-name-waiter");
			}
			if( (request.getParameter("form-lastname-waiter") != null) 
					&& (!request.getParameter("form-lastname-waiter").equals(""))
					&& (request.getParameter("form-lastname-waiter").length() > 3)){
				prezime = request.getParameter("form-lastname-waiter");
			}
			if( (request.getParameter("form-email-waiter") != null) 
					&& (!request.getParameter("form-email-waiter").equals(""))
					){
				email = request.getParameter("form-email-waiter");
			}
			if( (request.getParameter("form-password-waiter") != null) 
					&& (!request.getParameter("form-password-waiter").equals(""))
					&& (request.getParameter("form-password-waiter").length() > 3)){
				lozinka = request.getParameter("form-password-waiter");
			}
			if( (request.getParameter("form-date-waiter") != null) 
					&& (!request.getParameter("form-date-waiter").equals(""))
					&& (request.getParameter("form-date-waiter").length() == 10)){
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				try {
					datumRodjenja = sdf.parse(request.getParameter("form-date-waiter"));
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}
			}
			if( (request.getParameter("form-clothes-size-waiter") != null) 
					&& (!request.getParameter("form-clothes-size-waiter").equals(""))){
				velicinaOdece = request.getParameter("form-clothes-size-waiter");
			}
			if( (request.getParameter("form-shoe-size-waiter") != null) 
					&& (!request.getParameter("form-shoe-size-waiter").equals(""))
					&& (request.getParameter("form-shoe-size-waiter").length() == 2)){
				velicinaObuce = Integer.parseInt(request.getParameter("form-shoe-size-waiter"));
			}
			
			Konobar konobar = new Konobar();

			if(ime != null){ konobar.setKorisnik_ime(ime); }
			if(prezime != null){ konobar.setKorisnik_prezime(prezime); }
			if(email != null){ konobar.setKorisnik_email(email); }
			if(lozinka != null){ konobar.setKorisnik_lozinka(lozinka); }
			if(datumRodjenja != null) { konobar.setDatumRodjenjaKonobar(datumRodjenja); }
			if(velicinaOdece != null) { konobar.setKonfekcijskiBrojKonobar(velicinaOdece);}
			if(velicinaObuce != null) { konobar.setVelicinaObuceKonobar(velicinaObuce);}
			
			konobar.setKorisnik_uloga("KONOBAR");
			konobar.setValidiran(true);
			konobar.setRestoran(restoran);
			
			konobarDao.persist(konobar);
			
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
 