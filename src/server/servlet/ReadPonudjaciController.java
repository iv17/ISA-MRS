package server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import server.session.PonudjacDaoLocal;

/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
public class ReadPonudjaciController extends HttpServlet {

	private static final long serialVersionUID = -2544396238785425302L;
	
	@EJB 
	private PonudjacDaoLocal ponudjacDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("menadzer_restorana") != null){
			
			session.removeAttribute("ponudjaci");
			session.setAttribute("ponudjaci", ponudjacDao.findAll());
			
			response.sendRedirect("./home_page_menadzer_restorana.jsp#suppliers");
			
		} else{
			
			RequestDispatcher disp = request.getRequestDispatcher("./login.jsp");
			disp.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}
}
