package server.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
public class PrepareUpdatePonudaController extends HttpServlet {

	private static final long serialVersionUID = 1069341894540010096L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("ponudjac") != null){
			String ponudaId = request.getParameter("ponudaId");
			System.out.println(ponudaId);
			String namirnicaId = request.getParameter("namirnicaId");
			System.out.println(namirnicaId);
			if ((ponudaId != null) && (!ponudaId.equals("")) &&
					(namirnicaId != null) && (!namirnicaId.equals(""))) {
				session.setAttribute("ponuda", ponudaId);
				session.setAttribute("namirnica", namirnicaId);
				//response.sendRedirect("./home_page_ponudjac_update.jsp");
				
				getServletContext().getRequestDispatcher("/home_page_ponudjac_update.jsp").forward(request, response);
			}	
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}