
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.RemedioDAO;
import br.edu.ifsul.modelo.Remedio;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Suelen A. Camargo
 * @email s-a-camargo@hotmail.com
 */
@Named(value = "controleRemedio")
@SessionScoped
public class ControleRemedio implements Serializable {
    
    @EJB
    private RemedioDAO<Remedio> dao;
    private Remedio objeto;
    private Boolean editando;


    public ControleRemedio(){
         editando = false;
        
    }
     public String listar() {
        editando = false;
        return "/privado/remedio/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Remedio();
        editando = true;
    }

    public void alterar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            editando = true;
    
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: "
                    + Util.getMensagemErro(e));
        }
    }

    public void excluir(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao remover objeto: "
                    + Util.getMensagemErro(e));
        }
    }

    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
            editando = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: "
                    + Util.getMensagemErro(e));
        }
    }

    public RemedioDAO<Remedio> getDao() {
        return dao;
    }

    public void setDao(RemedioDAO<Remedio> dao) {
        this.dao = dao;
    }

    public Remedio getObjeto() {
        return objeto;
    }

    public void setObjeto(Remedio objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }
    
}
    