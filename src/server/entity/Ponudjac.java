package server.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ponudjac") 
public class Ponudjac extends Korisnik implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "ponudjac")
	private Set<Ponuda> ponude = new HashSet<Ponuda>();

	@Column(name = "fleg", unique = false, nullable = true)
	private Integer fleg;
	
	public void add(Ponuda p) {
		if (p.getPonudjac() != null)
			p.getPonudjac().getPonude().remove(p);
		p.setPonudjac(this);
		ponude.add(p);
	}

	public void remove(Ponuda p) {
		p.setPonudjac(null);
		ponude.remove(p);
	}
	
	public Set<Ponuda> getPonude() {
		return ponude;
	}

	public void setPonude(Set<Ponuda> ponude) {
		this.ponude = ponude;
	}

	public Integer getFleg() {
		return fleg;
	}

	public void setFleg(Integer fleg) {
		this.fleg = fleg;
	}

	public Ponudjac() {
		super();
	}

	public Ponudjac(String korisnik_ime, String korisnik_prezime, String korisnik_email, String korisnik_lozinka,
			String korisnik_uloga, boolean validiran, Set<Ponuda> ponude, Set<Smena> smene) {
		super(korisnik_ime, korisnik_prezime, korisnik_email, korisnik_lozinka, korisnik_uloga, validiran, smene);
		this.ponude = ponude;
	}
	
}
