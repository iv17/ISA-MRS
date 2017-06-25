package server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import server.entity.Ponudjac;
import server.session.PonudjacDaoLocal;

/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
public class ReadPonudjacController extends HttpServlet {

	private static final long serialVersionUID = -2544396238785425302L;
	
	@EJB 
	private PonudjacDaoLocal ponudjacDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		
		Integer ponudjacId = null;
		
		if(session.getAttribute("ponudjac") != null){
			Ponudjac p = (Ponudjac) request.getSession().getAttribute("ponudjac");
			
			ponudjacId = p.getKorisnik_id();
			
			session.removeAttribute("ponudjac");
			session.setAttribute("ponudjac", ponudjacDao.findById(ponudjacId));
			
			response.sendRedirect("./home_page_ponudjac.jsp#my_profile");
			
		} else{
			
			RequestDispatcher disp = request.getRequestDispatcher("./login.jsp");
			disp.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}
}
