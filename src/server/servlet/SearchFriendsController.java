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

public class SearchFriendsController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(SearchFriendsController.class);
	@EJB
	private PrijateljDaoLocal prijateljDao;
	
	
	@EJB
	private GostDaoLocal gostDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			//HttpSession session = request.getSession();
			if ((request.getSession().getAttribute("gost")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			
			Gost kor = (Gost)request.getSession().getAttribute("gost");
			System.out.println("TUTUTUTTUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
			String try_search = request.getParameter("search_fr");
			System.out.println(try_search);
			System.out.println("CCCCCCCCCCCCCCCCCCCCCCC");
			List<Prijatelj> prijatelji = prijateljDao.findFriends(kor.getKorisnik_id(),"da");
			System.out.println(prijatelji.size());
			List<Gost> sviPrijatelji = new ArrayList<Gost>();
			for(Prijatelj p : prijatelji){
				int idp = p.getGlavniPrijatelj();
				Gost g = gostDao.findById(idp);
				sviPrijatelji.add(g);	
			}
			System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBB");
			List<Gost> prijateljis = new ArrayList<Gost>();
			for(Gost prijatelj : sviPrijatelji){
				if(prijatelj.getKorisnik_ime().toLowerCase().contains(try_search.toLowerCase()) || prijatelj.getKorisnik_prezime().toLowerCase().contains(try_search.toLowerCase())){
					prijateljis.add(prijatelj);	
					
				}
				
				
			}
			//request.setAttribute("restorani", restoranDao.showRestaurants());
			//System.out.println(restorani.get(0).getNazivRestoran());
			request.removeAttribute("prijatelji");
			//System.out.println(restoranis.get(0).getNazivRestoran());
			System.out.println("SSSSSSSSSSSSSSS");
			request.setAttribute("prijatelji", prijateljis);
			getServletContext().getRequestDispatcher("/show_friends.jsp").forward(request, response);
		
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
