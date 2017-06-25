package server.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import server.entity.Korisnik;
import server.session.JeloDaoLocal;
import server.session.JelovnikDaoLocal;
import server.session.KartaPicaDaoLocal;
import server.session.KonobarDaoLocal;
import server.session.KorisnikDaoLocal;
import server.session.KuvarDaoLocal;
import server.session.NamirniceDaoLocal;
import server.session.NarudzbinaDaoLocal;
import server.session.ObrokDaoLocal;
import server.session.PonudaDaoLocal;
import server.session.PonudjacDaoLocal;
import server.session.PosetaDaoLocal;
import server.session.RasporedDaoLocal;
import server.session.ReonDaoLocal;
import server.session.RestoranDaoLocal;
import server.session.RezervacijaDaoLocal;
import server.session.SankerDaoLocal;
import server.session.SmenaDaoLocal;
import server.session.StoDaoLocal;


public class LoginController extends HttpServlet {

	private static final long serialVersionUID = -7345471861052209628L;
	
	private static Logger log = Logger.getLogger(LoginController.class);

	@EJB
	private KorisnikDaoLocal gostDao;

	//OVO MENI TREBA
	@EJB 
	private ReonDaoLocal reonDao;
	@EJB
	private KonobarDaoLocal konobarDao;
	@EJB
	private KuvarDaoLocal kuvarDao;
	@EJB
	private SankerDaoLocal sankerDao;
	@EJB 
	private JelovnikDaoLocal jelovnikDao;
	@EJB
	private KartaPicaDaoLocal kartaPicaDao;
	@EJB
	private JeloDaoLocal jeloDao;
	@EJB
	private RestoranDaoLocal restoranDao;
	@EJB 
	private NamirniceDaoLocal namirniceDao;
	@EJB
	private PonudjacDaoLocal ponudjacDao;
	@EJB
	private PonudaDaoLocal ponudaDao;
	@EJB
	private SmenaDaoLocal smenaDao;
	@EJB
	private RasporedDaoLocal rasporedDao;
	@EJB
	private PosetaDaoLocal posetaDao;
	@EJB
	private StoDaoLocal stoDao;
	@EJB
	private NarudzbinaDaoLocal narudzbinaDao;
	@EJB
	private RezervacijaDaoLocal rezervacijaDao;
	@EJB
	private ObrokDaoLocal obrokDao;

	//IVANA
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			String email_gosta = request.getParameter("email_korisnika");
			String lozinka_gosta = request.getParameter("lozinka_korisnika");
			System.out.println(email_gosta);
			System.out.println(lozinka_gosta + "************");
			response.setContentType("text/html;charset=UTF-8");
			HttpSession session = request.getSession(true);
			if(session.getAttribute("wrong") != null){
				
				session.removeAttribute("wrong");
				
			}
			
			if ((email_gosta == null) || (email_gosta.equals("")) || (lozinka_gosta == null) || (lozinka_gosta.equals(""))) {
				/*out.println("<script type=\"text/javascript\">");
				out.println("alert('User or password incorrect');");
				out.println("location='login.jsp';");
				out.println("</script>");*/
				response.sendRedirect(response.encodeRedirectURL("./login.jsp"));
				
				return;
			}
			System.out.println("prijavaaaaaaaaaa");
			Korisnik korisnik = gostDao.findKorisnikSaKorisnickimImenomILozinkom(email_gosta, lozinka_gosta);
			
