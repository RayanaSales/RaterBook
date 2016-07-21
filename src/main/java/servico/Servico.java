package servico;

import entidades.EntidadeNegocio;
import exception.NegocioException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import org.jboss.logging.Logger;

public abstract class Servico<T extends EntidadeNegocio> {

    protected static final Logger LOG = Logger.getLogger(Servico.class);
    
    @PersistenceContext(unitName = "rater-book", type = PersistenceContextType.TRANSACTION)
    protected EntityManager entityManager;

    public void salvar(T entidadeNegocio) throws NegocioException {
        validarCadastro(entidadeNegocio);
        entityManager.persist(entidadeNegocio);
    }

    public void validarCadastro(T entidadeNegocio) throws NegocioException {

        try {
            Boolean existe = verificarExistencia(entidadeNegocio);
            if (existe) {
                throw new NegocioException(NegocioException.OBJETO_EXISTENTE);
            }
        } catch (NonUniqueResultException e) {
            throw new NegocioException(NegocioException.OBJETO_EXISTENTE);
        } catch (NoResultException e) {
            
        }
    }

    public abstract Boolean verificarExistencia(T entidadeNegocio);

    public void alterar(T entidadeNegocio) throws NegocioException {
        entityManager.merge(entidadeNegocio);
    }

    public void remover(T entidadeNegocio) throws NegocioException {
        if (entidadeNegocio.associado() == false) {
            entidadeNegocio = entityManager.find(getClasseEntidade(), entidadeNegocio.getChavePrimaria());
            entityManager.remove(entidadeNegocio);
        } else {
            throw new NegocioException(NegocioException.ENTIDADE_ASSOCIADA);
        }
    }

    public List<T> listarTodos() {
        StringBuilder jpql = new StringBuilder();
        jpql.append("select e ");
        jpql.append(" from ");
        jpql.append(getClasseEntidade().getSimpleName());
        jpql.append(" as e ");
        TypedQuery<T> query = entityManager.createQuery(jpql.toString(), getClasseEntidade());
        return query.getResultList();
    }

    public abstract Class<T> getClasseEntidade();

    public abstract T getEntidadeNegocio();
}
