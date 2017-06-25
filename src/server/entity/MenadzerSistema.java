package server.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;
/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
@Entity
@Table(name = "menadzer_sistema") 
public class MenadzerSistema extends Korisnik implements Serializable {

	private static final long serialVersionUID = 1L;

	public MenadzerSistema() {
		super();
	}

	public MenadzerSistema(String korisnik_ime, String korisnik_prezime, String korisnik_email, String korisnik_lozinka,
			String korisnik_uloga, boolean validiran, Set<Smena> smene) {
		super(korisnik_ime, korisnik_prezime, korisnik_email, korisnik_lozinka, korisnik_uloga, validiran, smene);
	}

	

	
}
