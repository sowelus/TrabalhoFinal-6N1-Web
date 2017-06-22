package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Medico;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Suelen A. Camargo
 * @email s-a-camargo@hotmail.com
 */

@Stateful
public class MedicoDAO<T> extends DAOGenerico<Medico> implements Serializable {

    public MedicoDAO() {
        super();
        super.classePersistente = Medico.class;
    }

}
