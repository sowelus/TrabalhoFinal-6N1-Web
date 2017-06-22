package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Paciente;
import java.io.Serializable;
import javax.ejb.Stateful;


/**
 *
 * @author Suelen A. Camargo
 * @email s-a-camargo@hotmail.com
 */
@Stateful
public class PacienteDAO<T> extends DAOGenerico<Paciente> implements Serializable {

    public PacienteDAO(){
        super();
        super.classePersistente = Paciente.class;
    }
}
