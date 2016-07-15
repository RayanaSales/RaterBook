package entidades;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import util.constantes.CategoriaLivro;
import util.constantes.SituacaoLivro;

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

    @Pattern(regexp = "[0-9]{3}-[0-9]{2}-[0-9]{4}-[0-9]{3}-[0-9]{1}")
    private String isbn;

    @Min(value = 1)
    private Integer edicao;

    @Min(value = 0)
    private Integer unidades;

    @Past
    private Integer dataCriacao;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EDITORA", referencedColumnName = "ID", nullable = false)
    private Editora editora;

    @NotBlank
    private SituacaoLivro situacao;

    @NotBlank
    private CategoriaLivro categoria;

    @ManyToMany(mappedBy = "livros", fetch = FetchType.LAZY)
    private List<Autor> autores;

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

    @Override
    public boolean associado() {
        if (autores.isEmpty()) {
            return false;
        }
        return true;
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

    public Integer getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Integer dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public SituacaoLivro getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoLivro situacao) {
        this.situacao = situacao;
    }

    public Integer getUnidades() {
        return unidades;
    }

    public void setUnidades(Integer unidades) {
        this.unidades = unidades;
    }

}
