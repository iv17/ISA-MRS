package server.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import server.entity.Gost;
import server.entity.Prijatelj;
import server.session.GostDaoLocal;
import server.session.PrijateljDaoLocal;

public class DeclineFriendController extends HttpServlet {

	
	private static Logger log = Logger.getLogger(DeclineFriendController.class);

	private static final long serialVersionUID = 1L;
	@EJB
	private PrijateljDaoLocal prijateljDao;
	
	@EJB
	private GostDaoLocal gostDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			
			Gost kor = (Gost)request.getSession().getAttribute("gost");
			if ((request.getSession().getAttribute("gost")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			
			
			
			System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV");
			String pid = request.getParameter("prijateljId");
			System.out.println(pid);
			//pronadji prijatelja koji se menja
			List<Prijatelj> lista_p = prijateljDao.removeFriend(Integer.parseInt(pid), kor.getKorisnik_id());
			Prijatelj odbijen = lista_p.get(0);
			//prihvacen.setJePrijatelj(kor);
			prijateljDao.remove(odbijen);
			
			//Gost novi = gostDao.findById(Integer.parseInt(pid));
			String s = "da";
			
			request.setAttribute("odbijenp", s);
			getServletContext().getRequestDispatcher("/ViewFriendRequestsController").forward(request, response);
			
			//response.sendRedirect(response.encodeRedirectURL("./ViewFriendRequestsController"));
			
			
			
		
		} catch (IOException e) {
			log.error(e);
			throw e;
		}		
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
}
