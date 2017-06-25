package server.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
@Entity
@Table(name = "smena") 
@NamedQuery(name = "findSmena", query = "SELECT s FROM Smena s WHERE s.smena_od like :smena_od AND s.smena_do LIKE :smena_do" )
public class Smena implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "smena_id", unique = true, nullable = true)
	private Integer smena_id;
	
	@Column(name = "smena_od", unique = false, nullable = true)
	private Date smena_od;
	
	@Column(name = "smena_do", unique = false, nullable = true)
	private Date  smena_do;
	
	@Column(name = "trenutna_nedelja", unique = false, nullable = true)
	private Boolean  trenutna_nedelja;
	
	@ManyToOne
	@JoinColumn(name = "korisnik_id", referencedColumnName = "korisnik_id", nullable = true)
	private Korisnik korisnik;

	@ManyToOne
	@JoinColumn(name = "reon_id", referencedColumnName = "reon_id", nullable = true)
	private Reon reon;

	@ManyToOne
	@JoinColumn(name = "restoran_id", referencedColumnName = "restoran_id", nullable = true)
	private Restoran restoran;
	
	public Integer getSmena_id() {
		return smena_id;
	}

	public void setSmena_id(Integer smena_id) {
		this.smena_id = smena_id;
	}

	public Date getSmena_od() {
		return smena_od;
	}

	public String getSmena_odString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		return sdf.format(smena_od);
	}
	
	public void setSmena_od(Date smena_od) {
		this.smena_od = smena_od;
	}

	public Date getSmena_do() {
		return smena_do;
	}

	public String getSmena_doString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		return sdf.format(smena_do);
	}
	
	public void setSmena_do(Date smena_do) {
		this.smena_do = smena_do;
	}

	public Boolean getTrenutna_nedelja() {
		return trenutna_nedelja;
	}

	public void setTrenutna_nedelja(Boolean trenutna_nedelja) {
		this.trenutna_nedelja = trenutna_nedelja;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	
	public Reon getReon() {
		return reon;
	}

	public void setReon(Reon reon) {
		this.reon = reon;
	}
	
	public Restoran getRestoran() {
		return restoran;
	}

	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}

	public Smena() {
		super();
	}
	
	public Smena(Date smena_od, Date smena_do, Boolean trenutna_nedelja,
			Korisnik korisnik, Reon reon, Restoran restoran) {
		this.smena_od = smena_od;
		this.smena_do = smena_do;
		this.trenutna_nedelja = trenutna_nedelja;
		korisnik.add(this);
		reon.add(this);
		restoran.add(this);
	}
	
}
