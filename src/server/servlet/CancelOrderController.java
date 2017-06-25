package server.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import server.entity.Narudzbina;
import server.entity.Rezervacija;
import server.session.NarudzbinaDaoLocal;

public class CancelOrderController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(CancelOrderController.class);
	
	@EJB
	NarudzbinaDaoLocal narudzbinaDao;
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			//Gost kor = (Gost)request.getSession().getAttribute("gost");
			if ((request.getSession().getAttribute("gost")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			
			
			
			System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV");
			String narId = request.getParameter("narId");
			Narudzbina narudzbina = narudzbinaDao.findById(Integer.parseInt(narId));
			Rezervacija rezervacija = narudzbina.getRezervacija();
			Date datum_rez = rezervacija.getDatumDolaskaRezervacija();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            java.util.Date curr_date = new java.util.Date();
            String datetime = dateFormat.format(curr_date);
            System.out.println("Current Date Time : " + datetime);

            //provera da li su isti datumi za danas i datum sa rezervacije
            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
            if(fmt.format(datum_rez).equals(fmt.format(curr_date)) == true){
            	//provera da li je ostalo manje od 30 min
            	if ( datum_rez.getTime() - curr_date.getTime() < 30*60*1000) {
            		long a = curr_date.getTime() - datum_rez.getTime();
            		
            		System.out.println("AAAAAAAAAAAAAAAbbbbbbbb");
            		System.out.println(a);
            		Boolean bad_time = true;
            		request.setAttribute("order_can", bad_time);
            		getServletContext().getRequestDispatcher("/ShowOrdersController").forward(request, response);
            		return;
            	}
            	
            }
            System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMDDDDD");
            narudzbinaDao.remove(narudzbina);
            Boolean otkazano = true;
            request.setAttribute("cancel_order", otkazano);
    		
            getServletContext().getRequestDispatcher("/ShowOrdersController").forward(request, response);
    		
		
		} catch (IOException e) {
			log.error(e);
			throw e;
		}		
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}



}
