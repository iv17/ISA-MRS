package server.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "narudzbina") 
@NamedQueries({@NamedQuery(name = "getNarudzbineZaGostaZaRezervaciju", query = "SELECT n FROM Narudzbina n WHERE n.rezervacija.idRezervacija like :gost_rez AND n.gost.korisnik_id like :gost_id AND n.poseta.idPoseta is null"),
	@NamedQuery(name = "getRezervacijeZaGosta", query = "SELECT n FROM Narudzbina n WHERE n.gost.korisnik_id like :gost_za_rez AND n.poseta.idPoseta is null"),
	@NamedQuery(name = "getPoseteZaGosta", query = "SELECT n FROM Narudzbina n WHERE n.gost.korisnik_id like :gost_za_rez AND n.poseta.idPoseta is not null"),
	@NamedQuery(name = "getPoseteZaRestoran", query = "SELECT n FROM Narudzbina n WHERE n.restoran.idRestorana like :res_id AND n.poseta.idPoseta is not null"),
	@NamedQuery(name = "getPoseteZaRestoranZaGosta", query = "SELECT n FROM Narudzbina n WHERE n.restoran.idRestorana like :res_id AND n.gost.korisnik_id like :gost_za_rez AND n.poseta.idPoseta is not null")})
public class Narudzbina implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "narudzbina_id", unique = true, nullable = false)
	private Integer idNarudzbine;

	@ManyToOne
	@JoinColumn(name = "rezervacija_id", referencedColumnName = "rezervacija_id", nullable = true)
	private Rezervacija rezervacija;
	
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "obrok_id", referencedColumnName = "obrok_id", nullable = true)
	private Obrok obrok;
	
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "poruceno_pice_id", referencedColumnName = "poruceno_pice_id", nullable = true)
	private PorucenoPice porucenoPice;
	
	

	@ManyToOne
	@JoinColumn(name = "konobar_id", referencedColumnName = "korisnik_id", nullable = true)
	private Konobar konobar;
	
	@ManyToOne
	@JoinColumn(name = "gost_id", referencedColumnName = "korisnik_id", nullable = true)
	private Gost gost;

	@ManyToOne
	@JoinColumn(name = "poseta_id", referencedColumnName = "poseta_id", nullable = true)
	private Poseta poseta;

	@ManyToOne
	@JoinColumn(name = "restoran_id", referencedColumnName = "restoran_id", nullable = true)
	private Restoran restoran;
	
	public Integer getIdNarudzbine() {
		return idNarudzbine;
	}

	public void setIdNarudzbine(Integer idNarudzbine) {
		this.idNarudzbine = idNarudzbine;
	}

	public Rezervacija getRezervacija() {
		return rezervacija;
	}

	public void setRezervacija(Rezervacija rezervacija) {
		this.rezervacija = rezervacija;
	}

	public Obrok getObrok() {
		return obrok;
	}

	public void setObrok(Obrok obrok) {
		this.obrok = obrok;
	}

	public PorucenoPice getPorucenoPice() {
		return porucenoPice;
	}

	public void setPorucenoPice(PorucenoPice porucenoPice) {
		this.porucenoPice = porucenoPice;
	}

	public Konobar getKonobar() {
		return konobar;
	}

	public void setKonobar(Konobar konobar) {
		this.konobar = konobar;
	}

	public Gost getGost() {
		return gost;
	}

	public void setGost(Gost gost) {
		this.gost = gost;
	}

	public Poseta getPoseta() {
		return poseta;
	}

	public void setPoseta(Poseta poseta) {
		this.poseta = poseta;
	}

	
	public Restoran getRestoran() {
		return restoran;
	}

	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}

	public Narudzbina() {
		super();
	}
	
	public Narudzbina(Rezervacija rezervacija, Obrok obrok, PorucenoPice porucenoPice,
			Konobar konobar, Gost gost, Poseta poseta, Restoran restoran) {
		this.rezervacija = rezervacija;
		this.obrok = obrok;
		this.porucenoPice = porucenoPice;
		this.konobar = konobar;
		this.gost = gost;
		this.poseta = poseta;
		this.restoran = restoran;
	}

}
