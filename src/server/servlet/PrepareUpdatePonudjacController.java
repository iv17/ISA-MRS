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
public class PrepareUpdatePonudjacController extends HttpServlet {

	private static final long serialVersionUID = 1069341894540010096L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("ponudjac") != null)
		{
			String ponudjacId = request.getParameter("idPonudjaca");
			
			if ((ponudjacId != null) && (!ponudjacId.equals(""))) {
				response.sendRedirect("./home_page_ponudjac.jsp#update_profile");
			}
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}