package server.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RedirectToRegisterController extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
			HttpSession session = request.getSession(true);
			if(session.getAttribute("exists") != null){
				
				session.removeAttribute("exists");
				
			}
			if(session.getAttribute("nopass") != null){
				
				session.removeAttribute("nopass");
				
			}
			if(session.getAttribute("missing") != null){
	
				session.removeAttribute("missing");
	
			}
			
			
			
			response.sendRedirect(response.encodeRedirectURL("./registration.jsp"));
			
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}





