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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
@Entity
@Table(name = "reon") 
public class Reon implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "reon_id", unique = true, nullable = false)
	private Integer idReona;

	@Column(name = "reon_naziv", unique = false, nullable = true)
	private String nazivReona;

	@Column(name = "reon_broj_stolova", unique = false, nullable = true)
	private Integer brojStolovaReon;
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "reon")
	private Set<Sto> stolovi = new HashSet<Sto>();

	public void add(Sto s) {
		if (s.getReon() != null)
			s.getReon().getStolovi().remove(s);
		s.setReon(this);
		stolovi.add(s);
	}

	public void remove(Sto s) {
		s.setReon(null);
		stolovi.remove(s);
	}	

	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "reon")
	private Set<Smena> smene = new HashSet<Smena>();

	public void add(Smena s) {
		if (s.getReon() != null)
			s.getReon().getSmene().remove(s);
		s.setReon(this);
		smene.add(s);
	}

	public void remove(Smena s) {
		s.setReon(null);
		smene.remove(s);
	}
	
	@ManyToOne
	@JoinColumn(name = "restoran_id", referencedColumnName = "restoran_id", nullable = true)
	private Restoran restoran;
	
	public Integer getIdReona() {
		return idReona;
	}

	public void setIdReona(Integer idReona) {
		this.idReona = idReona;
	}

	public String getNazivReona() {
		return nazivReona;
	}

	public void setNazivReona(String nazivReona) {
		this.nazivReona = nazivReona;
	}

	public Integer getBrojStolovaReon() {
		return brojStolovaReon;
	}

	public void setBrojStolovaReon(Integer brojStolovaReon) {
		this.brojStolovaReon = brojStolovaReon;
	}
	
	public Set<Sto> getStolovi() {
		return stolovi;
	}

	public void setStolovi(Set<Sto> stolovi) {
		this.stolovi = stolovi;
	}
	
	public Set<Smena> getSmene() {
		return smene;
	}

	public void setSmene(Set<Smena> smene) {
		this.smene = smene;
	}

	public Restoran getRestoran() {
		return restoran;
	}

	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}

	public Reon() {
		super();
	}
	public Reon(String nazivReona, Integer brojStolovaReon, Set<Sto> stolovi, Set<Smena> smene, Restoran restoran) {
		this.nazivReona = nazivReona;
		this.brojStolovaReon = brojStolovaReon;
		this.stolovi = stolovi;
		this.smene = smene;
		restoran.add(this);
	}

}
