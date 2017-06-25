package server.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import server.entity.MenadzerRestorana;
import server.entity.Narudzbina;
import server.entity.Poseta;
import server.entity.Restoran;

public class IzvestajRestoranOcenaController extends HttpServlet {

	private static final long serialVersionUID = -2544396238785425302L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		//PROVERA DA LI JE ULOGOVAN MENADZER
		HttpSession session = request.getSession();
		if(session.getAttribute("menadzer_restorana") != null){
		
			MenadzerRestorana menadzer = (MenadzerRestorana) session.getAttribute("menadzer_restorana");
			Restoran restoran = menadzer.getRestoran();
			
			Integer sumaOcena = 0;
			Integer brOcena = 0;
			Integer brPoseta = 0;
			List<Narudzbina> narudzbine = (List<Narudzbina>) session.getAttribute("narudzbine");
			for (Narudzbina narudzbina : narudzbine) {
				if(narudzbina.getRestoran().getIdRestorana().equals(restoran.getIdRestorana())){
					Poseta poseta = narudzbina.getPoseta();
					System.out.println(poseta.getIdPoseta() + " poseta");
					if(poseta.getOcenaRestoranaPoseta() != null){
						Integer ocena = poseta.getOcenaRestoranaPoseta();
						System.out.println("ocena " + ocena);
						sumaOcena += ocena;
						brOcena++;
					}
					
				}else{
					brPoseta++;
				}
			}
			if(brPoseta == narudzbine.size()){
				session.setAttribute("ocenaRestorana", -1);
				session.setAttribute("ocenaJela", 0);
				session.setAttribute("ocenaKonobara", 0);
				session.setAttribute("prihodKonobara", -1.0);
				session.setAttribute("prihodRestorana", -1.0);
				session.setAttribute("brojPosetaDnevni", -1);
				session.setAttribute("brojPosetaNedeljni", -1);
				session.setAttribute("brKonobara", 0);
				session.setAttribute("zaradaJelo", -1.0);
				session.setAttribute("zaradaPice", -1.0);
			}
			else if(sumaOcena != 0 && brOcena != 0){
				Integer ocenaRestorana = sumaOcena/brOcena;
				System.out.println("ocenaRes "  + ocenaRestorana);
				session.setAttribute("ocenaRestorana", ocenaRestorana);
				session.setAttribute("ocenaJela", 0);
				session.setAttribute("ocenaKonobara", 0);
				session.setAttribute("prihodKonobara", -1.0);
				session.setAttribute("prihodRestorana", -1.0);
				session.setAttribute("brojPosetaDnevni", -1);
				session.setAttribute("brojPosetaNedeljni", -1);
				session.setAttribute("brKonobara", 0);
				session.setAttribute("zaradaJelo", -1.0);
				session.setAttribute("zaradaPice", -1.0);
				
				response.sendRedirect("./home_page_menadzer_restorana.jsp#restaurant_rating");
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
 