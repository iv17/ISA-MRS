package server.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import server.entity.MenadzerRestorana;
import server.entity.RasporedStolova;
import server.entity.Restoran;
import server.entity.Sto;
import server.session.RasporedDaoLocal;
import server.session.ReonDaoLocal;
import server.session.StoDaoLocal;

/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
public class CreateStoController extends HttpServlet {

	private static final long serialVersionUID = -2544396238785425302L;
	@EJB 
	RasporedDaoLocal rasporedDao;
	@EJB
	StoDaoLocal stoDao;
	@EJB
	ReonDaoLocal reonDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		//PROVERA DA LI JE ULOGOVAN MENADZER
		HttpSession session = request.getSession();
		if(session.getAttribute("menadzer_restorana") != null){
			String jsonRaspored = null;
			MenadzerRestorana menadzer = (MenadzerRestorana) session.getAttribute("menadzer_restorana");
			Restoran restoran = menadzer.getRestoran();
			
			List<RasporedStolova> rasporedi = (List<RasporedStolova>) session.getAttribute("rasporedi");
			for (RasporedStolova rasporedStolova : rasporedi) {
				if(rasporedStolova.getAktivanRaspored() == true){
					jsonRaspored = rasporedStolova.getJsonRaspored();
					
					JSONParser parser = new JSONParser();
					Object obj = null;
					try {
						obj = parser.parse(jsonRaspored);
					} catch (ParseException e) {
						
						e.printStackTrace();
					}
					
					JSONArray array = new JSONArray();
					array.add(obj);
   
			        JSONObject obj1 = null;
			        Object o = null;
			        String s = null;
			        String q = null;
			        String [] list = null;
			        for (int j = 0; j < array.size(); j++) {
			        	obj1 = (JSONObject)array.get(j);
			        	o = obj1.get("objects");
			        	s = o.toString();
			        	s = s.substring(1, s.length()-1);
			        	s = s.replace("},{", "}%{");
			        	list = s.split("%");
			        	for (int i = 0; i < list.length; i++) {
							Sto sto = new Sto();
							sto.setRestoran(restoran);
							sto.setRaspored(rasporedStolova);
							sto.setStoJson(list[0]);
							stoDao.persist(sto);
						} 	
					}
				}
			}
			
			response.sendRedirect("./ReadRasporedStolovaController");
			
		} else {
			
			RequestDispatcher disp = request.getRequestDispatcher("./login.jsp");
			disp.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
 