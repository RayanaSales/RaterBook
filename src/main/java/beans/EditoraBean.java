/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entidades.editora.Editora;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import servico.EditoraServico;
import servico.Servico;

/**
 *
 * @author Edmilson Santana
 */
@ManagedBean
@ViewScoped
public class EditoraBean extends Bean<Editora> {

    private static final long serialVersionUID = -2765574855367937965L;

    @EJB
    private EditoraServico editoraServico;
    
    @Override
    protected Servico inicializarServico() {
        return editoraServico;
    }
    
}
