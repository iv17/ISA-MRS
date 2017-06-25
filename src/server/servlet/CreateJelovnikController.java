package server.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import server.entity.Jelovnik;
import server.session.JelovnikDaoLocal;

/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
public class CreateJelovnikController extends HttpServlet {

	private static final long serialVersionUID = -2544396238785425302L;

	@EJB 
	private JelovnikDaoLocal jelovnikDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String naziv = null;
		Date datumOd = null;
		Date datumDo = null;

		//PROVERA DA LI JE ULOGOVAN MENADZER
		HttpSession session = request.getSession();
		if(session.getAttribute("menadzer_restorana") != null){
			if( (request.getParameter("form-name-menu") != null) 
					&& (!request.getParameter("form-name-menu").equals(""))
					&& (request.getParameter("form-name-menu").length() > 3 )){
				naziv = request.getParameter("form-name-menu");
			}
			if( (request.getParameter("form-date1-menu") != null) 
					&& (!request.getParameter("form-date1-menu").equals(""))
					&& (request.getParameter("form-date1-menu").length() == 10)){
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				try {
					datumOd = sdf.parse(request.getParameter("form-date1-menu"));
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}
			}
			if( (request.getParameter("form-date2-menu") != null) 
					&& (!request.getParameter("form-date2-menu").equals(""))
					&& (request.getParameter("form-date2-menu").length() == 10)){
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				try {
					datumDo = sdf.parse(request.getParameter("form-date2-menu"));
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}
			}
			
			Jelovnik jelovnik = new Jelovnik();
			if(naziv != null) { jelovnik.setNazivJelovnik(naziv);}
			if(datumOd != null) { jelovnik.setDatumOdJelovnik(datumOd);}
			if(datumDo != null) { jelovnik.setDatumDoJelovnik(datumDo);}
			
			jelovnikDao.persist(jelovnik);

			response.sendRedirect("./ReadJelovnikController");
			
		} else {
			
			RequestDispatcher disp = request.getRequestDispatcher("./login.jsp");
			disp.forward(request, response);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}
}
