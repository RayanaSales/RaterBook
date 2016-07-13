/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import entidades.EntidadeNegocio;
import entidades.Usuario;
import javax.persistence.TypedQuery;

/**
 *
 * @author Edmilson
 */
public class UsuarioServico extends Servico {

    @Override
    public Class getClasseEntidade() {
        return Usuario.class;
    }

    @Override
    public EntidadeNegocio getEntidadeNegocio() {
        return new Usuario();
    }

    public Usuario buscarUsuario(String email) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("select u from ");
        jpql.append(getClasseEntidade().getSimpleName());
        jpql.append(" where u.email = ?1 ");
        TypedQuery<Usuario> query = super.em.createQuery(jpql.toString(),
                getClasseEntidade());
        return query.getSingleResult();
    }

}
