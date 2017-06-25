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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "korisnik")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "findKorisnikSaKorisnickimImenomILozinkom", query = "SELECT k FROM Korisnik k WHERE k.korisnik_email like :korisnik_email AND k.korisnik_lozinka LIKE :korisnik_lozinka")

public class Korisnik implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name = "korisnik_id", unique = true, nullable = false)
	private Integer korisnik_id;

	@Column(name = "korisnik_ime", unique = false, nullable = true)
	private String korisnik_ime;

	@Column(name = "korisnik_prezime", unique = false, nullable = true)
	private String korisnik_prezime;

	@Column(name = "korisnik_email", unique = true, nullable = true)
	private String korisnik_email;

	@Column(name = "korisnik_lozinka", unique = false, nullable = true)
	private String korisnik_lozinka;
	
	@Column(name = "korisnik_uloga", unique = false, nullable = true)
	private String korisnik_uloga;
	
	@Column(name = "validiran", unique = false, nullable = true)
	private boolean validiran;

	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "korisnik")
	private Set<Smena> smene = new HashSet<Smena>();

	public void add(Smena s) {
		if (s.getKorisnik() != null)
			s.getKorisnik().getSmene().remove(s);
		s.setKorisnik(this);
		smene.add(s);
	}
	
	public void remove(Smena s) {
		s.setKorisnik(null);
		smene.remove(s);
	}
	
	public boolean isValidiran() {
		return validiran;
	}

	public void setValidiran(boolean validiran) {
		this.validiran = validiran;
	}

	public String getKorisnik_uloga() {
		return korisnik_uloga;
	}

	public void setKorisnik_uloga(String korisnik_uloga) {
		this.korisnik_uloga = korisnik_uloga;
	}

	public Integer getKorisnik_id() {
		return korisnik_id;
	}

	public void setKorisnik_id(Integer korisnik_id) {
		this.korisnik_id = korisnik_id;
	}

	public String getKorisnik_ime() {
		return korisnik_ime;
	}

	public void setKorisnik_ime(String korisnik_ime) {
		this.korisnik_ime = korisnik_ime;
	}

	
	public String getKorisnik_prezime() {
		return korisnik_prezime;
	}

	public void setKorisnik_prezime(String korisnik_prezime) {
		this.korisnik_prezime = korisnik_prezime;
	}

	public String getKorisnik_email() {
		return korisnik_email;
	}

	public void setKorisnik_email(String korisnik_email) {
		this.korisnik_email = korisnik_email;
	}

	public String getKorisnik_lozinka() {
		return korisnik_lozinka;
	}

	public void setKorisnik_lozinka(String korisnik_lozinka) {
		this.korisnik_lozinka = korisnik_lozinka;
	}

	public Set<Smena> getSmene() {
		return smene;
	}

	public void setSmene(Set<Smena> smene) {
		this.smene = smene;
	}

	public Korisnik() {
		super();
	}

	public Korisnik(String korisnik_ime, String korisnik_prezime, String korisnik_email,
			String korisnik_lozinka, String korisnik_uloga, boolean validiran, Set<Smena> smene) {
		super();
		this.korisnik_ime = korisnik_ime;
		this.korisnik_prezime = korisnik_prezime;
		this.korisnik_email = korisnik_email;
		this.korisnik_lozinka = korisnik_lozinka;
		this.korisnik_uloga = korisnik_uloga;
		this.validiran = validiran;
		this.smene = smene;
	}

	
	@Override
	public String toString() {
		return "Korisnik [id=" + korisnik_id + ", imeKorisnika=" + korisnik_ime + ", prezimeKorisnika=" + korisnik_prezime
				+ ", emailKorisnika=" + korisnik_email + ", lozinkaKorisnika=" + korisnik_lozinka + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((korisnik_email == null) ? 0 : korisnik_email.hashCode());
		result = prime * result + ((korisnik_id == null) ? 0 : korisnik_id.hashCode());
		result = prime * result + ((korisnik_ime == null) ? 0 : korisnik_ime.hashCode());
		result = prime * result + ((korisnik_lozinka == null) ? 0 : korisnik_lozinka.hashCode());
		result = prime * result + ((korisnik_prezime == null) ? 0 : korisnik_prezime.hashCode());
		result = prime * result + (validiran ? 1231 : 1237);
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
		Korisnik other = (Korisnik) obj;
		if (korisnik_email == null) {
			if (other.korisnik_email != null)
				return false;
		} else if (!korisnik_email.equals(other.korisnik_email))
			return false;
		if (korisnik_id == null) {
			if (other.korisnik_id != null)
				return false;
		} else if (!korisnik_id.equals(other.korisnik_id))
			return false;
		if (korisnik_ime == null) {
			if (other.korisnik_ime != null)
				return false;
		} else if (!korisnik_ime.equals(other.korisnik_ime))
			return false;
		if (korisnik_lozinka == null) {
			if (other.korisnik_lozinka != null)
				return false;
		} else if (!korisnik_lozinka.equals(other.korisnik_lozinka))
			return false;
		if (korisnik_prezime == null) {
			if (other.korisnik_prezime != null)
				return false;
		} else if (!korisnik_prezime.equals(other.korisnik_prezime))
			return false;
		if (validiran != other.validiran)
			return false;
		return true;
	}
	
	
	
	



}
