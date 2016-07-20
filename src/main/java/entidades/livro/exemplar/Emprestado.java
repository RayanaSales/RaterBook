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
public class Emprestado implements OperacoesEstadoExemplar {

    private static Emprestado instancia = null;

    private Emprestado() {
    }

    public static Emprestado getInstance() {
        if (instancia == null) {
            instancia = new Emprestado();
        }
        return instancia;
    }

    @Override
    public EstadoExemplar devolver(Exemplar exemplar) throws NegocioException {
       // exemplar.getUltimoEmprestimo().devolver();
        return EstadoExemplar.DISPONIVEL;
    }

    @Override
    public EstadoExemplar solicitar(Aluno aluno, Exemplar exemplar) throws NegocioException {
        throw new NegocioException(NegocioException.EXEMPLAR_INDISPONIVEL);
    }

}
