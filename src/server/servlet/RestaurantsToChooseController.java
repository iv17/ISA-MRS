package server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import server.session.RestoranDaoLocal;

public class RestaurantsToChooseController extends HttpServlet{
	
	
	private static Logger log = Logger.getLogger(RestaurantsToChooseController.class);

	private static final long serialVersionUID = 1L;
	@EJB
	private RestoranDaoLocal restoranDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if ((request.getSession().getAttribute("gost")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			if ((request.getAttribute("biraj_rest")) != null) {
				
				request.removeAttribute("biraj_rest");
			}
			//System.out.println("TUTUTUTTUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
			//List<Restoran> restorani = restoranDao.showRestaurants();
			request.setAttribute("biraj_rest", restoranDao.showRestaurants());
			//response.sendRedirect(response.encodeURL("./show_restaurants.jsp"));
			
			getServletContext().getRequestDispatcher("/choose_restaurant.jsp").forward(request, response);
				//response.sendRedirect(response.encodeURL("./choose_restaurant.jsp"));
				
			//getServletContext().getRequestDispatcher("/show_restaurants.jsp").forward(request, response);
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
