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
import javax.persistence.Table;

/*
 * @author      Ivana Savin ivana.unitedforce@gmail.com
 */
@Entity
@Table(name = "ponuda") 
public class Ponuda implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ponuda_id", unique = true, nullable = false)
	private Integer idPonuda;

	@Column(name = "ponuda_cena", unique = false, nullable = true)
	private Double cenaPonuda;

	@Column(name = "ponuda_garancija", unique = false, nullable = true)
	private String ponudaGarancija;
	
	@Column(name = "ponuda_rok_isporuke", unique = false, nullable = true)
	private Date ponudaRokIsporuke;

	@Column(name = "ponuda_stanje", unique = false, nullable = true)
	private Boolean ponudaStanje;
	
	@Column(name = "ponuda_izabrana", unique = false, nullable = true)
	private Boolean izabrana;
	
	@ManyToOne
	@JoinColumn(name = "korisnik_id", referencedColumnName = "korisnik_id", nullable = true)
	private Ponudjac ponudjac;
	
	@ManyToOne
	@JoinColumn(name = "namirnica_id", referencedColumnName = "namirnice_id", nullable = true)
	private Namirnice namirnice;

	@Column(name = "ponuda_datum_slanja", unique = false, nullable = true)
	private Date ponudaDatumSlanja;
	
	public Integer getIdPonuda() {
		return idPonuda;
	}

	public void setIdPonuda(Integer idPonuda) {
		this.idPonuda = idPonuda;
	}

	public Double getCenaPonuda() {
		return cenaPonuda;
	}

	public void setCenaPonuda(Double cenaPonuda) {
		this.cenaPonuda = cenaPonuda;
	}

	public String getPonudaGarancija() {
		return ponudaGarancija;
	}

	public void setPonudaGarancija(String ponudaGarancija) {
		this.ponudaGarancija = ponudaGarancija;
	}

	public Date getPonudaRokIsporuke() {
		return ponudaRokIsporuke;
	}
	
	public String getPonudaRokIsporukeString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		return sdf.format(ponudaRokIsporuke);
	}
	
	public void setPonudaRokIsporuke(Date ponudaRokIsporuke) {
		this.ponudaRokIsporuke = ponudaRokIsporuke;
	}
	
	public Boolean getPonudaStanje() {
		return ponudaStanje;
	}

	public void setPonudaStanje(Boolean ponudaStanje) {
		this.ponudaStanje = ponudaStanje;
	}

	public Ponudjac getPonudjac() {
		return ponudjac;
	}

	public void setPonudjac(Ponudjac ponudjac) {
		this.ponudjac = ponudjac;
	}

	public Namirnice getNamirnice() {
		return namirnice;
	}

	public void setNamirnice(Namirnice namirnice) {
		this.namirnice = namirnice;
	}

	public Boolean getIzabrana() {
		return izabrana;
	}

	public void setIzabrana(Boolean izabrana) {
		this.izabrana = izabrana;
	}

	
	public Date getPonudaDatumSlanja() {
		return ponudaDatumSlanja;
	}

	public void setPonudaDatumSlanja(Date ponudaDatumSlanja) {
		this.ponudaDatumSlanja = ponudaDatumSlanja;
	}

	public Ponuda() {
		super();
	}
	
	public Ponuda(Double cenaPonuda, String ponudaGarancija, Date ponudaRokIsporuke, Boolean stanje,Ponudjac ponudjac,
			Namirnice namirnice, Boolean izabrana, Date datumSlanja) {
		super();
		this.cenaPonuda = cenaPonuda;
		this.ponudaGarancija = ponudaGarancija;
		this.ponudaRokIsporuke = ponudaRokIsporuke;
		this.ponudaStanje = stanje;
		ponudjac.add(this);
		namirnice.add(this);
		this.izabrana = izabrana;
		this.ponudaDatumSlanja = datumSlanja;
	}

}
