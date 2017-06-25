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
@Table(name = "karta_pica") 
public class KartaPica implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "karta_pica_id", unique = true, nullable = false)
	private Integer idKartaPica;

	@Column(name = "karta_pica_naziv", unique = true, nullable = false)
	private String nazivKartaPica;
	
	@Column(name = "karta_pica_datum_od", unique = false, nullable = true)
	private Date datumOdKartaPica;

	@Column(name = "karta_pica_datum_do", unique = false, nullable = true)
	private Date datumDoKartaPica;

	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "kartaPica")
	private Set<Pice> pica = new HashSet<Pice>();

	public void add(Pice p) {
		if (p.getKartaPica() != null)
			p.getKartaPica().getPica().remove(p);
		p.setKartaPica(this);
		pica.add(p);
	}

	public void remove(Pice p) {
		p.setKartaPica(null);
		pica.remove(p);
	}
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "karta_pica")
	private Set<Restoran> restorani = new HashSet<Restoran>();

	public void add(Restoran r) {
		if (r.getKartaPica() != null)
			r.getKartaPica().getRestorani().remove(r);
		r.setKartaPica(this);
		restorani.add(r);
	}

	public void remove(Restoran r) {
		r.setKartaPica(null);
		restorani.remove(r);
	}
	

	public Integer getIdKartaPica() {
		return idKartaPica;
	}

	public void setIdKartaPica(Integer idKartaPica) {
		this.idKartaPica = idKartaPica;
	}

	public String getNazivKartaPica() {
		return nazivKartaPica;
	}

	public void setNazivKartaPica(String nazivKartaPica) {
		this.nazivKartaPica = nazivKartaPica;
	}

	public Date getDatumOdKartaPica() {
		return datumOdKartaPica;
	}
	
	public String getDatumOdKartaPicaString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		return sdf.format(datumOdKartaPica);
	}
	
	public void setDatumOdKartaPica(Date datumOdKartaPica) {
		this.datumOdKartaPica = datumOdKartaPica;
	}

	public Date getDatumDoKartaPica() {
		return datumDoKartaPica;
	}
	
	public String getDatumDoKartaPicaString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		return sdf.format(datumDoKartaPica);
	}
	
	public void setDatumDoKartaPica(Date datumDoKartaPica) {
		this.datumDoKartaPica = datumDoKartaPica;
	}
	
	public Set<Pice> getPica() {
		return pica;
	}

	public void setPica(Set<Pice> pica) {
		this.pica = pica;
	}
	
	public Set<Restoran> getRestorani() {
		return restorani;
	}

	public void setRestorani(Set<Restoran> restorani) {
		this.restorani = restorani;
	}

	public KartaPica() {
		super();
	}

	public KartaPica(String nazivKartaPica, Date datumOdKartaPica, Date datumDoKartaPica,
			Set<Pice> pica, Set<Restoran> restorani) {
		this.nazivKartaPica = nazivKartaPica;
		this.datumOdKartaPica = datumOdKartaPica;
		this.datumDoKartaPica = datumDoKartaPica;
		this.pica = pica;
		this.restorani = restorani;
	}
	
	
}
