package server.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
@Entity
@Table(name = "jelo")
@NamedQuery(name = "getJelaJelovnika", query = "SELECT j FROM Jelo j WHERE j.jelovnik.idJelovnika like :jelovnik_id")
public class Jelo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "jelo_id", unique = true, nullable = false)
	private Integer idJela;

	@Column(name = "jelo_naziv", unique = false, nullable = true)
	private String nazivJela;

	@Column(name = "jelo_tip", unique = false, nullable = true)
	private String tipJela;

	@Column(name = "jelo_opis", unique = false, nullable = true)
	private String opisJela;

	@Column(name = "jelo_cena", unique = false, nullable = true)
	private Double cenaJela;
	
	

	@ManyToOne
	@JoinColumn(name = "jelovnik_id", referencedColumnName = "jelovnik_id", nullable = true)
	private Jelovnik jelovnik;

	@ManyToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER, mappedBy="jela")  
	private Set<Obrok> obroci = new HashSet<Obrok>();
	
	public Integer getIdJela() {
		return idJela;
	}

	public void setIdJela(Integer idJela) {
		this.idJela = idJela;
	}

	public String getNazivJela() {
		return nazivJela;
	}

	public void setNazivJela(String nazivJela) {
		this.nazivJela = nazivJela;
	}

	public String getTipJela() {
		return tipJela;
	}

	public void setTipJela(String tipJela) {
		this.tipJela = tipJela;
	}

	public String getOpisJela() {
		return opisJela;
	}

	public void setOpisJela(String opisJela) {
		this.opisJela = opisJela;
	}

	public Double getCenaJela() {
		return cenaJela;
	}

	public void setCenaJela(Double cenaJela) {
		this.cenaJela = cenaJela;
	}

	public Jelovnik getJelovnik() {
		return jelovnik;
	}

	public void setJelovnik(Jelovnik jelovnik) {
		this.jelovnik = jelovnik;
	}
	
	public Set<Obrok> getObroci() {
		return obroci;
	}

	public void setObroci(Set<Obrok> obroci) {
		this.obroci = obroci;
	}

	
	public Jelo() {
		super();
	}

	public Jelo(String nazivJela, String tipJela, String opisJela, 
			Double cenaJela, Jelovnik jelovnik, Set<Obrok> obroci) {
		super();
		this.nazivJela = nazivJela;
		this.tipJela = tipJela;
		this.opisJela = opisJela;
		this.cenaJela = cenaJela;
		jelovnik.add(this);
		this.obroci = obroci;
		
	}

	@Override
	public String toString() {
		return "Jelo : idJela=" + idJela + ", nazivJela=" + nazivJela 
				+ ", cenaJela=" + cenaJela;
	}

	
}
