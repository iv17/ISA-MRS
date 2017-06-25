package server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import server.entity.Ponudjac;
import server.session.PonudjacDaoLocal;

/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
public class CreatePonudjacController extends HttpServlet {

	private static final long serialVersionUID = -2544396238785425302L;


	@EJB
	private PonudjacDaoLocal ponudjacDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String ime = null;
		String prezime = null;
		String email = null;
		String lozinka = null;

		//PROVERA DA LI JE ULOGOVAN MENADZER
		HttpSession session = request.getSession();
		if(session.getAttribute("menadzer_restorana") != null){

			if( (request.getParameter("form-name-supplier") != null)
					&& (!request.getParameter("form-name-supplier").equals(""))
					&& (request.getParameter("form-name-supplier").length() > 3)){
				ime = request.getParameter("form-name-supplier");
				
			}
			if( (request.getParameter("form-lastname-supplier") != null) 
					&& (!request.getParameter("form-lastname-supplier").equals(""))
					&& (request.getParameter("form-lastname-supplier").length() > 3)){
				prezime = request.getParameter("form-lastname-supplier");
				
			}
			if( (request.getParameter("form-email-supplier") != null) 
					&& (!request.getParameter("form-email-supplier").equals(""))
					&& (request.getParameter("form-email-supplier").length() > 10)){
				email = request.getParameter("form-email-supplier");
				
			}
			if( (request.getParameter("form-password-supplier") != null) 
					&& (!request.getParameter("form-password-supplier").equals(""))
					&& (request.getParameter("form-password-supplier").length() > 3)){
				lozinka = request.getParameter("form-password-supplier");
				
			}

			Ponudjac ponudjac = new Ponudjac();

			if(ime != null){ ponudjac.setKorisnik_ime(ime);}
			if(prezime != null){ ponudjac.setKorisnik_prezime(prezime); }
			if(email != null){ ponudjac.setKorisnik_email(email); }
			if(lozinka != null){ ponudjac.setKorisnik_lozinka(lozinka); }
			
			ponudjac.setKorisnik_uloga("PONUDJAC");
			ponudjac.setValidiran(true);

			ponudjacDao.persist(ponudjac);
			
			response.sendRedirect("./ReadPonudjaciController");

		} else {
			RequestDispatcher disp = request.getRequestDispatcher("./login.jsp");
			disp.forward(request, response);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}
}