			boolean wrong = false;
			if (korisnik != null) {	
				
				
				
				log.info("Gost " + korisnik.getKorisnik_ime() + " se prijavio.");
				if(korisnik.getKorisnik_uloga().equals("gost")){
					response.sendRedirect(response.encodeRedirectURL("./ShowPoseteController"));
					session.setAttribute("gost", korisnik);
						
				}
				else if(korisnik.getKorisnik_uloga().equals("MENADZER RESTORANA")){
					//NA SESIJU ZA MENADZERA SE KACI SVE STO MU TREBA
					response.sendRedirect(response.encodeRedirectURL("./home_page_menadzer_restorana.jsp"));
					session.setAttribute("menadzer_restorana", korisnik);
				
					session.setAttribute("reoni", reonDao.findAll());
					session.setAttribute("jelovnici", jelovnikDao.findAll());
					session.setAttribute("kartePica", kartaPicaDao.findAll());
					session.setAttribute("jela", jeloDao.findAll());
					session.setAttribute("konobari", konobarDao.findAll());
					session.setAttribute("kuvari", kuvarDao.findAll());
					session.setAttribute("sankeri", sankerDao.findAll());
					session.setAttribute("restorani", restoranDao.findAll());
					session.setAttribute("namirnice", namirniceDao.findAll());
					session.setAttribute("restoran", restoranDao.findById(1));
					session.setAttribute("ponude", ponudaDao.findAll());
					session.setAttribute("rasporedi", rasporedDao.findAll());
					session.setAttribute("posete", posetaDao.findAll());
					session.setAttribute("stolovi", stoDao.findAll());
					session.setAttribute("rezervacije", rezervacijaDao.findAll());
					session.setAttribute("narudzbine", narudzbinaDao.findAll());
					session.setAttribute("obroci", obrokDao.findAll());
					session.setAttribute("ocenaKonobara", 0);
					session.setAttribute("ocenaJela", 0);
					session.setAttribute("obrociSize", -1);
					session.setAttribute("ocenaRestorana", 0);
					session.setAttribute("prihodKonobara", -1.0);
					session.setAttribute("prihodRestorana", -1.0);
					session.setAttribute("zaradaJelo", -1.0);
					session.setAttribute("zaradaPice", -1.0);
					session.setAttribute("brojPosetaDnevni", -1);
					session.setAttribute("brojPosetaNedeljni", -1);
					session.setAttribute("brKonobara", 0);
					session.setAttribute("json", new ArrayList<String>());
					session.setAttribute("json", new ArrayList<String>());
					session.setAttribute("jsonNedeljni", new ArrayList<String>());
					session.setAttribute("jsonDnevni", new ArrayList<String>());
					session.setAttribute("ponudjaci", ponudjacDao.findAll());
					session.setAttribute("smene", smenaDao.findAll());
					//IVANA
				}
				else if(korisnik.getKorisnik_uloga().equals("PONUDJAC")){
					//NA SESIJU ZA MENADZERA SE KACI SVE STO MU TREBA
					response.sendRedirect(response.encodeRedirectURL("./home_page_ponudjac.jsp"));
					session.setAttribute("ponudjac", korisnik);
					session.setAttribute("ponude", ponudaDao.findAll());
					session.setAttribute("namirnice", namirniceDao.findAll());
					//IVANA
				}
				else if(korisnik.getKorisnik_uloga().equals("menadzer_sistema")){
					response.sendRedirect(response.encodeRedirectURL("./dodajMenadzera.jsp"));
					session.setAttribute("menadzer_sistema", korisnik);
				}
				else if(korisnik.getKorisnik_uloga().equals("KONOBAR")){
					session.setAttribute("reoni", reonDao.findAll());
					session.setAttribute("jelovnici", jelovnikDao.findAll());
					session.setAttribute("kartePica", kartaPicaDao.findAll());
					session.setAttribute("jela", jeloDao.findAll());
					session.setAttribute("konobari", konobarDao.findAll());
					session.setAttribute("kuvari", kuvarDao.findAll());
					session.setAttribute("sankeri", sankerDao.findAll());
					session.setAttribute("restorani", restoranDao.findAll());
					session.setAttribute("namirnice", namirniceDao.findAll());
					session.setAttribute("restoran", restoranDao.findById(1));
					session.setAttribute("ponude", ponudaDao.findAll());
					session.setAttribute("rasporedi", rasporedDao.findAll());
					session.setAttribute("posete", posetaDao.findAll());
					session.setAttribute("stolovi", stoDao.findAll());
					session.setAttribute("rezervacije", rezervacijaDao.findAll());
					session.setAttribute("narudzbine", narudzbinaDao.findAll());
					session.setAttribute("obroci", obrokDao.findAll());
					session.setAttribute("ocenaKonobara", 0);
					session.setAttribute("ocenaJela", 0);
					session.setAttribute("obrociSize", -1);
					session.setAttribute("brKonobara", 0);
					session.setAttribute("ocenaRestorana", 0);
					session.setAttribute("prihodKonobara", -1.0);
					session.setAttribute("zaradaJelo", -1.0);
					session.setAttribute("zaradaPice", -1.0);
					
					response.sendRedirect(response.encodeRedirectURL("./IndexKonobar1.jsp"));
					session.setAttribute("konobar", korisnik);
				}
				else if(korisnik.getKorisnik_uloga().equals("KUVAR")){
					session.setAttribute("reoni", reonDao.findAll());
					session.setAttribute("jelovnici", jelovnikDao.findAll());
					session.setAttribute("kartePica", kartaPicaDao.findAll());
					session.setAttribute("jela", jeloDao.findAll());
					session.setAttribute("konobari", konobarDao.findAll());
					session.setAttribute("kuvari", kuvarDao.findAll());
					session.setAttribute("sankeri", sankerDao.findAll());
					session.setAttribute("restorani", restoranDao.findAll());
					session.setAttribute("namirnice", namirniceDao.findAll());
					session.setAttribute("restoran", restoranDao.findById(1));
					session.setAttribute("ponude", ponudaDao.findAll());
					session.setAttribute("rasporedi", rasporedDao.findAll());
					session.setAttribute("posete", posetaDao.findAll());
					session.setAttribute("stolovi", stoDao.findAll());
					session.setAttribute("rezervacije", rezervacijaDao.findAll());
					session.setAttribute("narudzbine", narudzbinaDao.findAll());
					session.setAttribute("obroci", obrokDao.findAll());
					session.setAttribute("ocenaKonobara", 0);
					session.setAttribute("ocenaJela", 0);
					session.setAttribute("obrociSize", -1);
					session.setAttribute("brKonobara", 0);
					session.setAttribute("ocenaRestorana", 0);
					session.setAttribute("prihodKonobara", -1.0);
					session.setAttribute("zaradaJelo", -1.0);
					session.setAttribute("zaradaPice", -1.0);
					
					response.sendRedirect(response.encodeRedirectURL("./IndexKuvar1.jsp"));
					session.setAttribute("kuvar", korisnik);
				}
				else if(korisnik.getKorisnik_uloga().equals("SANKER")){
					session.setAttribute("reoni", reonDao.findAll());
					session.setAttribute("jelovnici", jelovnikDao.findAll());
					session.setAttribute("kartePica", kartaPicaDao.findAll());
					session.setAttribute("jela", jeloDao.findAll());
					session.setAttribute("konobari", konobarDao.findAll());
					session.setAttribute("kuvari", kuvarDao.findAll());
					session.setAttribute("sankeri", sankerDao.findAll());
					session.setAttribute("restorani", restoranDao.findAll());
					session.setAttribute("namirnice", namirniceDao.findAll());
					session.setAttribute("restoran", restoranDao.findById(1));
					session.setAttribute("ponude", ponudaDao.findAll());
					session.setAttribute("rasporedi", rasporedDao.findAll());
					session.setAttribute("posete", posetaDao.findAll());
					session.setAttribute("stolovi", stoDao.findAll());
					session.setAttribute("rezervacije", rezervacijaDao.findAll());
					session.setAttribute("narudzbine", narudzbinaDao.findAll());
					session.setAttribute("obroci", obrokDao.findAll());
					session.setAttribute("ocenaKonobara", 0);
					session.setAttribute("ocenaJela", 0);
					session.setAttribute("obrociSize", -1);
					session.setAttribute("brKonobara", 0);
					session.setAttribute("ocenaRestorana", 0);
					session.setAttribute("prihodKonobara", -1.0);
					session.setAttribute("zaradaJelo", -1.0);
					session.setAttribute("zaradaPice", -1.0);
					
					response.sendRedirect(response.encodeRedirectURL("./IndexSanker1.jsp"));
					session.setAttribute("sanker", korisnik);
				}
				
				//getServletContext().getRequestDispatcher("/ReadController").forward(request, response);
				
			}
			else{
				
				response.sendRedirect(response.encodeRedirectURL("./login.jsp"));
				wrong = true;
				//response.getOutputStream().println("<script>showMessage();</script>");
				//out.println("<script language='JavaScript'>showMessage();</script>");
				session.setAttribute("wrong", wrong);
				return;
				
			}
			
		} catch (EJBException e) {
			if (e.getCause().getClass().equals(NoResultException.class)) {
				response.sendRedirect(response.encodeRedirectURL("./login.jsp"));
			} else {
				throw e;
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
