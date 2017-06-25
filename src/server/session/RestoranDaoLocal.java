package server.session;

import java.util.List;

import server.entity.Restoran;

public interface RestoranDaoLocal extends GenericDaoLocal<Restoran, Integer>{

	public List<Restoran> showRestaurants();
	

	
	
}
