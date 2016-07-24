/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entidades.livro.emprestimo.Emprestimo;
import entidades.livro.emprestimo.SituacaoEmprestimo;
import java.util.Comparator;

/**
 *
 * @author Edmilson Santana
 */
public class DateComparator implements Comparator<Emprestimo> {

    @Override
    public int compare(Emprestimo t, Emprestimo t1) {
        return t.getDataEmprestimo().compareTo(t1.getDataEmprestimo());
    }
    
}
