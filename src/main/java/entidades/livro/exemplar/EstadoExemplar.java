/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.livro.exemplar;

import entidades.aluno.Aluno;
import exception.NegocioException;

/**
 *
 * @author Edmilson Santana
 */
public enum EstadoExemplar implements OperacoesEstadoExemplar {
    DISPONIVEL(Disponivel.getInstance()),
    EMPRESTADO(Emprestado.getInstance());

    private final OperacoesEstadoExemplar operacoes;

    private EstadoExemplar(OperacoesEstadoExemplar operacoes) {
        this.operacoes = operacoes;
    }

    @Override
    public EstadoExemplar devolver(Exemplar exemplar) throws NegocioException {
        return operacoes.devolver(exemplar);
    }

    @Override
    public EstadoExemplar solicitar(Aluno aluno, Exemplar exemplar) throws NegocioException {
        return operacoes.solicitar(aluno, exemplar);
    }
}
