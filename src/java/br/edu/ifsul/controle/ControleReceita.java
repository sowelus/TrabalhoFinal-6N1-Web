package br.edu.ifsul.controle;

import br.edu.ifsul.dao.RemedioDAO;
import br.edu.ifsul.dao.PacienteDAO;
import br.edu.ifsul.dao.MedicoDAO;
import br.edu.ifsul.dao.ReceitaDAO;
import br.edu.ifsul.modelo.Remedio;
import br.edu.ifsul.modelo.Paciente;
import br.edu.ifsul.modelo.Medico;
import br.edu.ifsul.modelo.Receita;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named(value = "controleReceita")
@SessionScoped
public class ControleReceita implements Serializable {

    @EJB
    private ReceitaDAO<Receita> dao;
    private Receita objeto;
    private Boolean editando;
    
    @EJB
    private MedicoDAO<Paciente> daoMedico;
    private Medico medico;
  
    @EJB
    private PacienteDAO<Paciente> daoPaciente;
    private Paciente paciente;

    @EJB
    private RemedioDAO<Remedio> daoRemedio;
    private Remedio remedio;
    private Boolean editandoRemedio;

    public ControleReceita() {
        editando = false;
        editandoRemedio = false;

    }

    public String listar() {
        editando = false;
        return "/privado/receita/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Receita();
        editando = true;
        editandoRemedio = false;
    }

    public void alterar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            editando = true;
            editandoRemedio = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: "
                    + Util.getMensagemErro(e));
        }
    }

    public void excluir(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Removidas com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao remover: "
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

    public void novoRemedio() {
        editandoRemedio = true;
    }

    public void salvarRemedio() {
        if (!objeto.getRemedios().contains(remedio)) {
            objeto.getRemedios().add(remedio);
            Util.mensagemInformacao("Remédio adicionado a esta receita!");
        } else {
            Util.mensagemErro("Receita já possui este remédio!");
        }
        editandoRemedio = false;
    }

    public void removerRemedio(Remedio obj){
        objeto.getRemedios().remove(obj);
        Util.mensagemInformacao("Remédio removido desta receita!");
    }
    
    public ReceitaDAO<Receita> getDao() {
        return dao;
    }

    public void setDao(ReceitaDAO<Receita> dao) {
        this.dao = dao;
    }

    public Receita getObjeto() {
        return objeto;
    }

    public void setObjeto(Receita objeto) {
        this.objeto = objeto;
    }


    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public RemedioDAO<Remedio> getDaoRemedio() {
        return daoRemedio;
    }

    public void setDaoRemedio(RemedioDAO<Remedio> daoRemedio) {
        this.daoRemedio = daoRemedio;
    }

    public Remedio getRemedio() {
        return remedio;
    }

    public void setRemedio(Remedio remedio) {
        this.remedio = remedio;
    }

    public Boolean getEditandoRemedio() {
        return editandoRemedio;
    }

    public void setEditandoRemedio(Boolean editandoRemedio) {
        this.editandoRemedio = editandoRemedio;
    }

    public PacienteDAO<Paciente> getDaoPaciente() {
        return daoPaciente;
    }

    public void setDaoPaciente(PacienteDAO<Paciente> daoPaciente) {
        this.daoPaciente = daoPaciente;
    }

    public MedicoDAO<Paciente> getDaoMedico() {
        return daoMedico;
    }

    public void setDaoMedico(MedicoDAO<Paciente> daoMedico) {
        this.daoMedico = daoMedico;
    }


    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
