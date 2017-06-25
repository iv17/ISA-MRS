package server.session;

import java.util.List;

import server.entity.Rezervacija;

public interface RezervacijaDaoLocal extends GenericDaoLocal<Rezervacija, Integer>{

	public List<Rezervacija> getRezervacije();
	public List<Rezervacija> getRezervacijeRestorana(Integer id_res);
	public List<Rezervacija> getRezervacijeZaSto(Integer id_sto);
	public List<Rezervacija> getRezervacijeZaGosta(Integer id_gosta);
	
}
