/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Edmilson Santana
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class EmailServico {
    
    @Resource(name = "mail/Biblioteca")
    private Session sessao;
    
    @Asynchronous
    public void enviarMensagem(String destinatario) {
        enviar(destinatario);
    }
    
    private boolean enviar(String destinatario) {
        try {
            Message mensagem = new MimeMessage(sessao);
            mensagem.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destinatario));
            mensagem.setSubject("Cadastro realizado com sucesso");
            mensagem.setText("Cadastro realizado com sucesso. E-mail automático.");
            Transport.send(mensagem);
            return true;
        } catch (MessagingException ex) {
            Logger.getLogger(EmailServico.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }
}