package server.session;

import java.util.List;

import server.entity.Pice;

public interface PiceDaoLocal extends GenericDaoLocal<Pice, Integer>{
	
	public List<Pice> getPicaKarte(Integer id_karte);
	

}
