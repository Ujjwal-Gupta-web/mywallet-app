package com.mywallet.backend.app.utility;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;

public class JwtSecretKeyGenerator {

    public static void main(String[] args) {
        // Generate a secure key for HS512
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        // Convert the key to a Base64-encoded string for storage or usage
        String base64EncodedKey = Base64.getEncoder().encodeToString(key.getEncoded());

        System.out.println("Generated HS512 Secret Key (Base64 Encoded): " + base64EncodedKey);
    }
}
