/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import entidades.Autor;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.TypedQuery;

/**
 *
 * @author Edmilson Santana
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AutorServico extends Servico<Autor> {

    @Override
    public Class<Autor> getClasseEntidade() {
        return Autor.class;
    }

    @Override
    public Autor getEntidadeNegocio() {
        return new Autor();
    }

    @Override
    public Boolean verificarExistencia(Autor entidadeNegocio) {
        TypedQuery<Autor> query;
        query = entityManager.createQuery("select a from Autor a where a.nome like ?1", getClasseEntidade());
        query.setParameter(1, entidadeNegocio.getNome());
        List<Autor> autores = query.getResultList();

        if (autores.isEmpty()) {
            return false;
        }

        return true;
    }
}
