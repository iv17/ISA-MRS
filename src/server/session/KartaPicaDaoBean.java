package server.session;

import javax.ejb.Local;
import javax.ejb.Stateless;

import server.entity.KartaPica;

@Stateless
@Local(KartaPicaDaoLocal.class)
public class KartaPicaDaoBean extends GenericDaoBean<KartaPica, Integer>
implements KartaPicaDaoLocal{

}
