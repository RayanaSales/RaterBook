/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import exception.MensagemExcecao;
import java.io.Serializable;
import java.util.Date;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Edmilson Santana
 */
@ManagedBean
@SessionScoped
public class UserSettings implements Serializable {

    private static final long serialVersionUID = 677831240483987806L;

    private String tema = "hot-sneaks";

    private Locale localizacao;

    @PostConstruct
    private void inicializar() {
        this.localizacao = FacesContext.getCurrentInstance()
                .getViewRoot().getLocale();
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String[] getTemas() {
        return new String[]{"afterdark", "afternoon", "afterwork", "aristo",
            "black-tie", "blitzer", "bluesky", "bootstrap", "casablanca",
            "cupertino", "cruze", "dark-hive", "delta", "dot-luv",
            "eggplant", "excite-bike", "flick", "glass-x", "home",
            "hot-sneaks", "humanity", "le-frog", "midnight", "mint-choc",
            "overcast", "pepper-grinder", "redmond", "rocket", "sam",
            "smoothness", "south-street", "start", "sunny", "swanky-purse",
            "trontastic", "ui-darkness", "ui-lightness", "vader"};
    }

    public String alterarIdioma(String linguagem, String pais) {

        if (!pais.isEmpty()) {
            alterarLocalidade(new Locale(linguagem, pais));
        } else {
            alterarLocalidade(new Locale(linguagem));
        }
        return null;
    }

    private void alterarLocalidade(Locale novaLocalidade) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getViewRoot().setLocale(this.localizacao);
        context.addMessage(null, new FacesMessage("Idioma Alterado com Sucesso"));
        this.localizacao = novaLocalidade;
        MensagemExcecao.getInstance().trocarArquivoProperty(novaLocalidade);
    }

    public Locale getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Locale localizacao) {
        this.localizacao = localizacao;
    }
    

}
