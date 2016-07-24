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
public class Disponivel implements OperacoesEstadoExemplar {

    private static Disponivel instancia = null;

    private Disponivel() {
    }

    public static Disponivel getInstance() {
        if (instancia == null) {
            instancia = new Disponivel();
        }
        return instancia;
    }

    @Override
    public void devolver(Exemplar exemplar) throws NegocioException {
        throw new NegocioException(NegocioException.EXEMPLAR_DISPONIVEL);
    }

    @Override
    public Emprestimo solicitar(Aluno aluno, Date dataPrevistaEntrega, Exemplar exemplar) {
        Emprestimo emprestimo = exemplar.novoEmprestimo(aluno, dataPrevistaEntrega);
        exemplar.setEstadoExemplar(EstadoExemplar.EMPRESTADO);
        return emprestimo;
    }

}
