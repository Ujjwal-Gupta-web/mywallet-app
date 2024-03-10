package com.mywallet.backend.app.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class AuthUtility {
    private static final long EXPIRATION_TIME = 15L * 24 * 60 * 60 * 1000;
    private static final String SECRET="IUvLs56wm+6044w2SXIxGpKUOQoSZwwo6K2Gsb4T+YnGEshHcHBTj+RcTz3uUQdwWUuzAhjfWeWWKyfD5JJ7gg==";

    public static String generateToken(String username) {
        // Use jjwt library to generate a JWT token
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
    public static boolean isValidToken(String token) {
        try {
            // Use jjwt library to parse and verify the token
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getUsernameFromToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject();
        } catch (Exception e) {
            // Handle exception, such as token expiration, tampered token, etc.
            return null;
        }
    }

}



