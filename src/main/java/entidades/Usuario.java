/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
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
    private String nome;

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
