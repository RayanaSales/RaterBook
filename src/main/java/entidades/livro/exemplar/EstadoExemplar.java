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
public interface EstadoExemplar {

    EstadoExemplar devolver(Exemplar exemplar) throws NegocioException;

    EstadoExemplar solicitar(Aluno aluno, Exemplar exemplar) throws NegocioException;
}
