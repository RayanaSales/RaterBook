package servico;

import entidades.livro.Livro;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.TypedQuery;

/**
 *
 * @author rayana
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class LivroServico extends Servico<Livro> {

    @Override
    public Class<Livro> getClasseEntidade() {
        return Livro.class;
    }

    @Override
    public Livro getEntidadeNegocio() {
        return new Livro();
    }

    @Override
    public Boolean verificarExistencia(Livro entidadeNegocio) {
        Long quantidade = this.contarLivrosPorIsbn(entidadeNegocio.getIsbn());
        Boolean cadastrado = Boolean.FALSE;
        if (quantidade > 0) {
            cadastrado = Boolean.TRUE;
        }
        return cadastrado;
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Long contarLivrosPorIsbn(String isbn) {
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select count(l) FROM Livro l ");
        jpql.append(" JOIN FETCH l.editora ");
        jpql.append(" WHERE l.isbn = ?1 ");
        TypedQuery<Long> query = entityManager.createQuery(jpql.toString(),
                Long.class);
        query.setParameter(1, isbn);
        return query.getSingleResult();
    }

}
