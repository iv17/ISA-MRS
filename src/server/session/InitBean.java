package server.session;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import server.entity.Gost;
import server.entity.Jelo;
import server.entity.Jelovnik;
import server.entity.KartaPica;
import server.entity.Konobar;
import server.entity.Kuvar;
import server.entity.MenadzerRestorana;
import server.entity.MenadzerSistema;
import server.entity.Namirnice;
import server.entity.Narudzbina;
import server.entity.Obrok;
import server.entity.Pice;
import server.entity.Ponuda;
import server.entity.Ponudjac;
import server.entity.PorucenoPice;
import server.entity.Poseta;
import server.entity.Prijatelj;
import server.entity.RasporedStolova;
import server.entity.Reon;
import server.entity.Restoran;
import server.entity.Rezervacija;
import server.entity.Sanker;
import server.entity.Smena;
import server.entity.Sto;

@Stateless
@Remote(Init.class)

public class InitBean implements Init{

	@PersistenceContext(unitName = "tim14")
	EntityManager em;

	@Override
	public void init() {
		
		Set<Pice> pica1 = new HashSet<Pice>();
		Set<Pice> pica2 = new HashSet<Pice>();
		Set<Pice> pica3 = new HashSet<Pice>();
		Set<Pice> pica4 = new HashSet<Pice>();
		Set<Pice> pica5 = new HashSet<Pice>();
		Set<Jelo> jela1 =  new HashSet<Jelo>();
		Set<Jelo> jela2 =  new HashSet<Jelo>();
		Set<Jelo> jela3 =  new HashSet<Jelo>();
		Set<Jelo> jela4 =  new HashSet<Jelo>();
		Set<Jelo> jela5 =  new HashSet<Jelo>();
		Set<Restoran> restorani = new HashSet<Restoran>();
		Set<Konobar> konobari = new HashSet<Konobar>();
		Set<Kuvar> kuvari = new HashSet<Kuvar>();
		Set<Sanker> sankeri = new HashSet<Sanker>();
		Set<Sto> stolovi = new HashSet<Sto>();
		Set<Rezervacija> rezervacije = new HashSet<Rezervacija>();
		Set<MenadzerRestorana> menadzeri = new HashSet<MenadzerRestorana>();
		Set<Ponuda> ponude = new HashSet<Ponuda>();
		Set<Smena> smene = new HashSet<Smena>();
		Set<RasporedStolova> rasporedi = new HashSet<RasporedStolova>();
		Set<Reon> reoni = new HashSet<Reon>();
		Set<Obrok> obroci1 = new HashSet<Obrok>();
		Set<Obrok> obroci2 = new HashSet<Obrok>();
		Set<Obrok> obroci3 = new HashSet<Obrok>();
		Set<Obrok> obroci4 = new HashSet<Obrok>();
		Set<PorucenoPice> porucenaPica1 = new HashSet<PorucenoPice>();
		Set<PorucenoPice> porucenaPica2 = new HashSet<PorucenoPice>();
		Set<PorucenoPice> porucenaPica3 = new HashSet<PorucenoPice>();
		Set<Narudzbina> narudzbine = new HashSet<Narudzbina>();
		Set<Gost> prihvaceniGosti1 = new HashSet<Gost>();
		Set<Gost> prihvaceniGosti2 = new HashSet<Gost>();
		Set<Gost> prihvaceniGosti3 = new HashSet<Gost>();
		Set<Gost> prihvaceniGosti4 = new HashSet<Gost>();
		Set<Gost> prihvaceniGosti5 = new HashSet<Gost>();
		Set<Gost> prihvaceniGosti6 = new HashSet<Gost>();

		//mine TAMARA
		Gost korisnik = new Gost("Pera", "Peric", "pp@gmail.com", "perapera", "gost", true, null);
		Gost korisnik1 = new Gost("Mita", "Mitic", "mita@gmail.com", "mita", "gost", true, null);
		Gost korisnik2 = new Gost("Jova", "Jovic", "jova@gmail.com", "jova", "gost", true, null);

		Gost korisnik3 = new Gost("Dule", "Bajin", "db@gmail.com", "ddbb", "gost", true, null);
		Gost korisnik4 = new Gost("Maja", "Pap", "mp@gmail.com", "mmpp", "gost", true, null);
		Gost korisnik5 = new Gost("Vera", "Tomin", "vt@gmail.com", "vvtt", "gost", true, null);

		em.persist(korisnik);
		em.persist(korisnik2);
		em.persist(korisnik1);
		em.persist(korisnik3);
		em.persist(korisnik4);
		em.persist(korisnik5);

		//ONI KOJI ZOVU
		Gost g1 = new Gost("Ana", "Ananic", "ana.ananic@gmail.com", "ana.ana", "gost", true, null);
		em.persist(g1);
		Gost g2 = new Gost("Mila", "Milicevic", "mila.123@gmail.com", "mila123", "gost", true, null);
		em.persist(g2);
		Gost g3 = new Gost("Milica", "Nikolic", "milica123@gmail.com", "milica123", "gost", true, null);
		em.persist(g3);
		Gost g4 = new Gost("Danica", "Danicic", "daca.daca@gmail.com", "daca.daca", "gost", true, null);
		em.persist(g4);
		//PRIHVACENI
		Gost g5 = new Gost("Jovana", "Jovandic", "jovana@gmail.com", "jovana", "gost", true, null);
		em.persist(g5);	
		Gost g6 = new Gost("Milana", "Milinkov", "milana@gmail.com", "milana", "gost", true, null);
		em.persist(g6);
		Gost g7 = new Gost("Natasa", "Dragas", "nata@gmail.com", "nata", "gost", true, null);
		em.persist(g7);
		Gost g8 = new Gost("Nevena", "Djurkovic", "nevena@gmail.com", "nena", "gost", true, null);
		em.persist(g8);
		Gost g9 = new Gost("Dragana", "Popovic", "dragana@gmail.com", "dragana", "gost", true, null);
		em.persist(g9);
		Gost g10 = new Gost("Zorica", "Nikcevic", "zorica@gmail.com", "zorica", "gost", true, null);
		em.persist(g10);
		Gost g11 = new Gost("Danijela", "Jovic", "dani@gmail.com", "dani", "gost", true, null);
		em.persist(g11);
		Gost g12 = new Gost("Desanka", "Davidovic", "desa@gmail.com", "desa", "gost", true, null);
		em.persist(g12);
		Gost g13 = new Gost("Katarina", "Kocic", "katarina@gmail.com", "katarina", "gost", true, null);
		em.persist(g13);
		Gost g14 = new Gost("Ksenija", "Naumov", "ksena@gmail.com", "ksena", "gost", true, null);
		em.persist(g14);
		Gost g15 = new Gost("Marko", "Andjic", "marko@gmail.com", "marko", "gost", true, null);
		em.persist(g15);
		Gost g16 = new Gost("Dusko", "Lopicic", "dusko@gmail.com", "dusko", "gost", true, null);
		em.persist(g16);
		Gost g17 = new Gost("Sasa", "Savic", "sasa@gmail.com", "sasa", "gost", true, null);
		em.persist(g17);
		prihvaceniGosti2.add(g5);
		prihvaceniGosti2.add(g6);
		prihvaceniGosti2.add(g7);
		prihvaceniGosti2.add(g8);
		prihvaceniGosti3.add(g9);
		prihvaceniGosti3.add(g10);
		prihvaceniGosti3.add(g11);
		prihvaceniGosti3.add(g12);
		prihvaceniGosti3.add(g13);
		prihvaceniGosti1.add(g14);
		prihvaceniGosti1.add(g15);
		prihvaceniGosti1.add(g16);
		prihvaceniGosti1.add(g17);
		
		
		//mita je prijatelj peri
		Prijatelj pera_mita = new Prijatelj();
		pera_mita.setJePrijatelj(korisnik);
		pera_mita.setGlavniPrijatelj(korisnik1.getKorisnik_id());
		pera_mita.setPrihvacen("da");
		//pera je prijatelj miti
		Prijatelj mita_pera = new Prijatelj();
		mita_pera.setJePrijatelj(korisnik1);
		mita_pera.setGlavniPrijatelj(korisnik.getKorisnik_id());
		mita_pera.setPrihvacen("da");

		//jova je prijatelj peri
		Prijatelj pera_jova = new Prijatelj();
		pera_jova.setJePrijatelj(korisnik);
		pera_jova.setGlavniPrijatelj(korisnik2.getKorisnik_id());
		pera_jova.setPrihvacen("da");

		//pera je prijatelj jovi
		Prijatelj jova_pera = new Prijatelj();
		jova_pera.setJePrijatelj(korisnik2);
		jova_pera.setGlavniPrijatelj(korisnik.getKorisnik_id());
		jova_pera.setPrihvacen("da");



		em.persist(pera_mita);
		em.persist(mita_pera);
		em.persist(pera_jova);
		em.persist(jova_pera);

		Restoran res2 = new Restoran("etno","Gradska");
		em.persist(res2);
		Restoran res1 = new Restoran("kineski","Dva stapica");
		em.persist(res1);
		Restoran res3 = new Restoran("kafe restoran","Ambar");
		em.persist(res3);


		@SuppressWarnings("deprecation")
		Date datumOd = new Date(116, 5, 21);
		@SuppressWarnings("deprecation")
		Date datumDo = new Date(116, 5, 25);
		
		
		Jelovnik jelovnik1 = new Jelovnik("Zimski jelovnik",datumOd, datumDo, jela1, restorani);
		em.persist(jelovnik1);


		Jelo jelo = new Jelo();
		jelo.setNazivJela("Sarma");
		jelo.setTipJela("Glavno jelo");
		jelo.setOpisJela("mmmmmm");
		jelo.setJelovnik(jelovnik1);
		jelo.setCenaJela(1000.00);
		jela1.add(jelo);
		Jelo jelo2 = new Jelo();
		jelo2.setNazivJela("Pizza");
		jelo2.setTipJela("Glavno jelo");
		jelo2.setOpisJela("mmmm");
		jelo2.setJelovnik(jelovnik1);
		jelo2.setCenaJela(580.00);
		jela1.add(jelo2);
		Jelo jelo3 = new Jelo();
		jelo3.setNazivJela("Palacinke");
		jelo3.setTipJela("Dezert");
		jelo3.setOpisJela("mmmm");
		jelo3.setJelovnik(jelovnik1);
		jelo3.setCenaJela(250.00);
		jela1.add(jelo3);
		Jelo jelo4 = new Jelo();
		jelo4.setNazivJela("Pljeskavica");
		jelo4.setTipJela("Glavno jelo");
		jelo4.setOpisJela("mmmmm");
		jelo4.setJelovnik(jelovnik1);
		jelo4.setCenaJela(300.00);
		jela1.add(jelo4);
		Jelo jelo5 = new Jelo();
		jelo5.setNazivJela("Pilece rolnice");
		jelo5.setTipJela("Glavno jelo");
		jelo5.setOpisJela("mmmmmm");
		jelo5.setJelovnik(jelovnik1);
		jelo5.setCenaJela(400.00);
		jela2.add(jelo5);
		Jelo jelo6 = new Jelo();
		jelo6.setNazivJela("Reforma");
		jelo6.setTipJela("Dezert");
		jelo6.setOpisJela("mmmm");
		jelo6.setJelovnik(jelovnik1);
		jelo6.setCenaJela(2580.00);
		jela2.add(jelo6);
		Jelo jelo7 = new Jelo();
		jelo7.setNazivJela("Twister");
		jelo7.setTipJela("Glavno jelo");
		jelo7.setOpisJela("mmmm");
		jelo7.setJelovnik(jelovnik1);
		jelo7.setCenaJela(350.00);
		jela2.add(jelo7);
		Jelo jelo8 = new Jelo();
		jelo8.setNazivJela("Kiflice");
		jelo8.setTipJela("Glavno jelo");
		jelo8.setOpisJela("mmmmm");
		jelo8.setJelovnik(jelovnik1);
		jelo8.setCenaJela(150.00);
		jela2.add(jelo8);
		Jelo jelo9 = new Jelo();
		jelo9.setNazivJela("Rolovana pileca dzigerica");
		jelo9.setTipJela("Glavno jelo");
		jelo9.setOpisJela("mmmmmm");
		jelo9.setJelovnik(jelovnik1);
		jelo9.setCenaJela(1100.00);
		jela3.add(jelo9);
		Jelo jelo10 = new Jelo();
		jelo10.setNazivJela("Gomboce");
		jelo10.setTipJela("Dezert");
		jelo10.setOpisJela("mmmm");
		jelo10.setJelovnik(jelovnik1);
		jelo10.setCenaJela(580.00);
		jela3.add(jelo10);
		Jelo jelo11 = new Jelo();
		jelo11.setNazivJela("Strudla sa makom");
		jelo11.setTipJela("Dezert");
		jelo11.setOpisJela("mmmm");
		jelo11.setJelovnik(jelovnik1);
		jelo11.setCenaJela(880.00);
		jela3.add(jelo11);
		Jelo jelo12 = new Jelo();
		jelo12.setNazivJela("Domaca supa");
		jelo12.setTipJela("Supa");
		jelo12.setOpisJela("mmmmm");
		jelo12.setJelovnik(jelovnik1);
		jelo12.setCenaJela(80.00);
		jela3.add(jelo12);

		Obrok ob1 = new Obrok();
		ob1.setFlagObrok(true);
		ob1.setNarudzbine(narudzbine);
		obroci1.add(ob1);
		Obrok ob2 = new Obrok();
		ob2.setFlagObrok(true);
		ob2.setNarudzbine(narudzbine);
		obroci1.add(ob2);
		Obrok ob3 = new Obrok();
		ob3.setFlagObrok(false);
		ob3.setNarudzbine(narudzbine);
		obroci1.add(ob3);

		jelo.setObroci(obroci1);
		jelo2.setObroci(obroci1);
		jelo3.setObroci(obroci1);
		jelo4.setObroci(obroci1);
		jelo5.setObroci(obroci1);
		jelo6.setObroci(obroci1);
		jelo7.setObroci(obroci1);
		jelo8.setObroci(obroci1);
		jelo9.setObroci(obroci1);
		jelo10.setObroci(obroci1);
		jelo11.setObroci(obroci1);
		jelo12.setObroci(obroci1);

		em.persist(jelo);
		em.persist(jelo2);
		em.persist(jelo3);
		em.persist(jelo4);
		em.persist(jelo5);
		em.persist(jelo6);
		em.persist(jelo7);
		em.persist(jelo8);
		em.persist(jelo9);
		em.persist(jelo10);
		em.persist(jelo11);
		em.persist(jelo12);

		ob1.setJela(jela3);
		ob2.setJela(jela2);
		ob2.setJela(jela1);

		em.persist(ob1);
		em.persist(ob2);
		em.persist(ob3);
		
		KartaPica kartaPica1 = new KartaPica("Kokteli",datumOd, datumDo, pica1, restorani);
		em.persist(kartaPica1);
		KartaPica kartaPica2 = new KartaPica("Alkohol",datumOd, datumDo, pica1, restorani);
		em.persist(kartaPica2);

		Reon reon = new Reon();
		reon.setNazivReona("LETNJA BASTA");
		reon.setBrojStolovaReon(5);
		reon.setStolovi(stolovi);
		em.persist(reon);
		Reon reon2 = new Reon();
		reon2.setNazivReona("ZA PUSACE");
		reon2.setBrojStolovaReon(15);
		reon2.setStolovi(stolovi);
		em.persist(reon2);
		Reon reon3 = new Reon();
		reon3.setNazivReona("ZA NEPUSACE");
		reon3.setBrojStolovaReon(10);
		reon3.setStolovi(stolovi);
		em.persist(reon3);

		Restoran restoran1 = new Restoran("DOMACA KUHINJA", "Banatska kuca", kartaPica1, jelovnik1, rezervacije, menadzeri, rasporedi, reoni, stolovi, konobari, kuvari,sankeri, smene);
		em.persist(restoran1);

		Konobar konobar1 = new Konobar("Ivan", "Milic", "ivan.mili@gmail.com", "ivan123", datumDo, "XL", 45, "KONOBAR", false, smene, restoran1);
		em.persist(konobar1);
		Konobar konobar2 = new Konobar("Andrija", "Ilic", "andrija@gmail.com", "andrija123", datumDo, "L", 43, "KONOBAR", false, smene, restoran1);
		em.persist(konobar2);
		Konobar konobar3 = new Konobar("Milos", "Medic", "medo@gmail.com", "medo", datumDo, "XXL", 47, "KONOBAR", false, smene, restoran1);
		em.persist(konobar3);
		Konobar konobar4 = new Konobar("Jelena", "Djokic", "jeca.pereca@gmail.com", "jeca89", datumDo, "S", 39, "KONOBAR", false, smene, restoran1);
		em.persist(konobar4);
		Konobar konobar5 = new Konobar("Marko", "Milosevic", "marko.sps@gmail.com", "sloba.mira", datumDo, "XXL", 48, "KONOBAR", false, smene, restoran1);
		em.persist(konobar5);
		Konobar konobar6 = new Konobar("Slobodan", "Milosevic", "slobo@gmail.com", "slobo.slobo", datumDo, "XXL", 49, "KONOBAR", false, smene, restoran1);
		em.persist(konobar6);
		Konobar konobar7 = new Konobar("Ivica", "Dacic", "dacic@gmail.com", "prase", datumDo, "XXL", 47, "KONOBAR", false, smene, restoran1);
		em.persist(konobar7);
		Konobar konobar8 = new Konobar("Zoran", "Kesic", "kesic24@gmail.com", "24min", datumDo, "L", 42, "KONOBAR", false, smene, restoran1);
		em.persist(konobar8);
		Konobar konobar9 = new Konobar("Ceda", "Cvorak", "ceda.luna@gmail.com", "ceda.luna", datumDo, "L", 48, "KONOBAR", false, smene, restoran1);
		em.persist(konobar9);
		Konobar konobar10 = new Konobar("Miladin", "Markovic", "miladin@gmail.com", "miladin", datumDo, "XXL", 49, "KONOBAR", false, smene, restoran1);
		em.persist(konobar10);
		Konobar konobar11 = new Konobar("Petar", "Peric", "pera@gmail.com", "perica", datumDo, "L", 47, "KONOBAR", false, smene, restoran1);
		em.persist(konobar11);
		Konobar konobar12 = new Konobar("Goran", "Jocic", "goran123@gmail.com", "goksi", datumDo, "L", 42, "KONOBAR", false,smene, restoran1);
		em.persist(konobar12);

		Date d1 = new Date(116,3,25,8,30);
		Date d2 = new Date(116,3,25,14,30);
		Smena s1 = new Smena(d1, d2, false, konobar9, reon2, restoran1);
		em.persist(s1);

		Kuvar kuvar1 = new Kuvar("Luka", "Kostic", "luka.kostic@gmail.com", "kole-car", datumDo, "M", 44, "ZA SALATE", "KUVAR",true, smene, restoran1);
		em.persist(kuvar1);
		Sanker sanker1 = new Sanker("Aleksa", "Jovic", "aleksa.jovic@gmail.com", "alex85", datumDo, "L", 46, "SANKER", true, smene, restoran1);
		em.persist(sanker1);
		Sanker sanker2 = new Sanker("Jovan", "Markovic", "jovan.jova@gmail.com", "jova89", datumDo, "M", 47, "SANKER", true, smene, restoran1);
		em.persist(sanker2);
		Sanker sanker3 = new Sanker("Miladin", "Novkov", "milnov@gmail.com", "milko", datumDo, "XL", 48, "SANKER", true, smene, restoran1);
		em.persist(sanker3);

		Pice pice2 = new Pice();
		pice2.setNazivPica("Djus");
		pice2.setTipPica("BEZALKOHOLNO");
		pice2.setOpisPica("ooo");
		pice2.setCenaPica(120.0);
		pice2.setKartaPica(kartaPica1);
		pica1.add(pice2);
		Pice pice3 = new Pice();
		pice3.setNazivPica("Pivo");
		pice3.setTipPica("PIVO");
		pice3.setOpisPica("ooo");
		pice3.setCenaPica(100.0);
		pice3.setKartaPica(kartaPica1);
		pica1.add(pice3);
		Pice pice4 = new Pice();
		pice4.setNazivPica("Vinjak");
		pice4.setTipPica("APERITIV");
		pice4.setOpisPica("ooo");
		pice4.setCenaPica(200.0);
		pice4.setKartaPica(kartaPica1);
		pica1.add(pice4);
		PorucenoPice pp1 = new PorucenoPice();
		pp1.setSanker(sanker1);
		pp1.setNarudzbine(narudzbine);
		porucenaPica1.add(pp1);
		PorucenoPice pp2 = new PorucenoPice();
		pp2.setSanker(sanker2);
		pp2.setNarudzbine(narudzbine);
		porucenaPica1.add(pp2);
		PorucenoPice pp3 = new PorucenoPice();
		pp3.setSanker(sanker3);
		pp3.setNarudzbine(narudzbine);
		porucenaPica1.add(pp3);
		PorucenoPice pp4 = new PorucenoPice();
		pp4.setSanker(sanker3);
		pp4.setNarudzbine(narudzbine);
		porucenaPica1.add(pp4);

		pice2.setPorucena(porucenaPica1);
		pice3.setPorucena(porucenaPica1);
		pice4.setPorucena(porucenaPica1);
		em.persist(pice2);
		em.persist(pice3);
		em.persist(pice4);
		pp1.setPica(pica1);
		pp2.setPica(pica1);
		pp3.setPica(pica1);
		pp4.setPica(pica1);
		em.persist(pp1);
		em.persist(pp2);
		em.persist(pp3);
		em.persist(pp4);


		MenadzerSistema mensis1 = new MenadzerSistema("Ana", "Kositic", "ana.kostic@gmail.com", "admin admin", "ADMIN", true, smene);
		em.persist(mensis1);
		MenadzerRestorana menres1 = new MenadzerRestorana("Anja", "Jovanov", "anja.jovanov@gmail.com", "anja.anja", "MENADZER RESTORANA", true, restoran1, smene);
		em.persist(menres1);


		Sto sto1 = new Sto(4, false, reon, rezervacije, restoran1);
		em.persist(sto1);
		Sto sto2 = new Sto(6, false, reon, rezervacije, restoran1);
		em.persist(sto2);
		Sto sto3 = new Sto(8, false, reon, rezervacije, restoran1);
		em.persist(sto3);
		Sto sto4 = new Sto(4, false, reon, rezervacije, restoran1);
		em.persist(sto4);
		Sto sto5 = new Sto(6, false, reon, rezervacije, restoran1);
		em.persist(sto5);

		Namirnice namirnica1 = new Namirnice("Krompir", "opis", 10, datumDo, null, ponude);
		em.persist(namirnica1);
		Namirnice namirnica2 = new Namirnice("Luk", "opis", 10, datumDo, null, ponude);
		em.persist(namirnica2);
		Namirnice namirnica3 = new Namirnice("Brasno", "opis", 10, datumDo, null, ponude);
		em.persist(namirnica3);
		Namirnice namirnica4 = new Namirnice("Chicken", "opis", 10, datumDo, null, ponude);
		em.persist(namirnica4);
		Namirnice namirnica5 = new Namirnice("Beef", "opis", 20, datumDo, null, ponude);
		em.persist(namirnica5);
		Namirnice namirnica6 = new Namirnice("Fish", "opis", 5, datumDo, null, ponude);
		em.persist(namirnica6);
		Namirnice namirnica7 = new Namirnice("Bacon", "opis", 10, datumDo, null, ponude);
		em.persist(namirnica7);
		Namirnice namirnica8 = new Namirnice("Milk", "opis", 10, datumDo, null, ponude);
		em.persist(namirnica8);
		Namirnice namirnica9 = new Namirnice("Eggs", "opis", 10, datumDo, null, ponude);
		em.persist(namirnica9);
		Namirnice namirnica10 = new Namirnice("Cheese", "opis", 10, datumDo, null, ponude);
		em.persist(namirnica10);
		Namirnice namirnica11 = new Namirnice("Yogurts", "opis", 20, datumDo, null, ponude);
		em.persist(namirnica11);
		Namirnice namirnica12 = new Namirnice("Carrots", "opis", 5, datumDo, null, ponude);
		em.persist(namirnica12);
		
		Ponudjac ponudjac1 = new Ponudjac("Nikola", "Maric", "nikola.maric@gmail.com", "maric123", "PONUDJAC", true, ponude, smene);
		em.persist(ponudjac1);
		Ponudjac ponudjac2 = new Ponudjac("Drasko", "Dragicevic", "drasko@gmail.com", "drasko", "PONUDJAC", true, ponude, smene);
		em.persist(ponudjac2);
		Ponuda ponuda1 = new Ponuda(1000.00, "garancija1", datumDo, true,  ponudjac1, namirnica1, false, datumOd);
		em.persist(ponuda1);
		ponude.add(ponuda1);
		Ponuda ponuda2 = new Ponuda(10.00, "garancija2", datumDo, true,  ponudjac1, namirnica2, false, datumOd);
		em.persist(ponuda2);
		ponude.add(ponuda2);
		Ponuda ponuda3 = new Ponuda(200.00, "garancija3", datumDo, true,  ponudjac1, namirnica3, false, datumOd);
		em.persist(ponuda3);
		ponude.add(ponuda3);
		Ponuda ponuda4 = new Ponuda(30.00, "garancija1", datumDo, false,  ponudjac1, namirnica4, false, datumOd);
		em.persist(ponuda4);
		ponude.add(ponuda4);
		Ponuda ponuda5 = new Ponuda(100.00, "garancija2", datumDo, false,  ponudjac1, namirnica5, false, datumOd);
		em.persist(ponuda5);
		ponude.add(ponuda5);
		Ponuda ponuda6 = new Ponuda(500.00, "garancija3", datumDo, false,  ponudjac1, namirnica6, false, datumOd);
		em.persist(ponuda6);
		ponude.add(ponuda6);
		
		Ponuda ponuda7 = new Ponuda(800.00, "garancija1", datumOd, true,  ponudjac2, namirnica1, false, datumOd);
		em.persist(ponuda7);
		Ponuda ponuda8 = new Ponuda(10.00, "garancija2", datumDo, true,  ponudjac2, namirnica8, false, datumOd);
		em.persist(ponuda8);
		Ponuda ponuda9 = new Ponuda(200.00, "garancija3", datumDo, true,  ponudjac2, namirnica9, false, datumOd);
		em.persist(ponuda9);
		Ponuda ponuda10 = new Ponuda(30.00, "garancija1", datumDo, false,  ponudjac2, namirnica10, false, datumOd);
		em.persist(ponuda10);
		Ponuda ponuda11 = new Ponuda(100.00, "garancija2", datumDo, false,  ponudjac2, namirnica12, false, datumOd);
		em.persist(ponuda11);
		Ponuda ponuda12 = new Ponuda(500.00, "garancija3", datumDo, false,  ponudjac2, namirnica11, false, datumOd);
		em.persist(ponuda12);
		
		Poseta poseta1 = new Poseta(5, 5, 5,narudzbine);
		em.persist(poseta1);
		Poseta poseta2 = new Poseta(5, 5, 4,narudzbine);
		em.persist(poseta2);
		Poseta poseta3 = new Poseta(5, 4, 4,narudzbine);
		em.persist(poseta3);
		Poseta poseta4 = new Poseta(4, 4, 4,narudzbine);
		em.persist(poseta4);
		Poseta poseta5 = new Poseta(4, 4, 3,narudzbine);
		em.persist(poseta5);
		Poseta poseta6 = new Poseta(3, 3, 3,narudzbine);
		em.persist(poseta6);
		Poseta poseta7 = new Poseta(2, 3, 4,narudzbine);
		em.persist(poseta7);
		Poseta poseta8 = new Poseta(3, 5, 2,narudzbine);
		em.persist(poseta8);
		Poseta poseta9 = new Poseta(2, 5, 3,narudzbine);
		em.persist(poseta9);
		
		Poseta poseta10 = new Poseta();
		poseta10.setNarudzbine(narudzbine);
		em.persist(poseta10);
		Poseta poseta11 = new Poseta();
		poseta11.setNarudzbine(narudzbine);
		em.persist(poseta11);

		
		Date d3 = new Date(116,5,21,8,30);
		Date d4 = new Date(116,5,21,14,30);
		Date d5 = new Date(116,5,22,8,30);
		Date d6 = new Date(116,5,23,14,30);
		Date d7 = new Date(116,5,24,8,30);
		Date d8 = new Date(116,5,25,14,30);
		Date d9 = new Date(116,5,26,8,30);
		Date d10 = new Date(116,5,26,14,30);
		/*
		Rezervacija rez1 = new Rezervacija(d3, d4, g1, sto1, restoran1, narudzbine,prihvaceniGosti2);
		em.persist(rez1);
		Rezervacija rez2 = new Rezervacija(d3, d4, g2, sto2, restoran1, narudzbine, prihvaceniGosti2);
		em.persist(rez2);
		Rezervacija rez3 = new Rezervacija(d3, d4, g3, sto3, restoran1, narudzbine, prihvaceniGosti2);
		em.persist(rez3);
		Rezervacija rez4 = new Rezervacija(d3, d4, g4, sto1, restoran1, narudzbine, prihvaceniGosti2);
		em.persist(rez4);
		Rezervacija rez5 = new Rezervacija(d3, d4, g2, sto1, restoran1, narudzbine, prihvaceniGosti3);
		em.persist(rez5);
		Rezervacija rez6 = new Rezervacija(d3, d4, g1, sto4, restoran1, narudzbine, prihvaceniGosti3);
		em.persist(rez6);
		
		*/
		Rezervacija rez1 = new Rezervacija(d3, d4, g1, sto1, restoran1, narudzbine,prihvaceniGosti1);
		em.persist(rez1);
		Rezervacija rez2 = new Rezervacija(d5, d6, g2, sto2, restoran1, narudzbine, prihvaceniGosti1);
		em.persist(rez2);
		Rezervacija rez3 = new Rezervacija(d7, d8, g3, sto3, restoran1, narudzbine, prihvaceniGosti2);
		em.persist(rez3);
		Rezervacija rez4 = new Rezervacija(d9, d10, g4, sto1, restoran1, narudzbine, prihvaceniGosti2);
		em.persist(rez4);
		Rezervacija rez5 = new Rezervacija(d7, d8, g2, sto1, restoran1, narudzbine, prihvaceniGosti3);
		em.persist(rez5);
		Rezervacija rez6 = new Rezervacija(d9, d10, g1, sto4, restoran1, narudzbine, prihvaceniGosti3);
		em.persist(rez6);
		
		Narudzbina n1 = new Narudzbina(rez1, ob1, pp1, konobar9, g1, poseta1, restoran1);
		em.persist(n1);
		narudzbine.add(n1);
		Narudzbina n2 = new Narudzbina(rez2, ob2, pp2, konobar8, g2, poseta2, restoran1);
		em.persist(n2);
		narudzbine.add(n2);
		Narudzbina n3 = new Narudzbina(rez3, ob3, pp3, konobar7, g3, poseta3, restoran1);
		em.persist(n3);
		narudzbine.add(n3);
		Narudzbina n4 = new Narudzbina(rez4, ob2, pp4, konobar6, g4, poseta4, restoran1);
		em.persist(n4);
		narudzbine.add(n3);
		Narudzbina n5 = new Narudzbina(rez1, ob3, pp2, konobar9, g1, poseta9, restoran1);
		em.persist(n5);
		narudzbine.add(n5);
		Narudzbina n6 = new Narudzbina(rez5, ob2, pp2, konobar9, korisnik, poseta10, restoran1);
		em.persist(n6);
		narudzbine.add(n6);
		Narudzbina n7 = new Narudzbina(rez6, ob1, pp2, konobar9, korisnik, poseta11, restoran1);
		em.persist(n7);
		narudzbine.add(n7);
	}

}
