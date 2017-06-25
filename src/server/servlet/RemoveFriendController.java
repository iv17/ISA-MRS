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

public class RemoveFriendController extends HttpServlet{
	
	private static Logger log = Logger.getLogger(RemoveFriendController.class);

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
			//pronadji onog za brisanje iz gostiju
			
			System.out.println(pid);
			List<Prijatelj> lista_brisi = prijateljDao.removeFriend(kor.getKorisnik_id(),Integer.parseInt(pid));
			Prijatelj brisi = lista_brisi.get(0);
			if(brisi == null){
				System.out.println("BRISI JE NULL");
			}
			
			prijateljDao.remove(brisi);
			
			List<Prijatelj> lista_brisi2 = prijateljDao.removeFriend(Integer.parseInt(pid), kor.getKorisnik_id());
			Prijatelj brisi2 = lista_brisi2.get(0);
			prijateljDao.remove(brisi2);
			
			
			response.sendRedirect(response.encodeRedirectURL("./ShowFreindsController"));
			
			/*List<Prijatelj> prij = prijateljDao.findFriends(kor.getKorisnik_id(),"da");
			request.setAttribute("prijatelji", prij);
			getServletContext().getRequestDispatcher("/show_friends.jsp").forward(request, response);
		*/
		
		} catch (IOException e) {
			log.error(e);
			throw e;
		}		
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
