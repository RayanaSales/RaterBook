/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.editora;

import entidades.EntidadeNegocio;
import entidades.livro.Livro;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Edmilson Santana
 */
@Entity
public class Editora extends EntidadeNegocio {

    private static final long serialVersionUID = 3035399348697353532L;

    @NotBlank
    @Size(max = 50)
    @Pattern(regexp = "[A-Za-z ]+", message = "{entidades.editora.Editora.nome}")
    private String nome;

    @OneToMany(mappedBy = "editora", fetch = FetchType.LAZY)
    private List<Livro> livros;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

}
