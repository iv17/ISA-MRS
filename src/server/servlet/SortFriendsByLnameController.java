package server.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

public class SortFriendsByLnameController extends HttpServlet {

	private static Logger log = Logger.getLogger(SortFriendsByLnameController.class);

	private static final long serialVersionUID = 1L;
	@EJB
	private PrijateljDaoLocal prijateljDao;
	
	@EJB
	private GostDaoLocal gostDao;
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if ((request.getSession().getAttribute("gost")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}

			Gost kor = (Gost)request.getSession().getAttribute("gost");
			System.out.println("TUTUTUTTUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
			List<Prijatelj> prijatelji = prijateljDao.findFriends(kor.getKorisnik_id(),"da");
			
			List<Gost> sortirani = new ArrayList<Gost>();
			for(Prijatelj p : prijatelji){
				System.out.println("gostiiii");
				int idp = p.getGlavniPrijatelj();
				Gost g = gostDao.findById(idp);
				sortirani.add(g);
				
				
			}
			
			Collections.sort(sortirani, new TestComparatorFriendLname());
			
			if(request.getAttribute("prijatelji") != null){
				request.removeAttribute("prijatelji");
				
			}
			request.setAttribute("prijatelji", sortirani);
			//System.out.println(restorani.get(0).getNazivRestoran());
			System.out.println("SAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
			
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
	
	public class TestComparatorFriendLname implements Comparator<Object> {
		
	    public int compare(Object obj1, Object obj2){
	        Gost p1=(Gost) obj1;
	        Gost p2=(Gost) obj2;
	       
	        String p1name=p1.getKorisnik_prezime();
	        String p2name=p2.getKorisnik_prezime();
	       
	        return p1name.compareTo(p2name);
	    }
	


}
	
	
	
}
