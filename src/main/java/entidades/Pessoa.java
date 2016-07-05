package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

@Entity
@SequenceGenerator(name = "PESSOA_SEQUENCE", sequenceName = "PESSOA_SEQUENCE", allocationSize = 1, initialValue = 1)
public class Pessoa implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PESSOA_SEQUENCE")
    private Integer id;
    
    @Column(name = "txt_nome")
    @Size(min = 3, max = 100)
    @Pattern(regexp = "[A-Za-z ]+", message = "{entidades.Pessoa.nome}")
    private String nome;
    
    @NotNull
    @Column(name = "txt_username")
    @Size(min = 5, max = 40)
    @Email
    private String username;
    
    @NotNull
    @Column(name = "txt_senha")
    private String senha;

    public Pessoa()
    {
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }
    
    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }
}
