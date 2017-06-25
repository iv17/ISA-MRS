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

import server.entity.Namirnice;
import server.session.NamirniceDaoLocal;

public class CreateNamirniceController extends HttpServlet {

	private static final long serialVersionUID = -2544396238785425302L;

	@EJB
	private NamirniceDaoLocal namirniceDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {

		String naziv = null;
		String opis = null;
		Integer kolicina = null;
		Date rok = null;

		//PROVERA DA LI JE ULOGOVAN MENADZER
		HttpSession session = request.getSession();
		if(session.getAttribute("menadzer_restorana") != null){
			if( (request.getParameter("form-name-necessity") != null) 
					&& (!request.getParameter("form-name-necessity").equals(""))
					&& (request.getParameter("form-name-necessity").length() > 3)){
				naziv = request.getParameter("form-name-necessity");
			}
			if( (request.getParameter("form-desc-necessity") != null) 
					&& (!request.getParameter("form-desc-necessity").equals(""))
					&& (request.getParameter("form-desc-necessity").length() > 3)){
				opis = request.getParameter("form-desc-necessity");
			}
			if( (request.getParameter("form-quantity-necessity") != null) 
					&& (!request.getParameter("form-quantity-necessity").equals(""))){
				kolicina = Integer.parseInt(request.getParameter("form-quantity-necessity"));
			}
			if( (request.getParameter("form-time-necessity") != null) 
					&& (!request.getParameter("form-time-necessity").equals(""))
					&& (request.getParameter("form-time-necessity").length() == 10)){
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				try {
					rok = sdf.parse(request.getParameter("form-time-necessity"));
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}
			}
			
			Namirnice namirnice = new Namirnice();
			if(naziv != null){ namirnice.setNazivNamirnice(naziv);}
			if(opis != null){ namirnice.setOpis_namirnice(opis);}
			if(kolicina != null){namirnice.setKolicinaNamirnice(kolicina);}
			if(rok != null){namirnice.setRokNamirnice(rok);}
			namirnice.setIzabranaPonuda(null);
			
			namirniceDao.persist(namirnice);
		
			response.sendRedirect("./ReadNamirniceController");
			
		} else {
			
			RequestDispatcher disp = request.getRequestDispatcher("./login.jsp");
			disp.forward(request, response);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}
}
