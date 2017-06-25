package server.session;

import java.util.List;

import server.entity.Prijatelj;

public interface PrijateljDaoLocal extends GenericDaoLocal<Prijatelj, Integer>{
	
	public List<Prijatelj> findFriends(Integer id, String prihvacen);
	public List<Prijatelj> removeFriend(Integer id, Integer idb);
	public List<Prijatelj> findAll(Integer id);
	public List<Prijatelj> findRequests(Integer id, String prihvacen);
	
	

}
