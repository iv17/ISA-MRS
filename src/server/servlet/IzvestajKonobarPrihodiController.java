package server.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import server.entity.Jelo;
import server.entity.Konobar;
import server.entity.Narudzbina;
import server.entity.Obrok;
import server.entity.Pice;
import server.entity.PorucenoPice;

public class IzvestajKonobarPrihodiController extends HttpServlet {

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
			Double zaradaKonobara = 0.0;
			Double sumaJelaIzObroka = 0.0;
			Double sumaPopijenogPica = 0.0;
			Integer brNarudzbina = 0;
			List<Narudzbina> narudzbine = (List<Narudzbina>) session.getAttribute("narudzbine");
			for (Narudzbina narudzbina : narudzbine) {
				if(narudzbina.getKonobar().equals(konobar)){
					System.out.println(konobar.getKorisnik_ime() + " konobar");
					Obrok o = narudzbina.getObrok();
					System.out.println(o.getIdObroka() + " id obroka");
					Set<Jelo> jelaIzObroka = o.getJela();
					for (Jelo jelo : jelaIzObroka) {
						Double cenaJela = jelo.getCenaJela();
						System.out.println("ID JELA " + jelo.getIdJela() + " " + cenaJela + " cena jela");
						sumaJelaIzObroka += cenaJela;
					}
					PorucenoPice pp = narudzbina.getPorucenoPice();
					System.out.println(pp.getIdPorucenoPice() + "pp id");
					Set<Pice> popijenoPice 	= pp.getPica();
					for (Pice pice : popijenoPice) {
						Double cenaPica = pice.getCenaPica();
						System.out.println(cenaPica + "  cena pica");
						sumaPopijenogPica += cenaPica;
					}
				}else{
					brNarudzbina++;
				}
			}
			if(brNarudzbina ==  narudzbine.size()){
				session.setAttribute("prihodKonobara", 0.0);
				session.setAttribute("ocenaRestorana", 0);
				session.setAttribute("ocenaJela", 0);
				session.setAttribute("ocenaKonobara", 0);
				session.setAttribute("prihodRestorana", -1.0);
				session.setAttribute("brojPosetaDnevni", -1);
				session.setAttribute("brojPosetaNedeljni", -1);
				session.setAttribute("brKonobara", 0);
				
				response.sendRedirect("./home_page_menadzer_restorana.jsp#waiters_report");
			}else{
				zaradaKonobara = sumaJelaIzObroka + sumaPopijenogPica;
				System.out.println("zaradaKon " + zaradaKonobara);
				session.setAttribute("prihodKonobara", zaradaKonobara);
				session.setAttribute("zaradaJelo", sumaJelaIzObroka);
				session.setAttribute("zaradaPice", sumaPopijenogPica);
				session.setAttribute("ocenaRestorana", 0);
				session.setAttribute("ocenaJela", 0);
				session.setAttribute("ocenaKonobara", 0);
				session.setAttribute("prihodRestorana", -1.0);
				session.setAttribute("brojPosetaDnevni", -1);
				session.setAttribute("brojPosetaNedeljni", -1);
				session.setAttribute("brKonobara", 0);
				
				response.sendRedirect("./home_page_menadzer_restorana.jsp#waiter_income");
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
 