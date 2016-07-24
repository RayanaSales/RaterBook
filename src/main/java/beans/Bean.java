package beans;

import entidades.EntidadeNegocio;
import exception.MensagemExcecao;
import exception.NegocioException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolationException;
import org.primefaces.event.RowEditEvent;
import servico.Servico;

/**
 *
 * @author rayana
 * @param <T>
 */
public abstract class Bean<T extends EntidadeNegocio> implements Serializable {

    private Servico<T> servico;

    protected List<T> entidades = new ArrayList<>();

    protected T entidade;

    public Bean() {
    }

    @PostConstruct
    public void inicializar() {
        servico = inicializarServico();
        inicializarEntidadeNegocio();
    }

    public void popularEntidades() {
        entidades = servico.listarTodos();
    }

    public void inicializarEntidadeNegocio() {
        entidade = servico.getEntidadeNegocio();
    }

    public void alterar() {
        try {
            servico.alterar(entidade);
            popularEntidades();
            mensagemAlteracaoSucesso();
        } catch (NegocioException ex) {
            adicionarMensagemView(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        } catch (EJBException ejbe) {
            if (ejbe.getCause() instanceof ConstraintViolationException) {
                MensagemExcecao mensagemExcecao = new MensagemExcecao(ejbe.getCause());
                adicionarMensagemView(mensagemExcecao.getMensagem(), FacesMessage.SEVERITY_WARN);
            } else {
                throw ejbe;
            }
        }
    }

    public void remover(T entidade) {
        try {
            servico.remover(entidade);
            popularEntidades();
            mensagemRemoverSucesso();
        } catch (NegocioException ex) {
            adicionarMensagemView(ex.getMessage(), FacesMessage.SEVERITY_WARN);
        }
    }

    public void editar(RowEditEvent editEvent) {
        entidade = (T) editEvent.getObject();
        alterar();
    }

    public void cadastrar() {
        try {
            servico.salvar(entidade);
            popularEntidades();
            inicializarEntidadeNegocio();
            mensagemCadastroSucesso();
        } catch (NegocioException ex) {
            adicionarMensagemView(ex.getMessage(), FacesMessage.SEVERITY_WARN);
        } catch (EJBException ejbe) {
            if (ejbe.getCause() instanceof ConstraintViolationException) {
                MensagemExcecao mensagemExcecao = new MensagemExcecao(ejbe.getCause());
                adicionarMensagemView(mensagemExcecao.getMensagem(), FacesMessage.SEVERITY_WARN);
            } else {
                throw ejbe;
            }
        }
    }

    public void mensagemAlteracaoSucesso() {
        this.adicionarMensagemView("Alteração realizada com sucesso!", FacesMessage.SEVERITY_INFO);
    }

    public void mensagemCadastroSucesso() {
        this.adicionarMensagemView("Cadastro realizado com sucesso!", FacesMessage.SEVERITY_INFO);
    }

    public void mensagemRemoverSucesso() {
        this.adicionarMensagemView("Cadastro removido com sucesso!", FacesMessage.SEVERITY_INFO);
    }

    public void adicionarMensagemView(String mensagem) {
        this.adicionarMensagemComponente(null, mensagem, null);
    }

    public void adicionarMensagemView(String mensagem, Severity severity) {
        this.adicionarMensagemComponente(null, mensagem, severity);
    }

    private void adicionarMensagemComponente(String componente, String mensagem, Severity severity) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(componente, new FacesMessage(severity, mensagem, null));
    }

    public List<T> getEntidades() {
        if (entidades.isEmpty()) {
            popularEntidades();
        }
        return entidades;
    }

    public void setEntidades(List<T> entidades) {
        this.entidades = entidades;
    }

    public T getEntidade() {
        return entidade;
    }

    public void setEntidade(T entidade) {
        this.entidade = entidade;
    }

    public void setAutor(T entidade) {
        this.entidade = entidade;
    }

    protected abstract Servico inicializarServico();

}
