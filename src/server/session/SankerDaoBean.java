package server.session;

import javax.ejb.Local;
import javax.ejb.Stateless;

import server.entity.Sanker;

@Stateless
@Local(SankerDaoLocal.class)
public class SankerDaoBean extends GenericDaoBean<Sanker, Integer>
implements SankerDaoLocal{

}
