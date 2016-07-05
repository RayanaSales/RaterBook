package beans;

import entidades.Livro;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import servico.LivroServico;
import util.constantes.LivroCategoria;

/**
 *
 * @author rayana
 */
@ManagedBean
@ViewScoped
public class LivroBean
{
    private Livro livro;
    private List<Livro> livros = new ArrayList<>();

    @EJB
    private LivroServico servico;

    @PostConstruct
    public void inicializar()
    {
        inicializarEntidadeNegocio();
        popularEntidades();
    }

    public void popularEntidades()
    {
        livros = servico.listarTodos();
    }

    public void inicializarEntidadeNegocio()
    {
        livro = servico.getEntidadeNegocio();
    }

    public void cadastrar()
    {
        servico.salvar(livro);
        popularEntidades();
        inicializarEntidadeNegocio();
        adicionarMensagemComponente(null, "Salvou!", FacesMessage.SEVERITY_INFO);
    }

    public void alterarEntidadeCadastrada(RowEditEvent editEvent)
    {
        livro = (Livro) editEvent.getObject();
        alterar();
    }

    public void alterar()
    {
        servico.alterar(livro);
        popularEntidades();
        adicionarMensagemComponente(null, "Alterou!", FacesMessage.SEVERITY_INFO);
    }

    public void remover(Livro livro)
    {
        servico.remover(livro);
        popularEntidades();
        adicionarMensagemComponente(null, "Removeu!", FacesMessage.SEVERITY_INFO);
    }

    private void adicionarMensagemComponente(String componente, String mensagem,
            FacesMessage.Severity severity)
    {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(componente, new FacesMessage(severity, mensagem, null));
    }

    public Livro getLivro()
    {
        return livro;
    }

    public void setLivro(Livro livro)
    {
        this.livro = livro;
    }

    public List<Livro> getLivros()
    {
        if (livros.isEmpty())
        {
            popularEntidades();
        }
        return livros;
    }

    public void setLivros(List<Livro> livros)
    {
        this.livros = livros;
    }
    
    
    
    
    public LivroCategoria[] getCategorias()
    {
        return LivroCategoria.values();
    }

}
