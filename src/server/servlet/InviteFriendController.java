package server.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.entity.Gost;
import server.entity.Rezervacija;
import server.session.GostDaoLocal;
import server.session.RezervacijaDaoLocal;

public class InviteFriendController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private RezervacijaDaoLocal rezervacijaDao;
	
	@EJB
	private GostDaoLocal gostDao;

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
			
			//Gost kor = (Gost)request.getSession().getAttribute("gost");
			if ((request.getSession().getAttribute("gost")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			
			
			String dodavanje = request.getParameter("korisnikId");
			Rezervacija rez = (Rezervacija) request.getSession().getAttribute("rezervacija");
			Set<Gost> pozvani = rez.getPozvaniGosti();
			@SuppressWarnings("unchecked")
			List<Gost> prijatelji = (List<Gost>)request.getSession().getAttribute("svi_prijatelji");
			Gost pozvaniGost = gostDao.findById(Integer.parseInt(dodavanje));
			
			
			prijatelji.remove(pozvaniGost);
			request.getSession().setAttribute("svi_prijatelji", prijatelji);
			System.out.println("NNNNNNNNNNNNN");
			System.out.println(rez.getPozvaniGosti());
			System.out.println(rez.getPozvaniGosti().size());
			rez.getPozvaniGosti().add(pozvaniGost);
			//pozvaniGost.getPozvaniZaDogadjaj().add(rez);
			rezervacijaDao.merge(rez);
			request.setAttribute("prijatelji_pozivi", prijatelji);
			if(request.getSession().getAttribute("poslat_zahtev") != null){
				request.getSession().removeAttribute("poslat_zahtev");
				
			}
			Boolean zahtev = true;
			
			request.getSession().setAttribute("poslat_zahtev",zahtev);
			
			getServletContext().getRequestDispatcher("/invite_friends.jsp").forward(request, response);
			
		}

protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
	doGet(request, response);
}
	
}
