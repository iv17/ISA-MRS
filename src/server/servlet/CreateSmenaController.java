package server.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import server.entity.MenadzerRestorana;
import server.entity.Restoran;
import server.entity.Smena;
import server.session.KorisnikDaoLocal;
import server.session.ReonDaoLocal;
import server.session.SmenaDaoLocal;

/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
public class CreateSmenaController extends HttpServlet {

	private static final long serialVersionUID = -2544396238785425302L;

	@EJB
	private KorisnikDaoLocal radnikDao;
	@EJB
	private SmenaDaoLocal smenaDao;
	@EJB
	private ReonDaoLocal reonDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		
		Date smena_od = null;
		Date smena_do = null;
		String title = null;
		Integer idRadnika = null;
		Integer idReona = null;
		//PROVERA DA LI JE ULOGOVAN MENADZER
		HttpSession session = request.getSession();
		if(session.getAttribute("menadzer_restorana") != null){
		
			MenadzerRestorana menadzer = (MenadzerRestorana) session.getAttribute("menadzer_restorana");
			Restoran restoran = menadzer.getRestoran();
			
			if( (request.getParameter("dOD") != null) 
					&& (!request.getParameter("dOD").equals(""))){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
				try {
					smena_od = sdf.parse(request.getParameter("dOD"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}		
			if( (request.getParameter("dDO") != null) 
					&& (!request.getParameter("dDO").equals(""))){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
				try {
					smena_do = sdf.parse(request.getParameter("dDO"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
			if( (request.getParameter("idRad") != null) 
					&& (!request.getParameter("idRad").equals(""))){
				idRadnika = Integer.parseInt(request.getParameter("idRad"));
			}
			
			if( (request.getParameter("imeRad") != null) 
					&& (!request.getParameter("imeRad").equals(""))){
				title = request.getParameter("imeRad");
			}
			
			if( (request.getParameter("rId") != null) 
					&& (!request.getParameter("rId").equals(""))){
				idReona = Integer.parseInt(request.getParameter("rId"));
			}
			
			Smena s = smenaDao.findSmena(smena_od, smena_do);
			if(s == null){
				Smena smena = new Smena();
				
				if(smena_od != null){smena.setSmena_od(smena_od);}
				if(smena_do != null){smena.setSmena_do(smena_do);}
				if(idRadnika != null){smena.setKorisnik(radnikDao.findById(idRadnika));}
				if(idReona != null){smena.setReon(reonDao.findById(idReona));}
				
				smena.setTrenutna_nedelja(true);
				smena.setRestoran(restoran);
				
				smenaDao.persist(smena);
				
				response.sendRedirect("./ReadSmenaController");
				
			}
			else {
				response.sendRedirect("./error.jsp");
			}
			
		} else {
			
			RequestDispatcher disp = request.getRequestDispatcher("./login.jsp");
			disp.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
 