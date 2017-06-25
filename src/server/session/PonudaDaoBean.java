package server.session;

import javax.ejb.Local;
import javax.ejb.Stateless;

import server.entity.Ponuda;

@Stateless
@Local(PonudaDaoLocal.class)
public class PonudaDaoBean extends GenericDaoBean<Ponuda, Integer>
implements PonudaDaoLocal {

}
