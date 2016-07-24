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
public interface OperacoesEstadoExemplar {

    void devolver(Exemplar exemplar) throws NegocioException;

    Emprestimo solicitar(Aluno aluno,  Date dataPrevistaEntrega, Exemplar exemplar) throws NegocioException;
}
