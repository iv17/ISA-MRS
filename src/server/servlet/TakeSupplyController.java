package server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
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
import server.session.PonudjacDaoLocal;

/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
public class TakeSupplyController extends HttpServlet {

	private static final long serialVersionUID = 5161949512237886627L;
	@EJB
	private PonudaDaoLocal ponudaDao;
	@EJB
	private NamirniceDaoLocal namirniceDao;
	@EJB
	private PonudjacDaoLocal ponudjacDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("menadzer_restorana") != null){
			
			String ponudaId = request.getParameter("ponudaId");
			String namirnicaId = request.getParameter("namirnicaId");
			if ((ponudaId != null) && (!ponudaId.equals("")) && (namirnicaId != null) && (!namirnicaId.equals(""))) {
				
				Namirnice namirnice = new Namirnice();
				namirnice.setIdNamirnice(Integer.parseInt(namirnicaId));
				namirnice.setIzabranaPonuda(ponudaDao.findById(Integer.parseInt(ponudaId)));
				namirniceDao.merge(namirnice);
				
				Ponuda p = ponudaDao.findById(Integer.parseInt(ponudaId));
				Ponudjac pp = p.getPonudjac();
				Integer id = pp.getKorisnik_id();
				
				Ponudjac noviPonudjac = new Ponudjac();
				noviPonudjac.setKorisnik_id(id);
				noviPonudjac.setFleg(1);
				ponudjacDao.merge(noviPonudjac);
				
				Ponuda ponuda  = new Ponuda();
				ponuda.setIdPonuda(Integer.parseInt(ponudaId));
				ponuda.setIzabrana(true);
				ponudaDao.merge(ponuda);
				response.sendRedirect("./ReadPonudaController");
			}	
		}
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
