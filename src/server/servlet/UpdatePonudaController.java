package server.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import server.entity.Ponuda;
import server.entity.Ponudjac;
import server.session.NamirniceDaoLocal;
import server.session.PonudaDaoLocal;

/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
public class UpdatePonudaController extends HttpServlet {
	
	private static final long serialVersionUID = 4676416691336033321L;
	
	@EJB 
	private PonudaDaoLocal ponudaDao;
	@EJB 
	private NamirniceDaoLocal namirniceDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Double cena = null;
		String garancija = null;
		Date rokIsporuke = null;
		String ponudaId = null;
		String namirnicaId = null;
		
		HttpSession session = request.getSession();
		if(session.getAttribute("ponudjac") != null){
			ponudaId = (String) session.getAttribute("ponuda");
			
			namirnicaId = (String) session.getAttribute("namirnica");
			
			
			if( (request.getParameter("form-price") != null) 
					&& (!request.getParameter("form-price").equals(""))
					&& (request.getParameter("form-price").length() > 3)){
				cena = Double.parseDouble(request.getParameter("form-price"));
			}
			if( (request.getParameter("form-guarantee") != null) 
					&& (!request.getParameter("form-guarantee").equals(""))
					&& (request.getParameter("form-guarantee").length() > 3)){
				garancija = request.getParameter("form-guarantee");
			}
			if( (request.getParameter("form-delivery") != null) 
					&& (!request.getParameter("form-delivery").equals(""))
					&& (request.getParameter("form-delivery").length() == 10)){
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				try {
					rokIsporuke = sdf.parse(request.getParameter("form-delivery"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
			if ((ponudaId !=null) && (!ponudaId.equals(""))
					&& (namirnicaId !=null) && (!namirnicaId.equals(""))) {
				
				Ponuda ponuda  = new Ponuda();
				
				ponuda.setIdPonuda(Integer.parseInt(ponudaId));
				
				ponuda.setNamirnice(namirniceDao.findById(Integer.parseInt(namirnicaId)));
				
				if(cena != null) { ponuda.setCenaPonuda(cena);}
				if(garancija != null) { ponuda.setPonudaGarancija(garancija);}
				if(rokIsporuke != null) { ponuda.setPonudaRokIsporuke(rokIsporuke);}
				
				ponuda.setPonudjac((Ponudjac) session.getAttribute("ponudjac"));
				ponuda.setPonudaStanje(true);
				ponudaDao.merge(ponuda);
				
				response.sendRedirect("./ReadPonudaZaPonudjacaController");
			}

		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
