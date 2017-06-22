package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Clinica;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Suelen A. Camargo
 * @email s-a-camargo@hotmail.com
 */
@Stateful
public class ClinicaDAO<T> extends DAOGenerico<Clinica> implements Serializable {

    public ClinicaDAO() {
        super();
        super.classePersistente = Clinica.class;
    }
}
