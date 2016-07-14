package servico;

import entidades.Livro;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
