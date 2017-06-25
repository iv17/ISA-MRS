package server.session;

import javax.ejb.Local;
import javax.ejb.Stateless;

import server.entity.MenadzerRestorana;

@Stateless
@Local(MenadzerRestoranaDaoLocal.class)
public class MenadzerRestoranaDaoBean extends GenericDaoBean<MenadzerRestorana, Integer>
implements MenadzerRestoranaDaoLocal {

}
