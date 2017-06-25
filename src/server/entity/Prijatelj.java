package server.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "prijatelji")
@NamedQueries({@NamedQuery(name = "findFriends", query = "SELECT p FROM Prijatelj p WHERE p.jePrijatelj.korisnik_id like :ima_prijatelja AND p.prihvacen like :prihvacenPrijatelj"), 
	@NamedQuery(name = "removeFriend", query = "SELECT p FROM Prijatelj p WHERE p.jePrijatelj.korisnik_id like :ima_prijatelja AND p.glavniPrijatelj like :zaBrisanje"),
	@NamedQuery(name = "findAll", query = "SELECT p FROM Prijatelj p WHERE p.jePrijatelj.korisnik_id like :ima_prijatelja"),
	@NamedQuery(name = "findRequests", query = "SELECT p FROM Prijatelj p WHERE p.glavniPrijatelj like :zahtevp AND p.prihvacen like :prihvacenPrijatelj")})

public class Prijatelj implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "prijatelj_id", unique = true, nullable = false)
	private Integer idPrijatelj;
	
	@Column(name = "je_prijatelj", unique = false, nullable = false)
	private Integer glavniPrijatelj;
	
	
	
	public Integer getGlavniPrijatelj() {
		return glavniPrijatelj;
	}



	public void setGlavniPrijatelj(Integer glavniPrijatelj) {
		this.glavniPrijatelj = glavniPrijatelj;
	}



	public Integer getIdPrijatelj() {
		return idPrijatelj;
	}



	public void setIdPrijatelj(Integer idPrijatelj) {
		this.idPrijatelj = idPrijatelj;
	}

	/*@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = LAZY, mappedBy = "imaPrijatelja")
	private Set<Gost> suPrijatelji = new HashSet<Gost>();*/
	
	@ManyToOne
	@JoinColumn(name = "ima_prijatelja", referencedColumnName = "korisnik_id", nullable = true)
	private Gost jePrijatelj;
	
	@Column(name = "prihvacen", unique = false, nullable = true)
	private String prihvacen;
	
	


	



	public String getPrihvacen() {
		return prihvacen;
	}



	public void setPrihvacen(String prihvacen) {
		this.prihvacen = prihvacen;
	}



	public Gost getJePrijatelj() {
		return jePrijatelj;
	}



	public void setJePrijatelj(Gost jePrijatelj) {
		this.jePrijatelj = jePrijatelj;
	}



	/*public Set<Gost> getSuPrijatelji() {
		return suPrijatelji;
	}



	public void setSuPrijatelji(Set<Gost> suPrijatelji) {
		this.suPrijatelji = suPrijatelji;
	}*/

/*	public Prijatelj(String ime, String prezime, String email, String korIme, String uloba, boolean validiran) {
		super(ime,prezime,email,korIme,uloba,validiran);
	}*/

	public Prijatelj(Integer glavniPrijatelj, String prihvacen) {
		super();
		this.glavniPrijatelj = glavniPrijatelj;
		this.prihvacen = prihvacen;
	}



	public Prijatelj() {
		super();
	}







	@Override
	public String toString() {
		return "Prijatelj [idPrijatelj=" + idPrijatelj + ", glavniPrijatelj=" + glavniPrijatelj + ", jePrijatelj="
				+ jePrijatelj + ", prihvacen=" + prihvacen + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPrijatelj == null) ? 0 : idPrijatelj.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prijatelj other = (Prijatelj) obj;
		if (idPrijatelj == null) {
			if (other.idPrijatelj != null)
				return false;
		} else if (!idPrijatelj.equals(other.idPrijatelj))
			return false;
		return true;
	}
	
	



	


}
