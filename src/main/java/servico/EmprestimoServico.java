/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import entidades.aluno.Aluno;
import entidades.livro.emprestimo.Emprestimo;
import entidades.livro.exemplar.Exemplar;
import exception.NegocioException;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

/**
 *
 * @author Edmilson Santana
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class EmprestimoServico extends Servico<Emprestimo> {

    @EJB
    private AlunoServico alunoServico;

    @EJB
    private LivroServico livroServico;

    @Override
    public Boolean verificarExistencia(Emprestimo entidadeNegocio) {
        return false;
    }

    @Override
    public Class<Emprestimo> getClasseEntidade() {
        return Emprestimo.class;
    }

    @Override
    public Emprestimo getEntidadeNegocio() {
        return new Emprestimo();
    }

    public void cadastrarNovoEmprestimo(Aluno aluno, Date dataEntrega, Exemplar exemplar) throws NegocioException {
        if (!verificarMultasPendentes(aluno)) {
            Emprestimo emprestimo = exemplar.solicitar(aluno, dataEntrega);
            super.salvar(emprestimo);
            alunoServico.alterar(aluno);
            livroServico.atualizarExemplar(exemplar);
        } else {
            throw new NegocioException(NegocioException.POSSUI_MULTA);
        }

    }

    public void devolverEmprestimo(Emprestimo emprestimo) throws NegocioException {
        Exemplar exemplar = emprestimo.getExemplar();
        exemplar.devolver();
        super.alterar(emprestimo);
        livroServico.atualizarExemplar(exemplar);

    }

    public void pagarMulta(Emprestimo emprestimo) throws NegocioException {
        emprestimo.pagarMulta();
        super.alterar(emprestimo);
    }

    private boolean verificarMultasPendentes(Aluno aluno) {
        int quantidadeDeMultas = 0;
        for (Emprestimo emp : aluno.getEmprestimos()) {
            if (emp.posssuiMulta()) {
                quantidadeDeMultas++;
            }
        }
        return quantidadeDeMultas > 0;
    }

}
