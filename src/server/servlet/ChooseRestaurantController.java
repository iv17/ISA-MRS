package server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import server.entity.Restoran;
import server.session.RestoranDaoLocal;

public class ChooseRestaurantController extends HttpServlet{
	
	private static Logger log = Logger.getLogger(ChooseRestaurantController.class);

	private static final long serialVersionUID = 1L;
	@EJB
	private RestoranDaoLocal restoranDao;
	
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			//Gost kor = (Gost)request.getSession().getAttribute("gost");
			if ((request.getSession().getAttribute("gost")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			
			if ((request.getSession().getAttribute("izabraniRestoran")) != null) {
				request.getSession().removeAttribute("izabraniRestoran");
			}
			
			if ((request.getSession().getAttribute("wrong_time")) != null) {
				request.getSession().removeAttribute("wrong_time");
			}
			
			if ((request.getSession().getAttribute("error_time")) != null) {
				request.getSession().removeAttribute("error_time");
			}
			String izabrani = request.getParameter("restoran");
			System.out.println(izabrani + "iiiiiiiiiiiiiiiiiiiii");
			Restoran res = restoranDao.findById(Integer.parseInt(izabrani));
			System.out.println(res.getNazivRestoran());
			System.out.println("KKKKKKKKKKKKKKKKKKKK");
			
			
			request.getSession().setAttribute("izabraniRestoran", res);
			
			//Boolean err = true;
			//request.getSession().setAttribute("error_time", err);
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			//getServletContext().getRequestDispatcher("/AddFriendsController").forward(request, response);
			
			response.sendRedirect(response.encodeRedirectURL("./choose_time.jsp"));
			
			//RequestDispatcher disp = request.getRequestDispatcher("/choose_time.jsp");
			//disp.forward(request, response);
		
		} catch (IOException e) {
			log.error(e);
			throw e;
		}		
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}



	

}
