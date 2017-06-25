package server.servlet;

import java.io.IOException;
import java.util.ArrayList;
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

public class ViewFriendRequestsController extends HttpServlet {

	private static Logger log = Logger.getLogger(ViewFriendRequestsController.class);

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
			if ((request.getAttribute("prijatelji")) != null) {
				request.removeAttribute("prijatelji");
			}
			
			if ((request.getAttribute("zahtevi")) != null) {
				request.removeAttribute("zahtevi");
			}
			
			
			List<Prijatelj> prijatelji = prijateljDao.findRequests(kor.getKorisnik_id(),"ne");
			List<Gost> zahtevi = new ArrayList<Gost>();
			//List<Gost> sortirani = new ArrayList<Gost>();
			for(Prijatelj p : prijatelji){
				System.out.println("gostiiii");
				Gost g = p.getJePrijatelj();
				zahtevi.add(g);
				
				
			}
			request.setAttribute("zahtevi", zahtevi);
			
			if(request.getSession().getAttribute("prihvacen") != null){
				request.getSession().removeAttribute("prihvacen");
				
			}
			
			if(request.getSession().getAttribute("odbijen") != null){
				request.getSession().removeAttribute("odbijen");
				
			}
			
			String s = (String)request.getAttribute("prihvatio");
			if(s != null){
				System.out.println("dodaoooooooo");
				request.getSession().setAttribute("prihvacen", s);
			}
			
			String ss = (String)request.getAttribute("odbijenp");
			if(ss != null){
				System.out.println("odbijen");
				request.getSession().setAttribute("odbijen", ss);
			}
			
			getServletContext().getRequestDispatcher("/show_friend_requests.jsp").forward(request, response);
		
		} catch (ServletException e) {
			log.error(e);
			throw e;
		} catch (IOException e) {
			log.error(e);
			throw e;
		}		
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}
