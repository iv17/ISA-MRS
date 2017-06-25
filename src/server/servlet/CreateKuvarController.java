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

import server.entity.Kuvar;
import server.entity.MenadzerRestorana;
import server.entity.Restoran;
import server.session.KuvarDaoLocal;

/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
public class CreateKuvarController extends HttpServlet {

	private static final long serialVersionUID = -2544396238785425302L;

	@EJB
	private KuvarDaoLocal kuvarDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

		String ime = null;
		String prezime = null;
		String email = null;
		String lozinka = null;
		Date datumRodjenja = null;
		String velicinaOdece = null;
		Integer velicinaObuce = null;
		String tipKuvara = null;

		//PROVERA DA LI JE ULOGOVAN MENADZER
		HttpSession session = request.getSession();
		if(session.getAttribute("menadzer_restorana") != null){

			MenadzerRestorana menadzer = (MenadzerRestorana) session.getAttribute("menadzer_restorana");
			Restoran restoran = menadzer.getRestoran();
			
			if( (request.getParameter("form-name-cook") != null) 
					&& (!request.getParameter("form-name-cook").equals(""))
					&& (request.getParameter("form-name-cook").length() > 3)){
				ime = request.getParameter("form-name-cook");
			}
			if( (request.getParameter("form-lastname-cook") != null) 
					&& (!request.getParameter("form-lastname-cook").equals(""))
					&& (request.getParameter("form-lastname-cook").length() > 3)){
				prezime = request.getParameter("form-lastname-cook");
			}
			if( (request.getParameter("form-email-cook") != null) 
					&& (!request.getParameter("form-email-cook").equals(""))
					){
				email = request.getParameter("form-email-cook");
			}
			if( (request.getParameter("form-password-cook") != null) 
					&& (!request.getParameter("form-password-cook").equals(""))
					&& (request.getParameter("form-password-cook").length() > 3)){
				lozinka = request.getParameter("form-password-cook");
			}
			if( (request.getParameter("form-date-cook") != null) 
					&& (!request.getParameter("form-date-cook").equals(""))
					&& (request.getParameter("form-date-cook").length() == 10)){
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				try {
					datumRodjenja = sdf.parse(request.getParameter("form-date-cook"));
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}
			}
			if( (request.getParameter("form-clothes-size") != null) 
					&& (!request.getParameter("form-clothes-size").equals(""))){
				velicinaOdece = request.getParameter("form-clothes-size");
				System.out.println(velicinaOdece);
			}
			if( (request.getParameter("form-shoe-size") != null) 
					&& (!request.getParameter("form-shoe-size").equals(""))
					&& (request.getParameter("form-shoe-size").length() == 10)){
				velicinaObuce = Integer.parseInt(request.getParameter("form-shoe-size"));
			}
			if( (request.getParameter("form-type") != null) 
					&& (!request.getParameter("form-type").equals(""))){
				tipKuvara = request.getParameter("form-type");
			}


			Kuvar kuvar = new Kuvar();

			if(ime != null){ kuvar.setKorisnik_ime(ime); }
			if(prezime != null){ kuvar.setKorisnik_prezime(prezime); }
			if(email != null){ kuvar.setKorisnik_email(email); }
			if(lozinka != null){ kuvar.setKorisnik_lozinka(lozinka); }
			if(datumRodjenja != null) { kuvar.setDatumRodjenjaKuvar(datumRodjenja); }
			if(velicinaOdece != null) { kuvar.setKonfekcijskiBrojKuvar(velicinaOdece);}
			if(velicinaObuce != null) { kuvar.setVelicinaObuceKuvar(velicinaObuce);}
			if(tipKuvara != null) {kuvar.setTipKuvar(tipKuvara);}
			
			kuvar.setKorisnik_uloga("KUVAR");
			kuvar.setValidiran(false);
			kuvar.setRestoran(restoran);
			
			kuvarDao.persist(kuvar);
			
			response.sendRedirect("./ReadKuvarController");

		} else{
			RequestDispatcher disp = request.getRequestDispatcher("./login.jsp");
			disp.forward(request, response);
			
		}


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}
}
