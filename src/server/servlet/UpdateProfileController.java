package server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import server.entity.Korisnik;
import server.session.KorisnikDaoLocal;

public class UpdateProfileController extends HttpServlet{


	//private static Logger log = Logger.getLogger(ConfirmController.class);

	@EJB
	private KorisnikDaoLocal korisnikDao;
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		boolean exist = true;
		if(session.getAttribute("exist") != null){
			session.removeAttribute("exist");
		}
		
		if(session.getAttribute("emptyspace") != null){
			session.removeAttribute("emptyspace");
		}
		
		if(session.getAttribute("wrongold") != null){
			session.removeAttribute("wrongold");
		}
		
		if(session.getAttribute("correct") != null){
			session.removeAttribute("correct");
		}
		if ((session.getAttribute("gost")) != null) {
			String email_gosta = request.getParameter("form-email");
			String ime_gosta = request.getParameter("form-ime");
			String prezime_gosta = request.getParameter("form-prezime");
			String stara_sifra = request.getParameter("form-oldpass");
			String nova_sifra = request.getParameter("form-newpass");
			String potvrda_sifra = request.getParameter("form-newpass2");
			System.out.println(email_gosta);
			System.out.println("*-*-*-*-*-*-*-*--**-*-*-*-*-**");
			System.out.println("*-*-*-*-*-*-*-*--**-*-*-*-*-**");
			System.out.println(ime_gosta);
			System.out.println("*-*-*-*-*-*-*-*--**-*-*-*-*-**");
			System.out.println(prezime_gosta);
			System.out.println("*-*-*-*-*-*-*-*--**-*-*-*-*-**");
			
			if ((email_gosta == null) || (email_gosta.equals("")) || (ime_gosta == null) || (ime_gosta.equals(""))
					|| (prezime_gosta == null) || (prezime_gosta.equals(""))) {
				boolean emptyspace = true;
				session.setAttribute("emptyspace", emptyspace);
				response.sendRedirect(response.encodeRedirectURL("./show_profile.jsp"));
				System.out.println("NEEE TREBAAAAAAAA");
				return;
			}
			
			
			Korisnik k = (Korisnik)request.getSession().getAttribute("gost");
			System.out.println(k.getKorisnik_ime());
			
			if(((stara_sifra == null) || (stara_sifra.equals(""))) && ((nova_sifra == null) || (nova_sifra.equals(""))) && ((potvrda_sifra == null) || (potvrda_sifra.equals("")))){
				if(!email_gosta.equals(k.getKorisnik_email())){
					Korisnik gost = korisnikDao.findKorisnikSaKorisnickimImenomILozinkom(email_gosta, k.getKorisnik_lozinka());
					if (gost != null) {
						
						if(session.getAttribute("exist") == null){
							session.setAttribute("exist", exist);
						}
						response.sendRedirect(response.encodeRedirectURL("./show_profile.jsp"));
						//ako postoji vec korisnik sa istim email-om i lozinkom koja nije menjana
						System.out.println("::::::::::::::::::::::::::::::::::");
						return;
					}
					
				}
				
				k.setKorisnik_email(email_gosta);
				k.setKorisnik_ime(ime_gosta);
				k.setKorisnik_prezime(prezime_gosta);
				
				System.out.println("*************************************************AA");
				System.out.println(k.getKorisnik_email());
				System.out.println(k.getKorisnik_ime());
				System.out.println(k.getKorisnik_prezime());
				System.out.println(k.getKorisnik_lozinka());
				System.out.println("*************");
				korisnikDao.merge(k);
				
						
				
				
			}
			else if((nova_sifra != null)){
				Korisnik gost2 = korisnikDao.findKorisnikSaKorisnickimImenomILozinkom(email_gosta, nova_sifra);
				if (gost2 != null) {
					if(session.getAttribute("exist") == null){
						session.setAttribute("exist", exist);
					}
					response.sendRedirect(response.encodeRedirectURL("./show_profile.jsp"));
					//ako postoji vec korisnik sa istim email-om i lozinkom koja nije menjana
					return;
				
				}
				else{
				//ako je uneo stari i novi password
					if((stara_sifra.equals(k.getKorisnik_lozinka())) && (nova_sifra.equals(potvrda_sifra)) && (!nova_sifra.equals(""))){
						k.setKorisnik_email(email_gosta);
						k.setKorisnik_ime(ime_gosta);
						k.setKorisnik_prezime(prezime_gosta);
						k.setKorisnik_lozinka(nova_sifra);
						
						System.out.println("*************************************************AA");
						System.out.println(k.getKorisnik_email());
						System.out.println(k.getKorisnik_ime());
						System.out.println(k.getKorisnik_prezime());
						System.out.println(k.getKorisnik_lozinka());
						System.out.println("*************");
						korisnikDao.merge(k);
					
						
					}
					else{
						boolean wrongold = true;
						session.setAttribute("wrongold", wrongold);
						response.sendRedirect(response.encodeRedirectURL("./show_profile.jsp"));
						return;
					}
				}
				
			}
			else{
				
				response.sendRedirect(response.encodeRedirectURL("./show_profile.jsp"));
				return;
				
			}
		}
		boolean correct = true;
		session.setAttribute("correct", correct);
		response.sendRedirect(response.encodeRedirectURL("./show_profile.jsp"));
	
	
	}
	
	
	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}





}
