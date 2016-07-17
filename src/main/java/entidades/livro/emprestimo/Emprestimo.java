/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.livro.emprestimo;

import entidades.livro.exemplar.Exemplar;
import entidades.EntidadeNegocio;
import entidades.aluno.Aluno;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import util.constantes.SituacaoEmprestimo;

/**
 *
 * @author Edmilson Santana
 */
@Entity
public class Emprestimo extends EntidadeNegocio {

    @NotNull
    @ManyToOne
    private Exemplar exemplar;
    @NotNull
    @ManyToOne
    private Aluno aluno;

    @Future
    @Temporal(TemporalType.DATE)
    private Date dataPrevistaEntrega;

    @Transient
    private final Double valorDiarioMulta;
            
    @Transient
    private final Integer INTERVALO_DATA_ENTREGA = 7;

    @Temporal(TemporalType.DATE)
    private Date dataDevolucao;

    @Temporal(TemporalType.DATE)
    private final Date dataEmprestimo;

    private SituacaoEmprestimo situacaoEmprestimo;

    public Emprestimo() {
        dataEmprestimo = new Date();
        valorDiarioMulta = 2.0;
    }


    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
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

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public SituacaoEmprestimo getSituacaoEmprestimo() {
        return situacaoEmprestimo;
    }

    public void setSituacaoEmprestimo(SituacaoEmprestimo situacaoEmprestimo) {
        this.situacaoEmprestimo = situacaoEmprestimo;
    }

}
