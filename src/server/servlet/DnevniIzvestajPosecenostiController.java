package server.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import server.entity.Gost;
import server.entity.Narudzbina;
import server.entity.Poseta;
import server.entity.Rezervacija;
import server.session.PosetaDaoLocal;

public class DnevniIzvestajPosecenostiController extends HttpServlet {

	private static final long serialVersionUID = -2544396238785425302L;
	@EJB 
	private PosetaDaoLocal posetaDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		//PROVERA DA LI JE ULOGOVAN MENADZER
		HttpSession session = request.getSession();
		if(session.getAttribute("menadzer_restorana") != null){
		
			String startTime = null;
			String endTime = null;
			if( (request.getParameter("startTime") != null) 
					&& (!request.getParameter("startTime").equals(""))){
				startTime  = request.getParameter("startTime");
			}
			if( (request.getParameter("endTime") != null) 
					&& (!request.getParameter("endTime").equals(""))){
				endTime  = request.getParameter("endTime");
			}
			int brojNeuspesnih = 0;
			ArrayList<String> jsonDnevni = new ArrayList<String>();
			List<Poseta> posete = (List<Poseta>) session.getAttribute("posete");
			List<Narudzbina> narudzbine = (List<Narudzbina>) session.getAttribute("narudzbine");
			int i = 0;
			Integer ukupanBrojPoseta = 0;
			for (Poseta poseta : posete) {
				for (Narudzbina narudzbina : narudzbine) {
					if(poseta.getIdPoseta().equals(narudzbina.getPoseta().getIdPoseta())){
						Rezervacija r = narudzbina.getRezervacija();
						String samoDatum = r.getDatumDolaskaRezervacijaString().substring(0, 10);
						String samoVreme = r.getDatumDolaskaRezervacijaString().substring(11, 16);
						String samoVremeStart = r.getDatumDolaskaRezervacijaString().substring(11, 16);
						String satStart = samoVremeStart.substring(0,2);
						String minutiStart = samoVremeStart.substring(3);
						Integer minStartBaza = Integer.parseInt(satStart)*60 + Integer.parseInt(minutiStart);
						String samoVremeEnd = r.getRezervacija_duzina_boravkaString().substring(11, 16);
						String satEnd = samoVremeEnd.substring(0,2);
						String minutiEnd = samoVremeEnd.substring(3);
						Integer minEndBaza = Integer.parseInt(satEnd)*60 + Integer.parseInt(minutiEnd);
						String startTimeSat = "";
						String startTimeMinuti = "";
						Integer startTimeMinutiInt = 0;
						if(startTime.length() == 15){ //20/06/2016 7:30
							String startTimeVreme = startTime.substring(11);
							startTimeSat = startTimeVreme.substring(0,1);
							startTimeMinuti = startTimeVreme.substring(2);
							startTimeMinutiInt = Integer.parseInt(startTimeSat)*60 + Integer.parseInt(startTimeMinuti);
						}
						String endTimeSat = "";
						String endTimeMinuti = "";
						Integer endTimeMinutiInt = 0;
						if(endTime.length() == 16){ //20/06/2016 17:30
							String endTimeVreme = endTime.substring(11);
							endTimeSat = endTimeVreme.substring(0,2);
							endTimeMinuti = endTimeVreme.substring(3);
							endTimeMinutiInt = Integer.parseInt(endTimeSat)*60 + Integer.parseInt(endTimeMinuti);
						}
						if(samoDatum.equals(startTime.substring(0,10))){
							if(minStartBaza >= startTimeMinutiInt && endTimeMinutiInt >= minEndBaza){
								System.out.println(i++);
								Set<Gost> gostiPoRezervaciji = r.getPrihvaceniGosti();
								Integer brojGostijuPoRezervaciji = gostiPoRezervaciji.size() + 1;
								JSONObject obj = new JSONObject();
								obj.put("period", samoVreme);
								obj.put("brojGostiju", brojGostijuPoRezervaciji);
								String jsonString = obj.toJSONString();
								jsonDnevni.add(jsonString);
								System.out.println(gostiPoRezervaciji.size() + " gostiPoRezervaciji");
								ukupanBrojPoseta += brojGostijuPoRezervaciji;
							}else{
								brojNeuspesnih++;
							}
							
						}
					}
					
				}
			}
			if(brojNeuspesnih == narudzbine.size()){
				session.setAttribute("brojPosetaDnevni", 0);
				session.setAttribute("ocenaKonobara", 0);
				session.setAttribute("ocenaJela", 0);
				session.setAttribute("obrociSize", -1);
				session.setAttribute("ocenaRestorana", 0);
				session.setAttribute("prihodKonobara", -1.0);
				session.setAttribute("prihodRestorana", -1.0);
				session.setAttribute("zaradaJelo", -1.0);
				session.setAttribute("zaradaPice", -1.0);
				session.setAttribute("brojPosetaNedeljni", -1);
				session.setAttribute("brKonobara", 0);
				response.sendRedirect("./home_page_menadzer_restorana.jsp#day_visitation_picker");
			}else{
				System.out.println(ukupanBrojPoseta + " ukupanBrojPoseta");
				session.setAttribute("brojPosetaDnevni", ukupanBrojPoseta);
				for (String  js : jsonDnevni) {
					System.out.println(js);
				}
				session.setAttribute("jsonDnevni",jsonDnevni);
				session.setAttribute("ocenaKonobara", 0);
				session.setAttribute("ocenaJela", 0);
				session.setAttribute("obrociSize", -1);
				session.setAttribute("ocenaRestorana", 0);
				session.setAttribute("prihodKonobara", -1.0);
				session.setAttribute("prihodRestorana", -1.0);
				session.setAttribute("zaradaJelo", -1.0);
				session.setAttribute("zaradaPice", -1.0);
				session.setAttribute("brojPosetaNedeljni", -1);
				session.setAttribute("brKonobara", 0);
				response.sendRedirect("./home_page_menadzer_restorana.jsp#day_visitation_report");	
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
 