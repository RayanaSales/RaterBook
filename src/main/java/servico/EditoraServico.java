/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import entidades.editora.Editora;
import exception.NegocioException;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Douglas Albuqerque
 */
@Stateless
public class EditoraServico extends Servico<Editora> {

    @Override
    public Boolean verificarExistencia(Editora entidadeNegocio) {
        StringBuilder jpqlConsulta = new StringBuilder();
        jpqlConsulta.append(" SELECT COUNT(editora.chavePrimaria) FROM ");
        jpqlConsulta.append(getClasseEntidade().getSimpleName());
        jpqlConsulta.append(" AS editora ");
        jpqlConsulta.append(" WHERE editora.nome = ?1");
        TypedQuery<Long> query = super.entityManager
                .createQuery(jpqlConsulta.toString(), Long.class);
        query.setParameter(1, entidadeNegocio.getNome());
        Long resultado = query.getSingleResult();
        return resultado > 0;
    }

    @Override
    public void remover(Editora entidadeNegocio) throws NegocioException {
        Boolean possuiLivros = this.verificarSeEditoraPossuiLivros(entidadeNegocio);
        if (possuiLivros) {
            throw new NegocioException(NegocioException.ENTIDADE_ASSOCIADA);
        } else {
            super.remover(entidadeNegocio);
        }

    }

    public Boolean verificarSeEditoraPossuiLivros(Editora editora) {
        StringBuilder jpqlConsulta = new StringBuilder();
        jpqlConsulta.append(" SELECT COUNT(editora) FROM ");
        jpqlConsulta.append(getClasseEntidade().getSimpleName());
        jpqlConsulta.append(" AS editora ");
        jpqlConsulta.append(" WHERE editora.nome = ?1 and editora.livros is empty");
        TypedQuery<Long> query = super.entityManager
                .createQuery(jpqlConsulta.toString(), Long.class);
        query.setParameter(1, editora.getNome());
        Long resultado = query.getSingleResult();
        return !(resultado == 1);
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
