package server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import server.session.KonobarDaoLocal;
import server.session.ReonDaoLocal;

/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
public class PrepareCreateKonobarController extends HttpServlet {

	private static final long serialVersionUID = 5161949512237886627L;
	
	@EJB
	private ReonDaoLocal reonDao;
	@EJB
	private KonobarDaoLocal konobarDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("menadzer_restorana") != null){

			request.setAttribute("reoni", reonDao.findAll());
			RequestDispatcher disp = request.getRequestDispatcher("./CreateKonobarController");
			disp.forward(request, response);
			
		}
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
