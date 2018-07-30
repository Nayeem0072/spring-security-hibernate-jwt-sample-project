package com.syn.helpers;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

public class JWTokenHandler {
	private static final Key secret = MacProvider.generateKey(SignatureAlgorithm.HS256);
    private static final byte[] secretBytes = secret.getEncoded();
    private static final String base64SecretBytes = Base64.getEncoder().encodeToString(secretBytes);
    
    public String generateToken(int userId) {        
    	String id = Integer.toString(userId);    	
        Date now = new Date();
        Date exp = new Date(System.currentTimeMillis() + (1000 * 30)); // 30 seconds

        String token = Jwts.builder()
            .setId(id)
            .setIssuedAt(now)
            .setSubject("logintoken")
            .setIssuer("ekpay")
            .setNotBefore(now)
            .setExpiration(exp)
            .signWith(SignatureAlgorithm.HS256, base64SecretBytes)
            .compact();

        return token;
    }
    
    public String verifyToken(String token) {
    	System.out.println("verifying token: ");
    	String verifyMessage = "";
    	try{
    		Claims claims = Jwts.parser()    	
				            .setSigningKey(base64SecretBytes)
				            .parseClaimsJws(token).getBody();
	        
    		verifyMessage += "Token Details - ID: " + claims.getId();
    		verifyMessage += ", Subject: " + claims.getSubject();
    		verifyMessage += ", Issuer: " + claims.getIssuer();
    		verifyMessage += ", Expiration: " + claims.getExpiration();
    	}
    	catch(Exception e){
    		verifyMessage = "token could not be verified";
    		e.printStackTrace();
    	}
        return verifyMessage;
    }
    
}