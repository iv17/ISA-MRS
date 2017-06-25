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

import server.entity.KartaPica;
import server.session.KartaPicaDaoLocal;

/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
public class CreateKartaPicaController extends HttpServlet {

	private static final long serialVersionUID = -2544396238785425302L;

	@EJB
	private KartaPicaDaoLocal kartaPicaDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String naziv = null;
		Date datumOd = null;
		Date datumDo = null;

		//PROVERA DA LI JE ULOGOVAN MENADZER
		HttpSession session = request.getSession();
		if(session.getAttribute("menadzer_restorana") != null){
			if( (request.getParameter("form-name-drink_cart") != null) 
					&& (!request.getParameter("form-name-drink_cart").equals(""))
					&& (request.getParameter("form-name-drink_cart").length() > 3)){
				naziv = request.getParameter("form-name-drink_cart");
			}
			if( (request.getParameter("form-date1-drink_cart") != null) 
					&& (!request.getParameter("form-date1-drink_cart").equals(""))
					&& (request.getParameter("form-date1-drink_cart").length() == 10)){
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				try {
					datumOd = sdf.parse(request.getParameter("form-date1-drink_cart"));
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}
			}
			if( (request.getParameter("form-date2-drink_cart") != null) 
					&& (!request.getParameter("form-date2-drink_cart").equals(""))
					&& (request.getParameter("form-date2-drink_cart").length() == 10)){
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				try {
					datumDo = sdf.parse(request.getParameter("form-date2-drink_cart"));
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}
			}

			KartaPica kartaPica = new KartaPica();
			if(naziv != null) { kartaPica.setNazivKartaPica(naziv);}
			if(datumOd != null) { kartaPica.setDatumOdKartaPica(datumOd);}
			if(datumDo != null) { kartaPica.setDatumDoKartaPica(datumDo);}

			kartaPicaDao.persist(kartaPica);
			
			response.sendRedirect("./ReadKartaPicaController");
			
		}else {
			RequestDispatcher disp = request.getRequestDispatcher("./login.jsp");
			disp.forward(request, response);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}
}
