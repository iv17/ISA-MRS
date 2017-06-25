package server.servlet;

import java.io.IOException;

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

public class AddFriendController extends HttpServlet{

	private static Logger log = Logger.getLogger(AddFriendController.class);

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
			
			
			System.out.println("ADD FRIENDDDDD");
			String dodavanje = request.getParameter("korisnikId");
			
			//Prijatelj dodaj = (Prijatelj) request.getSession().getAttribute(dodavanje);
			System.out.println(dodavanje);
			System.out.println("ADDDDD");
			//Gost novi = gostDao.findById(Integer.parseInt(dodavanje));
			//Prijatelj dodajNovog = (Prijatelj) novi;
			//dodajNovog.setPrihvacen("ne");
			/*Set<Prijatelj> dodajNove = new HashSet<Prijatelj>();
			List<Prijatelj> imaPrijatelje = prijateljDao.findFriends(kor.getKorisnik_id(),"da");
			for(Prijatelj p : imaPrijatelje){
				dodajNove.add(p);
				
			}*/
			//dodajNove.add(dodajNovog);
			//System.out.println(dodajNove.size());
			//kor.setImaPrijatelje(dodajNove);
			//dodajNovog.setJePrijatelj(kor);
			//prijateljDao.merge(dodajNovog);
			Prijatelj gost_p = new Prijatelj();
			gost_p.setJePrijatelj(kor);
			gost_p.setGlavniPrijatelj(Integer.parseInt(dodavanje));
			gost_p.setPrihvacen("ne");
			prijateljDao.merge(gost_p);
			System.out.println("KKKKKKKKKKKKKKKKKKKK");
			
			
			
			String poslato = "poslato";
			request.setAttribute("req", poslato);
			
			getServletContext().getRequestDispatcher("/AddFriendsController").forward(request, response);
			
			//response.sendRedirect(response.encodeRedirectURL("./AddFriendsController"));
			
			///getServletContext().getRequestDispatcher("/add_friends.jsp").forward(request, response);
		
		
		} catch (IOException e) {
			log.error(e);
			throw e;
		}		
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}




}
