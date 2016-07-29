/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.livro.emprestimo;

/**
 *
 * @author Edmilson Santana
 */
public enum SituacaoEmprestimo {

    EM_ANDAMENTO("Andamento"),
    MULTA_A_PAGAR("Multa a Pagar"),
    ATRASADO("Atrasado"),
    QUITADO("Quitado");
    
    private final String label;

    private SituacaoEmprestimo(String label) {
        this.label = label;
    }
    
    public String getLabel() {
        return this.label;
    }
    
    

}
