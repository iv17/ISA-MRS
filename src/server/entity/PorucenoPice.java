package server.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "poruceno_pice") 
public class PorucenoPice implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "poruceno_pice_id", unique = true, nullable = false)
	private Integer idPorucenoPice;
	
	@ManyToOne
	@JoinColumn(name = "sanker_id", referencedColumnName = "korisnik_id", nullable = true)
	private Sanker sanker;

	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinTable(name="pice_poruceno_pice", 
		joinColumns={@JoinColumn(name="poruceno_pice_id")}, 
		inverseJoinColumns={@JoinColumn(name="pice_id")})
	private Set<Pice> pica = new HashSet<Pice>();
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "porucenoPice")
	private Set<Narudzbina> narudzbine = new HashSet<Narudzbina>();

	public void add(Narudzbina n) {
		if (n.getPorucenoPice() != null)
			n.getPorucenoPice().getNarudzbine().remove(n);
		n.setPorucenoPice(this);
		narudzbine.add(n);
	}

	public void remove(Narudzbina n) {
		n.setPorucenoPice(null);
		narudzbine.remove(n);
	}
	
	public Integer getIdPorucenoPice() {
		return idPorucenoPice;
	}

	public void setIdPorucenoPice(Integer idPorucenoPice) {
		this.idPorucenoPice = idPorucenoPice;
	}

	public Sanker getSanker() {
		return sanker;
	}

	public void setSanker(Sanker sanker) {
		this.sanker = sanker;
	}

	public Set<Pice> getPica() {
		return pica;
	}

	public void setPica(Set<Pice> pica) {
		this.pica = pica;
	}

	public Set<Narudzbina> getNarudzbine() {
		return narudzbine;
	}

	public void setNarudzbine(Set<Narudzbina> narudzbine) {
		this.narudzbine = narudzbine;
	}

	public PorucenoPice() {
		super();
	}

	public PorucenoPice(Sanker sanker, Set<Pice> pica, Set<Narudzbina> narudzbine) {
		super();
		this.sanker = sanker;
		this.pica = pica;
		this.narudzbine = narudzbine;
	}

}
