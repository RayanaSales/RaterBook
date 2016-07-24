/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.livro.exemplar;

import entidades.aluno.Aluno;
import entidades.livro.emprestimo.Emprestimo;
import exception.NegocioException;
import java.util.Date;

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
    public void devolver(Exemplar exemplar) throws NegocioException {
        Emprestimo emprestimo = exemplar.getUltimoEmprestimo();
        exemplar.setEstadoExemplar(EstadoExemplar.DISPONIVEL);
        emprestimo.devolverEmprestimo();
    }

    @Override
    public Emprestimo solicitar(Aluno aluno, Date dataPrevistaEntrega, Exemplar exemplar) throws NegocioException {
        throw new NegocioException(NegocioException.EXEMPLAR_INDISPONIVEL);
    }

}
