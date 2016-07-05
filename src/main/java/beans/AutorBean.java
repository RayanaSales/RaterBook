/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entidades.Autor;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import servico.AutorServico;

/**
 *
 * @author Edmilson Santana
 */
@ManagedBean
@ViewScoped
public class AutorBean {

    private Autor autor;

    private List<Autor> autores = new ArrayList<>();

    @EJB
    private AutorServico servico;

    @PostConstruct
    public void inicializar() {

        inicializarEntidadeNegocio();
        popularEntidades();
    }

    public void popularEntidades() {
        autores = servico.listarTodos();
    }

    public void inicializarEntidadeNegocio() {
        autor = servico.getEntidadeNegocio();
    }

    public void cadastrar() {
        servico.salvar(autor);
        popularEntidades();
        inicializarEntidadeNegocio();
        adicionarMensagemComponente(null, "Salvou!", FacesMessage.SEVERITY_INFO);
    }

    public void alterarEntidadeCadastrada(RowEditEvent editEvent) {
        autor = (Autor) editEvent.getObject();
        alterar();
    }

    public void alterar() {
        servico.alterar(autor);
        popularEntidades();
        adicionarMensagemComponente(null, "Alterou!", FacesMessage.SEVERITY_INFO);
    }

    public void remover(Autor autor) {
        servico.remover(autor);
        popularEntidades();
        adicionarMensagemComponente(null, "Removeu!", FacesMessage.SEVERITY_INFO);
    }

    public List<Autor> getAutores() {
        if ( autores.isEmpty() ) {
            popularEntidades();
        }
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }
    

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    
     private void adicionarMensagemComponente(String componente, String mensagem,
             FacesMessage.Severity severity) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(componente, new FacesMessage(severity, mensagem, null));
    }

}
