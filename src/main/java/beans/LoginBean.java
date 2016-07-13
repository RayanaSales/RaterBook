/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entidades.Usuario;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
import servico.UsuarioServico;

/**
 *
 * @author Edmilson
 */
@ManagedBean
@RequestScoped
public class LoginBean {

    private String email;

    private String senha;

    @EJB
    private UsuarioServico usuarioServico;

    public String efetuarLogin() {

        try {
            Usuario usuario = usuarioServico.buscarUsuario(email);
            adicionarMensagemComponente(null, usuario.getEmail(),
                    FacesMessage.SEVERITY_WARN);
        } catch (NoResultException e) {
            System.out.println(e);
            adicionarMensagemComponente(null, "Usuário não encontrado",
                    FacesMessage.SEVERITY_WARN);
        }

        return "sucesso";
    }

    public String efetuarLogout() {
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
