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
@Table(name = "sanker") 
public class Sanker extends Korisnik implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "sanker_datum_rodjenja", unique = false, nullable = true)
	private Date datumRodjenjaSanker;
	
	@Column(name = "sanker_konfekcijski_broj", unique = false, nullable = true)
	private String konfekcijskiBrojSanker;
	
	@Column(name = "sanker_velicina_obuce", unique = false, nullable = true)
	private Integer  velicinaObuceSanker;

	@ManyToOne
	@JoinColumn(name = "restoran_id", referencedColumnName = "restoran_id", nullable = true)
	private Restoran restoran;
	
	public Date getDatumRodjenjaSanker() {
		
		return datumRodjenjaSanker;
	}
	
	public String getDatumRodjenjaSankerString() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		return sdf.format(datumRodjenjaSanker);
		
	}

	public void setDatumRodjenjaSanker(Date datumRodjenjaSanker) {
		this.datumRodjenjaSanker = datumRodjenjaSanker;
	}

	public String getKonfekcijskiBrojSanker() {
		return konfekcijskiBrojSanker;
	}

	public void setKonfekcijskiBrojSanker(String konfekcijskiBrojSanker) {
		this.konfekcijskiBrojSanker = konfekcijskiBrojSanker;
	}

	public Integer getVelicinaObuceSanker() {
		return velicinaObuceSanker;
	}

	public void setVelicinaObuceSanker(Integer velicinaObuceSanker) {
		this.velicinaObuceSanker = velicinaObuceSanker;
	}

	public Restoran getRestoran() {
		return restoran;
	}

	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}

	public Sanker() {
		super();
	}

	public Sanker(String korisnik_ime, String korisnik_prezime, String korisnik_email, String korisnik_lozinka,
			Date datumRodjenjaSanker, String konfekcijskiBrojSanker,
			Integer velicinaObuceSanker,
			String korisnik_uloga, boolean validiran, Set<Smena> smene,
			Restoran restoran) {
		super(korisnik_ime, korisnik_prezime, korisnik_email, korisnik_lozinka, korisnik_uloga, validiran, smene);
		this.datumRodjenjaSanker = datumRodjenjaSanker;
		this.konfekcijskiBrojSanker = konfekcijskiBrojSanker;
		this.velicinaObuceSanker = velicinaObuceSanker;
		restoran.add(this);
	}
	
}
