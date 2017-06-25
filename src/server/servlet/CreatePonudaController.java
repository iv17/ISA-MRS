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

import server.entity.Namirnice;
import server.entity.Ponuda;
import server.entity.Ponudjac;
import server.session.NamirniceDaoLocal;
import server.session.PonudaDaoLocal;

public class CreatePonudaController extends HttpServlet {

	private static final long serialVersionUID = -2544396238785425302L;
	
	@EJB 
	private PonudaDaoLocal ponudaDao;
	@EJB
	private NamirniceDaoLocal namirniceDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Double cena = null;
		String garancija = null;
		Date rokIsporuke = null;
		String namirniceId = null;
		
		HttpSession session = request.getSession();
		if(session.getAttribute("ponudjac") != null){
			
			namirniceId = (String) session.getAttribute("namirnica");
			System.out.println(namirniceId);
			if( (request.getParameter("form-price") != null) 
					&& (!request.getParameter("form-price").equals(""))){
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
			
			if ((namirniceId !=null) && (!namirniceId.equals(""))) {
				
				Ponuda ponuda  = new Ponuda();
				Namirnice namirnice = namirniceDao.findById(Integer.parseInt(namirniceId));
				ponuda.setNamirnice(namirniceDao.findById(Integer.parseInt(namirniceId)));
				
				if(cena != null) { ponuda.setCenaPonuda(cena);}
				if(garancija != null) { ponuda.setPonudaGarancija(garancija);}
				if(rokIsporuke != null) { ponuda.setPonudaRokIsporuke(rokIsporuke);}
			
				ponuda.setPonudaDatumSlanja(new Date());
				System.out.println(ponuda.getPonudaDatumSlanja() + " datumSlanja");
				ponuda.setPonudjac((Ponudjac) session.getAttribute("ponudjac"));
				if(ponuda.getPonudaDatumSlanja().compareTo(namirnice.getRokNamirnice()) <= 0){
					ponuda.setPonudaStanje(true);
					ponuda.setIzabrana(false);
					
					ponudaDao.persist(ponuda);
					
					response.sendRedirect("./ReadPonudaZaPonudjacaController");
				}
				
				
			} else{
				
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
