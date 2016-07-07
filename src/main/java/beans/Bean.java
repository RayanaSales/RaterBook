/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entidades.Autor;
import entidades.EntidadeNegocio;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import servico.Servico;

/**
 *
 * @author rayana
 * @param <T>
 */
public abstract class Bean<T extends EntidadeNegocio>
{

    private Servico<T> servico;

    private List<T> entidades = new ArrayList<>();

    private T entidade;

    @PostConstruct
    public void inicializar()
    {
        servico = inicializarServico();
        inicializarEntidadeNegocio();
    }

    public void popularEntidades()
    {
        entidades = servico.listarTodos();
    }

    public void inicializarEntidadeNegocio()
    {
        entidade = servico.getEntidadeNegocio();
    }

    public void alterar()
    {
        servico.alterar(entidade);
        popularEntidades();
        adicionarMensagemComponente(null, "Alterou!", FacesMessage.SEVERITY_INFO);
    }

    public void remover(T entidade)
    {
        servico.remover(entidade);
        popularEntidades();
        adicionarMensagemComponente(null, "Removeu!", FacesMessage.SEVERITY_INFO);
    }

    public void editar(RowEditEvent editEvent)
    {
        entidade = (T) editEvent.getObject();
        alterar();
    }

    public void cadastrar()
    {
        servico.salvar(entidade);
        popularEntidades();
        inicializarEntidadeNegocio();
        adicionarMensagemComponente(null, "Salvou!", FacesMessage.SEVERITY_INFO);
    }

    private void adicionarMensagemComponente(String componente, String mensagem,
            FacesMessage.Severity severity)
    {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(componente, new FacesMessage(severity, mensagem, null));
    }

    public List<T> getEntidades()
    {
        if (entidades.isEmpty())
        {
            popularEntidades();
        }
        return entidades;
    }

    public void setEntidades(List<T> entidades)
    {
        this.entidades = entidades;
    }

    public T getEntidade()
    {
        return entidade;
    }

    public void setAutor(T entidade)
    {
        this.entidade = entidade;
    }

    protected abstract Servico inicializarServico();

}
