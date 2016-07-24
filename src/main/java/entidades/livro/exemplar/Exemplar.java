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
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import util.DateComparator;

/**
 *
 * @author Edmilson Santana
 */
@Entity
@AttributeOverrides(
        {
            @AttributeOverride(name = "chavePrimaria", column = @Column(name = "EXEMPLAR_ID"))
        })
@NamedQueries(
        @NamedQuery(
                name = Exemplar.ULTIMO_TOMBO_EXEMPLAR,
                query = "SELECT max(e.tombo) FROM Exemplar e"
        ))
public class Exemplar extends EntidadeNegocio {

    private static final long serialVersionUID = 405832921706298633L;
    
    public static final String ULTIMO_TOMBO_EXEMPLAR = "UltimoTomboExemplar";
    
    @ManyToOne
    private Livro livro;

    @Column(unique = true)
    private Long tombo;

    @Column()
    private Long numeroExemplar;
    
    @OneToMany(mappedBy = "exemplar")
    private List<Emprestimo> emprestimos;
    
    @Enumerated(EnumType.STRING)
    private EstadoExemplar estadoExemplar;

    public Exemplar() {
        estadoExemplar = EstadoExemplar.DISPONIVEL;
    }

    public void devolver() throws NegocioException {
        estadoExemplar.devolver(this);
    }

    public Emprestimo solicitar(Aluno aluno, Date dataPrevistaEntrega) throws NegocioException {
        return estadoExemplar.solicitar(aluno, dataPrevistaEntrega, this);
    }

    public Emprestimo getUltimoEmprestimo() {
        Collections.sort(emprestimos, new DateComparator());
        return emprestimos.get(0);
    }

    protected Emprestimo novoEmprestimo(Aluno aluno, Date dataPrevistaEntrega) {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setDataPrevistaEntrega(dataPrevistaEntrega);
        aluno.addEmprestimo(emprestimo);
        this.addEmprestimo(emprestimo);
        return emprestimo;
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
    
    public Boolean isEmprestado() {
        Boolean emprestado = Boolean.FALSE;
        if ( estadoExemplar.equals(EstadoExemplar.EMPRESTADO)) {
            emprestado = Boolean.TRUE;
        }
        return emprestado;
    }

    public EstadoExemplar getEstadoExemplar() {
        return estadoExemplar;
    }

    public void setEstadoExemplar(EstadoExemplar estadoExemplar) {
        this.estadoExemplar = estadoExemplar;
    }

    public Long getNumeroExemplar() {
        return numeroExemplar;
    }

    public void setNumeroExemplar(Long numeroExemplar) {
        this.numeroExemplar = numeroExemplar;
    }
    
    

}
