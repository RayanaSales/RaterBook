/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entidades.Autor;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import servico.AutorServico;
import servico.Servico;

/**
 *
 * @author Edmilson Santana
 */
@ManagedBean
@ViewScoped
public class AutorBean extends Bean<Autor> {

    private static final long serialVersionUID = -9045311142076127181L;

    @EJB
    private AutorServico autorServico;

    @Override
    protected Servico inicializarServico() {
        return autorServico;
    }

}
