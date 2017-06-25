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

import server.entity.MenadzerRestorana;
import server.entity.Restoran;
import server.entity.Sanker;
import server.session.SankerDaoLocal;

public class CreateSankerController extends HttpServlet {


	private static final long serialVersionUID = 6462072210246748866L;

	//private static Logger log = Logger.getLogger(CreateSankerController.class);

	@EJB
	private SankerDaoLocal sankerDao;

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
			
			if( (request.getParameter("form-name-bartender") != null)
					&& (!request.getParameter("form-name-bartender").equals(""))
					&& (request.getParameter("form-name-bartender").length() > 3)){
				ime = request.getParameter("form-name-bartender");
			}
			if( (request.getParameter("form-lastname-bartender") != null) 
					&& (!request.getParameter("form-lastname-bartender").equals(""))
					&& (request.getParameter("form-lastname-bartender").length() > 3)){
				prezime = request.getParameter("form-lastname-bartender");
			}
			if( (request.getParameter("form-email-bartender") != null) 
					&& (!request.getParameter("form-email-bartender").equals(""))
					){
				email = request.getParameter("form-email-bartender");
			}
			if( (request.getParameter("form-password-bartender") != null) 
					&& (!request.getParameter("form-password-bartender").equals(""))
					&& (request.getParameter("form-password-bartender").length() > 3)){
				lozinka = request.getParameter("form-password-bartender");
			}
			if( (request.getParameter("form-date-bartender") != null) 
					&& (!request.getParameter("form-date-bartender").equals(""))
					&& (request.getParameter("form-date-bartender").length() == 10)){
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				try {
					datumRodjenja = sdf.parse(request.getParameter("form-date-bartender"));
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}
			}
			if( (request.getParameter("form-clothes-size-bartender") != null) 
					&& (!request.getParameter("form-clothes-size-bartender").equals(""))){
				velicinaOdece = request.getParameter("form-clothes-size-bartender");
			}
			if( (request.getParameter("form-shoe-size-bartender") != null) 
					&& (!request.getParameter("form-shoe-size-bartender").equals(""))
					&& (request.getParameter("form-shoe-size-bartender").length() == 2)){
				velicinaObuce = Integer.parseInt(request.getParameter("form-shoe-size-bartender"));
			}

			Sanker sanker = new Sanker();

			if(ime != null){ sanker.setKorisnik_ime(ime); }
			if(prezime != null){ sanker.setKorisnik_prezime(prezime); }
			if(email != null){ sanker.setKorisnik_email(email); }
			if(lozinka != null){ sanker.setKorisnik_lozinka(lozinka); }
			if(datumRodjenja != null) { sanker.setDatumRodjenjaSanker(datumRodjenja); }
			if(velicinaOdece != null) { sanker.setKonfekcijskiBrojSanker(velicinaOdece);}
			if(velicinaObuce != null) { sanker.setVelicinaObuceSanker(velicinaObuce);}

			sanker.setKorisnik_uloga("SANKER");
			sanker.setValidiran(false);
			sanker.setRestoran(restoran);
			sankerDao.persist(sanker);

			response.sendRedirect("./ReadSankerController");
		} else {
			
			RequestDispatcher disp = request.getRequestDispatcher("./login.jsp");
			disp.forward(request, response);
			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}
}
