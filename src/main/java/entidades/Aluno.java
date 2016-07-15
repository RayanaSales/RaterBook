/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import javax.persistence.Entity;

/**
 *
 * @author Edmilson Santana
 */
@Entity
public class Aluno extends EntidadeNegocio {

    private static final long serialVersionUID = -6967794368017326187L;

    private String nome;
    
    private String matricula;
    
}
