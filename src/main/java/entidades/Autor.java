package entidades;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author douglas
 */
@Entity
@AttributeOverrides({
    @AttributeOverride(name = "chavePrimaria", column = @Column(name = "AUTOR_ID"))})
public class Autor extends EntidadeNegocio {

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
