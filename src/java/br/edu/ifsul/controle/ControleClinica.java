package br.edu.ifsul.controle;

import br.edu.ifsul.dao.MedicoDAO;
import br.edu.ifsul.dao.PacienteDAO;
import br.edu.ifsul.dao.ClinicaDAO;
import br.edu.ifsul.modelo.Medico;
import br.edu.ifsul.modelo.Paciente;
import br.edu.ifsul.modelo.Clinica;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named(value = "controleClinica")
@SessionScoped
public class ControleClinica implements Serializable {

    @EJB
    private ClinicaDAO<Clinica> dao;
    private Clinica objeto;
    private Boolean editando;
    
    @EJB
    private PacienteDAO<Paciente> daoPaciente;
    private Paciente paciente;
    
    @EJB
    private MedicoDAO<Medico> daoMedico;
    private Medico medico;
    private Boolean editandoMedico;

    public ControleClinica() {
        editando = false;
        editandoMedico = false;
    }

    public String listar() {
        editando = false;
        return "/privado/clinica/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Clinica();
        editando = true;
        editandoMedico = false;
    }

    public void alterar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            editando = true;
            editandoMedico = false;
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

    public void novoMedico() {
        editandoMedico = true;
    }

    public void salvarMedico() {
        if (!objeto.getMedicos().contains(medico)) {
            objeto.getMedicos().add(medico);
            Util.mensagemInformacao("Medico adicionado com sucesso!");
        } else {
            Util.mensagemErro("Clinica já possui este medico!");
        }
        editandoMedico = false;
    }

    public void removerMedico(Medico obj) {
        objeto.getMedicos().remove(obj);
        Util.mensagemInformacao("Medico removido com sucesso!");
    }

    public ClinicaDAO<Clinica> getDao() {
        return dao;
    }

    public void setDao(ClinicaDAO<Clinica> dao) {
        this.dao = dao;
    }

    public Clinica getObjeto() {
        return objeto;
    }

    public void setObjeto(Clinica objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public PacienteDAO<Paciente> getDaoPaciente() {
        return daoPaciente;
    }

    public void setDaoPaciente(PacienteDAO<Paciente> daoPaciente) {
        this.daoPaciente = daoPaciente;
    }

    public MedicoDAO<Medico> getDaoMedico() {
        return daoMedico;
    }

    public void setDaoMedico(MedicoDAO<Medico> daoMedico) {
        this.daoMedico = daoMedico;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Boolean getEditandoMedico() {
        return editandoMedico;
    }

    public void setEditandoMedico(Boolean editandoMedico) {
        this.editandoMedico = editandoMedico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

}
