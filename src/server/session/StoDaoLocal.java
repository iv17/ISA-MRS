package server.session;

import java.util.List;

import server.entity.Sto;

public interface StoDaoLocal extends GenericDaoLocal<Sto, Integer>{

	public List<Sto> getTablesForRestaurant(Integer id);
}
