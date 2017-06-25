package server.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
@Entity
@Table(name = "konobar") 
public class Konobar extends Korisnik implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "konobar_datum_rodjenja", unique = false, nullable = true)
	private Date datumRodjenjaKonobar;
	
	@Column(name = "konobar_konfekcijski_broj", unique = false, nullable = true)
	private String konfekcijskiBrojKonobar;
	
	@Column(name = "konobar_velicina_obuce", unique = false, nullable = true)
	private Integer  velicinaObuceKonobar;

	@ManyToOne
	@JoinColumn(name = "restoran_id", referencedColumnName = "restoran_id", nullable = true)
	private Restoran restoran;
	
	public Date getDatumRodjenjaKonobar() {
		return datumRodjenjaKonobar;
	}
	
	public String getDatumRodjenjaKonobarString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		return sdf.format(datumRodjenjaKonobar);
	}

	public void setDatumRodjenjaKonobar(Date datumRodjenjaKonobar) {
		this.datumRodjenjaKonobar = datumRodjenjaKonobar;
	}

	public String getKonfekcijskiBrojKonobar() {
		return konfekcijskiBrojKonobar;
	}

	public void setKonfekcijskiBrojKonobar(String konfekcijskiBrojKonobar) {
		this.konfekcijskiBrojKonobar = konfekcijskiBrojKonobar;
	}

	public Integer getVelicinaObuceKonobar() {
		return velicinaObuceKonobar;
	}

	public void setVelicinaObuceKonobar(Integer velicinaObuceKonobar) {
		this.velicinaObuceKonobar = velicinaObuceKonobar;
	}

	public Restoran getRestoran() {
		return restoran;
	}

	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}

	public Konobar() {
		super();
	}

	public Konobar(String korisnik_ime, String korisnik_prezime, String korisnik_email, String korisnik_lozinka,
			Date datumRodjenjaKonobar, String konfekcijskiBrojKonobar,
			Integer velicinaObuceKonobar,
			String korisnik_uloga, boolean validiran, Set<Smena> smene,
			Restoran restoran) {
		super(korisnik_ime, korisnik_prezime, korisnik_email, korisnik_lozinka, korisnik_uloga, validiran, smene);
		this.datumRodjenjaKonobar = datumRodjenjaKonobar;
		this.konfekcijskiBrojKonobar = konfekcijskiBrojKonobar;
		this.velicinaObuceKonobar = velicinaObuceKonobar;
		restoran.add(this);
		
	}

}
