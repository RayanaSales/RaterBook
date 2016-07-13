package exception;

import javax.ejb.ApplicationException;

/**
 *
 * @author Edmilson Santana
 */
@ApplicationException(rollback = true)
public class NegocioException extends Exception {

    private String chave;
    private static final long serialVersionUID = -4032758349094923282L;
    public static final String EMAIL_EXISTENTE = "execption.NegocioException.usuarioService.verificarExistenciaDeUsuario";
    public static final String ENTIDADE_ASSOCIADA = "execption.NegocioException.entidadeService.verificarAssociacaoEntidade";

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
