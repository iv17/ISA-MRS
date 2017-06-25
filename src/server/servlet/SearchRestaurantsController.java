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

import server.entity.Restoran;
import server.session.RestoranDaoLocal;

public class SearchRestaurantsController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ShowRestaurantsController.class);
	@EJB
	private RestoranDaoLocal restoranDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			//HttpSession session = request.getSession();
			if ((request.getSession().getAttribute("gost")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			
			
			System.out.println("TUTUTUTTUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
			String try_search = request.getParameter("search_res");
			System.out.println(try_search);
			System.out.println("CCCCCCCCCCCCCCCCCCCCCCC");
			List<Restoran> restorani = restoranDao.showRestaurants();
			System.out.println(restorani.size());
			List<Restoran> restoranis = new ArrayList<Restoran>();
			System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBB");
			for(Restoran restoran : restorani){
				if(restoran.getNazivRestoran().toLowerCase().contains(try_search.toLowerCase())){
					restoranis.add(restoran);	
					
				}
				
				
			}
			//request.setAttribute("restorani", restoranDao.showRestaurants());
			//System.out.println(restorani.get(0).getNazivRestoran());
			request.removeAttribute("restorani");
			//System.out.println(restoranis.get(0).getNazivRestoran());
			System.out.println("SSSSSSSSSSSSSSS");
			request.setAttribute("restorani", restoranis);
			getServletContext().getRequestDispatcher("/show_restaurants.jsp").forward(request, response);
		
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
