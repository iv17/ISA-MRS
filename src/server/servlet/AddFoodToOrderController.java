package server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import server.entity.Gost;
import server.entity.Jelo;
import server.entity.Narudzbina;
import server.entity.Obrok;
import server.session.JeloDaoLocal;
import server.session.NarudzbinaDaoLocal;

public class AddFoodToOrderController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(AddFoodToOrderController.class);
	
	@EJB
	NarudzbinaDaoLocal narudzbinaDao;
	
	@EJB
	JeloDaoLocal jeloDao;
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			Gost kor = (Gost)request.getSession().getAttribute("gost");
			if ((request.getSession().getAttribute("gost")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			
			Narudzbina narudzbina = (Narudzbina) request.getSession().getAttribute("narudzbina");
			if (narudzbina == null){
				response.sendRedirect(response.encodeURL("./error.jsp"));
				
			}
			String jelo = request.getParameter("jeloId");
			
			Obrok obrok = narudzbina.getObrok();
			System.out.println("ADADADADADADAD");
			Jelo porucenoJelo = jeloDao.findById(Integer.parseInt(jelo));
			
			obrok.getJela().add(porucenoJelo);
			System.out.println("GGGGGGGG");
			narudzbina.setObrok(obrok);
			narudzbinaDao.merge(narudzbina);
			
			getServletContext().getRequestDispatcher("/create_order.jsp").forward(request, response);
			
			
		
		} catch (IOException e) {
			log.error(e);
			throw e;
		}		
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	

}
