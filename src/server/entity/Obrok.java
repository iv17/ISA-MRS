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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
@Entity
@Table(name = "obrok") 
public class Obrok implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "obrok_id", unique = true, nullable = false)
	private Integer idObroka;

	@Column(name = "obrok_flag", nullable = true)
	private Boolean flagObrok;
	
	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinTable(name="jelo_obrok", 
		joinColumns={@JoinColumn(name="obrok_id")}, 
		inverseJoinColumns={@JoinColumn(name="jelo_id")})
	 private Set<Jelo> jela = new HashSet<Jelo>();
	 
	 
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "obrok")
	private Set<Narudzbina> narudzbine = new HashSet<Narudzbina>();

	public void add(Narudzbina n) {
		if (n.getObrok() != null)
			n.getObrok().getNarudzbine().remove(n);
		n.setObrok(this);
		narudzbine.add(n);
	}

	public void remove(Narudzbina n) {
		n.setObrok(null);
		narudzbine.remove(n);
	}
	
	public Integer getIdObroka() {
		return idObroka;
	}

	public void setIdObroka(Integer idObroka) {
		this.idObroka = idObroka;
	}

	public Boolean getFlagObrok() {
		return flagObrok;
	}

	public void setFlagObrok(Boolean flagObrok) {
		this.flagObrok = flagObrok;
	}
	
	public Set<Jelo> getJela() {
		return jela;
	}

	public void setJela(Set<Jelo> jela) {
		this.jela = jela;
	}

	public Set<Narudzbina> getNarudzbine() {
		return this.narudzbine;
	}

	public void setNarudzbine(Set<Narudzbina> narudzbine) {
		this.narudzbine = narudzbine;
	}

	public Obrok() {
		super();
	}

	public Obrok(Boolean flagObrok, Set<Jelo> jela, Set<Narudzbina> narudzbine) {
		super();
		this.flagObrok = flagObrok;
		this.jela = jela;
		this.narudzbine = narudzbine;
	}

}
