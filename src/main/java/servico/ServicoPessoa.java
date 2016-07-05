package servico;

import entidades.Pessoa;
import java.util.List;
import javax.persistence.TypedQuery;

public class ServicoPessoa extends Servico
{
    public boolean salvar(Pessoa pessoa)
    {
        if (existente(pessoa.getUsername()) == false)
        {
            abrirTransacao();
            em.persist(pessoa);
            fecharTransacao();
            return true;
        }

        return false;
    }

    public Pessoa buscar(int id)
    {
        Pessoa pessoa = em.find(Pessoa.class, id);
        return pessoa;
    }

    private boolean existente(String username)
    {
        abrirTransacao();
        TypedQuery<Pessoa> query;
        query = em.createQuery("select b from Pessoa b where b.username = ?1", Pessoa.class);
        query.setParameter(1, username);
        List<Pessoa> pessoas = query.getResultList();
        fecharTransacao();

        if (pessoas.isEmpty())
        {
            return false;
        }
        return true;
    }
}
