package server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import server.session.JelovnikDaoLocal;
import server.session.KartaPicaDaoLocal;
import server.session.RestoranDaoLocal;

/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
public class PrepareUpdateRestoranController extends HttpServlet {

	private static final long serialVersionUID = 1069341894540010096L;
	
	@EJB 
	private RestoranDaoLocal restoranDao;
	@EJB
	private JelovnikDaoLocal jelovnikDao;
	@EJB
	private KartaPicaDaoLocal kartaPicaDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if(request.getSession().getAttribute("menadzer_restorana") != null){
			
			session.removeAttribute("jelovnici");
			session.setAttribute("jelovnici", jelovnikDao.findAll());
			
			session.removeAttribute("kartePica");
			session.setAttribute("kartePica", kartaPicaDao.findAll());
			
			String restoranId = request.getParameter("idRestorana");
			
			if ((restoranId != null) && (!restoranId.equals(""))) {
				session.removeAttribute("restoran");
				session.setAttribute("restoran", restoranDao.findById(Integer.parseInt(restoranId)));
				
				response.sendRedirect("./home_page_menadzer_restorana.jsp#update_restaurant");
			}
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}