package server.session;

import java.util.List;

import server.entity.Jelo;

public interface JeloDaoLocal extends GenericDaoLocal<Jelo, Integer>{

	public List<Jelo> getJelaJelovnika(Integer id_jelovnika);
	
}
