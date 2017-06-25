package server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import server.entity.Korisnik;
import server.session.KorisnikDaoLocal;

public class ConfirmController extends HttpServlet{

	
	
	/**
	 * 
	 */
	
	private static Logger log = Logger.getLogger(ConfirmController.class);

	@EJB
	private KorisnikDaoLocal korisnikDao;
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if ((request.getSession().getAttribute("gost")) != null) {
			Korisnik k = (Korisnik)request.getSession().getAttribute("gost");
			System.out.println(k.getKorisnik_ime());
			System.out.println(k.isValidiran());
			k.setValidiran(true);
			System.out.println(k.isValidiran());
			request.getSession().removeAttribute("gost");
			request.getSession().setAttribute("gost", k);
			korisnikDao.merge(k);
		}
		response.sendRedirect(response.encodeRedirectURL("./login.jsp"));
	
	
	}
	
	
	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
