/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entidades.Usuario;
import exception.NegocioException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import servico.UsuarioServico;

/**
 *
 * @author Edmilson
 */
@ManagedBean
@RequestScoped
public class LoginBean implements Serializable {

    @Email
    private String email;
    @NotBlank
    private String senha;

    @EJB
    private UsuarioServico usuarioServico;

    public String efetuarLogin() {
        FacesContext context = FacesContext.getCurrentInstance();
        String fluxo = "sucesso";
        try {
            Usuario usuarioLogado = usuarioServico.buscarUsuario(email, senha);
            HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
            session.setAttribute("usuarioLogado", usuarioLogado);
        } catch (NegocioException e) {
            fluxo = "falha";
            adicionarMensagemComponente(null, e.getMessage(),
                    FacesMessage.SEVERITY_WARN);
        }

        return fluxo;
    }

    public String efetuarLogout() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
        return "sair";
    }

    private void adicionarMensagemComponente(String componente, String mensagem,
            FacesMessage.Severity severity) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(componente, new FacesMessage(severity, mensagem, null));
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
