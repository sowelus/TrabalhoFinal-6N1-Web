
package br.edu.ifsul.converters;

import br.edu.ifsul.modelo.Paciente;
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
@FacesConverter(value = "converterPaciente")
public class ConverterPaciente implements Converter, Serializable {
    
    @PersistenceContext(unitName = "TrabalhoFinal-6N1-WebPU")
    private EntityManager em;    

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")){
            return null;
        }
        return em.find(Paciente.class, Integer.parseInt(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null){
            return null;
        }
        Paciente obj = (Paciente) o;
        return obj.getId().toString();
    }
    

}
