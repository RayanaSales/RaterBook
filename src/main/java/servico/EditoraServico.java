/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import entidades.editora.Editora;
import entidades.EntidadeNegocio;
import javax.ejb.Stateless;

/**
 *
 * @author Edmilson
 */
@Stateless
public class EditoraServico extends Servico<Editora> {

    @Override
    public Boolean verificarExistencia(Editora entidadeNegocio) {
        return Boolean.TRUE;
    }

    @Override
    public Class<Editora> getClasseEntidade() {
        return Editora.class;
    }

    @Override
    public Editora getEntidadeNegocio() {
        return new Editora();
    }

}
