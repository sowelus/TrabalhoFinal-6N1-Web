
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Receita;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Suelen A. Camargo
 * @email s-a-camargo@hotmail.com
 */
@Stateful
public class ReceitaDAO<T> extends DAOGenerico<Receita> implements Serializable {

    public  ReceitaDAO(){
        super();
        super.classePersistente = Receita.class;
    }
    
    @Override
    public Receita getObjectById(Integer id) throws Exception {
        Receita obj = (Receita) em.find(classePersistente, id);
        /// inicializando a lista para não lazy inicialization exception
        obj.getRemedios().size();
        return obj;
    }    
            
}
