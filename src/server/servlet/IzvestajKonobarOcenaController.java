package server.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import server.entity.Konobar;
import server.entity.Narudzbina;
import server.entity.Poseta;

/*
 * @Author Ivana Savin, sw17/2013
 */
public class IzvestajKonobarOcenaController extends HttpServlet {

	private static final long serialVersionUID = -2544396238785425302L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		//PROVERA DA LI JE ULOGOVAN MENADZER
		HttpSession session = request.getSession();
		if(session.getAttribute("menadzer_restorana") != null){
			
			Integer konobarId = null;
			if( (request.getParameter("konobarId") != null) 
					&& (!request.getParameter("konobarId").equals(""))){
				konobarId = Integer.parseInt(request.getParameter("konobarId"));
			}
			Konobar konobar = null;
			List<Konobar> konobari = (List<Konobar>) session.getAttribute("konobari");
			for (Konobar k : konobari) {
				if(k.getKorisnik_id().equals(konobarId))
					konobar = k;
			}
			Integer sumaOcena = 0;
			Integer brOcena = 0;
			List<Narudzbina> narudzbine = (List<Narudzbina>) session.getAttribute("narudzbine");
			Integer brKonobara = 0;
			for (Narudzbina narudzbina : narudzbine) {
				if(narudzbina.getKonobar().equals(konobar)){
					Poseta poseta = narudzbina.getPoseta();
					if(poseta.getOcenaKonobaraPoseta() != null){
						Integer ocena = poseta.getOcenaKonobaraPoseta();
						System.out.println("ocena " + ocena);
						sumaOcena += ocena;
						brOcena++;
					}
					
				}else{
					brKonobara++;
				}
			}
			if(narudzbine.size() == brKonobara){
				session.setAttribute("brKonobara", brKonobara);
				session.setAttribute("ocenaKonobara", -1);
				session.setAttribute("ocenaJela", 0);
				session.setAttribute("ocenaRestorana", 0);
				session.setAttribute("prihodKonobara", -1.0);
				session.setAttribute("prihodRestorana", -1.0);
				session.setAttribute("brojPosetaDnevni", -1);
				session.setAttribute("brojPosetaNedeljni", -1);
				session.setAttribute("zaradaJelo", -1.0);
				session.setAttribute("zaradaPice", -1.0);
				
				response.sendRedirect("./home_page_menadzer_restorana.jsp#waiters_report");
			}
			else{
				Integer ocenaKonobara = sumaOcena/brOcena;
				session.setAttribute("brKonobara", brKonobara);
				System.out.println("ocenaKon "  + ocenaKonobara);
				session.setAttribute("ocenaKonobara", ocenaKonobara);
				session.setAttribute("ocenaJela", 0);
				session.setAttribute("ocenaRestorana", 0);
				session.setAttribute("prihodKonobara", -1.0);
				session.setAttribute("prihodRestorana", -1.0);
				session.setAttribute("brojPosetaDnevni", -1);
				session.setAttribute("brojPosetaNedeljni", -1);
				session.setAttribute("zaradaJelo", -1.0);
				session.setAttribute("zaradaPice", -1.0);
				
				response.sendRedirect("./home_page_menadzer_restorana.jsp#waiter_rating");
			}
			
		} else {
			
			RequestDispatcher disp = request.getRequestDispatcher("./login.jsp");
			disp.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
 