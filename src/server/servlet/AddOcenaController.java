package server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import server.entity.Poseta;
import server.session.PosetaDaoLocal;

public class AddOcenaController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(AddOcenaController.class);
	
	@EJB
	PosetaDaoLocal posetaDao;
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if ((request.getSession().getAttribute("gost")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			
			
			String a = request.getParameter("input-1");
			System.out.println(a + "prviii");
			Integer r_ocena = Integer.parseInt(a);
			String b = request.getParameter("input-2");
			System.out.println(b + "drugiii");
			Integer j_ocena = Integer.parseInt(b);
			String c = request.getParameter("input-3");
			System.out.println(c + "treciiii");
			Integer k_ocena = Integer.parseInt(c);
			
			String e = request.getParameter("poseta_id");
			Poseta poseta = posetaDao.findById(Integer.parseInt(e));
			System.out.println(e);
			if(r_ocena != 0 || k_ocena != 0 || j_ocena != 0){
				if(r_ocena != 0){
					if(poseta.getOcenaRestoranaPoseta() != null){
						Boolean aa = true;
						request.setAttribute("res_postoji", aa);	
					}
					else{
						poseta.setOcenaRestoranaPoseta(r_ocena);
						posetaDao.merge(poseta);
					}
					
				}
				if(j_ocena != 0){
					if(poseta.getOcenaJelaPoseta() != null){
						Boolean bb = true;
						request.setAttribute("jelo_postoji", bb);	
					}
					else{
						poseta.setOcenaJelaPoseta(j_ocena);
						posetaDao.merge(poseta);
					}
					
				}
				if(k_ocena != 0){
					if(poseta.getOcenaKonobaraPoseta() != null){
						Boolean cc = true;
						request.setAttribute("konobar_postoji", cc);	
					}
					else{
						poseta.setOcenaKonobaraPoseta(k_ocena);
						posetaDao.merge(poseta);
					}
					
				}
				getServletContext().getRequestDispatcher("/ShowPoseteController").forward(request, response);
				
				
			}
			else{
				response.sendRedirect(response.encodeURL("./ShowPoseteController"));
			}
			
			
			
		} catch (IOException e) {
			log.error(e);
			throw e;
		}		
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	


}
