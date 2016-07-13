/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author rayana
 */
@ManagedBean
@SessionScoped
public class ConfiguracaoBean implements Serializable
{

    private String linguagem;
    private String pais;

    public String alterarIdioma()
    {
        if (!pais.isEmpty())
        {
            alterarLocalidade(new Locale(linguagem, pais));
        } else
        {
            alterarLocalidade(new Locale(linguagem));
        }
        return null;
    }

    private void alterarLocalidade(Locale locale)
    {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

    public String getLinguagem()
    {
        return linguagem;
    }

    public void setLinguagem(String linguagem)
    {
        this.linguagem = linguagem;
    }

    public String getPais()
    {
        return pais;
    }

    public void setPais(String pais)
    {
        this.pais = pais;
    }

}
