package beans;

import entidades.Autor;
import entidades.Livro;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import servico.AutorServico;
import servico.LivroServico;
import servico.Servico;
import util.constantes.LivroCategoria;

/**
 *
 * @author rayana
 */
@ManagedBean
@ViewScoped
public class LivroBean extends Bean<Livro>
{

    @EJB
    private LivroServico servico;

    @EJB
    private AutorServico autorServico;

    public LivroCategoria[] getCategorias()
    {
        return LivroCategoria.values();
    }

    @Override
    protected Servico inicializarServico()
    {
        return servico;
    }

    public List<Autor> getAutores()
    {
        return autorServico.listarTodos();
    }

}
