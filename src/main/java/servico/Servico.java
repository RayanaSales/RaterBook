package servico;

import entidades.EntidadeNegocio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

public abstract class Servico<T extends EntidadeNegocio> {

    @PersistenceContext(unitName = "rater-book", type = PersistenceContextType.TRANSACTION)
    protected EntityManager em;

    public void salvar(T entidadeNegocio) {

        em.persist(entidadeNegocio);

    }

    public void alterar(T entidadeNegocio) {

        em.merge(entidadeNegocio);

    }

    public void remover(T entidadeNegocio) {

        entidadeNegocio = em.find(getClasseEntidade(), entidadeNegocio.getChavePrimaria());
        em.remove(entidadeNegocio);

    }

    public List<T> listarTodos() {
        StringBuilder jpql = new StringBuilder();
        jpql.append("select e ");
        jpql.append(" from ");
        jpql.append(getClasseEntidade().getSimpleName());
        jpql.append(" as e ");
        TypedQuery<T> query = em.createQuery(jpql.toString(), 
                getClasseEntidade());
        return query.getResultList();
    }

    public abstract Class<T> getClasseEntidade();

    public abstract T getEntidadeNegocio();
}
