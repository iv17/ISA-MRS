package server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import server.entity.Restoran;
import server.session.RestoranDaoLocal;

public class CreateRestaurantController extends HttpServlet {
	private static final long serialVersionUID = -2544396238785425302L;
	
	private static Logger log = Logger.getLogger(CreateRestaurantController.class);

	@EJB
	private RestoranDaoLocal restoranDao;
	/**
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String restoran_tip = null;
			String restoran_naziv = null;

			if ((request.getSession().getAttribute("menadzer_sistema")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}

			if ((request.getParameter("restoran_tip") != null) && (!"".equals(request.getParameter("restoran_tip")))) {
				restoran_tip = request.getParameter("restoran_tip");
			}

			if ((request.getParameter("restoran_naziv") != null) && (!"".equals(request.getParameter("restoran_naziv")))) {
				restoran_naziv = request.getParameter("restoran_naziv");
			}

			Restoran r = new Restoran();

			if (restoran_tip != null)
				r.setTipRestoran(restoran_tip);

			if (restoran_naziv != null)
				r.setNazivRestoran(restoran_naziv);

			restoranDao.persist(r);

			//getServletContext().getRequestDispatcher("/ReadController").forward(request, response);
			return;
			
		} /*catch (ServletException e) {
			log.error(e);
			throw e;
		}*/ catch (IOException e) {
			log.error(e);
			throw e;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
