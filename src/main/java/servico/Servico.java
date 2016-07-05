package servico;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Servico
{
    protected static EntityManagerFactory emf;
    protected EntityManager em;
    private EntityTransaction et;

    public Servico()
    {
        
    }

    public static void encerrarServico()
    {
        emf.close();
    }

    public void abrirTransacao()
    {
        emf = Persistence.createEntityManagerFactory("rater_book");
        //DbUnitUtil.inserirDados();
        
        em = emf.createEntityManager();
        et = em.getTransaction();
        et.begin();
    }

    public void fecharTransacao()
    {
        try
        {
            if (et != null && et.isActive())
            {
                et.commit();
            }
        } catch (Exception ex)
        {
            et.rollback();
        } finally
        {
            em.close();
        }
    }
}
