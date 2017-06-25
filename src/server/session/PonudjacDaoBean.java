package server.session;

import javax.ejb.Local;
import javax.ejb.Stateless;

import server.entity.Ponudjac;

@Stateless
@Local(PonudjacDaoLocal.class)
public class PonudjacDaoBean extends GenericDaoBean<Ponudjac, Integer>
implements PonudjacDaoLocal {

}
