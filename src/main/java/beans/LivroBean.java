package beans;

import entidades.Autor;
import entidades.Editora;
import entidades.Livro;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;
import servico.AutorServico;
import servico.EditoraServico;
import servico.LivroServico;
import servico.Servico;
import util.constantes.CategoriaLivro;

/**
 *
 * @author rayana
 */
@ManagedBean
@ViewScoped
public class LivroBean extends Bean<Livro> {

    private static final long serialVersionUID = 6413956801026910848L;

    @EJB
    private LivroServico servico;

    @EJB
    private EditoraServico editoraServico;
    
    @EJB
    private AutorServico autorServico;
    
    private Editora editora;

    private DualListModel<Autor> autores = new DualListModel<>();

    @Override
    public void inicializar() {
        super.inicializar();
        editora = editoraServico.getEntidadeNegocio();
        autores.setSource(autorServico.listarTodos());
    }

    @Override
    public void cadastrar() {
        
        entidade.setAutores(autores.getTarget());
        super.cadastrar();
    }

    public CategoriaLivro[] getCategorias() {
        return CategoriaLivro.values();
    }
    
    public List<Editora> getEditoras() {
        return editoraServico.listarTodos();
    }

    @Override
    protected Servico inicializarServico() {
        return servico;
    }

    public DualListModel<Autor> getAutores() {
        return autores;
    }

    public void setAutores(DualListModel<Autor> autores) {
        this.autores = autores;
    }

}
