package server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.entity.MenadzerRestorana;
import server.entity.Restoran;
import server.session.JelovnikDaoLocal;
import server.session.KartaPicaDaoLocal;
import server.session.RestoranDaoLocal;

/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
public class UpdateRestoranController extends HttpServlet {

	private static final long serialVersionUID = 4676416691336033321L;

	@EJB
	private RestoranDaoLocal restoranDao;

	@EJB
	private KartaPicaDaoLocal kartaPicaDao;

	@EJB
	private JelovnikDaoLocal jelovnikDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String restoranId = null;
		String tip = null;
		String naziv = null;
		Integer kartaPicaId = null;
		Integer jelovnikId = null;

		if(request.getSession().getAttribute("menadzer_restorana") != null){

			MenadzerRestorana m = (MenadzerRestorana) request.getSession().getAttribute("menadzer_resorana");
			Restoran rr = m.getRestoran();
	
			restoranId = String.valueOf(rr.getIdRestorana());
			
			if((request.getParameter("form-name-restoran") != null) 
					&& (!request.getParameter("form-name-restoran").equals(""))
					&& (request.getParameter("form-name-restoran").length() > 3)){
				naziv = request.getParameter("form-name-restoran");
			}
			if((request.getParameter("form-type") != null) 
					&& (!request.getParameter("form-type").equals(""))
					&& (request.getParameter("form-type").length() > 3)){
				tip = request.getParameter("form-type");
			}
			if((request.getParameter("form-menu") != null) 
					&& (!request.getParameter("form-menu").equals(""))
					&& (request.getParameter("form-menu").length() > 3)){
				jelovnikId = Integer.parseInt(request.getParameter("form-menu"));
			}
			if((request.getParameter("form-drink_cart") != null) 
					&& (!request.getParameter("form-drink_cart").equals(""))
					&& (request.getParameter("form-drink_cart").length() > 3)){
				kartaPicaId = Integer.parseInt(request.getParameter("form-drink_cart"));
			}

			if((restoranId != null) && (!restoranId.equals(""))){

				Restoran restoran = new Restoran();

				restoran.setIdRestorana(Integer.parseInt(restoranId));
				if(naziv != null){ restoran.setNazivRestoran(naziv); }
				if(tip != null){restoran.setTipRestoran(tip);}
				if(jelovnikId != null){ restoran.setJelovnik(jelovnikDao.findById(jelovnikId));}
				if(kartaPicaId != null) {restoran.setKartaPica(kartaPicaDao.findById(kartaPicaId));}

				restoranDao.merge(restoran);
				
			}
			
			response.sendRedirect("./ReadRestoranController");
			
		} else {
			
			RequestDispatcher disp = request.getRequestDispatcher("./login.jsp");
			disp.forward(request, response);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
