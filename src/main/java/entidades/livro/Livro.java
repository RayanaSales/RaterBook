package entidades.livro;

import entidades.editora.Editora;
import entidades.autor.Autor;
import entidades.EntidadeNegocio;
import entidades.livro.exemplar.Exemplar;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import util.constantes.CategoriaLivro;

@Entity
@AttributeOverrides(
        {
            @AttributeOverride(name = "chavePrimaria", column = @Column(name = "LIVRO_ID"))
        })
@NamedQueries(
        @NamedQuery(
                name = Livro.LIVRO_POR_ISBN,
                query = "SELECT l FROM Livro l JOIN FETCH l.editora WHERE l.isbn = ?1"
        ))
public class Livro extends EntidadeNegocio {
    
    private static final long serialVersionUID = 2982381374540178511L;
    
    public static final String LIVRO_POR_ISBN = "LivroPorIsbn";
    
    @NotBlank
    @Size(min = 2, max = 50)
    private String titulo;
    
    @Pattern(regexp = "[0-9]{3}-[0-9]{2}-[0-9]{4}-[0-9]{3}-[0-9]{1}", message = "{entidades.editora.Livro.isbn}")
    private String isbn;
    
    @Min(value = 1)
    private Integer edicao;
    
    @Min(value = 1)
    private Integer anoEdicao;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Editora editora;
    
    @NotNull
    private CategoriaLivro categoria;
    
    @OneToMany(mappedBy = "livro", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Exemplar> exemplares;
    
    @ManyToMany(mappedBy = "livros", fetch = FetchType.EAGER)
    private List<Autor> autores;
    
    public List<Autor> getAutores() {
        return this.autores;
    }
    public void adicionarAutor(Autor autor) {
        if (autores == null) {
            autores = new ArrayList<>();
        }
        autores.add(autor);
    }
    
    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }
    
    public CategoriaLivro getCategoria() {
        return categoria;
    }
    
    public void setCategoria(CategoriaLivro categoria) {
        this.categoria = categoria;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public Integer getEdicao() {
        return edicao;
    }
    
    public void setEdicao(Integer edicao) {
        this.edicao = edicao;
    }
    
    public Editora getEditora() {
        return editora;
    }
    
    public void setEditora(Editora editora) {
        this.editora = editora;
    }
    
    public List<Exemplar> getExemplares() {
        return exemplares;
    }
    
    public void adicionarExemplar(Exemplar exemplar) {
        if (this.exemplares == null) {
            this.exemplares = new ArrayList<>();
        }
        exemplar.setLivro(this);
        this.exemplares.add(exemplar);
    }
    
    public void novoExemplar(Long tombo) {
        Exemplar exemplar = new Exemplar();
        Long numeroExemplar = Integer.toUnsignedLong(this.exemplares.size() + 1);
        exemplar.setTombo(tombo);
        exemplar.setNumeroExemplar(numeroExemplar);
        this.adicionarExemplar(exemplar);
    }
    
    public Integer getAnoEdicao() {
        return anoEdicao;
    }
    
    public void setAnoEdicao(Integer anoEdicao) {
        this.anoEdicao = anoEdicao;
    }
    
    @Override
    public boolean associado() {
        return !autores.isEmpty();
    }
    
    
    
}
