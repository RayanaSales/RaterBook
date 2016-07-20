/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import com.mysql.jdbc.StringUtils;
import entidades.usuario.Usuario;
import util.Util;
import exception.NegocioException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Edmilson
 */
@Stateless
public class UsuarioServico extends Servico<Usuario> {

    @EJB
    private EmailServico emailServico;

    @Override
    public Class<Usuario> getClasseEntidade() {
        return Usuario.class;
    }

    @Override
    public Usuario getEntidadeNegocio() {
        return new Usuario();
    }

    @Override
    public void salvar(Usuario usuario) throws NegocioException {
        super.salvar(usuario);
        emailServico.enviarMensagem(usuario.getEmail());
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Usuario buscarUsuario(String email, String senha) throws NegocioException {
        Usuario usuario = this.buscarUsuario(email);
        String hash = null;
        if (!senha.isEmpty()) {
            hash = Util.gerarHash(senha);
        }
        if (!usuario.getSenha().equals(hash)) {
            throw new NegocioException(NegocioException.USUARIO_NAO_ENCONTRADO);
        }

        return usuario;
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Usuario buscarUsuario(String email) throws NegocioException {
        StringBuilder jpql = new StringBuilder();
        jpql.append("select u from ");
        jpql.append(getClasseEntidade().getSimpleName());
        jpql.append(" as u ");
        jpql.append(" where u.email = ?1 ");
        TypedQuery<Usuario> query = super.entityManager.createQuery(jpql.toString(),
                getClasseEntidade());
        query.setParameter(1, email);
        Usuario usuario = null;
        try {
            usuario = query.getSingleResult();
        } catch (NoResultException e) {
            throw new NegocioException(NegocioException.USUARIO_NAO_ENCONTRADO);
        }
        return usuario;
    }

    @Override
    public Boolean verificarExistencia(Usuario entidadeNegocio) {
        Boolean cadastrado = Boolean.TRUE;
        try {
            this.buscarUsuario(entidadeNegocio.getEmail());
        } catch (NegocioException e) {
            cadastrado = Boolean.FALSE;
        }
        return cadastrado;
    }

}
