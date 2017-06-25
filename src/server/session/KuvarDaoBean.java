package server.session;

import javax.ejb.Local;
import javax.ejb.Stateless;

import server.entity.Kuvar;

@Stateless
@Local(KuvarDaoLocal.class)
public class KuvarDaoBean extends GenericDaoBean<Kuvar, Integer>
implements KuvarDaoLocal {

}
