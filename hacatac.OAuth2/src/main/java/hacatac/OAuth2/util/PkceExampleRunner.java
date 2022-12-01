package hacatac.OAuth2.util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PkceExampleRunner {

    public static void main(String[] args) {

        try {

            PkceUtil pkce = new PkceUtil();

            String codeVerifier = pkce.generateCodeVerifier();
            System.out.println("Code verifier: " + codeVerifier);

            String codeChallenge = pkce.generateCodeChallange(codeVerifier);
            System.out.println("Code challenge: " + codeChallenge);

        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(PkceExampleRunner.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}