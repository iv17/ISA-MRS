package server.session;

import javax.ejb.Local;
import javax.ejb.Stateless;

import server.entity.Reon;

@Stateless
@Local(ReonDaoLocal.class)
public class ReonDaoBean extends GenericDaoBean<Reon, Integer>
implements ReonDaoLocal {

}
