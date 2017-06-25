package server.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import server.entity.Jelo;
import server.entity.Narudzbina;
import server.entity.Obrok;
import server.entity.Poseta;
import server.session.JeloDaoLocal;

/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
public class IzvestajJeloOcenaController extends HttpServlet {

	private static final long serialVersionUID = -2544396238785425302L;
	@EJB 
	private JeloDaoLocal jeloDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		//PROVERA DA LI JE ULOGOVAN MENADZER
		HttpSession session = request.getSession();
		if(session.getAttribute("menadzer_restorana") != null){

			Integer jeloId = null;
			if( (request.getParameter("jeloId") != null) 
					&& (!request.getParameter("jeloId").equals(""))){
				jeloId = Integer.parseInt(request.getParameter("jeloId"));
			}
			Jelo jelo = jeloDao.findById(jeloId);
			Integer sumaOcena = 0;
			Integer brOcena = 0;
			if(jelo.getObroci().size()==0){
				session.setAttribute("obrociSize", 0);
				session.setAttribute("ocenaJela", -1);
				session.setAttribute("ocenaKonobara", 0);
				session.setAttribute("ocenaRestorana", 0);
				session.setAttribute("prihodKonobara", -1.0);
				session.setAttribute("prihodRestorana", -1.0);
				session.setAttribute("brojPosetaDnevni", -1);
				session.setAttribute("brojPosetaNedeljni", -1);
				session.setAttribute("brKonobara", 0);
				session.setAttribute("zaradaJelo", -1.0);
				session.setAttribute("zaradaPice", -1.0);
				
				response.sendRedirect("./home_page_menadzer_restorana.jsp#meal_report");
			}
			else{
				session.setAttribute("obrociSize", jelo.getObroci().size());
				Set<Obrok> oo = jelo.getObroci();
				System.out.println(oo.size());
				for (Obrok obrok : oo) {
					System.out.println(obrok.getIdObroka() + " oo id");
					List<Narudzbina> narudzbine = (List<Narudzbina>) session.getAttribute("narudzbine");
					System.out.println(narudzbine.size() + "  narsize");
					for (Narudzbina narudzbina : narudzbine) {
						if(narudzbina.getObrok().getIdObroka().equals(obrok.getIdObroka())){
							System.out.println(narudzbina.getObrok().getIdObroka() + " obr id");
							Poseta poseta  = narudzbina.getPoseta();
							System.out.println("poseta " + poseta.getIdPoseta());
							if(poseta.getOcenaJelaPoseta() != null){
								Integer ocenaObroka = poseta.getOcenaJelaPoseta();
								System.out.println("ocenaObroka = " + ocenaObroka);
								sumaOcena += ocenaObroka;			 
								brOcena++;
							}
							
						}

					}
				}

				Integer ocenaIzabranogJela = sumaOcena/brOcena;
				System.out.println("ocenaIzabranogJela " + ocenaIzabranogJela);
				session.setAttribute("ocenaJela", ocenaIzabranogJela);
				session.setAttribute("ocenaKonobara", 0);
				session.setAttribute("ocenaRestorana", 0);
				session.setAttribute("prihodKonobara", -1.0);
				session.setAttribute("prihodRestorana", -1.0);
				session.setAttribute("brojPosetaDnevni", -1);
				session.setAttribute("brojPosetaNedeljni", -1);
				session.setAttribute("brKonobara", 0);
				session.setAttribute("zaradaJelo", -1.0);
				session.setAttribute("zaradaPice", -1.0);
				
				response.sendRedirect("./home_page_menadzer_restorana.jsp#meal_rating");
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
