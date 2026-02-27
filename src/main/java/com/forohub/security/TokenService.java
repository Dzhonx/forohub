package com.forohub.security;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
@Service
public class TokenService{
 @Value("${api.security.token.secret}")
 private String secret;
 private Key getKey(){return Keys.hmacShaKeyFor(secret.getBytes());}
 public String generarToken(String login){
  return Jwts.builder().setSubject(login)
  .setExpiration(new Date(System.currentTimeMillis()+3600000))
  .signWith(getKey(),SignatureAlgorithm.HS256).compact();
 }
 public String getSubject(String token){
  return Jwts.parserBuilder().setSigningKey(getKey()).build()
  .parseClaimsJws(token).getBody().getSubject();
 }
}