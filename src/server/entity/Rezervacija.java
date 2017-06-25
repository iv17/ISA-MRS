package server.entity;

import static javax.persistence.CascadeType.ALL;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
@Entity
@Table(name = "rezervacija")
@NamedQueries({@NamedQuery(name = "getRezervacije", query = "SELECT r FROM Rezervacija r"),
	@NamedQuery(name = "getRezervacijeRestorana", query = "SELECT r FROM Rezervacija r WHERE r.restoran.idRestorana like :restoranrez"),
	@NamedQuery(name = "getRezervacijeZaSto", query = "SELECT r FROM Rezervacija r WHERE r.sto.idStola like :storez"),
	@NamedQuery(name = "getRezervacijeGost", query = "SELECT r FROM Rezervacija r WHERE r.gost.korisnik_id like :grez")})

public class Rezervacija implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "rezervacija_id", unique = true, nullable = false)
	private Integer idRezervacija;

	@Column(name = "rezervacija_datum_dolaska", unique = false, nullable = true)
	private Date datumDolaskaRezervacija;

	@Column(name = "rezervacija_duzina_boravka", unique = false, nullable = true)
	private Date  rezervacija_duzina_boravka;
	
	@ManyToOne
	@JoinColumn(name = "gost_id", referencedColumnName = "korisnik_id", nullable = true)
	private Gost gost;
	
	@ManyToOne
	@JoinColumn(name = "sto_id", referencedColumnName = "sto_id", nullable = true)
	private Sto sto;

	@ManyToOne
	@JoinColumn(name = "restoran_id", referencedColumnName = "restoran_id", nullable = true)
	private Restoran restoran;
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "rezervacija")
	private Set<Narudzbina> narudzbine = new HashSet<Narudzbina>();

	public void add(Narudzbina n) {
		if (n.getRezervacija()!= null)
			n.getRezervacija().getNarudzbine().remove(n);
		n.setRezervacija(this);
		narudzbine.add(n);
	}

	public void remove(Narudzbina n) {
		n.setRezervacija(null);
		narudzbine.remove(n);
	}
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "poslati_pozivi", joinColumns = { 
			@JoinColumn(name = "rezervacija_id", nullable = false, updatable = true) }, 
			inverseJoinColumns = { @JoinColumn(name = "gost_id", 
					nullable = false, updatable = true) })
	private Set<Gost> pozvaniGosti = new HashSet<Gost>();
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "prihvaceni_pozivi", joinColumns = { 
			@JoinColumn(name = "rezervacija_id", nullable = false, updatable = true) }, 
			inverseJoinColumns = { @JoinColumn(name = "gost_id", 
					nullable = false, updatable = true) })
	private Set<Gost> prihvaceniGosti = new HashSet<Gost>();


	public Set<Gost> getPozvaniGosti() {
		return pozvaniGosti;
	}

	public void setPozvaniGosti(Set<Gost> pozvaniGosti) {
		this.pozvaniGosti = pozvaniGosti;
	}

	public Set<Gost> getPrihvaceniGosti() {
		return prihvaceniGosti;
	}

	public void setPrihvaceniGosti(Set<Gost> prihvaceniGosti) {
		this.prihvaceniGosti = prihvaceniGosti;
	}

	public Integer getIdRezervacija() {
		return idRezervacija;
	}

	public void setIdRezervacija(Integer idRezervacija) {
		this.idRezervacija = idRezervacija;
	}

	public Date getDatumDolaskaRezervacija() {
		return datumDolaskaRezervacija;
	}

	public String getDatumDolaskaRezervacijaString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		return sdf.format(datumDolaskaRezervacija);
	}
	
	public String getDatumDolaskaRezervacijaJSON() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		return sdf.format(datumDolaskaRezervacija);
	}
	
	public void setDatumDolaskaRezervacija(Date datumDolaskaRezervacija) {
		this.datumDolaskaRezervacija = datumDolaskaRezervacija;
	}

	public Date getRezervacija_duzina_boravka() {
		return rezervacija_duzina_boravka;
	}

	public String getRezervacija_duzina_boravkaString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		return sdf.format(rezervacija_duzina_boravka);
	}
	public void setRezervacija_duzina_boravka(Date rezervacija_duzina_boravka) {
		this.rezervacija_duzina_boravka = rezervacija_duzina_boravka;
	}
	
	public Set<Narudzbina> getNarudzbine() {
		return narudzbine;
	}

	public void setNarudzbine(Set<Narudzbina> narudzbine) {
		this.narudzbine = narudzbine;
	}

	public Gost getGost() {
		return gost;
	}

	public void setGost(Gost gost) {
		this.gost = gost;
	}

	public Sto getSto() {
		return sto;
	}

	public void setSto(Sto sto) {
		this.sto = sto;
	}

	public Restoran getRestoran() {
		return restoran;
	}

	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}

	public Rezervacija() {
		super();
	}
	
	public Rezervacija(Date datumDolaskaRezervacija, Date rezervacija_duzina_boravka,
			Gost gost, Sto sto, Restoran restoran, Set<Narudzbina> narudzbine,
			Set<Gost> prihvaceniGosti) {
		this.datumDolaskaRezervacija = datumDolaskaRezervacija;
		this.rezervacija_duzina_boravka = rezervacija_duzina_boravka;
		this.gost = gost;
		this.sto = sto;
		this.restoran = restoran;
		this.narudzbine = narudzbine;
		this.prihvaceniGosti = prihvaceniGosti;
	}
	
}
