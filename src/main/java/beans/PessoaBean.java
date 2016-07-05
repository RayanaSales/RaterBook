package beans;

import entidades.Pessoa;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import servico.ServicoPessoa;

@ManagedBean
@SessionScoped
public class PessoaBean implements Serializable
{
    Pessoa pessoa;
    ServicoPessoa pessoaServico;

    public PessoaBean()
    {
        pessoa = new Pessoa();
        pessoaServico = new ServicoPessoa();
    }

    public void salvar()
    {
        boolean salvou = pessoaServico.salvar(pessoa);
        
        if(salvou == true)
            adicionarMessagem(FacesMessage.SEVERITY_WARN, "Pessoa salva com sucesso");
        else if(salvou == false)
            adicionarMessagem(FacesMessage.SEVERITY_WARN, "Pessoa não foi salva");
    }

    protected void adicionarMessagem(FacesMessage.Severity severity, String mensagem)
    {
        FacesMessage message = new FacesMessage(severity, mensagem, "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public Pessoa getPessoa()
    {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa)
    {
        this.pessoa = pessoa;
    }
}
