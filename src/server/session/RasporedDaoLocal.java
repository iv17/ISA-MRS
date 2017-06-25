package server.session;

import java.util.List;

import server.entity.RasporedStolova;

public interface RasporedDaoLocal extends GenericDaoLocal<RasporedStolova, Integer>{
	
	public List<RasporedStolova> showRestaurants(Integer res_id);
	

}
