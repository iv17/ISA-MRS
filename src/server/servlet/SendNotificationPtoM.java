package server.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SendNotificationPtoM extends HttpServlet {

	private static final long serialVersionUID = -2544396238785425302L;

	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		

		//PROVERA DA LI JE ULOGOVAN MENADZER
		HttpSession session = request.getSession();
		if(session.getAttribute("ponudjac") != null){
			//content type must be set to text/event-stream
	        response.setContentType("text/event-stream");   
	 
	        //encoding must be set to UTF-8
	        response.setCharacterEncoding("UTF-8");
	       /*
	        PrintWriter writer = response.getWriter();
	        
	        writer.write("data from SendNotificationPtoM");
	 
	        writer.close();
	        */
	        response.sendRedirect("./ReadPonudaController");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}
}
