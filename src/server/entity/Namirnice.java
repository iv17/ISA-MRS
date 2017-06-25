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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
@Entity
@Table(name = "namirnice") 
public class Namirnice implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "namirnice_id", unique = true, nullable = false)
	private Integer idNamirnice;
	
	@Column(name = "namirnice_naziv", unique = false, nullable = true)
	private String nazivNamirnice;

	@Column(name = "namirnice_opis", unique = false, nullable = true)
	private String opis_namirnice;
	
	@Column(name = "namirnice_kolicina", unique = false, nullable = true)
	private Integer kolicinaNamirnice;

	@Column(name = "namirnice_rok", unique = false, nullable = true)
	private Date rokNamirnice;

	@ManyToOne
	@JoinColumn(name = "ponuda_id", referencedColumnName = "ponuda_id", nullable = true)
	private Ponuda izabranaPonuda;
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "namirnice")
	private Set<Ponuda> ponude = new HashSet<Ponuda>();

	public void add(Ponuda p) {
		if (p.getNamirnice() != null)
			p.getNamirnice().getPonude().remove(p);
		p.setNamirnice(this);
		ponude.add(p);
	}

	public void remove(Ponuda p) {
		p.setNamirnice(null);
		ponude.remove(p);
	}
	
	public Integer getIdNamirnice() {
		return idNamirnice;
	}

	public void setIdNamirnice(Integer idNamirnice) {
		this.idNamirnice = idNamirnice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNazivNamirnice() {
		return nazivNamirnice;
	}

	public void setNazivNamirnice(String nazivNamirnice) {
		this.nazivNamirnice = nazivNamirnice;
	}

	public String getOpis_namirnice() {
		return opis_namirnice;
	}

	public void setOpis_namirnice(String opis_namirnice) {
		this.opis_namirnice = opis_namirnice;
	}

	public Integer getKolicinaNamirnice() {
		return kolicinaNamirnice;
	}

	public void setKolicinaNamirnice(Integer kolicinaNamirnice) {
		this.kolicinaNamirnice = kolicinaNamirnice;
	}

	public Date getRokNamirnice() {
		return rokNamirnice;
	}

	public String getRokNamirniceString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		return sdf.format(rokNamirnice);
	}
	
	public void setRokNamirnice(Date rokNamirnice) {
		this.rokNamirnice = rokNamirnice;
	}

	public Set<Ponuda> getPonude() {
		return ponude;
	}

	public void setPonude(Set<Ponuda> ponude) {
		this.ponude = ponude;
	}
	
	public Ponuda getIzabranaPonuda() {
		return izabranaPonuda;
	}

	public void setIzabranaPonuda(Ponuda izabranaPonuda) {
		this.izabranaPonuda = izabranaPonuda;
	}

	public Namirnice() {
		super();
	}

	public Namirnice(String nazivNamirnice, String opis_namirnice, Integer kolicinaNamirnice,
			Date rokNamirnice, Ponuda izabranaPonuda,  Set<Ponuda> ponude) {
		this.nazivNamirnice = nazivNamirnice;
		this.opis_namirnice = opis_namirnice;
		this.kolicinaNamirnice = kolicinaNamirnice;
		this.rokNamirnice = rokNamirnice;
		this.izabranaPonuda = izabranaPonuda;
		this.ponude = ponude;
	}

	@Override
	public String toString() {
		return "Namirnice: idNamirnice=" + idNamirnice + ", nazivNamirnice=" + nazivNamirnice;
	}

	
}
