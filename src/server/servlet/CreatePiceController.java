package server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import server.entity.Pice;
import server.session.KartaPicaDaoLocal;
import server.session.PiceDaoLocal;

/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
public class CreatePiceController extends HttpServlet {

	private static final long serialVersionUID = -2544396238785425302L;

	@EJB
	private PiceDaoLocal piceDao;

	@EJB
	private KartaPicaDaoLocal kartaPicaDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String naziv = null;
		String opis = null;
		Double cena = null;
		String tip = null;
		Integer kartaPicaId = null;

		//PROVERA DA LI JE ULOGOVAN MENADZER
		HttpSession session = request.getSession();
		if(session.getAttribute("menadzer_restorana") != null){

			if( (request.getParameter("form-name-drink") != null) 
					&& (!request.getParameter("form-name-drink").equals(""))
					&& (request.getParameter("form-name-drink").length() > 3)){
				naziv = request.getParameter("form-name-drink");
			}
			if( (request.getParameter("form-desc-drink") != null) 
					&& (!request.getParameter("form-desc-drink").equals(""))
					&& (request.getParameter("form-desc-drink").length() > 10)){
				opis = request.getParameter("form-desc-drink");
			}
			if( (request.getParameter("form-price-drink") != null) 
					&& (!request.getParameter("form-price-drink").equals(""))){
				cena = Double.parseDouble(request.getParameter("form-price-drink"));
			}
			if( (request.getParameter("form-type") != null) 
					&& (!request.getParameter("form-type").equals(""))){
				tip = request.getParameter("form-type");
			}
			if( (request.getParameter("form-drink_cart") != null) 
					&& (!request.getParameter("form-drink_cart").equals(""))){
				kartaPicaId = Integer.parseInt(request.getParameter("form-drink_cart"));
			}

			Pice pice = new Pice();

			if(naziv != null){ pice.setNazivPica(naziv);}
			if(opis != null){ pice.setOpisPica(opis);}
			if(cena != null){ pice.setCenaPica(cena);}
			if(tip != null){ pice.setTipPica(tip);}
			if(kartaPicaId != null){ pice.setKartaPica(kartaPicaDao.findById(kartaPicaId));}

			piceDao.persist(pice);
			
			response.sendRedirect("./ReadPiceController");

		} else {
			
			RequestDispatcher disp = request.getRequestDispatcher("./login.jsp");
			disp.forward(request, response);
			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}
}
