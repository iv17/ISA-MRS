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

public class AddFriendsController extends HttpServlet{
	
	private static Logger log = Logger.getLogger(AddFriendsController.class);

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
			
			
			System.out.println("TUTUTUTTUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
			List<Prijatelj> prijatelji = prijateljDao.findAll(kor.getKorisnik_id());
			List<Gost> sviPrijatelji = new ArrayList<Gost>();
			for(Prijatelj p : prijatelji){
				System.out.println("gostiiii");
				int idp = p.getGlavniPrijatelj();
				Gost g = gostDao.findById(idp);
				sviPrijatelji.add(g);
				
				
			}
			List<Gost> gosti = gostDao.showGosti();
			List<Gost> za_dodavanje = new ArrayList<Gost>();
			
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAA");
			//List<Prijatelj> za_dodavanje = new ArrayList<Prijatelj>();
			
			boolean jednaki = false;
			for(Gost g : gosti){
				for(Gost p : sviPrijatelji){
					//System.out.println("MMMMMMMMMMMMMMMM")
					if((g.equals(p) == true)){
						jednaki = true;
					
					
					}
				}
				if(jednaki == false){
			        za_dodavanje.add(g);
			    }
				jednaki = false;
				
				
			}
			
			System.out.println(za_dodavanje.size());
			za_dodavanje.remove(kor);
			request.setAttribute("dodaj_prijatelje", za_dodavanje);
			
			if(request.getSession().getAttribute("fr") != null){
				request.getSession().removeAttribute("fr");
				
			}
			
			String aaa = (String)request.getAttribute("req");
			if(aaa != null){
				System.out.println("dodaoooooooo");
				request.getSession().setAttribute("fr", aaa);
			}
			
			
			
			getServletContext().getRequestDispatcher("/add_friends.jsp").forward(request, response);
		
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
	
	public boolean equalsp(Gost p1, Gost p2){
		if(p1.getKorisnik_email().equals(p2.getKorisnik_email()) && p1.getKorisnik_lozinka().equals(p2.getKorisnik_lozinka())){
			return true;
			
			
		}
		else{
			
			return false;
		}
		
		
	}
	

}
