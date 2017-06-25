package server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import server.session.NamirniceDaoLocal;

public class ReadNamirniceController extends HttpServlet {

	private static final long serialVersionUID = -2544396238785425302L;
	
	@EJB 
	private NamirniceDaoLocal namirniceDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("menadzer_restorana") != null){
			
			//PONOVO PROCITAJ IZ BAZE SVE KONOBARE
			session.removeAttribute("namirnice");
			session.setAttribute("namirnice", namirniceDao.findAll());
			
			response.sendRedirect("./home_page_menadzer_restorana.jsp#necessities");
			//response.sendRedirect("./home_page_ponudjac.jsp#announcements");
			
		} 
		/*if(session.getAttribute("ponudjac") != null){
			
			//PONOVO PROCITAJ IZ BAZE SVE KONOBARE
			session.removeAttribute("namirnice");
			session.setAttribute("namirnice", namirniceDao.findAll());
			
			//response.sendRedirect("./home_page_menadzer_restorana.jsp#necessities");
			response.sendRedirect("./home_page_ponudjac.jsp#announcements");
			
		} */
		else{
			
			RequestDispatcher disp = request.getRequestDispatcher("./login.jsp");
			disp.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}
}
