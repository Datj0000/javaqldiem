/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

public class MD5 {

    public static String MD5(String txt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(txt.getBytes());
        byte[] digest = md.digest();
        String hashtext = DatatypeConverter.printHexBinary(digest).toLowerCase();
        return hashtext;
    }
}
