package server.session;

import javax.ejb.Local;
import javax.ejb.Stateless;

import server.entity.Obrok;

@Stateless
@Local(ObrokDaoLocal.class)
public class ObrokDaoBean extends GenericDaoBean<Obrok, Integer>
implements ObrokDaoLocal {

}
