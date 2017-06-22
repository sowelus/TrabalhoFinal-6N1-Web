package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Remedio;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Suelen A. Camargo
 * @email s-a-camargo@hotmail.com
 */
@Stateful
public class RemedioDAO<T> extends DAOGenerico<Remedio> implements Serializable {

    public RemedioDAO(){
        super();
        super.classePersistente = Remedio.class;
    }
    
    public T getObjectById(String id) throws Exception {
        return (T) em.find(classePersistente, id);
    }    
}
