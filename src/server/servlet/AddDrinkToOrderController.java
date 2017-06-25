package server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import server.entity.Gost;
import server.entity.Narudzbina;
import server.entity.Pice;
import server.entity.PorucenoPice;
import server.session.NarudzbinaDaoLocal;
import server.session.PiceDaoLocal;

public class AddDrinkToOrderController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private static Logger log = Logger.getLogger(AddDrinkToOrderController.class);
	
	@EJB
	NarudzbinaDaoLocal narudzbinaDao;
	
	@EJB
	PiceDaoLocal piceDao;
	
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
			String piceid = request.getParameter("piceId");
			PorucenoPice poruceno = narudzbina.getPorucenoPice();
			
			
			System.out.println("ADADADADADADAD");
			Pice porucenoPice = piceDao.findById(Integer.parseInt(piceid));
			
			poruceno.getPica().add(porucenoPice);
			System.out.println("GGGGGGGG");
			narudzbina.setPorucenoPice(poruceno);
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
