package entidades;

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

/**
 *
 * @author douglas
 */
@Entity
@AttributeOverrides(
        {
            @AttributeOverride(name = "chavePrimaria", column = @Column(name = "AUTOR_ID"))
        })
public class Autor extends EntidadeNegocio
{

    private String nome;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "TB_AUTOR_LIVRO", joinColumns = @JoinColumn(name = "AUTOR_ID"),
            inverseJoinColumns = @JoinColumn(name = "LIVRO_ID"))    
    private List<Livro> livros;

    public void adicionarAutor(Livro livro)
    {
        if (livros == null)
        {
            livros = new ArrayList<>();
        }
        livros.add(livro);
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

}
