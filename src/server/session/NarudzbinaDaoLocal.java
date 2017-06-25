package server.session;

import java.util.List;

import server.entity.Narudzbina;

public interface NarudzbinaDaoLocal extends GenericDaoLocal<Narudzbina, Integer>{

	public List<Narudzbina> getNarudzbineZaGostaZaRezervaciju(Integer id_rez, Integer id_gost);
	public List<Narudzbina> getRezervacijeZaGosta(Integer id_gost);
	public List<Narudzbina> getPoseteZaGosta(Integer id_gost);
	public List<Narudzbina> getPoseteZaRestoran(Integer id_res);
	public List<Narudzbina> getPoseteZaRestoranZaGosta(Integer id_res, Integer id_gost);
	
	
	

}
