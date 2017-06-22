
package br.edu.ifsul.util;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Suelen A. Camargo
 * @email s-a-camargo@hotmail.com
 */
public class Util {
	
	public static void mensagemInformacao(String msg){
		FacesContext facesContext =	FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash().setKeepMessages(true);	
		FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
		facesContext.addMessage(null, mensagem);
	}
	
	public static void mensagemErro(String msg){
		FacesContext facesContext =	FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash().setKeepMessages(true);	
		FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
		facesContext.addMessage(null, mensagem);		
	}  		

    public static String getMensagemErro(Exception e) {
        while (e.getCause() != null) {
            e = (Exception) e.getCause();
        }
        String retorno = e.getMessage();
        return retorno;
    }

}	