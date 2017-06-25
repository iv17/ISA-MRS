package server.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
public class NedeljniIzvestajPosecenostiController extends HttpServlet {

	private static final long serialVersionUID = -2544396238785425302L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		//PROVERA DA LI JE ULOGOVAN MENADZER
		HttpSession session = request.getSession();
		if(session.getAttribute("menadzer_restorana") != null){

			String startDate = null;
			String endDate = null;
			if( (request.getParameter("startDate") != null) 
					&& (!request.getParameter("startDate").equals(""))){
				startDate  = request.getParameter("startDate");
			}
			if( (request.getParameter("endDate") != null) 
					&& (!request.getParameter("endDate").equals(""))){
				endDate  = request.getParameter("endDate");
			}
			
			String startDateDan = startDate.substring(0, 2);
			String startDateMesec = startDate.substring(3,5);
			String startDateGodina = startDate.substring(6);
			Long startDateUDanima;
			if(Integer.parseInt(startDateMesec) == 01 || Integer.parseInt(startDateMesec) == 03 || Integer.parseInt(startDateMesec) == 05 ||
					Integer.parseInt(startDateMesec) == 07 ||  Integer.parseInt(startDateMesec) == 10 || Integer.parseInt(startDateMesec) == 12){
				startDateUDanima = Long.parseLong(startDateMesec)*31 + Long.parseLong(startDateDan);
			}else if(Integer.parseInt(startDateMesec) == 02){
				startDateUDanima = Long.parseLong(startDateMesec)*29 + Long.parseLong(startDateDan);
			}else{
				startDateUDanima = Long.parseLong(startDateMesec)*30 + Long.parseLong(startDateDan);
			}
			String endDateDan = endDate.substring(0, 2);
			String endDateMesec = endDate.substring(3,5);
			String endDateGodina = endDate.substring(6);
			Long endDateUDanima;
			if(Integer.parseInt(endDateMesec) == 01 || Integer.parseInt(endDateMesec) == 03 || Integer.parseInt(endDateMesec) == 05 ||
					Integer.parseInt(endDateMesec) == 07 ||  Integer.parseInt(endDateMesec) == 10 || Integer.parseInt(endDateMesec) == 12){
				endDateUDanima = Long.parseLong(endDateMesec)*31 + Long.parseLong(endDateDan);
			}else if(Integer.parseInt(endDateMesec) == 02){
				endDateUDanima = Long.parseLong(endDateMesec)*29 + Long.parseLong(endDateDan);
			}else{
				endDateUDanima = Long.parseLong(endDateMesec)*30 + Long.parseLong(endDateDan);
			}
			int brojNeuspesnih = 0;
			ArrayList<String> jsonNedelnji = new ArrayList<String>();
			List<Poseta> posete = (List<Poseta>) session.getAttribute("posete");
			List<Narudzbina> narudzbine = (List<Narudzbina>) session.getAttribute("narudzbine");
			Integer ukupanBrojPoseta = 0;
			for (Poseta poseta : posete) {
				
				for (Narudzbina narudzbina : narudzbine) {
					if(poseta.getIdPoseta().equals(narudzbina.getPoseta().getIdPoseta())){
						Rezervacija r = narudzbina.getRezervacija();
						String samoDatum = r.getDatumDolaskaRezervacijaString().substring(0, 10);
						String dan = samoDatum.substring(0, 2);
						String mesec = samoDatum.substring(3, 5);
						String godina = samoDatum.substring(6);
						Long datumUDanima;
						if(Integer.parseInt(mesec) == 01 || Integer.parseInt(mesec) == 03 || Integer.parseInt(mesec) == 05 ||
								Integer.parseInt(mesec) == 07 ||  Integer.parseInt(mesec) == 10 || Integer.parseInt(mesec) == 12){
							datumUDanima = Long.parseLong(mesec)*31 + Long.parseLong(dan);
						}else if(Integer.parseInt(mesec) == 02){
							datumUDanima = Long.parseLong(mesec)*29 + Long.parseLong(dan);
						}else{
							datumUDanima = Long.parseLong(mesec)*30 + Long.parseLong(dan);
						}
						if(godina.equals(startDateGodina)){
							if(datumUDanima <= endDateUDanima || datumUDanima >= startDateUDanima){
								System.out.println("POKLAPANJE");
								Set<Gost> gostiPoRezervaciji = r.getPrihvaceniGosti();
								Integer brojGostijuPoRezervaciji = gostiPoRezervaciji.size() + 1 ;
								JSONObject obj = new JSONObject();
								obj.put("period", samoDatum);
								obj.put("brojGostiju", brojGostijuPoRezervaciji);
								String jsonString = obj.toJSONString();
								jsonNedelnji.add(jsonString);
								ukupanBrojPoseta += brojGostijuPoRezervaciji;
							}else{
								brojNeuspesnih++;
							}
						}
					}

				}
			}
			if(brojNeuspesnih ==  narudzbine.size()){
				session.setAttribute("brojPosetaNedeljni", 0);
				session.setAttribute("ocenaJela", 0);
				session.setAttribute("obrociSize", -1);
				session.setAttribute("ocenaRestorana", 0);
				session.setAttribute("prihodKonobara", -1.0);
				session.setAttribute("prihodRestorana", -1.0);
				session.setAttribute("zaradaJelo", -1.0);
				session.setAttribute("zaradaPice", -1.0);
				session.setAttribute("brojPosetaDnevni", -1);
				session.setAttribute("brKonobara", 0);
				
				response.sendRedirect("./home_page_menadzer_restorana.jsp#week_visitation_picker");
			}else{
				System.out.println(ukupanBrojPoseta + " ukupanBrojPoseta");
				session.setAttribute("brojPosetaNedeljni", ukupanBrojPoseta);
				for (String  js : jsonNedelnji) {
					System.out.println(js);
				}
				session.setAttribute("jsonNedeljni",jsonNedelnji);
				session.setAttribute("ocenaKonobara", 0);
				session.setAttribute("ocenaJela", 0);
				session.setAttribute("obrociSize", -1);
				session.setAttribute("ocenaRestorana", 0);
				session.setAttribute("prihodKonobara", -1.0);
				session.setAttribute("prihodRestorana", -1.0);
				session.setAttribute("zaradaJelo", -1.0);
				session.setAttribute("zaradaPice", -1.0);
				session.setAttribute("brojPosetaDnevni", -1);
				session.setAttribute("brKonobara", 0);
				
				response.sendRedirect("./home_page_menadzer_restorana.jsp#week_visitation_report");
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
