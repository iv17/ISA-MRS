package server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import server.session.NamirniceDaoLocal;
import server.session.PonudaDaoLocal;

/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
public class PrepareCreatePonudaController extends HttpServlet {

	private static final long serialVersionUID = 5161949512237886627L;
	@EJB
	private PonudaDaoLocal ponudaDao;
	@EJB
	private NamirniceDaoLocal namirniceDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("ponudjac") != null){
			String namirnicaId = request.getParameter("namirnicaId");
			
			if ((namirnicaId != null) && (!namirnicaId.equals(""))) {
				session.setAttribute("namirnica",namirnicaId);
				getServletContext().getRequestDispatcher("/home_page_ponudjac_create.jsp").forward(request, response);
			}	
		}
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
