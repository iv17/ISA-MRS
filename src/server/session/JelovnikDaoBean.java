package server.session;

import javax.ejb.Local;
import javax.ejb.Stateless;

import server.entity.Jelovnik;

@Stateless
@Local(JelovnikDaoLocal.class)
public class JelovnikDaoBean extends GenericDaoBean<Jelovnik, Integer>
implements JelovnikDaoLocal{

}
