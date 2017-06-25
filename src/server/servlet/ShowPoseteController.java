package server.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import server.entity.Gost;
import server.entity.Narudzbina;
import server.session.NarudzbinaDaoLocal;

public class ShowPoseteController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ShowPoseteController.class);
	
	@EJB
	NarudzbinaDaoLocal narudzbinaDao;
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if ((request.getSession().getAttribute("gost")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			if ((request.getAttribute("moje_posete")) != null) {
				
				request.removeAttribute("moje_posete");
			}
			//za ispis ako je vec unesena ocena za restoran
			if(request.getSession().getAttribute("rocena") != null){
				request.getSession().removeAttribute("rocena");
				
			}
			
			Boolean a = (Boolean) request.getAttribute("res_postoji");
			if(a != null){
				request.getSession().setAttribute("rocena",a);
			}
			//za ispis ako je vec unesena ocena za jelo
			if(request.getSession().getAttribute("jocena") != null){
				request.getSession().removeAttribute("jocena");
				
			}
			
			Boolean b = (Boolean) request.getAttribute("jelo_postoji");
			if(b != null){
				request.getSession().setAttribute("jocena",b);
			}
			//za ispis ako je vec unesena ocena za konobara
			if(request.getSession().getAttribute("kocena") != null){
				request.getSession().removeAttribute("kocena");
				
			}
			
			Boolean c = (Boolean) request.getAttribute("konobar_postoji");
			if(c != null){
				request.getSession().setAttribute("kocena",c);
			}
			
			
			Gost kor = (Gost)request.getSession().getAttribute("gost");
			List<Narudzbina> narudzbine = new ArrayList<Narudzbina>();
			if(narudzbinaDao.getPoseteZaGosta(kor.getKorisnik_id()) != null){
				narudzbine.addAll(narudzbinaDao.getPoseteZaGosta(kor.getKorisnik_id()));
			}
			
			request.setAttribute("moje_posete", narudzbine);
			
			
			
			getServletContext().getRequestDispatcher("/home_page_gost.jsp").forward(request, response);
				//response.sendRedirect(response.encodeURL("./show_restaurants.jsp"));
		} catch (IOException e) {
			log.error(e);
			throw e;
		}		
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	

}
