package server.servlet;

import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.entity.Kuvar;
import server.session.KuvarDaoLocal;

public class UpdateKuvarController extends HttpServlet {
	private static final long serialVersionUID = 4676416691336033321L;
	
	@EJB
	private KuvarDaoLocal kuvarDao;
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String kuvarId = null;
		String kuvarIme = null;
		String kuvarPrezime = null;
		String kuvarEmail = null;
		String kuvarLozinka = null;
		String kuvarTip = null;
		
		Date dat = null;
		/*String dat = "1970-01-01 01:00:01";
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date kuvarDatumRodjenja = null;
		try {
			kuvarDatumRodjenja = format.parse(dat);
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		String kuvarKonfekcijskiBroj = null;
		Integer kuvarVelicinaObuce = 0;
		
		if(request.getSession().getAttribute("kuvar") != null){
		
			Kuvar kv = (Kuvar) request.getSession().getAttribute("kuvar");
			
			kuvarId = String.valueOf(kv.getKorisnik_id());
			
			if((request.getParameter("form-name-kuvar") != null) 
					&& (!request.getParameter("form-name-kuvar").equals(""))
					&& (request.getParameter("form-name-kuvar").length() > 3)){
				kuvarIme = request.getParameter("form-name-kuvar");
			}
			if((request.getParameter("form-lastname-kuvar") != null) 
					&& (!request.getParameter("form-lastname-kuvar").equals(""))
					&& (request.getParameter("form-lastname-kuvar").length() > 3)){
				kuvarPrezime = request.getParameter("form-lastname-kuvar");
			}
			if((request.getParameter("form-email-kuvar") != null) 
					&& (!request.getParameter("form-email-kuvar").equals(""))
					&& (request.getParameter("form-email-kuvar").length() > 3)){
				kuvarEmail = request.getParameter("form-email-kuvar");
			}
			if((request.getParameter("form-password-kuvar") != null) 
					&& (!request.getParameter("form-password-kuvar").equals(""))
					&& (request.getParameter("form-password-kuvar").length() > 3)){
				kuvarLozinka = request.getParameter("form-password-kuvar");
			}
			/*if((request.getParameter("form-datum-rodjenja-konobar") != null) 
					&& (!request.getParameter("form-datum-rodjenja-konobar").equals(""))
					&& (request.getParameter("form-datum-rodjenja-konobar").length() > 3)){
				konobarDatumRodjenja = request.getParameter("form-datum-rodjenja-konobar");
			}*/
			if( (request.getParameter("form-date-kuvar") != null) 
					&& (!request.getParameter("form-date-kuvar").equals(""))
					&& (request.getParameter("form-date-kuvar").length() == 10)){
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				try {
					dat = sdf.parse(request.getParameter("form-date-kuvar"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if((request.getParameter("form-konfekcijski-broj-kuvar") != null)){
				kuvarKonfekcijskiBroj = request.getParameter("form-konfekcijski-broj-kuvar");
			}
			if((request.getParameter("form-velicina-obuce-kuvar") != null) ){
				kuvarVelicinaObuce = Integer.parseInt(request.getParameter("form-velicina-obuce-kuvar"));
			}
			if((request.getParameter("form-tip-kuvar") != null) 
					&& (!request.getParameter("form-tip-kuvar").equals(""))){
				kuvarTip = request.getParameter("form-tip-kuvar");
			}
			
			if((kuvarId != null) && (!kuvarId.equals(""))){

				Kuvar kuvar = new Kuvar();
				
				kuvar.setKorisnik_id(Integer.parseInt(kuvarId));
				if(kuvarIme != null) { kuvar.setKorisnik_ime(kuvarIme);}
				if(kuvarPrezime != null) { kuvar.setKorisnik_prezime(kuvarPrezime);}
				if(kuvarEmail != null) { kuvar.setKorisnik_email(kuvarEmail);}
				if(kuvarLozinka != null) {kuvar.setKorisnik_lozinka(kuvarLozinka);}
				
				//kuvar.setDatumRodjenjaKuvar(kuvarDatumRodjenja);
				kuvar.setDatumRodjenjaKuvar(dat);
				if(kuvarKonfekcijskiBroj != null) {kuvar.setKonfekcijskiBrojKuvar(kuvarKonfekcijskiBroj);}
				if(kuvarVelicinaObuce != null) {kuvar.setVelicinaObuceKuvar(kuvarVelicinaObuce);}
				if(kuvarTip != null) { kuvar.setTipKuvar(kuvarTip);}
				
				kuvar.setKorisnik_uloga("KUVAR");
				kuvar.setValidiran(true);
				
				kuvarDao.merge(kuvar);
				System.out.println(kuvar);
			}
			response.sendRedirect("./ReadKuvarController");
		} else {
			
			RequestDispatcher disp = request.getRequestDispatcher("./login.jsp");
			disp.forward(request, response);
		
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
