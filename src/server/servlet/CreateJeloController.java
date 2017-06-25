package server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import server.entity.Jelo;
import server.session.JeloDaoLocal;
import server.session.JelovnikDaoLocal;

/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
public class CreateJeloController extends HttpServlet {

	private static final long serialVersionUID = -2544396238785425302L;

	@EJB 
	private JeloDaoLocal jeloDao;

	@EJB
	private JelovnikDaoLocal jelovnikDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String naziv = null;
		String opis = null;
		Double cena = null;
		String tip = null;
		Integer jelovnikId = null;

		HttpSession session = request.getSession();
		if(session.getAttribute("menadzer_restorana") != null){

			if( (request.getParameter("form-name-meal") != null) 
					&& (!request.getParameter("form-name-meal").equals(""))
					&& (request.getParameter("form-name-meal").length() > 3)){
				naziv = request.getParameter("form-name-meal");
			}
			if( (request.getParameter("form-desc-meal") != null) 
					&& (!request.getParameter("form-desc-meal").equals(""))
					&& (request.getParameter("form-desc-meal").length() > 10)){
				opis = request.getParameter("form-desc-meal");
			}
			if( (request.getParameter("form-price-meal") != null) 
					&& (!request.getParameter("form-price-meal").equals(""))){
				cena = Double.parseDouble(request.getParameter("form-price-meal"));
			}
			if( (request.getParameter("form-type") != null) 
					&& (!request.getParameter("form-type").equals(""))){
				tip = request.getParameter("form-type");
			}
			if( (request.getParameter("form-menu") != null) 
					&& (!request.getParameter("form-menu").equals(""))){
				jelovnikId = Integer.parseInt(request.getParameter("form-menu"));
			}
			
			Jelo jelo = new Jelo();
			
			if(naziv != null){ jelo.setNazivJela(naziv);}
			if(opis != null){ jelo.setOpisJela(opis);}
			if(cena != null){ jelo.setCenaJela(cena);}
			if(tip != null){ jelo.setTipJela(tip);}
			if(jelovnikId != null){ jelo.setJelovnik(jelovnikDao.findById(jelovnikId));}
			
			jeloDao.persist(jelo);
			
			response.sendRedirect("./ReadJeloController");
			
		} else {
			RequestDispatcher disp = request.getRequestDispatcher("./login.jsp");
			disp.forward(request, response);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}
}
