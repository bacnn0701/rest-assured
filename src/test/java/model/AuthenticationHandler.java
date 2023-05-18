package model;

import org.apache.commons.codec.binary.Base64;

public class AuthenticationHandler {

    public static String encodeCreStr(String email, String token) {

        if (email == null || token == null) {
            throw new IllegalArgumentException("[Err] Email or token can't be null !!!");
        }

        String cred = email.concat(":").concat(token);
        byte[] credBase64 = Base64.encodeBase64(cred.getBytes());
        return new String(credBase64);
    }
}
