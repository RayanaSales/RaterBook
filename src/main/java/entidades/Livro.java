package entidades;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import util.constantes.LivroCategoria;

/**
 *
 * @author rayana
 */
@Entity
@AttributeOverrides(
        {
            @AttributeOverride(name = "chavePrimaria", column = @Column(name = "LIVRO_ID"))
        })
public class Livro extends EntidadeNegocio
{

    @Size(min = 0, max = 5)
    private Integer avaliacao;
    
    @NotBlank
    @Size(min = 2, max = 50)
    private String nome;
    
    @NotBlank
    @Size(min = 10, max = 100)
    private String sinopse;
    
    @NotBlank
    private LivroCategoria categoria;
    
    @ManyToMany(mappedBy = "livros", fetch = FetchType.LAZY)
    private List<Autor> autores;

    public void adicionarAutor(Autor autor)
    {
        if (autores == null)
        {
            autores = new ArrayList<>();
        }
        autores.add(autor);
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public void setSinopse(String sinopse)
    {
        this.sinopse = sinopse;
    }

    public String getSinopse()
    {
        return sinopse;
    }

    public LivroCategoria getCategoria()
    {
        return categoria;
    }

    public void setCategoria(LivroCategoria categoria)
    {
        this.categoria = categoria;
    }

    public Integer getAvaliacao()
    {
        return avaliacao;
    }

    public void setAvaliacao(Integer avaliacao)
    {
        this.avaliacao = avaliacao;
    }

}
