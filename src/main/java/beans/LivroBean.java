package beans;

import entidades.autor.Autor;
import entidades.editora.Editora;
import entidades.livro.Livro;
import entidades.livro.exemplar.EstadoExemplar;
import entidades.livro.exemplar.Exemplar;
import exception.NegocioException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
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

    private List<Exemplar> exemplares = new ArrayList<>();

    @EJB
    private AutorServico autorServico;

    private List<Editora> editoras = new ArrayList<>();

    private DualListModel<Autor> autores = new DualListModel<>();

    @Override
    public void inicializar() {
        super.inicializar();
        autores.setSource(autorServico.listarTodos());
        editoras = editoraServico.listarTodos();
        exemplares = servico.obterExemplares();

    }

    @Override
    public void cadastrar() {

        entidade.setAutores(autores.getTarget());
        super.cadastrar();
    }

    public CategoriaLivro[] getCategorias() {
        return CategoriaLivro.values();
    }

    public EstadoExemplar[] getEstados() {
        return EstadoExemplar.values();
    }

    public List<Editora> getEditoras() {
        return editoras;
    }

    @Override
    protected Servico inicializarServico() {
        return servico;
    }

    public void criarNovoExemplar() throws NegocioException {
        servico.novoExemplar(entidade);
        String mensagem = "Um novo exemplar do livro "
                + entidade.getTitulo() + " foi criado";
        adicionarMensagemView(mensagem, FacesMessage.SEVERITY_INFO);

    }

    public DualListModel<Autor> getAutores() {
        return autores;
    }

    public boolean existemAutoresCadastrados() {
        return !autores.getSource().isEmpty();
    }

    public boolean existemEditorasCadastradas() {
        return !this.getEditoras().isEmpty();
    }

    public void setAutores(DualListModel<Autor> autores) {
        this.autores = autores;
    }

    public List<Exemplar> getExemplares() {
        return exemplares;
    }

    public void setExemplares(List<Exemplar> exemplares) {
        this.exemplares = exemplares;
    }

}
