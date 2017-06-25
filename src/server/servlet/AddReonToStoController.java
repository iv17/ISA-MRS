package server.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import server.entity.MenadzerRestorana;
import server.entity.RasporedStolova;
import server.entity.Sto;
import server.session.RasporedDaoLocal;
import server.session.ReonDaoLocal;
import server.session.StoDaoLocal;

/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
public class AddReonToStoController extends HttpServlet {

	private static final long serialVersionUID = -2544396238785425302L;
	@EJB 
	RasporedDaoLocal rasporedDao;
	@EJB
	StoDaoLocal stoDao;
	@EJB
	ReonDaoLocal reonDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();
		if(session.getAttribute("menadzer_restorana") != null){

			MenadzerRestorana menadzer = (MenadzerRestorana) session.getAttribute("menadzer_restorana");
			List<RasporedStolova> rasporedi = (List<RasporedStolova>) session.getAttribute("rasporedi");
			String jsonRaspored = null;
			for (RasporedStolova rasporedStolova : rasporedi) {
				if(rasporedStolova.getAktivanRaspored() == true){
					jsonRaspored = rasporedStolova.getJsonRaspored();

					Integer brojMesta = null;
					Integer idReona = null;
					String top = null;
					String left = null;

					if( (request.getParameter("idReona") != null) 
							&& (!request.getParameter("idReona").equals(""))){
						idReona = Integer.parseInt(request.getParameter("idReona"));
					}
					if( (request.getParameter("top") != null) 
							&& (!request.getParameter("top").equals(""))){
						top = request.getParameter("top");
						System.out.println(top + " top");
					}
					if( (request.getParameter("left") != null) 
							&& (!request.getParameter("left").equals(""))){
						left = request.getParameter("left");
						System.out.println(left + " left");
					}
		
					session.removeAttribute("stolovi");
					session.setAttribute("stolovi", stoDao.findAll());
					
					List<Sto> stolovi = (List<Sto>) session.getAttribute("stolovi");
					for (Sto sto : stolovi) {
						if(sto.getStoJson().contains(top) && sto.getStoJson().contains(left)){
							if(sto.getStoJson().contains("table1.jpg")){brojMesta = 4;}
							if(sto.getStoJson().contains("table2.jpg")){brojMesta = 8;}
							if(sto.getStoJson().contains("table3.jpg")){brojMesta = 6;}
							Sto noviSto = new Sto();
							noviSto.setIdStola(sto.getIdStola());
							noviSto.setBrojMestaSto(brojMesta);
							if(idReona != null){noviSto.setReon(reonDao.findById(idReona));}

							stoDao.merge(noviSto);
							break;
						}
					}
				}
			}
			response.sendRedirect("./ReadRasporedStolovaController");
		} else {

			RequestDispatcher disp = request.getRequestDispatcher("./login.jsp");
			disp.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
