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

/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
@Entity
@Table(name = "sto") 
@NamedQuery(name = "getTablesForRestaurant", query = "SELECT s FROM Sto s WHERE s.restoran.idRestorana like :restoranrez")

public class Sto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "sto_id", unique = true, nullable = false)
	private Integer idStola;

	@Column(name = "sto_broj_mesta", unique = false, nullable = true)
	private Integer brojMestaSto;

	@Column(name = "sto_rezervisan", unique = false, nullable = true)
	private Boolean rezervisanSto;
	
	@ManyToOne
	@JoinColumn(name = "reon", referencedColumnName = "reon_id", nullable = true)
	private Reon reon;

	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "sto")
	private Set<Rezervacija> rezervacije = new HashSet<Rezervacija>();

	public void add(Rezervacija r) {
		if (r.getSto() != null)
			r.getSto().getRezervacije().remove(r);
		r.setSto(this);
		rezervacije.add(r);
	}

	public void remove(Rezervacija r) {
		r.setSto(null);
		rezervacije.remove(r);
	}
	
	@ManyToOne
	@JoinColumn(name = "restoran_id", referencedColumnName = "restoran_id", nullable = true)
	private Restoran restoran;
	
	@ManyToOne
	@JoinColumn(name = "raspored_id", referencedColumnName = "raspored_id", nullable = true)
	private RasporedStolova raspored;
	
	@Column(name = "sto_json",columnDefinition = "LONGTEXT")
	private String stoJson;
	
	public Integer getIdStola() {
		return idStola;
	}

	public void setIdStola(Integer idStola) {
		this.idStola = idStola;
	}

	public Integer getBrojMestaSto() {
		return brojMestaSto;
	}

	public void setBrojMestaSto(Integer brojMestaSto) {
		this.brojMestaSto = brojMestaSto;
	}

	public Boolean getRezervisanSto() {
		return rezervisanSto;
	}

	public void setRezervisanSto(Boolean rezervisanSto) {
		this.rezervisanSto = rezervisanSto;
	}

	public Reon getReon() {
		return reon;
	}

	public void setReon(Reon reon) {
		this.reon = reon;
	}

	public Set<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(Set<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}
	
	public Restoran getRestoran() {
		return restoran;
	}

	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}

	public RasporedStolova getRaspored() {
		return raspored;
	}

	public void setRaspored(RasporedStolova raspored) {
		this.raspored = raspored;
	}
	public String getStoJson() {
		return stoJson;
	}

	public void setStoJson(String stoJson) {
		this.stoJson = stoJson;
	}
	
	public Sto() {
		super();
	}
	
	public Sto(Integer brojMestaSto, Boolean rezervisanSto, Reon reon, Set<Rezervacija> rezervacije,
			Restoran restoran, RasporedStolova raspored, String stoJson) {
		
		this.brojMestaSto = brojMestaSto;
		this.rezervisanSto = rezervisanSto;
		reon.add(this);
		this.rezervacije = rezervacije;
		restoran.add(this);
		raspored.add(this);
		this.stoJson = stoJson;
	}
	
	public Sto(Integer brojMestaSto, Boolean rezervisanSto, Reon reon, Set<Rezervacija> rezervacije,
			Restoran restoran) {
		
		this.brojMestaSto = brojMestaSto;
		this.rezervisanSto = rezervisanSto;
		reon.add(this);
		this.rezervacije = rezervacije;
		restoran.add(this);	
	}
}
