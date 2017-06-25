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
@Table(name = "pice") 
@NamedQuery(name = "getPicaKarte", query = "SELECT p FROM Pice p WHERE p.kartaPica.idKartaPica like :karta_id")
public class Pice implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "pice_id", unique = true, nullable = false)
	private Integer idPica;

	@Column(name = "pice_naziv", unique = false, nullable = true)
	private String nazivPica;

	@Column(name = "pice_tip", unique = false, nullable = true)
	private String tipPica;

	@Column(name = "pice_opis", unique = false, nullable = true)
	private String opisPica;
	
	@Column(name = "pice_cena", unique = false, nullable = true)
	private Double cenaPica;
	

	@ManyToOne
	@JoinColumn(name = "karta_pica_id", referencedColumnName = "karta_pica_id", nullable = true)
	private KartaPica kartaPica;

	
	@ManyToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER, mappedBy="pica")  
	private Set<PorucenoPice> porucena = new HashSet<PorucenoPice>();
	
	public Integer getIdPica() {
		return idPica;
	}

	public void setIdPica(Integer idPica) {
		this.idPica = idPica;
	}

	public String getNazivPica() {
		return nazivPica;
	}

	public void setNazivPica(String nazivPica) {
		this.nazivPica = nazivPica;
	}

	public String getTipPica() {
		return tipPica;
	}

	public void setTipPica(String tipPica) {
		this.tipPica = tipPica;
	}

	public String getOpisPica() {
		return opisPica;
	}

	public void setOpisPica(String opisPica) {
		this.opisPica = opisPica;
	}

	public Double getCenaPica() {
		return cenaPica;
	}

	public void setCenaPica(Double cenaPica) {
		this.cenaPica = cenaPica;
	}

	public KartaPica getKartaPica() {
		return kartaPica;
	}

	public void setKartaPica(KartaPica kartaPica) {
		this.kartaPica = kartaPica;
	}

	
	public Set<PorucenoPice> getPorucena() {
		return porucena;
	}

	public void setPorucena(Set<PorucenoPice> porucena) {
		this.porucena = porucena;
	}

	public Pice() {
		super();
	}

	public Pice(String nazivPica, String tipPica, String opisPica, Double cenaPica, KartaPica karta_pica,
			Set<PorucenoPice> porucenaPica) {
		super();
		this.nazivPica = nazivPica;
		this.tipPica = tipPica;
		this.opisPica = opisPica;
		this.cenaPica = cenaPica;
		this.kartaPica = karta_pica;
		this.porucena = porucenaPica;
	}
	
}
