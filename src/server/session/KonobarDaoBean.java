package server.session;

import javax.ejb.Local;
import javax.ejb.Stateless;

import server.entity.Konobar;
@Stateless
@Local(KonobarDaoLocal.class)
public class KonobarDaoBean extends GenericDaoBean<Konobar, Integer>
implements KonobarDaoLocal {

}
