/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.aluno;

import entidades.EntidadeNegocio;
import entidades.livro.emprestimo.Emprestimo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Edmilson Santana
 */
@Entity
public class Aluno extends EntidadeNegocio {

    private static final long serialVersionUID = -6967794368017326187L;

    @NotBlank
    @Size(min = 3, max = 30)
    @Pattern(regexp = "[A-Za-z ]+", message = "Apenas Letras")
    private String primeiroNome;
    
    @NotBlank
    @Size(min = 3, max = 30)
    @Pattern(regexp = "[A-Za-z ]+", message = "Apenas Letras")
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

    public void addEmprestimo(Emprestimo emprestimo) {
        if ( this.emprestimos == null ) {
            this.emprestimos = new ArrayList<>();
        }
        emprestimo.setAluno(this);
        this.emprestimos.add(emprestimo);
    }
    
//   @Override
//    public boolean equals(Object o)
//    {
//        super.equals(o);
//        
//        if (o != null)
//        {
//            if (o instanceof Aluno)
//            {
//                Aluno outra = (Aluno) o;
//                if (this.primeiroNome.equals(outra.primeiroNome) && this.ultimoNome.equals(outra.ultimoNome) && matricula.equals(outra.matricula))
//                {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
    
    
}
