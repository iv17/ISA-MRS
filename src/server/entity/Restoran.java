package server.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "restoran")
@NamedQuery(name = "showRestaurants", query = "SELECT r FROM Restoran r")

public class Restoran implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "restoran_id", unique = true, nullable = false)
	private Integer idRestorana;

	@Column(name = "restoran_tip", unique = false, nullable = true)
	private String tipRestoran;

	@Column(name = "restoran_naziv", unique = false, nullable = true)
	private String nazivRestoran;
	
	
	@Column(name = "ocene_svih", unique = false, nullable = true)
	private double oceneSvihKorisnika;
	
	public double getOceneSvihKorisnika() {
		return oceneSvihKorisnika;
	}

	public void setOceneSvihKorisnika(double oceneSvihKorisnika) {
		this.oceneSvihKorisnika = oceneSvihKorisnika;
	}
	
	@Column(name = "ocene_prijatelja", unique = false, nullable = true)
	private double ocenePrijatelja;

	public double getOcenePrijatelja() {
		return ocenePrijatelja;
	}

	public void setOcenePrijatelja(double ocenePrijatelja) {
		this.ocenePrijatelja = ocenePrijatelja;
	}

	@ManyToOne
	@JoinColumn(name = "karta_pica_id", referencedColumnName = "karta_pica_id", nullable = true)
	private KartaPica karta_pica;

	@ManyToOne
	@JoinColumn(name = "jelovnik_id", referencedColumnName = "jelovnik_id", nullable = true)
	private Jelovnik jelovnik;
	
	

	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "restoran")
	private Set<Rezervacija> rezervacije = new HashSet<Rezervacija>();

	public void add(Rezervacija r) {
		if (r.getRestoran() != null)
			r.getRestoran().getRezervacije().remove(r);
		r.setRestoran(this);
		rezervacije.add(r);
	}

	public void remove(Rezervacija r) {
		r.setRestoran(null);
		rezervacije.remove(r);
	}
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "restoran")
	private Set<MenadzerRestorana> menadzeri = new HashSet<MenadzerRestorana>();

	public void add(MenadzerRestorana m) {
		if (m.getRestoran() != null)
			m.getRestoran().getMenadzeri().remove(m);
		m.setRestoran(this);
		menadzeri.add(m);
	}

	public void remove(MenadzerRestorana m) {
		m.setRestoran(null);
		menadzeri.remove(m);
	}

	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "restoran")
	private Set<RasporedStolova> rasporedi = new HashSet<RasporedStolova>();

	public void add(RasporedStolova rs) {
		if (rs.getRestoran() != null)
			rs.getRestoran().getRasporedi().remove(rs);
		rs.setRestoran(this);
		rasporedi.add(rs);
	}

	public void remove(RasporedStolova rs) {
		rs.setRestoran(null);
		rasporedi.remove(rs);
	}
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "restoran")
	private Set<Reon> reoni = new HashSet<Reon>();

	public void add(Reon r) {
		if (r.getRestoran() != null)
			r.getRestoran().getReoni().remove(r);
		r.setRestoran(this);
		reoni.add(r);
	}

	public void remove(Reon r) {
		r.setRestoran(null);
		reoni.remove(r);
	}
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "restoran")
	private Set<Sto> stolovi = new HashSet<Sto>();

	public void add(Sto s) {
		if (s.getRestoran() != null)
			s.getRestoran().getStolovi().remove(s);
		s.setRestoran(this);
		stolovi.add(s);
	}

	public void remove(Sto s) {
		s.setRestoran(null);
		stolovi.remove(s);
	}
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "restoran")
	private Set<Konobar> konobari = new HashSet<Konobar>();

	public void add(Konobar k) {
		if (k.getRestoran() != null)
			k.getRestoran().getKonobari().remove(k);
		k.setRestoran(this);
		konobari.add(k);
	}

	public void remove(Konobar k) {
		k.setRestoran(null);
		konobari.remove(k);
	}
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "restoran")
	private Set<Kuvar> kuvari = new HashSet<Kuvar>();

	public void add(Kuvar k) {
		if (k.getRestoran() != null)
			k.getRestoran().getKuvari().remove(k);
		k.setRestoran(this);
		kuvari.add(k);
	}

	public void remove(Kuvar k) {
		k.setRestoran(null);
		kuvari.remove(k);
	}
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "restoran")
	private Set<Sanker> sankeri = new HashSet<Sanker>();

	public void add(Sanker s) {
		if (s.getRestoran() != null)
			s.getRestoran().getSankeri().remove(s);
		s.setRestoran(this);
		sankeri.add(s);
	}

	public void remove(Sanker s) {
		s.setRestoran(null);
		sankeri.remove(s);
	}
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "restoran")
	private Set<Smena> smene = new HashSet<Smena>();

	public void add(Smena s) {
		if (s.getRestoran() != null)
			s.getRestoran().getSmene().remove(s);
		s.setRestoran(this);
		smene.add(s);
	}

	public void remove(Smena s) {
		s.setRestoran(null);
		smene.remove(s);
	}
	
	public Integer getIdRestorana() {
		return idRestorana;
	}

	public void setIdRestorana(Integer idRestorana) {
		this.idRestorana = idRestorana;
	}

	public String getTipRestoran() {
		return tipRestoran;
	}

	public void setTipRestoran(String tipRestoran) {
		this.tipRestoran = tipRestoran;
	}

	public String getNazivRestoran() {
		return nazivRestoran;
	}

	public void setNazivRestoran(String nazivRestoran) {
		this.nazivRestoran = nazivRestoran;
	}

	public KartaPica getKartaPica() {
		return karta_pica;
	}

	public void setKartaPica(KartaPica kartaPica) {
		this.karta_pica = kartaPica;
	}

	public Jelovnik getJelovnik() {
		return jelovnik;
	}

	public void setJelovnik(Jelovnik jelovnik) {
		this.jelovnik = jelovnik;
	}

	public Set<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(Set<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}

	public Set<MenadzerRestorana> getMenadzeri() {
		return menadzeri;
	}

	public void setMenadzeri(Set<MenadzerRestorana> menadzeri) {
		this.menadzeri = menadzeri;
	}
	
	public Set<RasporedStolova> getRasporedi() {
		return rasporedi;
	}

	public void setRasporedi(Set<RasporedStolova> rasporedi) {
		this.rasporedi = rasporedi;
	}
	
	public Set<Reon> getReoni() {
		return reoni;
	}

	public void setReoni(Set<Reon> reoni) {
		this.reoni = reoni;
	}

	public Set<Sto> getStolovi() {
		return stolovi;
	}

	public void setStolovi(Set<Sto> stolovi) {
		this.stolovi = stolovi;
	}

	public Set<Konobar> getKonobari() {
		return konobari;
	}

	public void setKonobari(Set<Konobar> konobari) {
		this.konobari = konobari;
	}

	public Set<Kuvar> getKuvari() {
		return kuvari;
	}

	public void setKuvari(Set<Kuvar> kuvari) {
		this.kuvari = kuvari;
	}

	public Set<Sanker> getSankeri() {
		return sankeri;
	}

	public void setSankeri(Set<Sanker> sankeri) {
		this.sankeri = sankeri;
	}

	public Set<Smena> getSmene() {
		return smene;
	}

	public void setSmene(Set<Smena> smene) {
		this.smene = smene;
	}

	public Restoran() {
		super();
	}
	
	public Restoran(String tipRestoran, String nazivRestoran, KartaPica kartaPica,
			Jelovnik jelovnik, Set<Rezervacija> rezervacije, Set<MenadzerRestorana> menadzeri, 
			Set<RasporedStolova> rasporedi, Set<Reon> reoni, Set<Sto> stolovi,
			Set<Konobar> konobari, Set<Kuvar> kuvari, Set<Sanker> sankeri,
			Set<Smena> smene) {
		this.tipRestoran = tipRestoran;
		this.nazivRestoran = nazivRestoran;
		kartaPica.add(this);
		jelovnik.add(this);
		this.rezervacije = rezervacije;
		this.menadzeri = menadzeri;
		this.rasporedi = rasporedi;
		this.reoni = reoni;
		this.stolovi = stolovi;
		this.konobari = konobari;
		this.kuvari = kuvari;
		this.sankeri = sankeri;
		this.smene = smene;
	}
	
	public Restoran(String tipRestoran, String nazivRestoran) {
		this.tipRestoran = tipRestoran;
		this.nazivRestoran = nazivRestoran;
		
	}

	@Override
	public String toString() {
		return "Restoran [idRestorana=" + idRestorana + ", tipRestoran=" + tipRestoran + ", nazivRestoran="
				+ nazivRestoran + ", karta_pica=" + karta_pica + ", jelovnik=" + jelovnik + "]";
	}

	
}
