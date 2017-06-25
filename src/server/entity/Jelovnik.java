package server.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
@Entity
@Table(name = "jelovnik") 
public class Jelovnik implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "jelovnik_id", unique = true, nullable = false)
	private Integer idJelovnika;

	@Column(name = "jelovnik_naziv", unique = false, nullable = true)
	private String nazivJelovnik;
	
	@Column(name = "jelovnik_datum_od", unique = false, nullable = true)
	private Date datumOdJelovnik;

	@Column(name = "jelovnik_datum_do", unique = false, nullable = true)
	private Date datumDoJelovnik;

	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "jelovnik")
	private Set<Jelo> jela = new HashSet<Jelo>();

	public void add(Jelo j) {
		if (j.getJelovnik() != null)
			j.getJelovnik().getJela().remove(j);
		j.setJelovnik(this);
		jela.add(j);
	}
	
	public void remove(Jelo j) {
		j.setJelovnik(null);
		jela.remove(j);
	}

	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "jelovnik")
	private Set<Restoran> restorani = new HashSet<Restoran>();

	public void add(Restoran r) {
		if (r.getJelovnik() != null)
			r.getJelovnik().getRestorani().remove(r);
		r.setJelovnik(this);
		restorani.add(r);
	}
	
	public void remove(Restoran r) {
		r.setJelovnik(null);
		restorani.remove(r);
	}
	
	public Integer getIdJelovnika() {
		return idJelovnika;
	}

	public void setIdJelovnika(Integer idJelovnika) {
		this.idJelovnika = idJelovnika;
	}

	public String getNazivJelovnik() {
		return nazivJelovnik;
	}

	public void setNazivJelovnik(String nazivJelovnik) {
		this.nazivJelovnik = nazivJelovnik;
	}

	public Date getDatumOdJelovnik() {
		
		return datumOdJelovnik;
	}

	public String getDatumOdJelovnikString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		return sdf.format(datumOdJelovnik);
	}
	
	public void setDatumOdJelovnik(Date datumOdJelovnik) {
		this.datumOdJelovnik = datumOdJelovnik;
	}

	public Date getDatumDoJelovnik() {
		return datumDoJelovnik;
	}

	public String getDatumDoJelovnikString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		return sdf.format(datumDoJelovnik);
	}
	
	public void setDatumDoJelovnik(Date datumDoJelovnik) {
		this.datumDoJelovnik = datumDoJelovnik;
	}

	public Set<Jelo> getJela() {
		return jela;
	}

	public void setJela(Set<Jelo> jela) {
		this.jela = jela;
	}
	
	public Set<Restoran> getRestorani() {
		return restorani;
	}

	public void setRestorani(Set<Restoran> restorani) {
		this.restorani = restorani;
	}

	public Jelovnik() {
		super();
	}
	
	public Jelovnik(String nazivJelovnik, Date datumOdJelovnik, Date datumDoJelovnik,
			Set<Jelo> jela, Set<Restoran> restorani) {
		super();
		this.nazivJelovnik = nazivJelovnik;
		this.datumOdJelovnik = datumOdJelovnik;
		this.datumDoJelovnik = datumDoJelovnik;
		this.jela = jela;
		this.restorani = restorani;
	}

}
