package server.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import server.entity.Jelo;
import server.entity.Narudzbina;
import server.entity.Obrok;
import server.entity.Pice;
import server.entity.PorucenoPice;
import server.entity.Poseta;
import server.entity.Rezervacija;

public class IzvestajRestoranPrihodiController extends HttpServlet {

	private static final long serialVersionUID = -2544396238785425302L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();
		if(session.getAttribute("menadzer_restorana") != null){

			String startDay = null;
			String endDay = null;
			//PREUZIMANJE PARAMETARA IZ BOOTSTRAP DATE RANGE PICKER-A
			if( (request.getParameter("startDay") != null) 
					&& (!request.getParameter("startDay").equals(""))){
				startDay  = request.getParameter("startDay");
			}
			if( (request.getParameter("endDay") != null) 
					&& (!request.getParameter("endDay").equals(""))){
				endDay  = request.getParameter("endDay");
			}

			String startDateDan = startDay.substring(0, 2);
			String startDateMesec = startDay.substring(3,5);
			String startDateGodina = startDay.substring(6);
			Long startDateUDanima;
			if(Integer.parseInt(startDateMesec) == 01 || Integer.parseInt(startDateMesec) == 03 || Integer.parseInt(startDateMesec) == 05 ||
					Integer.parseInt(startDateMesec) == 07 ||  Integer.parseInt(startDateMesec) == 10 || Integer.parseInt(startDateMesec) == 12){
				startDateUDanima = Long.parseLong(startDateMesec)*31 + Long.parseLong(startDateDan);
			}else if(Integer.parseInt(startDateMesec) == 02){
				startDateUDanima = Long.parseLong(startDateMesec)*29 + Long.parseLong(startDateDan);
			}else{
				startDateUDanima = Long.parseLong(startDateMesec)*30 + Long.parseLong(startDateDan);
			}

			String endDateDan = endDay.substring(0, 2);
			String endDateMesec = endDay.substring(3,5);
			String endDateGodina = endDay.substring(6);
			Long endDateUDanima;
			if(Integer.parseInt(endDateMesec) == 01 || Integer.parseInt(endDateMesec) == 03 || Integer.parseInt(endDateMesec) == 05 ||
					Integer.parseInt(endDateMesec) == 07 ||  Integer.parseInt(endDateMesec) == 10 || Integer.parseInt(endDateMesec) == 12){
				endDateUDanima = Long.parseLong(endDateMesec)*31 + Long.parseLong(endDateDan);
			}else if(Integer.parseInt(endDateMesec) == 02){
				endDateUDanima = Long.parseLong(endDateMesec)*29 + Long.parseLong(endDateDan);
			}else{
				endDateUDanima = Long.parseLong(endDateMesec)*30 + Long.parseLong(endDateDan);
			}

			Integer brojNeuspesnih = 0; //ZA NOTIFIKACIJE!!!
			Double zaradaRestorana = 0.0;
			Double sumaJelaIzObroka = 0.0;
			Double sumaPopijenogPica = 0.0;
			ArrayList<String> json = new ArrayList<String>();
			List<Poseta> posete = (List<Poseta>) session.getAttribute("posete");
			List<Narudzbina> narudzbine = (List<Narudzbina>) session.getAttribute("narudzbine");
			for (Poseta poseta : posete) {
				for (Narudzbina narudzbina : narudzbine) {
					if(poseta.getIdPoseta().equals(narudzbina.getPoseta().getIdPoseta())){
						Rezervacija r = narudzbina.getRezervacija();
						Date datum = r.getDatumDolaskaRezervacija();
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
								Obrok o = narudzbina.getObrok();
								Set<Jelo> jelaIzObroka = o.getJela();
								for (Jelo jelo : jelaIzObroka) {
									Double cenaJela = jelo.getCenaJela();
									JSONObject obj = new JSONObject();
									obj.put("period", samoDatum);
									obj.put("cena", cenaJela);
									String jsonString = obj.toJSONString();
									json.add(jsonString);
									System.out.println("ID JELA: " + jelo.getIdJela() + ", CENA JELA: " + cenaJela);
									sumaJelaIzObroka += cenaJela;
								}
								PorucenoPice pp = narudzbina.getPorucenoPice();
								Set<Pice> popijenoPice 	= pp.getPica();
								for (Pice pice : popijenoPice) {
									Double cenaPica = pice.getCenaPica();
									System.out.println("ID PICA: " + pice.getIdPica()  + ", CENA PICA: " + cenaPica);
									sumaPopijenogPica += cenaPica;
								}

							}else{
								brojNeuspesnih++;
							}
						}
					}

				}
			}
			if(brojNeuspesnih == narudzbine.size()){
				session.setAttribute("prihodRestorana", 0.0);
				session.setAttribute("ocenaKonobara", 0);
				session.setAttribute("ocenaJela", 0);
				session.setAttribute("obrociSize", -1);
				session.setAttribute("ocenaRestorana", 0);
				session.setAttribute("prihodKonobara", -1.0);
				session.setAttribute("zaradaJelo", -1.0);
				session.setAttribute("zaradaPice", -1.0);
				session.setAttribute("brojPosetaDnevni", -1);
				session.setAttribute("brojPosetaNedeljni", -1);
				session.setAttribute("brKonobara", 0);
				response.sendRedirect("./home_page_menadzer_restorana.jsp#restaurant_picker");
			}else{
				zaradaRestorana = sumaJelaIzObroka + sumaPopijenogPica;
				System.out.println("UKUPNA ZARADA RESTORANA: " + zaradaRestorana);
				for (String  js : json) {
					System.out.println(js);
				}
				session.setAttribute("json",json);
				session.setAttribute("prihodRestorana", zaradaRestorana);
				session.setAttribute("ocenaKonobara", 0);
				session.setAttribute("ocenaJela", 0);
				session.setAttribute("obrociSize", -1);
				session.setAttribute("ocenaRestorana", 0);
				session.setAttribute("prihodKonobara", -1.0);
				session.setAttribute("zaradaJelo", -1.0);
				session.setAttribute("zaradaPice", -1.0);
				session.setAttribute("brojPosetaDnevni", -1);
				session.setAttribute("brojPosetaNedeljni", -1);
				session.setAttribute("brKonobara", 0);
				response.sendRedirect("./home_page_menadzer_restorana.jsp#restaurant_income");
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
