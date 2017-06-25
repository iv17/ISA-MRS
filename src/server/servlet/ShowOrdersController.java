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

public class ShowOrdersController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ShowOrdersController.class);
	
	@EJB
	NarudzbinaDaoLocal narudzbinaDao;
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if ((request.getSession().getAttribute("gost")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			if ((request.getAttribute("moje_narudzbine")) != null) {
				
				request.removeAttribute("moje_narudzbine");
			}
			
			//za ispis ako je pogresno vreeme za otkazivanje narudzbine
			if(request.getSession().getAttribute("order_can1") != null){
				request.getSession().removeAttribute("order_can1");
				
			}
			
			Boolean s = (Boolean) request.getAttribute("order_can");
			if(s != null){
				request.getSession().setAttribute("order_can1",s);
				
			}
			//ako je otkazana narudzbina
			if(request.getSession().getAttribute("cancel_order1") != null){
				request.getSession().removeAttribute("cancel_order1");
				
			}
			
			Boolean d = (Boolean) request.getAttribute("cancel_order");
			if(d != null){
				request.getSession().setAttribute("cancel_order1", d);
				
			}
			
			
			Gost kor = (Gost)request.getSession().getAttribute("gost");
			List<Narudzbina> narudzbine = new ArrayList<Narudzbina>();
			if(narudzbinaDao.getRezervacijeZaGosta(kor.getKorisnik_id()) != null){
				narudzbine.addAll(narudzbinaDao.getRezervacijeZaGosta(kor.getKorisnik_id()));
			}
			
			request.setAttribute("moje_narudzbine", narudzbine);
			
			
			
			getServletContext().getRequestDispatcher("/show_orders.jsp").forward(request, response);
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
