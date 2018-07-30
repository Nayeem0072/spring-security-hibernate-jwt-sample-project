package com.syn.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.stereotype.Service;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.syn.entities.AuthUser;
import static com.syn.security.JwtConstants.PLAIN_SECRET;
import static com.syn.security.JwtConstants.EXPIRE_HOURS;

@Service
public class JwtService{
    private final Long expireHours = EXPIRE_HOURS;
    
    private static final String plainSecret = PLAIN_SECRET;
    private static final String encodedSecret = Base64
									            .getEncoder()
									            .encodeToString(plainSecret.getBytes());
    
    private Date getExpirationTime(){
        Date now = new Date();
        Long expireInMilis = TimeUnit.HOURS.toMillis(expireHours);
        return new Date(expireInMilis + now.getTime());
    }

    public String getUser(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(encodedSecret)
                .parseClaimsJws(token)
                .getBody();
        
        String username = claims.getSubject();        
        System.out.println("username from token: " + username);
        
        return username;
    }    
        
    
    public String getToken(AuthUser user){
    	System.out.println("encodedSecret : " + encodedSecret);
        Date now = new Date();
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())        		
                .setSubject(user.getUsername())
                //.claim("role", jwtUser.getRole())
                .setIssuedAt(now)
                .setExpiration(getExpirationTime())
                .signWith(SignatureAlgorithm.HS512, encodedSecret)
                .compact();
    }
   
}