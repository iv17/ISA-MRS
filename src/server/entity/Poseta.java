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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
@Entity
@Table(name = "poseta") 
public class Poseta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "poseta_id", unique = true, nullable = false)
	private Integer idPoseta;

	@Column(name = "poseta_ocena_jela", unique = false, nullable = true)
	private Integer ocenaJelaPoseta;
	
	@Column(name = "poseta_ocena_konobara", unique = false, nullable = true)
	private Integer ocenaKonobaraPoseta;
	
	@Column(name = "poseta_ocena_restorana", unique = false, nullable = true)
	private Integer ocenaRestoranaPoseta;	

	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "poseta")
	private Set<Narudzbina> narudzbine = new HashSet<Narudzbina>();

	public void add(Narudzbina n) {
		if (n.getPoseta() != null)
			n.getPoseta().getNarudzbine().remove(n);
		n.setPoseta(this);
		narudzbine.add(n);
	}

	public void remove(Narudzbina n) {
		n.setPoseta(null);
		narudzbine.remove(n);
	}
	
	public Integer getIdPoseta() {
		return idPoseta;
	}

	public void setIdPoseta(Integer idPoseta) {
		this.idPoseta = idPoseta;
	}

	public Integer getOcenaJelaPoseta() {
		return ocenaJelaPoseta;
	}

	public void setOcenaJelaPoseta(Integer ocenaJelaPoseta) {
		this.ocenaJelaPoseta = ocenaJelaPoseta;
	}

	public Integer getOcenaKonobaraPoseta() {
		return ocenaKonobaraPoseta;
	}

	public void setOcenaKonobaraPoseta(Integer ocenaKonobaraPoseta) {
		this.ocenaKonobaraPoseta = ocenaKonobaraPoseta;
	}

	public Integer getOcenaRestoranaPoseta() {
		return ocenaRestoranaPoseta;
	}

	public void setOcenaRestoranaPoseta(Integer ocenaRestoranaPoseta) {
		this.ocenaRestoranaPoseta = ocenaRestoranaPoseta;
	}

	public Set<Narudzbina> getNarudzbine() {
		return narudzbine;
	}

	public void setNarudzbine(Set<Narudzbina> narudzbine) {
		this.narudzbine = narudzbine;
	}

	public Poseta() {
		super();
	}
	
	public Poseta(Integer ocenaJelaPoseta, Integer ocenaKonobaraPoseta, Integer ocenaRestoranaPoseta,
			Set<Narudzbina> narudzbine) {
		this.ocenaJelaPoseta = ocenaJelaPoseta;
		this.ocenaKonobaraPoseta = ocenaKonobaraPoseta;
		this.ocenaRestoranaPoseta = ocenaRestoranaPoseta;
		this.narudzbine = narudzbine;
	}

}
