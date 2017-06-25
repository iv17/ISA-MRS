package server.session;

import java.util.Date;

import server.entity.Smena;

public interface SmenaDaoLocal extends GenericDaoLocal<Smena, Integer>{

	public Smena findSmena(Date smena_od, Date smena_do);
}
