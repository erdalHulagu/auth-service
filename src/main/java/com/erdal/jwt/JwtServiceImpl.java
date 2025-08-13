package com.erdal.jwt;

import com.erdal.exeptions.ErrorMessages;
import  com.erdal.jwt.JwtService;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;



@Service
public class JwtServiceImpl implements JwtService{
	
	private static final Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);

    @Value("${jwt.secret-key}")   
    private String secretKey;

    @Value("${jwt.expiration-time}")
    private long expirationTime;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(UserDetails userDetails) {
		return Jwts.builder().setSubject(userDetails.getUsername())
				             .setIssuedAt(new Date(System.currentTimeMillis()))
                             .setExpiration(new Date(System.currentTimeMillis()+86400000))
                             .signWith(getSigningKey(), SignatureAlgorithm.HS512).compact();
	}

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

	
//	 JWT token valide edecek
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
			 Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
			 return true;
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException |  
				 IllegalArgumentException |SecurityException e ) {
			logger.error(String.format(ErrorMessages.JWTTOKEN_ERROR_MESSAGE, e.getMessage()));
		}
		return false ;
		
		
	}

	
	}
