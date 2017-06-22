
package br.edu.ifsul.converters;

import br.edu.ifsul.converters.*;
import br.edu.ifsul.modelo.Remedio;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Suelen A. Camargo
 * @email s-a-camargo@hotmail.com
 */
@FacesConverter(value = "converterRemedio")
public class ConverterReceita implements Converter, Serializable {
    
    @PersistenceContext(unitName = "TrabalhoFinal-6N1-WebPU")
    private EntityManager em;    

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")){
            return null;
        }
        return em.find(Remedio.class, string);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null){
            return null;
        }
        Remedio obj = (Remedio) o;
        return obj.getNome();
    }
    

}
