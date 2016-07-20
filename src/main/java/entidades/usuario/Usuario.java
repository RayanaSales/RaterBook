/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.usuario;

import entidades.EntidadeNegocio;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import util.Util;

/**
 *
 * @author Edmilson
 */
@Entity
@AttributeOverrides(
        {
            @AttributeOverride(name = "chavePrimaria", column = @Column(name = "USUARIO_ID"))
        })
public class Usuario extends EntidadeNegocio {

    private static final long serialVersionUID = -8462002161260522543L;

    @Email
    @Column(unique = true)
    private String email;
    @NotBlank
    private String senha;
    @NotBlank
    @Size(max = 45)
    private String primeiroNome;
    @NotBlank
    @Size(max = 45)
    private String ultimoNome;

    @PrePersist
    public void gerarHash() {
        setSenha(Util.gerarHash(senha));
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

  
}
