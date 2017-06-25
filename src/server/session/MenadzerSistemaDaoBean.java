package server.session;

import javax.ejb.Local;
import javax.ejb.Stateless;

import server.entity.MenadzerSistema;

@Stateless
@Local(MenadzerSistemaDaoLocal.class)
public class MenadzerSistemaDaoBean extends GenericDaoBean<MenadzerSistema, Integer>
implements MenadzerSistemaDaoLocal {

}
