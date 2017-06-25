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
@Table(name = "kuvar") 
public class Kuvar extends Korisnik implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "kuvar_datum_rodjenja", unique = false, nullable = true)
	private Date datumRodjenjaKuvar;
	
	@Column(name = "kuvar_konfekcijski_broj", unique = false, nullable = true)
	private String konfekcijskiBrojKuvar;
	
	@Column(name = "kuvar_velicina_obuce", unique = false, nullable = true)
	private Integer  velicinaObuceKuvar;
	
	@Column(name = "kuvar_tip", unique = false, nullable = true)
	private String  tipKuvar;

	@ManyToOne
	@JoinColumn(name = "restoran_id", referencedColumnName = "restoran_id", nullable = true)
	private Restoran restoran;
	
	public Date getDatumRodjenjaKuvar() {
		return datumRodjenjaKuvar;
	}
	
	public String getDatumRodjenjaKuvarString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		return sdf.format(datumRodjenjaKuvar);
	}

	public void setDatumRodjenjaKuvar(Date datumRodjenjaKuvar) {
		this.datumRodjenjaKuvar = datumRodjenjaKuvar;
	}

	public String getKonfekcijskiBrojKuvar() {
		return konfekcijskiBrojKuvar;
	}

	public void setKonfekcijskiBrojKuvar(String konfekcijskiBrojKuvar) {
		this.konfekcijskiBrojKuvar = konfekcijskiBrojKuvar;
	}

	public Integer getVelicinaObuceKuvar() {
		return velicinaObuceKuvar;
	}

	public void setVelicinaObuceKuvar(Integer velicinaObuceKuvar) {
		this.velicinaObuceKuvar = velicinaObuceKuvar;
	}

	public String getTipKuvar() {
		return tipKuvar;
	}

	public void setTipKuvar(String tipKuvar) {
		this.tipKuvar = tipKuvar;
	}

	public Restoran getRestoran() {
		return restoran;
	}

	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}

	public Kuvar() {
		super();
	}
	
	public Kuvar(String korisnik_ime, String korisnik_prezime, String korisnik_email, String korisnik_lozinka,
			Date datumRodjenjaKuvar, String konfekcijskiBrojKuvar, Integer velicinaObuceKuvar,
			String tipKuvar,String korisnik_uloga, boolean validiran,  Set<Smena> smene,
			Restoran restoran) {
		super(korisnik_ime, korisnik_prezime, korisnik_email, korisnik_lozinka, korisnik_uloga, validiran, smene);
		this.datumRodjenjaKuvar = datumRodjenjaKuvar;
		this.konfekcijskiBrojKuvar = konfekcijskiBrojKuvar;
		this.velicinaObuceKuvar = velicinaObuceKuvar;
		this.tipKuvar = tipKuvar;
		restoran.add(this);
	}

}
