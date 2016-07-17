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
public class Disponivel implements EstadoExemplar {

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
    public EstadoExemplar devolver(Exemplar exemplar) throws NegocioException {
        throw new NegocioException(NegocioException.EXEMPLAR_DISPONIVEL);
    }

    @Override
    public EstadoExemplar solicitar(Aluno aluno, Exemplar exemplar) {
        exemplar.novoEmprestimo(aluno);
        return Emprestado.getInstance();
    }

}
