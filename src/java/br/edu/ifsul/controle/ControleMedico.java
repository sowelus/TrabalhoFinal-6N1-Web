package br.edu.ifsul.controle;

import br.edu.ifsul.dao.MedicoDAO;
import br.edu.ifsul.dao.ReceitaDAO;
import br.edu.ifsul.modelo.Medico;
import br.edu.ifsul.modelo.Receita;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "controleMedico")
@SessionScoped
public class ControleMedico implements Serializable {

    @EJB
    private MedicoDAO<Medico> dao;
    private Medico objeto;
    @EJB
    private ReceitaDAO<Receita> daoReceita;
    private Boolean editando;

    public ControleMedico() {
        editando = false;

    }

    public String listar() {
        editando = false;
        return "/privado/medico/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Medico();
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

    public MedicoDAO<Medico> getDao() {
        return dao;
    }

    public void setDao(MedicoDAO<Medico> dao) {
        this.dao = dao;
    }

    public Medico getObjeto() {
        return objeto;
    }

    public void setObjeto(Medico objeto) {
        this.objeto = objeto;
    }

    public ReceitaDAO<Receita> getDaoReceita() {
        return daoReceita;
    }

    public void setDaoReceita(ReceitaDAO<Receita> daoReceita) {
        this.daoReceita = daoReceita;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

}
