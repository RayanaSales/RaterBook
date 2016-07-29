package servico;

import entidades.livro.Livro;
import entidades.livro.exemplar.EstadoExemplar;
import entidades.livro.exemplar.Exemplar;
import exception.NegocioException;
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
public class LivroServico extends Servico<Livro> {

    @Override
    public Class<Livro> getClasseEntidade() {
        return Livro.class;
    }

    public Class<Exemplar> getClasseEntidadeExemplar() {
        return Exemplar.class;
    }

    @Override
    public Livro getEntidadeNegocio() {
        return new Livro();
    }
    
    public Exemplar criarExemplar() {
        return new Exemplar();
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
        jpql.append(" WHERE l.isbn = ?1 ");
        TypedQuery<Long> query = entityManager.createQuery(jpql.toString(),
                Long.class);
        query.setParameter(1, isbn);
        return query.getSingleResult();
    }

    public void novoExemplar(Livro livro) throws NegocioException {
        Long tombo = this.gerarTomboExemplar();
        livro.novoExemplar(tombo);
        super.alterar(livro);
    }
    
    public void removerExemplar(Exemplar exemplar) throws NegocioException {
        Livro livro = exemplar.getLivro();
        livro.removerExemplar(exemplar);
        super.alterar(livro);
    }

    public List<Exemplar> obterExemplares() {
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select e FROM Exemplar e ");
        TypedQuery<Exemplar> query = entityManager.createQuery(
                jpql.toString(), getClasseEntidadeExemplar());
        return query.getResultList();
    }

    public Long gerarTomboExemplar() {
        TypedQuery<Long> query = entityManager.createNamedQuery(
                Exemplar.ULTIMO_TOMBO_EXEMPLAR, Long.class);
        Long ultimoTombo = query.getSingleResult();
        if (ultimoTombo == null) {
            ultimoTombo = 0L;
        }
        ultimoTombo += 1;
        return ultimoTombo;
    }
    
    public void atualizarExemplar(Exemplar exemplar) {
        super.entityManager.merge(exemplar);
    }

    public List<Exemplar> obterExemplaresDisponiveis() {
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select e FROM Exemplar e ");
        jpql.append(" where e.estadoExemplar = ?1 ");
        TypedQuery<Exemplar> query = entityManager.createQuery(
                jpql.toString(), getClasseEntidadeExemplar());
        query.setParameter(1, EstadoExemplar.DISPONIVEL);
        return query.getResultList();
    }

}
