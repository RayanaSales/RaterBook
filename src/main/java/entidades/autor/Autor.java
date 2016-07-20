package entidades.autor;

import entidades.EntidadeNegocio;
import entidades.livro.Livro;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author douglas
 */
@Entity
@AttributeOverrides(
        {
            @AttributeOverride(name = "chavePrimaria", column = @Column(name = "AUTOR_ID"))
        })
public class Autor extends EntidadeNegocio {

    private static final long serialVersionUID = -8161053151326463631L;

    @NotBlank
    @Size(min = 7, max = 30)
    @Pattern(regexp = "[A-Za-z ]+", message = "{entidades.autor.Autor.nome}")
    private String nome;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "TB_AUTOR_LIVRO", joinColumns = @JoinColumn(name = "AUTOR_ID"),
            inverseJoinColumns = @JoinColumn(name = "LIVRO_ID"))
    private List<Livro> livros;

    public Autor()
    {
    }
    
    public void adicionarAutor(Livro livro) {
        if (livros == null) {
            livros = new ArrayList<>();
        }
        livros.add(livro);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
