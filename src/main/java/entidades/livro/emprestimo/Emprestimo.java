/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.livro.emprestimo;

import entidades.livro.exemplar.Exemplar;
import entidades.EntidadeNegocio;
import entidades.aluno.Aluno;
import exception.NegocioException;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 *
 * @author Edmilson Santana
 */
@Entity
@AttributeOverrides(
        {
            @AttributeOverride(name = "chavePrimaria", column = @Column(name = "EMPRESTIMO_ID"))
        })
public class Emprestimo extends EntidadeNegocio {

    private static final long serialVersionUID = -6616763680915934583L;

    @NotNull
    @ManyToOne
    private Exemplar exemplar;

    @NotNull
    @ManyToOne
    private Aluno aluno;

    @Temporal(TemporalType.DATE)
    private Date dataPrevistaEntrega;

    @Transient
    private final Double valorDiarioMulta = 2.0;

    @Temporal(TemporalType.DATE)
    private Date dataDevolucao;

    @Temporal(TemporalType.DATE)
    private final Date dataEmprestimo;

    @Enumerated(EnumType.STRING)
    private SituacaoEmprestimo situacaoEmprestimo;

    public Emprestimo() {
        dataEmprestimo = new Date();
        situacaoEmprestimo = SituacaoEmprestimo.EM_ANDAMENTO;
    }

    public void devolverEmprestimo() throws NegocioException {
        if (emAndamento()) {
            this.dataDevolucao = Calendar.getInstance().getTime();
            if (this.isAtrasado()) {
                situacaoEmprestimo = SituacaoEmprestimo.MULTA_A_PAGAR;
                throw new NegocioException(NegocioException.EMPRESTIMO_ATRASADO);
            } else {
                situacaoEmprestimo = SituacaoEmprestimo.QUITADO;
            }
        }
    }

    public Double calcularMultaAtraso() {
        Double valorMultaAtraso = 0.0;
        int diasAtrasado;
        if (isAtrasado()) {
            if (dataDevolucao != null) {
                diasAtrasado = Days.daysBetween(new DateTime(dataDevolucao),
                        new DateTime(dataPrevistaEntrega)).getDays();

            } else {
                Date hoje = Calendar.getInstance().getTime();
                diasAtrasado = Days.daysBetween(new DateTime(hoje),
                        new DateTime(dataPrevistaEntrega)).getDays();
            }
            valorMultaAtraso = valorDiarioMulta * diasAtrasado;
        }
        return valorMultaAtraso;
    }

    public void pagarMulta() {
        if (posssuiMulta()) {
            situacaoEmprestimo = SituacaoEmprestimo.QUITADO;
        }
    }

    public Boolean emAndamento() {
        return situacaoEmprestimo.equals(SituacaoEmprestimo.EM_ANDAMENTO)
                || situacaoEmprestimo.equals(SituacaoEmprestimo.ATRASADO);
    }

    public Boolean posssuiMulta() {
        return situacaoEmprestimo.equals(SituacaoEmprestimo.MULTA_A_PAGAR);
    }

    private boolean isAtrasado() {

        Boolean atrasado = Boolean.FALSE;
        if (dataDevolucao != null) {
            if (dataDevolucao.after(dataPrevistaEntrega)) {
                atrasado = Boolean.TRUE;
                situacaoEmprestimo = SituacaoEmprestimo.ATRASADO;
            }
        } else {
            Date hoje = Calendar.getInstance().getTime();
            if (hoje.after(dataPrevistaEntrega)) {
                atrasado = Boolean.TRUE;
                situacaoEmprestimo = SituacaoEmprestimo.ATRASADO;
            }
        }
        return atrasado;
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

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public SituacaoEmprestimo getSituacaoEmprestimo() {
        return situacaoEmprestimo;
    }

    public void setSituacaoEmprestimo(SituacaoEmprestimo situacaoEmprestimo) {
        this.situacaoEmprestimo = situacaoEmprestimo;
    }

}
