package server.session;

import java.util.List;

import server.entity.Gost;

public interface GostDaoLocal extends GenericDaoLocal<Gost, Integer>{

	public List<Gost> showGosti();
	
	
}
