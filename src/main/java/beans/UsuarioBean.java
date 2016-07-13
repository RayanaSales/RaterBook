/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entidades.Usuario;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import servico.Servico;
import servico.UsuarioServico;

/**
 *
 * @author Edmilson
 */
@ManagedBean
public class UsuarioBean extends Bean<Usuario> {

    @EJB
    private UsuarioServico usuarioServico;

    public String cadastrarAluno() {
        super.cadastrar();
        return "sucesso";
    }

    @Override
    protected Servico inicializarServico() {
        return usuarioServico;
    }

}
