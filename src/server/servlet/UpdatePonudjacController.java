package server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.entity.Ponudjac;
import server.session.PonudjacDaoLocal;

/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
public class UpdatePonudjacController extends HttpServlet {
	
	private static final long serialVersionUID = 4676416691336033321L;
	
	@EJB
	private PonudjacDaoLocal ponudjacDao;
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ponudjacId = null;
		String ponudjacIme = null;
		String ponudjacPrezime = null;
		String ponudjacEmail = null;
		String ponudjacLozinka = null;
		
		if(request.getSession().getAttribute("ponudjac") != null){
		
			Ponudjac p = (Ponudjac) request.getSession().getAttribute("ponudjac");
			
			ponudjacId = String.valueOf(p.getKorisnik_id());
			
			if((request.getParameter("form-name-ponudjac") != null) 
					&& (!request.getParameter("form-name-ponudjac").equals(""))
					&& (request.getParameter("form-name-ponudjac").length() > 3)){
				ponudjacIme = request.getParameter("form-name-ponudjac");
			}
			if((request.getParameter("form-lastname-ponudjac") != null) 
					&& (!request.getParameter("form-lastname-ponudjac").equals(""))
					&& (request.getParameter("form-lastname-ponudjac").length() > 3)){
				ponudjacPrezime = request.getParameter("form-lastname-ponudjac");
			}
			if((request.getParameter("form-email-ponudjac") != null) 
					&& (!request.getParameter("form-email-ponudjac").equals(""))
					&& (request.getParameter("form-email-ponudjac").length() > 3)){
				ponudjacEmail = request.getParameter("form-email-ponudjac");
			}
			if((request.getParameter("form-password-ponudjac") != null) 
					&& (!request.getParameter("form-password-ponudjac").equals(""))
					&& (request.getParameter("form-password-ponudjac").length() > 3)){
				ponudjacLozinka = request.getParameter("form-password-ponudjac");
			}
			
			if((ponudjacId != null) && (!ponudjacId.equals(""))){

				Ponudjac ponudjac = new Ponudjac();
				
				ponudjac.setKorisnik_id(Integer.parseInt(ponudjacId));
				if(ponudjacIme != null) { ponudjac.setKorisnik_ime(ponudjacIme);}
				if(ponudjacPrezime != null) { ponudjac.setKorisnik_prezime(ponudjacPrezime);}
				if(ponudjacEmail != null) { ponudjac.setKorisnik_email(ponudjacEmail);}
				if(ponudjacLozinka != null) {ponudjac.setKorisnik_lozinka(ponudjacLozinka);}
				
				ponudjac.setKorisnik_uloga("PONUDJAC");
				ponudjac.setValidiran(true);
				
				ponudjacDao.merge(ponudjac);
				System.out.println(ponudjac);
			}
			response.sendRedirect("./ReadPonudjacController");
		} else {
			
			RequestDispatcher disp = request.getRequestDispatcher("./login.jsp");
			disp.forward(request, response);
		
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
