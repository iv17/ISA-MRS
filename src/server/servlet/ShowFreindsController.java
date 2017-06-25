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
import server.entity.Prijatelj;
import server.session.GostDaoLocal;
import server.session.PrijateljDaoLocal;
import server.session.RezervacijaDaoLocal;

public class ShowFreindsController extends HttpServlet {

	
	private static Logger log = Logger.getLogger(ShowFreindsController.class);

	private static final long serialVersionUID = 1L;
	@EJB
	private PrijateljDaoLocal prijateljDao;
	
	@EJB
	private GostDaoLocal gostDao;
	
	@EJB
	private RezervacijaDaoLocal rezervacijaDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			Gost kor = (Gost)request.getSession().getAttribute("gost");
			if ((request.getSession().getAttribute("gost")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			if ((request.getAttribute("prijatelji")) != null) {
				request.removeAttribute("prijatelji");
			}
			
			List<Prijatelj> prijatelji = prijateljDao.findFriends(kor.getKorisnik_id(),"da");
			List<Gost> sviPrijatelji = new ArrayList<Gost>();
			for(Prijatelj p : prijatelji){
				System.out.println("gostiiii");
				int idp = p.getGlavniPrijatelj();
				Gost g = gostDao.findById(idp);
				sviPrijatelji.add(g);
				
				
			}
			
			
			request.setAttribute("prijatelji", sviPrijatelji);
			//System.out.println(prijatelji.get(0).getKorisnik_ime());
			
			

			
			getServletContext().getRequestDispatcher("/show_friends.jsp").forward(request, response);
		
		
		} catch (ServletException e) {
			log.error(e);
			throw e;
		} catch (IOException e) {
			log.error(e);
			throw e;
		}		
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
