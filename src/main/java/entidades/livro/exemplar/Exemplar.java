/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.livro.exemplar;

import entidades.livro.exemplar.EstadoExemplar;
import entidades.livro.exemplar.Disponivel;
import entidades.EntidadeNegocio;
import entidades.aluno.Aluno;
import entidades.livro.emprestimo.Emprestimo;
import entidades.livro.Livro;
import exception.NegocioException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import util.constantes.SituacaoExemplar;

/**
 *
 * @author Edmilson Santana
 */
@Entity
public class Exemplar extends EntidadeNegocio {

    @ManyToOne
    private Livro livro;

    @Column(unique = true)
    private Long tombo;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Emprestimo> emprestimos;

    private SituacaoExemplar situacaoExemplar;

    @Transient
    private EstadoExemplar estadoExemplar;

    public Exemplar() {
        estadoExemplar = Disponivel.getInstance();
    }

    public void devolver() throws NegocioException {
        estadoExemplar = estadoExemplar.devolver(this);
    }

    public void solicitar(Aluno aluno) throws NegocioException {
        estadoExemplar = estadoExemplar.solicitar(aluno, this);
    }

    public Emprestimo getUltimoEmprestimo() {
        return emprestimos.get(0);
    }
    
    protected void novoEmprestimo(Aluno aluno) {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setAluno(aluno);
        this.addEmprestimo(emprestimo);
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Long getTombo() {
        return tombo;
    }

    public void setTombo(Long tombo) {
        this.tombo = tombo;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void addEmprestimo(Emprestimo emprestimo) {
        if (this.emprestimos == null) {
            this.emprestimos = new ArrayList<>();
        }
        emprestimo.setExemplar(this);
        this.emprestimos.add(emprestimo);
    }

    public SituacaoExemplar getSituacaoExemplar() {
        return situacaoExemplar;
    }

    public void setSituacaoExemplar(SituacaoExemplar situacaoExemplar) {
        this.situacaoExemplar = situacaoExemplar;
    }

    public EstadoExemplar getEstadoExemplar() {
        return estadoExemplar;
    }

    public void setEstadoExemplar(EstadoExemplar estadoExemplar) {
        this.estadoExemplar = estadoExemplar;
    }

}
