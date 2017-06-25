package server.servlet;

import java.io.IOException;
import java.util.Properties;

import javax.ejb.EJB;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import server.entity.Gost;
import server.entity.Korisnik;
import server.session.KorisnikDaoLocal;

public class RegisterController extends HttpServlet{

	private static final long serialVersionUID = -7345471861052209628L;
	
	private static Logger log = Logger.getLogger(RegisterController.class);

	@EJB
	private KorisnikDaoLocal gostDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			String email_gosta = request.getParameter("email_gosta");
			String lozinka_gosta = request.getParameter("lozinka_gosta");
			String ime_gosta = request.getParameter("ime_gosta");
			String prezime_gosta = request.getParameter("prezime_gosta");
			String lozinka_ponovo = request.getParameter("lozinka_ponovo");
			System.out.println("RRRRRRRRRRRRRRRRRRRRRRRRRRR");
			System.out.println(email_gosta);
			System.out.println(lozinka_gosta);
			System.out.println("RRRRRRRRRRRRRRRRRRAAAAAAAAAAAAA");
			HttpSession sessionhttp = request.getSession(true);
			if(sessionhttp.getAttribute("exists") != null){
				sessionhttp.removeAttribute("exists");
			}
			if(sessionhttp.getAttribute("missing") != null){
				sessionhttp.removeAttribute("missing");
			}
			if(sessionhttp.getAttribute("nopass") != null){
				sessionhttp.removeAttribute("nopass");
			}
			
			
			if ((email_gosta == null) || (email_gosta.equals("")) || (lozinka_gosta == null) || (lozinka_gosta.equals("")) || (ime_gosta == null) || (ime_gosta.equals(""))
					|| (prezime_gosta == null) || (prezime_gosta.equals("")) || (email_gosta.equals("email")) || (lozinka_gosta.equals("Password"))
					|| (ime_gosta.equals("first name")) || (prezime_gosta.equals("last name"))) {
				
				System.out.println("DADADADADAADADADADAADADADDADADADADADADA");
				System.out.println(email_gosta);
				System.out.println(lozinka_gosta);
				System.out.println(prezime_gosta);
				System.out.println(ime_gosta);
				System.out.println("DADADADADADADADADADADADADADADADADADADA");
				
				boolean missing = true;
				sessionhttp.setAttribute("missing", missing);
				response.sendRedirect(response.encodeRedirectURL("./registration.jsp"));
				return;
			}
			
			Korisnik gost = gostDao.findKorisnikSaKorisnickimImenomILozinkom(email_gosta, lozinka_gosta);
			if (gost == null) {	
				//HttpSession session = request.getSession(true);
				//session.setAttribute("gost", gost);
				//TODO treba ubaciti slanje mail-a
				//log.info("Gost " + gost.get_ime_gosta() + " se prijavio.");
				if(lozinka_ponovo.equals(lozinka_gosta)){
					
					Korisnik g = new Gost();
					g.setKorisnik_email(email_gosta);
					g.setKorisnik_lozinka(lozinka_gosta);
					g.setKorisnik_ime(ime_gosta);
					g.setKorisnik_prezime(prezime_gosta);
					g.setKorisnik_uloga("gost");
					g.setValidiran(false);
					g = gostDao.persist(g);
					
					sessionhttp.setAttribute("gost", g);
					System.out.println("---------" + g.getKorisnik_ime());
					//getServletContext().getRequestDispatcher("/ReadController").forward(request, response);
					response.sendRedirect(response.encodeRedirectURL("./logovani.jsp"));
					String mailTo = "tamaravaradjanin94@gmail.com";
					String mailFrom = "tamarava.isamrsa@gmail.com";
					String username = "tamara.isamrsa";
					String pass = "bazepodataka2015";
					Properties properties = System.getProperties();
					//properties.setProperty("mail.smtp.host", host);
					properties.put("mail.smtp.starttls.enable", "true"); 
					properties.put("mail.smtp.host", "smtp.gmail.com");
					properties.put("mail.smtp.user", username); // User name
					properties.put("mail.smtp.password", pass); // password
					properties.put("mail.smtp.port", "587");
					properties.put("mail.smtp.auth", "true");
					/*properties.put("mail.smtp.socketFactory.port", "587");
					properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
					properties.put("mail.smtp.socketFactory.fallback", "false");*/
					Session session = Session.getInstance(properties, new MyAuthenticator(username,pass));
					response.setContentType("text/html");
				    try{
				         // Create a default MimeMessage object.
				         MimeMessage message = new MimeMessage(session);
				         // Set From: header field of the header.
				         message.setFrom(new InternetAddress(mailFrom));
				         // Set To: header field of the header.
				         message.addRecipient(Message.RecipientType.TO,
				                                  new InternetAddress(mailTo));
				         // Set Subject: header field
				         message.setSubject("Confirm registration!");
				         // Now set the actual message
				         String mess = "Please, confirm your registration by clicking on link: ";
				         String mail_html = "<html>\n" +
				         "<head></head>\n" +
				         "<body>\n" +
				         "<p align=\"center\">" + mess + "<a href=\"http://localhost:8080/tim14/ConfirmController\">Confirm</a></p>\n" +
				         "</body></html>";
				         //MimeBodyPart messageBodyPart = new MimeBodyPart();
				         //messageBodyPart.setText(mail_html,"text/html; charset=utf-8");
				         message.setContent(mail_html,"text/html");
				         // Send message
				         Transport.send(message);
				         /*String title = "Send Email";
				         String res = "Sent message successfully....";
				         String docType =
				         "<!doctype html public \"-//w3c//dtd html 4.0 " +
				         "transitional//en\">\n";
				         out.println(docType +
				         "<html>\n" +
				         "<head><title>" + title + "</title></head>\n" +
				         "<body bgcolor=\"#f0f0f0\">\n" +
				         "<h1 align=\"center\">" + title + "</h1>\n" +
				         "<p align=\"center\">" + res + "</p>\n" +
				         "</body></html>");*/
				      }catch (MessagingException mex) {
				         mex.printStackTrace();
				      }
				}
				else{
	
					boolean nopass = true;
					sessionhttp.setAttribute("nopass", nopass);
					response.sendRedirect(response.encodeRedirectURL("./registration.jsp"));
					return;
					
				}
			}
				
			else{
					
				boolean exists = true;
				sessionhttp.setAttribute("exists", exists);
				response.sendRedirect(response.encodeRedirectURL("./registration.jsp"));
				return;
					
			}
				
			
		 /*catch (EJBException e) {
			if (e.getCause().getClass().equals(NoResultException.class)) {
				response.sendRedirect(response.encodeRedirectURL("./registration.jsp"));
			} else {
				throw e;
			}*/
			}
		 catch (IOException e) {
			log.error(e);
			throw e;
		}
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}





}
