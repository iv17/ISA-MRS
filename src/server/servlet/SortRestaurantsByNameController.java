package server.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import server.entity.Restoran;
import server.session.RestoranDaoLocal;

public class SortRestaurantsByNameController extends HttpServlet{

	/**
	 * 
	 */
	private static Logger log = Logger.getLogger(SortRestaurantsByNameController.class);

	private static final long serialVersionUID = 1L;
	@EJB
	private RestoranDaoLocal restoranDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if ((request.getSession().getAttribute("gost")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			System.out.println("TUTUTUTTUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
			List<Restoran> restorani = restoranDao.showRestaurants();
			
			List<Restoran> sortirani = new ArrayList<Restoran>(restorani);
			
			Collections.sort(sortirani, new TestComparator());
			
			if(request.getAttribute("restorani") != null){
				request.removeAttribute("restorani");
				
			}
			request.setAttribute("restorani", sortirani);
			System.out.println(restorani.get(0).getNazivRestoran());
			System.out.println("SAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
			
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
	

	public class TestComparator implements Comparator<Object> {
	
		    public int compare(Object obj1, Object obj2){
		        Restoran p1=(Restoran) obj1;
		        Restoran p2=(Restoran) obj2;
		       
		        String p1name=p1.getNazivRestoran();
		        String p2name=p2.getNazivRestoran();
		       
		        return p1name.compareTo(p2name);
		    }
		


	}
	
}
