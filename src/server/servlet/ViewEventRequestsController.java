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
import server.entity.Rezervacija;
import server.session.RezervacijaDaoLocal;

public class ViewEventRequestsController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ViewEventRequestsController.class);
	
	@EJB
	private RezervacijaDaoLocal rezervacijaDao;
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			Gost kor = (Gost)request.getSession().getAttribute("gost");
			if ((request.getSession().getAttribute("gost")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			
			List<Rezervacija> sveRezervacije = rezervacijaDao.getRezervacije();
			List<Rezervacija> pozivnice = new ArrayList<Rezervacija>();
			for(Rezervacija r : sveRezervacije){
				if(r.getPozvaniGosti().contains(kor)){
					pozivnice.add(r);
				}
				
			}
			System.out.println(pozivnice.size()  + "  FFFFFFFFFFFFFF");
			
			/*Set<Rezervacija> pozvaniNa = kor.getPozvaniZaDogadjaj();
			System.out.println(pozvaniNa.size() + "MMMMMMMMMMMMMMM");
			List<Rezervacija> pozivnice = new ArrayList<Rezervacija>(pozvaniNa);*/
			
			
			
			request.setAttribute("pozivnice", pozivnice);
			
			
			
			if(request.getSession().getAttribute("prihvacen_event") != null){
				request.getSession().removeAttribute("prihvacen_event");
				
			}
			
			Boolean p = (Boolean) request.getAttribute("prihvatio_event");
			if(p != null){
				request.getSession().setAttribute("prihvacen_event", p);
				
			}
			
			if(request.getSession().getAttribute("odbijen_event") != null){
				request.getSession().removeAttribute("odbijen_event");
				
			}
			
			Boolean o = (Boolean) request.getAttribute("odbio_event");
			if(o != null){
				request.getSession().setAttribute("odbijen_event", o);
				
			}
			
			getServletContext().getRequestDispatcher("/show_event_requests.jsp").forward(request, response);
		}
		catch (ServletException e) {
			log.error(e);
			throw e;
		}
			
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}
