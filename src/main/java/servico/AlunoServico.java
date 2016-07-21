package servico;

import entidades.aluno.Aluno;
import java.util.List;
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
public class AlunoServico extends Servico<Aluno>
{
    @Override
    public Class<Aluno> getClasseEntidade() {
        return Aluno.class;
    }

    @Override
    public Aluno getEntidadeNegocio() {
        return new Aluno();
    }

    @Override
    public Boolean verificarExistencia(Aluno entidadeNegocio) {
        TypedQuery<Aluno> query;
        query = entityManager.createQuery("select a from Aluno a where a.matricula like ?1", getClasseEntidade());
        query.setParameter(1, entidadeNegocio.getMatricula());
        List<Aluno> alunos = query.getResultList();

        if (alunos.isEmpty()) {
            return false;
        }

        return true;
    }
}
