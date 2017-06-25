package server.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import server.entity.RasporedStolova;
import server.entity.Restoran;
import server.session.RasporedDaoLocal;
import server.session.RezervacijaDaoLocal;

public class CheckReservationTimeController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB 
	private RasporedDaoLocal rasporedDao;
	
	@EJB
	private RezervacijaDaoLocal rezervacijaDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if ((request.getSession().getAttribute("wrong_time")) != null) {
			request.getSession().removeAttribute("wrong_time");
		}
		
		if ((request.getSession().getAttribute("error_time")) != null) {
			request.getSession().removeAttribute("error_time");
		}
		
		HttpSession session = request.getSession();
		Boolean error = false;
		if(session.getAttribute("gost") != null){
			String vremeOd = request.getParameter("datumrez");
			String vremeDo = request.getParameter("vremerez");
			System.out.println(vremeOd + "FFFFFFFFFF");
			if(vremeOd.equals("") || vremeDo.equals("")){
				error = true;
				request.getSession().setAttribute("error_time", error);
				response.sendRedirect("./choose_time.jsp");
				return;
				
			}
			
			
			Boolean manje = false;
			String partVremeOd = vremeOd.substring(11, 13);
			String partVremeDo = vremeDo.substring(0, 2);
			int od = Integer.parseInt(partVremeOd);
			int vdo = Integer.parseInt(partVremeDo);
			System.out.println(od + "AAAAAAAAAAAAA");
			System.out.println(vdo + "BBBBBBBBBBBBBBB");
			String minutiod = vremeOd.substring(14, 16);
			String minutido = vremeDo.substring(3, 5);
			int odm = Integer.parseInt(minutiod);
			int vdom = Integer.parseInt(minutido);
			//ako su sati manji ili ako su jednaki minuti ne smeju biti manji
			if(vdo < od || (vdo == od && vdom <= odm)){
				manje = true;
				request.getSession().setAttribute("wrong_time", manje);
				response.sendRedirect("./choose_time.jsp");
				return;
			}
			
			
			
			if(session.getAttribute("rasporedi") != null){
				session.removeAttribute("rasporedi");
			}
			if(session.getAttribute("vremeOd") != null){
				session.removeAttribute("vremeOd");
			}
			if(session.getAttribute("vremeDo") != null){
				session.removeAttribute("vremeDo");
			}
			String vremesesija = vremeOd.substring(0, 11) + vremeDo;
			System.out.println(vremesesija + "sesijaaaaaaaaaaaaa");
			
			
			
			
			Restoran res = (Restoran) session.getAttribute("izabraniRestoran");
			session.setAttribute("vremeOd", vremeOd);
			session.setAttribute("vremeDo", vremesesija);
			List<RasporedStolova> raspored = new ArrayList<RasporedStolova>();
			if(rasporedDao.showRestaurants(res.getIdRestorana()) != null){
				raspored.addAll(rasporedDao.showRestaurants(res.getIdRestorana()));
			}
			session.setAttribute("rasporedi", raspored);
			response.sendRedirect("./ucitajStolove.jsp");
			
		} 
		
		else{
			
			RequestDispatcher disp = request.getRequestDispatcher("./login.jsp");
			disp.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}
	

	
	
	
}
