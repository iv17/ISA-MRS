package server.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "gost_restorana")
@NamedQuery(name = "showGosti", query = "SELECT g FROM Gost g")

public class Gost extends Korisnik implements Serializable{
	    private static final long serialVersionUID = 1L;
	    
	    
	    /*@ManyToMany
	    @JoinTable(name="prijatelji",joinColumns=@JoinColumn(name = "korisnik_id"),
	    inverseJoinColumns=@JoinColumn(name = "korisnik_id"))
	    private Set<Gost> jePrijatelj = new HashSet<Gost>();
	    
	    @ManyToMany(mappedBy="jePrijatelj")
	    private Set<Gost> imaPrijatelja = new HashSet<Gost>();*/
	    

	    
	    /*@ManyToOne
	    @JoinColumn(name="prijatelj_id", referencedColumnName = "korisnik_id", nullable = true)
	    private Gost prijatelj;

	    public Gost getPrijatelj() {
			return prijatelj;
		}

		public void setPrijatelj(Gost prijatelj) {
			this.prijatelj = prijatelj;
		}

		public Set<Gost> getPrijatelji() {
			return prijatelji;
		}

		public void setPrijatelji(Set<Gost> prijatelji) {
			this.prijatelji = prijatelji;
		}

		@OneToMany(mappedBy="prijatelj", cascade = CascadeType.ALL)
	    private Set<Gost> prijatelji = new HashSet<Gost>();*/
	    
	   /* @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
		@JoinColumn(name = "je_prijatelj", referencedColumnName = "korisnik_id", nullable = true)
		private Prijatelj imaPrijatelja;

	    public Prijatelj getImaPrijatelja() {
			return imaPrijatelja;
		}

		public void setImaPrijatelja(Prijatelj imaPrijatelja) {
			this.imaPrijatelja = imaPrijatelja;
		}*/

		
	    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.PERSIST, mappedBy = "pozvaniGosti")
	    private Set<Rezervacija> pozvaniZaDogadjaj = new HashSet<Rezervacija>();

	    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.PERSIST, mappedBy = "prihvaceniGosti")
	    private Set<Rezervacija> prihvatiliDogadjaj = new HashSet<Rezervacija>();

		



		public Set<Rezervacija> getPrihvatiliDogadjaj() {
			return prihvatiliDogadjaj;
		}

		public void setPrihvatiliDogadjaj(Set<Rezervacija> prihvatiliDogadjaj) {
			this.prihvatiliDogadjaj = prihvatiliDogadjaj;
		}

		public Set<Rezervacija> getPozvaniZaDogadjaj() {
			return pozvaniZaDogadjaj;
		}

		public void setPozvaniZaDogadjaj(Set<Rezervacija> pozvaniZaDogadjaj) {
			this.pozvaniZaDogadjaj = pozvaniZaDogadjaj;
		}

		public Set<Prijatelj> getImaPrijatelje() {
			return imaPrijatelje;
		}

		public void setImaPrijatelje(Set<Prijatelj> imaPrijatelje) {
			this.imaPrijatelje = imaPrijatelje;
		}







		public Gost() {
			super();
		}


		



		public Gost(String korisnik_ime, String korisnik_prezime, String korisnik_email, String korisnik_lozinka,
				String korisnik_uloga, boolean validiran, Set<Smena> smene) {
			super(korisnik_ime, korisnik_prezime, korisnik_email, korisnik_lozinka, korisnik_uloga, validiran, smene);
			// TODO Auto-generated constructor stub
		}






		@OneToMany(cascade = { ALL }, fetch = FetchType.EAGER, mappedBy = "jePrijatelj", orphanRemoval = true)
		private Set<Prijatelj> imaPrijatelje = new HashSet<Prijatelj>();
		

		@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "gost")
		private Set<Rezervacija> rezervacije = new HashSet<Rezervacija>();

		public void add(Rezervacija r) {
			if (r.getGost() != null)
				r.getGost().getRezervacije().remove(r);
			r.setGost(this);
			rezervacije.add(r);
		}

		public void remove(Rezervacija r) {
			r.setGost(null);
			rezervacije.remove(r);
		}
		
		public Set<Rezervacija> getRezervacije() {
			return rezervacije;
		}

		public void setRezervacije(Set<Rezervacija> rezervacije) {
			this.rezervacije = rezervacije;
		}

		
		

		
		

		public Gost(Set<Rezervacija> rezervacije) {
			super();
			this.rezervacije = rezervacije;
		}


	      






}
