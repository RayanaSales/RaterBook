/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.livro.exemplar;

import entidades.EntidadeNegocio;
import entidades.aluno.Aluno;
import entidades.livro.emprestimo.Emprestimo;
import entidades.livro.Livro;
import exception.NegocioException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Edmilson Santana
 */
@Entity
@AttributeOverrides(
        {
            @AttributeOverride(name = "chavePrimaria", column = @Column(name = "EXEMPLAR_ID"))
        })
public class Exemplar extends EntidadeNegocio {

    private static final long serialVersionUID = 405832921706298633L;

    @ManyToOne
    private Livro livro;

    @Column(unique = true)
    private Long tombo;

    @OneToMany(mappedBy = "exemplar")
    private List<Emprestimo> emprestimos;
    
    @Enumerated(EnumType.STRING)
    private EstadoExemplar estadoExemplar;

    public Exemplar() {
        estadoExemplar = EstadoExemplar.DISPONIVEL;
    }

    public void devolver() throws NegocioException {
        estadoExemplar = estadoExemplar.devolver(this);
    }

    public void solicitar(Aluno aluno) throws NegocioException {
        estadoExemplar = estadoExemplar.solicitar(aluno, this);
    }

    public Emprestimo getUltimoEmprestimo() {
        return null;
    }

    protected void novoEmprestimo(Aluno aluno) {
        Emprestimo emprestimo = new Emprestimo();
        aluno.addEmprestimo(emprestimo);
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

    public EstadoExemplar getEstadoExemplar() {
        return estadoExemplar;
    }

    public void setEstadoExemplar(EstadoExemplar estadoExemplar) {
        this.estadoExemplar = estadoExemplar;
    }

}
