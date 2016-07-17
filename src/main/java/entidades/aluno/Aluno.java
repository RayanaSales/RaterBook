/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.aluno;

import entidades.EntidadeNegocio;
import entidades.livro.emprestimo.Emprestimo;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author Edmilson Santana
 */
@Entity
public class Aluno extends EntidadeNegocio {

    private static final long serialVersionUID = -6967794368017326187L;

    private String primeiroNome;
    
    private String ultimoNome;
    
    private String matricula;

    @OneToMany(mappedBy = "aluno")
    private List<Emprestimo> emprestimos;

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }
    
   
    
    
}
