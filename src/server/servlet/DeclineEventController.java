package server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import server.entity.Gost;
import server.entity.Rezervacija;
import server.session.RezervacijaDaoLocal;

public class DeclineEventController extends HttpServlet{

	/**
	 * 
	 */
	private static Logger log = Logger.getLogger(DeclineEventController.class);

	private static final long serialVersionUID = 1L;
	
	@EJB
	RezervacijaDaoLocal rezervacijaDao;
	
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			Gost kor = (Gost)request.getSession().getAttribute("gost");
			if ((request.getSession().getAttribute("gost")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			
			
			
			System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV");
			String eid = request.getParameter("eventId");
			//nadji rezervaciju koja je odbijena
			Rezervacija rez = rezervacijaDao.findById(Integer.parseInt(eid));
			//obrisi iz liste pozvanih 
			System.out.println("MMMMMMMMMMMAAAAAAAAAAAAAA");
			rez.getPozvaniGosti().remove(kor);
			//kor.getPozvaniZaDogadjaj().remove(rez);
			System.out.println(rez.getPozvaniGosti().size());
			System.out.println("NNNNNNNNNNNNNNNNN");
			rezervacijaDao.merge(rez);
			
		
			
			Boolean odbio = true;
			request.setAttribute("odbio_event", odbio);
			
			getServletContext().getRequestDispatcher("/ViewEventRequestsController").forward(request, response);
			
			//response.sendRedirect(response.encodeRedirectURL("./ViewFriendRequestsController"));
			
			
			
		
		} catch (IOException e) {
			log.error(e);
			throw e;
		}		
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}
