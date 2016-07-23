/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entidades.aluno.Aluno;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import servico.AlunoServico;
import servico.Servico;

@ManagedBean
@ViewScoped
public class AlunoBean extends Bean<Aluno>
{
    private static final long serialVersionUID = 5365800389243950009L;

    @EJB
    private AlunoServico alunoServico;

    @Override
    protected Servico inicializarServico()
    {
        return alunoServico;
    }

}
