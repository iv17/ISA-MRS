package server.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
@Entity
@Table(name = "menadzer_restorana") 
public class MenadzerRestorana extends Korisnik implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "restoran_id", referencedColumnName = "restoran_id", nullable = true)
	private Restoran restoran;

	public Restoran getRestoran() {
		return restoran;
	}

	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}

	public MenadzerRestorana(){
		super();
	}

	public MenadzerRestorana(String korisnik_ime, String korisnik_prezime, String korisnik_email,
			String korisnik_lozinka, String korisnik_uloga, boolean validiran, Restoran restoran, Set<Smena> smene) {
		super(korisnik_ime, korisnik_prezime, korisnik_email, korisnik_lozinka, korisnik_uloga, validiran, smene);
		this.restoran = restoran;
	}

}
