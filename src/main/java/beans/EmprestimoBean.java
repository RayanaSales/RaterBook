/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entidades.aluno.Aluno;
import entidades.livro.emprestimo.Emprestimo;
import entidades.livro.exemplar.Exemplar;
import exception.NegocioException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import servico.AlunoServico;
import servico.EmprestimoServico;
import servico.LivroServico;
import servico.Servico;

/**
 *
 * @author Edmilson Santana
 */
@ManagedBean
@ViewScoped
public class EmprestimoBean extends Bean<Emprestimo> {

    private static final long serialVersionUID = 5548651871830012533L;

    @EJB
    private AlunoServico alunoServico;

    @EJB
    private EmprestimoServico emprestimoServico;

    @EJB
    private LivroServico livroServico;

    private List<Aluno> alunos = new ArrayList<>();

    private List<Exemplar> exemplares = new ArrayList<>();

    private Exemplar exemplar;

    private Aluno aluno;

    private Date dataPrevistaEntrega;

    @Override
    public void inicializar() {
        super.inicializar();
        exemplar = livroServico.criarExemplar();
        aluno = alunoServico.getEntidadeNegocio();
        alunos = alunoServico.listarTodos();
        exemplares = livroServico.obterExemplaresDisponiveis();
    }

    @Override
    protected Servico inicializarServico() {
        return emprestimoServico;
    }

    public void criarNovoEmprestimo() {
        try {
            emprestimoServico.cadastrarNovoEmprestimo(aluno, dataPrevistaEntrega, exemplar);
            adicionarMensagemView("Empréstimo cadastrado!", FacesMessage.SEVERITY_WARN);
        } catch (NegocioException ex) {
            adicionarMensagemView(ex.getMessage(), FacesMessage.SEVERITY_WARN);
        }
    }

    public void devolverEmprestimo(Emprestimo emprestimo) {
        try {
            emprestimoServico.devolverEmprestimo(emprestimo);
        } catch (NegocioException ex) {
            adicionarMensagemView(ex.getMessage(), FacesMessage.SEVERITY_WARN);
        }
    }

    public void pagarMulta() throws NegocioException {
        emprestimoServico.pagarMulta(entidade);

    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Date getDataPrevistaEntrega() {
        return dataPrevistaEntrega;
    }

    public void setDataPrevistaEntrega(Date dataPrevistaEntrega) {
        this.dataPrevistaEntrega = dataPrevistaEntrega;
    }

    public List<Exemplar> getExemplares() {
        return exemplares;
    }

    public void setExemplares(List<Exemplar> exemplares) {
        this.exemplares = exemplares;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }
    
    
    public Date getDataLimiteEmprestimo() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

}
