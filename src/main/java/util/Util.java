/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Edmilson Santana
 */
public class Util {

    public static String gerarHash(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(str.getBytes(Charset.forName("UTF-8")));
            return Base64.encodeBase64String(digest.digest());
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static Date obterDataAtual() {
        return Calendar.getInstance().getTime();
    }

}
