package exception;

import javax.ejb.ApplicationException;

/**
 *
 * @author Edmilson Santana
 */
@ApplicationException(rollback = true)
public class NegocioException extends Exception {

    private final String chave;
    private static final long serialVersionUID = -4032758349094923282L;
    public static final String USUARIO_NAO_ENCONTRADO = "excecao.NegocioException.loginService.buscarUsuario";
    public static final String OBJETO_EXISTENTE = "excecao.NegocioException.objetoExistente";
    public static final String EMAIL_EXISTENTE = "excecao.NegocioException.usuarioService.verificarExistenciaDeUsuario";
    public static final String ENTIDADE_ASSOCIADA = "excecao.NegocioException.entidadeService.verificarAssociacaoEntidade";
    public static final String EXEMPLAR_INDISPONIVEL = "excecao.NegocioException.exemplarIndisponivel";
    public static final String EXEMPLAR_DISPONIVEL = "excecao.NegocioException.exemplarDisponivel";

    public NegocioException(String chave) {
        this.chave = chave;
    }

    public String getChave() {
        return chave;
    }

    @Override
    public String getMessage() {
        MensagemExcecao mensagemExcecao = new MensagemExcecao(this);
        return mensagemExcecao.getMensagem();
    }

}
