package entidades;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import util.constantes.LivroCategoria;

/**
 *
 * @author rayana
 */
@Entity
@AttributeOverrides({
    @AttributeOverride(name = "chavePrimaria", column = @Column(name = "LIVRO_ID"))})
public class Livro extends EntidadeNegocio {

    private String nome;    
    private String sinopse;  
    private LivroCategoria categoria;
          
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
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
}
