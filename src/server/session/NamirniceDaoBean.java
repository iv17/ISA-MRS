package server.session;

import javax.ejb.Local;
import javax.ejb.Stateless;

import server.entity.Namirnice;

@Stateless
@Local(NamirniceDaoLocal.class)
public class NamirniceDaoBean extends GenericDaoBean<Namirnice, Integer>
implements NamirniceDaoLocal{

}
