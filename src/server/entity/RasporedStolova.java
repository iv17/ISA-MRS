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
@Table(name = "raspored_stolova")
@NamedQuery(name = "getRasporedZaRestoran", query = "SELECT r FROM RasporedStolova r WHERE r.restoran.idRestorana like :res_id")

public class RasporedStolova implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "raspored_id", unique = true, nullable = false)
	private Integer idRasporeda;
	
	@Column(name = "raspored_json",columnDefinition = "LONGTEXT")
	protected String jsonRaspored;
	
	@Column(name = "raspored_aktivan", unique = false, nullable = false)
	private Boolean aktivanRaspored;

	@ManyToOne
	@JoinColumn(name = "restoran_id", referencedColumnName = "restoran_id", nullable = true)
	private Restoran restoran;

	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "raspored")
	private Set<Sto> stolovi = new HashSet<Sto>();

	public void add(Sto s) {
		if (s.getRaspored().getStolovi() != null)
			s.getRaspored().getStolovi().remove(s);
		s.setRaspored(this);
		stolovi.add(s);
	}

	public void remove(Sto s) {
		s.setRaspored(null);
		stolovi.remove(s);
	}
	
	public Integer getIdRasporeda() {
		return idRasporeda;
	}

	public void setIdRasporeda(Integer idRasporeda) {
		this.idRasporeda = idRasporeda;
	}

	public String getJsonRaspored() {
		return jsonRaspored;
	}

	public void setJsonRaspored(String jsonRaspored) {
		this.jsonRaspored = jsonRaspored;
	}

	public Boolean getAktivanRaspored() {
		return aktivanRaspored;
	}

	public void setAktivanRaspored(Boolean aktivanRaspored) {
		this.aktivanRaspored = aktivanRaspored;
	}

	public Restoran getRestoran() {
		return restoran;
	}

	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}
	
	public Set<Sto> getStolovi() {
		return stolovi;
	}

	public void setStolovi(Set<Sto> stolovi) {
		this.stolovi = stolovi;
	}

	public RasporedStolova() {
	}

	public RasporedStolova(Integer idRasporeda, String jsonRaspored,
			Boolean aktivanRaspored, Restoran restoran,
			Set<Sto> stolovi) {
		this.idRasporeda = idRasporeda;
		this.jsonRaspored = jsonRaspored;
		this.aktivanRaspored = aktivanRaspored;
		restoran.add(this);
		this.stolovi = stolovi;
		
	}
	
}
