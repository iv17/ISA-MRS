package server.session;

import javax.ejb.Local;
import javax.ejb.Stateless;

import server.entity.Poseta;

@Stateless
@Local(PosetaDaoLocal.class)
public class PosetaDaoBean extends GenericDaoBean<Poseta, Integer>
implements PosetaDaoLocal {

}
